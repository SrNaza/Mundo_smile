package com.example.tecomca.mylogin_seccion05.Fragments.AdsurdosFragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tecomca.mylogin_seccion05.Activities.MainActivity;
import com.example.tecomca.mylogin_seccion05.Fragments.Reconoce1.Reconoce1Fragment;
import com.example.tecomca.mylogin_seccion05.Model.Adsurdo;
import com.example.tecomca.mylogin_seccion05.Model.Characteristics;
import com.example.tecomca.mylogin_seccion05.Model.Stadistics;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;
import com.example.tecomca.mylogin_seccion05.Utils.Util;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;
import com.yarolegovich.lovelydialog.LovelySaveStateHandler;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class adsurdosFragment extends Fragment implements View.OnClickListener {
    private TextView textViewPregunta;
    private Button btn_resp1;
    private Button btn_resp2;
    List<Adsurdo> listaAbsurdos;
    int game;
    List<Characteristics> games;
    Characteristics actualGame;
    String[] answers;
    String trueAnswers;
    int turn = 0;
    private boolean answer;
    int true_answer;
    int correcto = 0; //1 2
    int incorrecto = 0;

    @BindView(R.id.imageViewAdsurdos) ImageView imagen;
    private SharedPreferences prefs;
    private DatabaseHelper databaseHelper;

    public adsurdosFragment() {
        // Required empty public constructor
    }

    public static adsurdosFragment newInstance(int game) {
        Bundle args = new Bundle();
        adsurdosFragment fragment = new adsurdosFragment();
        args.putInt("game", game);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = getArguments().getInt("game");
        games = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adsurdos, container, false);
        bindUI(view);
        ButterKnife.bind(this, view);
        initListeners();
        loadGame();
        changeQuestion();
        return view;
    }

    private void bindUI(View view){
        textViewPregunta = (TextView) view.findViewById(R.id.textViewPregunta);
        btn_resp1 = (Button) view.findViewById(R.id.btn_resp1);
        btn_resp2 = (Button) view.findViewById(R.id.btn_resp2);
        listaAbsurdos = new ArrayList<>();
    }

    private void loadGame(){
        prefs = getContext().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        games = databaseHelper.loadGame(game);
        Log.e("se", "-----> El juego = " + games);
        int random = (int) (Math.random() * games.size());
        actualGame = this.games.get(random);
        answers = games.get(random).getAnswer().split(",");
        trueAnswers = games.get(random).getTrue_answer();
        Glide.with(getContext())
                .load(games.get(random).getImage())
                .into(imagen);
    }

    private void initListeners(){
        btn_resp1.setOnClickListener(this);
        btn_resp2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int numero = Integer.parseInt(trueAnswers);
        switch (v.getId()){
            case R.id.btn_resp1:
                if(numero == 1){
                    Toast.makeText(getActivity(),"CORRECTO",Toast.LENGTH_SHORT).show();
                    correcto++;
                    if(turn == games.size()){
                        showDialog();
                    }else{
                        changeQuestion();
                    }
                } else{
                    Toast.makeText(getActivity(),"INCORRECTO",Toast.LENGTH_SHORT).show();
                    incorrecto++;
                    if(turn == games.size()){
                        showDialog();
                    }else{
                        changeQuestion();
                    }
                }
                break;
            case R.id.btn_resp2:
                if(numero == 0){
                    correcto++;
                    Toast.makeText(getActivity(),"CORRECTO",Toast.LENGTH_SHORT).show();
                    if(turn == games.size()){
                        showDialog();
                    }else{
                        changeQuestion();
                    }
                } else{
                    Toast.makeText(getActivity(),"INCORRECTO",Toast.LENGTH_SHORT).show();
                    incorrecto++;
                    if(turn == games.size()){
                        showDialog();
                    }else{
                        changeQuestion();
                    }
                }
                break;
        }
    }

    public void changeQuestion(){
        Glide.with(getContext())
                .load(games.get(turn).getImage())
                .apply(new RequestOptions().placeholder(R.drawable.carga).error(R.drawable.advertencia))
                .into(imagen);
        textViewPregunta.setText(games.get(turn).getAnswer());
        trueAnswers = games.get(turn).getTrue_answer();
        turn++;
    }

    public void resetStats(){
        correcto = 0;
        incorrecto = 0;
        turn=0;
    }

    public void showDialog(){
        new LovelyStandardDialog(getContext())
                .setTopColorRes(R.color.colorPrimary)
                .setButtonsColorRes(R.color.colorAccent)
                .setCancelable(false)
                .setIcon(R.drawable.ic_action_name)
                .setTitle("Estadisticas de la Partida")
                .setMessageGravity(50)
                .setMessage("Aciertos "+correcto+" - "+" Incorrectos "+incorrecto)
                .setMessageGravity(50)
                .setPositiveButton("Terminar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getFragmentManager().popBackStack();
                        Toast.makeText(getContext(),"HIZO CLICK",Toast.LENGTH_SHORT).show();
                        Stadistics stadistics = new Stadistics();
                        stadistics.setId_game(actualGame.getId_game());
                        stadistics.setNamePlayer(Util.getPlayerName(prefs));
                        stadistics.setBuenas(correcto);
                        stadistics.setMalas(incorrecto);
                        databaseHelper.saveStadistics(stadistics);
                        Log.e("se", "Se guardo en la base datos");
                    }
                })
                .setNegativeButton("Reiniciar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"Reinicio Juego",Toast.LENGTH_SHORT).show();
                        Stadistics stadistics = new Stadistics();
                        stadistics.setId_game(actualGame.getId_game());
                        stadistics.setNamePlayer(Util.getPlayerName(prefs));
                        stadistics.setBuenas(correcto);
                        stadistics.setMalas(incorrecto);
                        databaseHelper.saveStadistics(stadistics);
                        Log.e("se", "Se guardo en la base datos");
                        resetStats();
                        changeQuestion();
                    }
                })
                .show();
    }

}

package com.example.tecomca.mylogin_seccion05.Fragments.EmocionalFragments;


import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.tecomca.mylogin_seccion05.Model.Characteristics;
import com.example.tecomca.mylogin_seccion05.Model.Stadistics;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;
import com.example.tecomca.mylogin_seccion05.Utils.Util;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmocionalFragment extends Fragment {


    @BindView(R.id.imageViewEmocional) ImageView imageViewEmocional;
    @BindView(R.id.textViewPreguntaEmocional) TextView textViewPreguntaEmocional;
    @BindView(R.id.btn_resp1Emocional) Button btnResp1Emocional;
    @BindView(R.id.btn_resp2Emocional) Button btnResp2Emocional;
    Unbinder unbinder;

    //GAME
    int game;
    List<Characteristics> games;
    Characteristics actualGame;
    String[] answers;
    String trueAnswers;
    int turn = 0;
    int correcto = 0;
    int incorrecto = 0;

    //UTILS
    private SharedPreferences prefs;
    private DatabaseHelper databaseHelper;

    public EmocionalFragment() {
        // Required empty public constructor
    }

    public static EmocionalFragment newInstance(int game) {
        Bundle args = new Bundle();
        EmocionalFragment fragment = new EmocionalFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emocional, container, false);
        unbinder = ButterKnife.bind(this, view);
        loadGame();
        changeQuestion();
        return view;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_resp1Emocional, R.id.btn_resp2Emocional})
    public void onViewClicked(View view) {
        int numero = Integer.parseInt(trueAnswers);
        switch (view.getId()) {
            case R.id.btn_resp1Emocional:
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
            case R.id.btn_resp2Emocional:
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
                .into(imageViewEmocional);
    }

    public void changeQuestion(){
        Glide.with(getContext())
                .load(games.get(turn).getImage())
                .apply(new RequestOptions().placeholder(R.drawable.carga).error(R.drawable.advertencia))
                .into(imageViewEmocional);
        textViewPreguntaEmocional.setText(games.get(turn).getAnswer());
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
                        Stadistics stadistics = new Stadistics();
                        stadistics.setId_game(actualGame.getId_game());
                        stadistics.setNamePlayer(Util.getPlayerName(prefs));
                        stadistics.setNameGame(Util.getNameGame(prefs));
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
                        stadistics.setNameGame(Util.getNameGame(prefs));
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



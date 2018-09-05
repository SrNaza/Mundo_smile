package com.example.tecomca.mylogin_seccion05.Fragments.AdsurdosFragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tecomca.mylogin_seccion05.Fragments.Reconoce1.Reconoce1Fragment;
import com.example.tecomca.mylogin_seccion05.Model.Adsurdo;
import com.example.tecomca.mylogin_seccion05.Model.Characteristics;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class adsurdosFragment extends Fragment implements View.OnClickListener {

    private TextView textViewPregunta;
    private Button btn_resp1;
    private Button btn_resp2;
    List<Adsurdo> listaAbsurdos;
    int turn = 0;
    private boolean answer;
    int score = 0;

    @BindView(R.id.imageViewAdsurdos) ImageView imagen;

    int game;

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
        databaseHelper = new DatabaseHelper(getContext());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adsurdos, container, false);
        bindUI(view);
        ButterKnife.bind(this, view);
        initListeners();
        changeQuestion();
        return view;
    }



    private void bindUI(View view){
        //bindUI
        textViewPregunta = (TextView) view.findViewById(R.id.textViewPregunta);
        btn_resp1 = (Button) view.findViewById(R.id.btn_resp1);
        btn_resp2 = (Button) view.findViewById(R.id.btn_resp2);
        listaAbsurdos = new ArrayList<>();
        generateFakeList();
    }

    private void generateFakeList() {
        for(int i=0;i< prueba.answers.length;i++){
            listaAbsurdos.add(new Adsurdo(prueba.questions[i],prueba.answers[i],prueba.images[i]));
        }
    }


    private void initListeners(){
        btn_resp1.setOnClickListener(this);
        btn_resp2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_resp1:
                if(answer == true){
                    Toast.makeText(getActivity(),"CORRECTO,",Toast.LENGTH_SHORT).show();
                    if(turn == prueba.answers.length){
                        Toast.makeText(getActivity(),"TERMINO EL JUEGO",Toast.LENGTH_SHORT).show();
                    }else{
                        changeQuestion();
                    }

                } else{

                    Toast.makeText(getActivity(),"CORRECTO,",Toast.LENGTH_SHORT).show();
                    if(turn == prueba.answers.length){
                        Toast.makeText(getActivity(),"TERMINO EL JUEGO",Toast.LENGTH_SHORT).show();
                    }else{
                        changeQuestion();
                    }

                }

                break;
            case R.id.btn_resp2:
                if(answer == false){
                    Toast.makeText(getActivity(),"CORRECTO,",Toast.LENGTH_SHORT).show();
                    if(turn == prueba.answers.length){
                        Toast.makeText(getActivity(),"TERMINO EL JUEGO",Toast.LENGTH_SHORT).show();
                    }else{
                        changeQuestion();
                    }

                } else{

                    Toast.makeText(getActivity(),"INCORRECTO,",Toast.LENGTH_SHORT).show();
                    if(turn == prueba.answers.length){
                        Toast.makeText(getActivity(),"TERMINO EL JUEGO",Toast.LENGTH_SHORT).show();
                    }else{
                        changeQuestion();
                    }

                }
                break;

        }

    }

    public void changeQuestion(){
        Glide.with(getContext())
                .load(listaAbsurdos.get(turn).getImage())
                .apply(new RequestOptions().placeholder(R.drawable.doctor).error(R.drawable.kids))
                .into(imagen);
        textViewPregunta.setText(listaAbsurdos.get(turn).getQuestion());
        answer = listaAbsurdos.get(turn).isAnswer();
        turn++;
    }
}

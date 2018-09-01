package com.example.tecomca.mylogin_seccion05.Fragments.Reconoce1;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tecomca.mylogin_seccion05.Model.Characteristics;
import com.example.tecomca.mylogin_seccion05.Model.Games;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Reconoce1Fragment extends Fragment implements View.OnClickListener {

    Button btn_opc1, btn_opc2, btn_opc3, btn_opc4, btn_opc5, btn_opc6;
    ImageView imgView;
    int game;
    List<Characteristics> games;
    String[] answers;
    String[] trueAnswers;

    private DatabaseHelper databaseHelper;

    public Reconoce1Fragment() {
        // Required empty public constructor
    }

    public static Reconoce1Fragment newInstance(int game) {
        Bundle args = new Bundle();
        Reconoce1Fragment fragment = new Reconoce1Fragment();
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
        View view = inflater.inflate(R.layout.fragment_reconoce1, container, false);
        initViews(view);
        initListeners();
        loadGame();
        return view;
    }

    public void loadGame() {
        games = databaseHelper.loadGame(game);
        answers = games.get(0).getAnswer().split(",");
        trueAnswers = games.get(0).getTrue_answer().split(",");
        btn_opc1.setText(answers[0]);
        btn_opc2.setText(answers[2]);
        btn_opc3.setText(answers[1]);
        btn_opc4.setText(answers[4]);
        btn_opc5.setText(answers[3]);
        btn_opc6.setText(answers[5]);
        Glide.with(getContext())
                .load(games.get(0).getImage())
                .into(imgView);
    }

    private void initViews(View view) {
        btn_opc1 = view.findViewById(R.id.btn_ans_1);
        btn_opc2 = view.findViewById(R.id.btn_ans_2);
        btn_opc3 = view.findViewById(R.id.btn_ans_3);
        btn_opc4 = view.findViewById(R.id.btn_ans_4);
        btn_opc5 = view.findViewById(R.id.btn_ans_5);
        btn_opc6 = view.findViewById(R.id.btn_ans_6);
        imgView = view.findViewById(R.id.iv_image);
    }

    private void initListeners() {
        btn_opc1.setOnClickListener(this);
        btn_opc2.setOnClickListener(this);
        btn_opc3.setOnClickListener(this);
        btn_opc4.setOnClickListener(this);
        btn_opc5.setOnClickListener(this);
        btn_opc6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ans_1:
                for (int i = 0; i < trueAnswers.length; i++){
                    if (trueAnswers[i].equals(btn_opc1.getText().toString())) {
                        btn_opc1.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    } else {
                        btn_opc1.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                }
                break;
            case R.id.btn_ans_2:
                for (int i = 0; i < trueAnswers.length; i++){
                    if (trueAnswers[i].equals(btn_opc2.getText().toString())) {
                        btn_opc2.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    } else {
                        btn_opc2.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                }
                break;
            case R.id.btn_ans_3:
                for (int i = 0; i < trueAnswers.length; i++){
                    if (trueAnswers[i].equals(btn_opc3.getText().toString())) {
                        btn_opc3.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    } else {
                        btn_opc3.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                }
                break;
            case R.id.btn_ans_4:
                for (int i = 0; i < trueAnswers.length; i++){
                    if (trueAnswers[i].equals(btn_opc4.getText().toString())) {
                        btn_opc4.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    } else {
                        btn_opc4.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                }
                break;
            case R.id.btn_ans_5:
                for (int i = 0; i < trueAnswers.length; i++){
                    if (trueAnswers[i].equals(btn_opc5.getText().toString())) {
                        btn_opc5.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    } else {
                        btn_opc5.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                }
                break;
            case R.id.btn_ans_6:
                for (int i = 0; i < trueAnswers.length; i++){
                    if (trueAnswers[i].equals(btn_opc6.getText().toString())) {
                        btn_opc6.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    } else {
                        btn_opc6.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                }
                break;
        }
    }

}
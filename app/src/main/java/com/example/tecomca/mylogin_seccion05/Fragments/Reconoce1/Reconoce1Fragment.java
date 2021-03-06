package com.example.tecomca.mylogin_seccion05.Fragments.Reconoce1;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tecomca.mylogin_seccion05.Model.Characteristics;
import com.example.tecomca.mylogin_seccion05.Model.Games;
import com.example.tecomca.mylogin_seccion05.Model.Stadistics;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;
import com.example.tecomca.mylogin_seccion05.Utils.Util;

import java.util.ArrayList;
import java.util.List;

public class Reconoce1Fragment extends Fragment implements View.OnClickListener {

    Button btn_opc1, btn_opc2, btn_opc3, btn_opc4, btn_opc5, btn_opc6, btn_back, btn_newGame;
    ImageView imgView;
    int game;
    List<Characteristics> games;
    Characteristics actualGame;
    Characteristics actualImage;
    String[] answers;
    String[] trueAnswers;
    int buenas;
    int malas;

    TextView tv_tries;
    int tries;
    private SharedPreferences prefs;

    private DatabaseHelper databaseHelper;

    public Reconoce1Fragment() {
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
//        buenas.clear();
//        malas.clear();
        buenas = 0;
        malas= 0;
        prefs = getContext().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        this.tv_tries.setText("3");
        this.tries = 3;
        games = databaseHelper.loadGame(game);
        int random = (int) (Math.random() * games.size());
        actualGame = this.games.get(random);
        actualImage = this.games.get(random);
        answers = games.get(random).getAnswer().split(",");
        trueAnswers = games.get(random).getTrue_answer().split(",");
        btn_opc1.setText(answers[0]);
        btn_opc2.setText(answers[1]);
        btn_opc3.setText(answers[2]);
        btn_opc4.setText(answers[3]);
        btn_opc5.setText(answers[4]);
        btn_opc6.setText(answers[5]);
        btn_opc1.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_games));
        btn_opc2.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_games));
        btn_opc3.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_games));
        btn_opc4.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_games));
        btn_opc5.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_games));
        btn_opc6.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_games));
        Glide.with(getContext())
                .load(games.get(random).getImage())
                .into(imgView);
    }

    private void initViews(View view) {
        tv_tries = view.findViewById(R.id.tv_tries);
        btn_opc1 = view.findViewById(R.id.btn_ans_1);
        btn_opc2 = view.findViewById(R.id.btn_ans_2);
        btn_opc3 = view.findViewById(R.id.btn_ans_3);
        btn_opc4 = view.findViewById(R.id.btn_ans_4);
        btn_opc5 = view.findViewById(R.id.btn_ans_5);
        btn_opc6 = view.findViewById(R.id.btn_ans_6);
        imgView = view.findViewById(R.id.iv_image);
        btn_back = view.findViewById(R.id.btn_back);
        btn_newGame = view.findViewById(R.id.btn_newGame);
    }

    private void initListeners() {
        btn_opc1.setOnClickListener(this);
        btn_opc2.setOnClickListener(this);
        btn_opc3.setOnClickListener(this);
        btn_opc4.setOnClickListener(this);
        btn_opc5.setOnClickListener(this);
        btn_opc6.setOnClickListener(this);
        btn_newGame.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_newGame: {
                loadGame();
                this.tries++;
                break;
            }
            case R.id.btn_back: {
                getActivity().onBackPressed();
                break;
            }
        }
        if (this.tries > 0) {
            this.tries--;
            this.tv_tries.setText(String.valueOf(this.tries));
            switch (v.getId()) {
                case R.id.btn_ans_1:
                    for (int i = 0; i < trueAnswers.length; i++) {
                        if (trueAnswers[i].equals(btn_opc1.getText().toString())) {
                            btn_opc1.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_true));
                            buenas ++;
                            Log.e("se", "Se guardo en la base datos buenas: " + buenas);
                            break;
                        } else {
                            btn_opc1.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_false));
                            if (i == 2) {
                                malas ++;
                                Log.e("se", "Se guardo en la base datos malas: " + malas);
                                break;
                            }
                        }
                    }
                    break;
                case R.id.btn_ans_2:
                    for (int i = 0; i < trueAnswers.length; i++) {
                        if (trueAnswers[i].equals(btn_opc2.getText().toString())) {
                            btn_opc2.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_true));
                            buenas ++;
                            Log.e("se", "Se guardo en la base datos buenas: " + buenas);
                            break;
                        } else {
                            btn_opc2.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_false));
                            if (i == 2) {
                                malas ++;
                                Log.e("se", "Se guardo en la base datos malas: " + malas);
                                break;
                            }
                        }
                    }
                    break;
                case R.id.btn_ans_3:
                    for (int i = 0; i < trueAnswers.length; i++) {
                        if (trueAnswers[i].equals(btn_opc3.getText().toString())) {
                            btn_opc3.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_true));
                            buenas ++;
                            Log.e("se", "Se guardo en la base datos buenas: " + buenas);
                            break;
                        } else {
                            btn_opc3.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_false));
                            if (i == 2) {
                                malas ++;
                                Log.e("se", "Se guardo en la base datos malas: " + malas);
                                break;
                            }
                        }
                    }
                    break;
                case R.id.btn_ans_4:
                    for (int i = 0; i < trueAnswers.length; i++) {
                        if (trueAnswers[i].equals(btn_opc4.getText().toString())) {
                            btn_opc4.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_true));
                            buenas ++;
                            Log.e("se", "Se guardo en la base datos buenas: " + buenas);
                            break;
                        } else {
                            btn_opc4.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_false));
                            if (i == 2) {
                                malas ++;
                                Log.e("se", "Se guardo en la base datos malas: " + malas);
                                break;
                            }
                        }
                    }
                    break;
                case R.id.btn_ans_5:
                    for (int i = 0; i < trueAnswers.length; i++) {
                        if (trueAnswers[i].equals(btn_opc5.getText().toString())) {
                            btn_opc5.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_true));
                            buenas ++;
                            Log.e("se", "Se guardo en la base datos buenas: " + buenas);
                            break;
                        } else {
                            btn_opc5.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_false));
                            if (i == 2) {
                                malas ++;
                                Log.e("se", "Se guardo en la base datos malas: " + malas);
                                break;
                            }
                        }
                    }
                    break;
                case R.id.btn_ans_6:
                    for (int i = 0; i < trueAnswers.length; i++) {
                        if (trueAnswers[i].equals(btn_opc6.getText().toString())) {
                            btn_opc6.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_true));
                            buenas ++;
                            Log.e("se", "Se guardo en la base datos buenas: " + buenas);
                            break;
                        } else {
                            if (i == 2) {
                                btn_opc6.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_game_answer_false));
                                malas ++;
                                Log.e("se", "Se guardo en la base datos malas: " + malas);
                                break;
                            }
                        }
                    }
                    break;
            }
            if (tries == 0) {
                Stadistics stadistics = new Stadistics();
                stadistics.setId_game(actualGame.getId_game());
                stadistics.setImage(actualImage.getImage());
                stadistics.setNamePlayer(Util.getPlayerName(prefs));
                stadistics.setNameGame(Util.getNameGame(prefs));
                stadistics.setBuenas(buenas);
                stadistics.setMalas(malas);
                databaseHelper.saveStadistics(stadistics);
                Log.e("se", "Se guardo en la base datos");
            }
        } else {
            if (getView() != null)
                Toast.makeText(getContext(), "se te acabo los intentos", Toast.LENGTH_SHORT).show();
        }
    }

}
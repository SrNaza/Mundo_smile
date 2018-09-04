package com.example.tecomca.mylogin_seccion05.Fragments.AdsurdosFragment;


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

import com.example.tecomca.mylogin_seccion05.Fragments.Reconoce1.Reconoce1Fragment;
import com.example.tecomca.mylogin_seccion05.Model.Adsurdo;
import com.example.tecomca.mylogin_seccion05.Model.Characteristics;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class adsurdosFragment extends Fragment implements View.OnClickListener {

    private TextView textViewPregunta;
    private Button btn_resp1;
    private Button btn_resp2;
    private ImageView imageViewAdsurdos;
    List<Adsurdo> listaAbsurdos;

    int game;

    private DatabaseHelper databaseHelper;

    public adsurdosFragment() {
        // Required empty public constructor
    }

    int turn = 1;

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
        listaAbsurdos = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getContext());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adsurdos, container, false);
        bindUI(view);
        initListeners();
        changeQuestion(turn);
        return view;
    }

    private void bindUI(View view){
        //bindUI
        textViewPregunta = (TextView) view.findViewById(R.id.textViewPregunta);
        btn_resp1 = (Button) view.findViewById(R.id.btn_resp1);
        btn_resp2 = (Button) view.findViewById(R.id.btn_resp2);
        imageViewAdsurdos = (ImageView) view.findViewById(R.id.imageViewAdsurdos);
        listaAbsurdos = new ArrayList<>();

        //add listas
        for(int i= 0; i < new prueba().answer.length; i++){
            listaAbsurdos.add(new Adsurdo(new prueba().question[i],new prueba().answer[i],new prueba().image[i]));

        }
        //
        Collections.shuffle(listaAbsurdos);

    }

    private void initListeners(){
        btn_resp1.setOnClickListener(this);
        btn_resp2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_resp1:
                Toast.makeText(getActivity(),"Estoy en el boton 1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_resp2:
                Toast.makeText(getActivity(),"Estoy en el boton 2",Toast.LENGTH_SHORT).show();
                break;

        }

    }

    public void changeQuestion(int number){
        imageViewAdsurdos.setImageResource(listaAbsurdos.get(number-1).getImage());
        textViewPregunta.setText(listaAbsurdos.get(number).getQuestion());
    }
}

package com.example.tecomca.mylogin_seccion05.Fragments.Juegos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tecomca.mylogin_seccion05.Fragments.AdsurdosFragment.adsurdosFragment;
import com.example.tecomca.mylogin_seccion05.Fragments.DiasSemana.DiasSemanaFragment;
import com.example.tecomca.mylogin_seccion05.Fragments.MesesYearFragment.MesesYearFragment;
import com.example.tecomca.mylogin_seccion05.Fragments.Reconoce1.Reconoce1Fragment;
import com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment.CategoriesAdapter;
import com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment.CatergorisFragment;
import com.example.tecomca.mylogin_seccion05.Model.Category;
import com.example.tecomca.mylogin_seccion05.Model.Games;
import com.example.tecomca.mylogin_seccion05.Model.Juegos;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;
import com.example.tecomca.mylogin_seccion05.Utils.Util;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class ListaJuegosFragment extends Fragment implements ListaJuegosAdapter.OnItemClickListener {

    private ComunViews comunViews;

    private RecyclerView recyclerJuegos;
    private DatabaseHelper databaseHelper;
    private ListaJuegosAdapter adapter;
    private SharedPreferences prefs;

//    private List<String> nombres;
//    private List<Integer> images;
//    private List<String> listNombres;
    private List<Games> juegos;
//    private List<Integer> listFotos;

    private int category;
//    private final String TAG = CatergorisFragment.class.getSimpleName();

    public ListaJuegosFragment() {
        // Required empty public constructor
    }

    public static ListaJuegosFragment newInstance(ComunViews cv, int category) {
        Bundle args = new Bundle();
        args.putParcelable("comun", cv);
        args.putInt("category", category);
        ListaJuegosFragment fragment = new ListaJuegosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comunViews = getArguments().getParcelable("comun");
        category = getArguments().getInt("category");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_juegos, container, false);
        prefs = getContext().getSharedPreferences("Preferences", MODE_PRIVATE);
        recyclerJuegos = view.findViewById(R.id.listJuegos);
        RecyclerViewUpdate();
        return view;
    }

    public void RecyclerViewUpdate() {
        databaseHelper = new DatabaseHelper(getContext());
        recyclerJuegos.setLayoutManager(new GridLayoutManager(getContext(), 2));
        juegos = new ArrayList<>();
        adapter = new ListaJuegosAdapter(databaseHelper.loadGamesCategory(category), getContext());
        adapter.setOnItemClickListener(this);
        recyclerJuegos.setAdapter(adapter);
    }

    @Override
    public void onClickSelectedItem(Games juegos) {
        switch (juegos.getId_game()) {
            case 1:
                comunViews.changeFragment(Reconoce1Fragment.newInstance(juegos.getId_game()));
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Que Reconoces en la Imagen?");
                Util.setNameGame(prefs, juegos.getName());
//                Util.setImageGame(prefs, juegos.getImage().toString());
                break;
            case 2:
                Toast.makeText(getActivity(), "Juego en Construccion", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Log.e("onClick", "Numero de juego :" + juegos.getId_game());
                comunViews.changeFragment(DiasSemanaFragment.newInstance(juegos.getId_game()));
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Cuales Dias de la Semana Faltan?");
                Util.setNameGame(prefs, juegos.getName());
                break;
            case 4:
                comunViews.changeFragment(MesesYearFragment.newInstance(juegos.getId_game()));
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Cuales Meses del Año Faltan?");
                Util.setNameGame(prefs, juegos.getName());
                break;
            case 5:
                comunViews.changeFragment(adsurdosFragment.newInstance(juegos.getId_game()));
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(juegos.getName());
                Util.setNameGame(prefs, juegos.getName());
                break;
            case 6:
                Toast.makeText(getActivity(), "Juego en Construccion", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(getActivity(), "Juego en Construccion", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}

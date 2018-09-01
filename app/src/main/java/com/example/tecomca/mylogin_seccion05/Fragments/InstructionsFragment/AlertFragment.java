package com.example.tecomca.mylogin_seccion05.Fragments.InstructionsFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tecomca.mylogin_seccion05.Fragments.Juegos.ListaJuegosFragment;
import com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment.CategoriesAdapter;
import com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment.CatergorisFragment;
import com.example.tecomca.mylogin_seccion05.Model.Category;
import com.example.tecomca.mylogin_seccion05.Model.Instructions;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;

import java.util.ArrayList;
import java.util.List;

public class AlertFragment extends Fragment implements AlertAdapter.OnItemClickListener {

    private ComunViews comunViews;

    private RecyclerView recyclerInstrutions;
    private DatabaseHelper databaseHelper;
    private AlertAdapter adapter;


    private List<String> nombres;
    private List<Integer> images;
    private List<String> listNombres;
    private List<Instructions> instructions;



    private final String TAG = AlertFragment.class.getSimpleName();


    public AlertFragment() {
        // Required empty public constructor
    }

    public static AlertFragment newInstance(ComunViews cv) {
        Bundle args = new Bundle();
        args.putParcelable("comun", cv);
        AlertFragment fragment = new AlertFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comunViews = (ComunViews) getArguments().getParcelable("comun");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alert, container, false);
        recyclerInstrutions = view.findViewById(R.id.listInstrucciones);
        // /initAll(view);
        databaseHelper = new DatabaseHelper(getContext());
        RecyclerViewUpdate();
        return view;
    }

    public void RecyclerViewUpdate() {
        recyclerInstrutions.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        if (adapter == null) {
        //Log.i(TAG, "--->listadoAdapter null");
        instructions = new ArrayList<>();
        adapter = new AlertAdapter(this.databaseHelper.getInstruction(), getContext());
        adapter.setOnItemClickListener(this);
        recyclerInstrutions.setAdapter(adapter);
    }

    @Override
    public void onClickSelectedItem(Instructions instructions) {
        Log.i(TAG, "--->Category name: " + instructions.getDescription());
        comunViews.changeFragment(ListaJuegosFragment.newInstance(comunViews, instructions.getId()));
    }



}

package com.example.tecomca.mylogin_seccion05.Fragments.EstadisticoFragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tecomca.mylogin_seccion05.Model.Stadistics;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class InforFragment extends Fragment implements InforAdapter.OnItemClickListener {


    private ComunViews comunViews;

    private RecyclerView recyclerStadistics;
    private DatabaseHelper databaseHelper;
    private InforAdapter adapter;
    private List<Stadistics> stadistics;

    private SharedPreferences prefs;
    private TextView textView;

    private final String TAG = InforFragment.class.getSimpleName();

    public InforFragment() {
        // Required empty public constructor
    }

    public static InforFragment newInstance(ComunViews cv) {
        Bundle args = new Bundle();
        args.putParcelable("comun", cv);
        InforFragment fragment = new InforFragment();
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
        View view = inflater.inflate(R.layout.fragment_infor, container, false);
        //prefs = getContext().getSharedPreferences("Preferences", MODE_PRIVATE);
//        textView = (TextView) view.findViewById(R.id.textViewSql);
//        textView.setText("Welcome ".concat(Util.getSessionName(prefs)));
        recyclerStadistics = view.findViewById(R.id.listStadistics);
        // /initAll(view);
        databaseHelper = new DatabaseHelper(getContext());
        RecyclerViewUpdate();
        return view;
    }

    public void RecyclerViewUpdate() {
        recyclerStadistics.setLayoutManager(new LinearLayoutManager(getContext()));
        stadistics = new ArrayList<>();
        adapter = new InforAdapter(this.databaseHelper.getInstruction(), getContext());
        adapter.setOnItemClickListener(this);
        recyclerStadistics.setAdapter(adapter);
    }

    @Override
    public void onClickSelectedItem(Stadistics stadistics) {
        Log.i(TAG, "--->Category name: " + stadistics.getNamePlayer());
        // comunViews.changeFragment(ListaJuegosFragment.newInstance(comunViews, instructions.getId()));
    }
}

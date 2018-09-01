package com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment;

import android.icu.util.ULocale;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tecomca.mylogin_seccion05.Activities.LoginActivity;
import com.example.tecomca.mylogin_seccion05.Fragments.InforFragment;
import com.example.tecomca.mylogin_seccion05.Fragments.Juegos.ListaJuegosFragment;
import com.example.tecomca.mylogin_seccion05.Fragments.Reconoce1.Reconoce1Fragment;
import com.example.tecomca.mylogin_seccion05.Model.Category;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CatergorisFragment extends Fragment implements CategoriesAdapter.OnItemClickListener {

    private ComunViews comunViews;

    private RecyclerView recyclerCategories;
    private DatabaseHelper databaseHelper;
    private CategoriesAdapter adapter;


    private List<String> nombres;
    private List<Integer> images;
    private List<String> listNombres;
    private List<Category> categorias;



    private final String TAG = CatergorisFragment.class.getSimpleName();

    public CatergorisFragment() {
        // Required empty public constructor
    }

//    public void initListImagenes(){
//        listFotos = new ArrayList<>();
//        listFotos.add("https://img.elcomercio.pe/files/article_content_ec_fotos/uploads/2018/02/22/5a8f26856f0b8.jpeg");
//        listFotos.add("http://mouse.latercera.com/wp-content/uploads/2018/03/goku.jpg");
//        listFotos.add("https://www.elastic.co/assets/bltada7771f270d08f6/enhanced-buzz-1492-1379411828-15.jpg");
//        listFotos.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBIpaUc5rQhLMs6aKu27sNu7xgaOyMm_8YgH2tktB181U3kRYi");
//        listFotos.add("https://cdn.pixabay.com/photo/2018/06/09/22/56/peacock-3465442__340.jpg");
//        listFotos.add("https://www.gettyimages.in/gi-resources/images/Homepage/Hero/US/SEP2016/prestige-476863311.jpg");
//    }
//
//    public void initListNombres(){
//        listNombres = new ArrayList<>();
//        listNombres.add("Área fisico y personal");
//        listNombres.add("Área psicomotriz");
//        listNombres.add("Área intelectual");
//        listNombres.add("Área escolar");
//        listNombres.add("Área Habitos");
//        listNombres.add("Área emocional solcial");
//
//    }

    public static CatergorisFragment newInstance(ComunViews cv) {
        Bundle args = new Bundle();
        args.putParcelable("comun", cv);
        CatergorisFragment fragment = new CatergorisFragment();
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
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        recyclerCategories = view.findViewById(R.id.listCategories);
        // /initAll(view);
        databaseHelper = new DatabaseHelper(getContext());
        RecyclerViewUpdate();
//        initListImagenes();
//        initListNombres();
//        getDataFake();
        return view;
    }

//    public void getDataFake(){
//        List<Category> list= new ArrayList<>();
//        for (int i=0;i<6;i++){
//            list.add(new Category(i,listNombres.get(i),listFotos.get(i)));
//        }
//        categorias=list;
//        RecyclerViewUpdate();
//    }

    public void RecyclerViewUpdate() {
        recyclerCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        if (adapter == null) {
        //Log.i(TAG, "--->listadoAdapter null");
        categorias = new ArrayList<>();
//        adapter = new CategoriesAdapter(categorias, getContext());
        adapter = new CategoriesAdapter(this.databaseHelper.getCategories(), getContext());
        adapter.setOnItemClickListener(this);
//        } else {
//            //Log.i(TAG, "--->listadoAdapter update");
//            adapter.updateAll(categorias);
//        }
        recyclerCategories.setAdapter(adapter);
    }

//    public void initAll(View view){
//        listCategories = view.findViewById(R.id.listCategories);
//        nombres = new ArrayList<>();
//        images = new ArrayList<>();
//        categorias = new ArrayList<>();
//        nombres.add("Área fisico y personal");
//        nombres.add("Área psicomotriz");
//        nombres.add("Área intelectual");
//        nombres.add("Área escolar");
//        nombres.add("Área Habitos");
//        nombres.add("Área emocional solcial");
//        adapter = new CategoriesAdapter(nombres,this.comunViews);
//        listCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        listCategories.setAdapter(adapter);
//    }

    @Override
    public void onClickSelectedItem(Category category) {
        Log.i(TAG, "--->Category name: " + category.getName());
        comunViews.changeFragment(ListaJuegosFragment.newInstance(comunViews, category.getId()));
    }

}
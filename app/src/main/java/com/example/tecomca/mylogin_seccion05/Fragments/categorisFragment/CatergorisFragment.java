package com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecomca.mylogin_seccion05.Fragments.Juegos.ListaJuegosFragment;
import com.example.tecomca.mylogin_seccion05.Model.Category;
import com.example.tecomca.mylogin_seccion05.Model.Stadistics;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;
import com.example.tecomca.mylogin_seccion05.Utils.Util;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CatergorisFragment extends Fragment implements CategoriesAdapter.OnItemClickListener {

    private ComunViews comunViews;

    private RecyclerView recyclerCategories;
    private DatabaseHelper databaseHelper;
    private CategoriesAdapter adapter;
    private SharedPreferences prefs;

    private final String TAG = CatergorisFragment.class.getSimpleName();

    public CatergorisFragment() {
    }

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
        comunViews = getArguments().getParcelable("comun");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        prefs = getContext().getSharedPreferences("Preferences", MODE_PRIVATE);
        recyclerCategories = view.findViewById(R.id.listCategories);
        databaseHelper = new DatabaseHelper(getContext());
        RecyclerViewUpdate();
        return view;
    }

    public void RecyclerViewUpdate() {
        recyclerCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new CategoriesAdapter(this.databaseHelper.getCategories(), getContext());
        adapter.setOnItemClickListener(this);
        recyclerCategories.setAdapter(adapter);
    }


    @Override
    public void onClickSelectedItem(final Category category) {
        Log.i(TAG, "--->Category name: " + category.getName());
        showDialog(category);
//        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_name, null);
//        dialogBuilder.setView(dialogView);
//        final EditText et_name = dialogView.findViewById(R.id.et_name);
//        TextView tv_cancelar = dialogView.findViewById(R.id.tv_cancel);
//        TextView tv_save = dialogView.findViewById(R.id.tv_save);
//        final AlertDialog alertDialog = dialogBuilder.create();
//        tv_cancelar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        tv_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                Util.setPlayerName(prefs, et_name.getText().toString());
//                comunViews.changeFragment(ListaJuegosFragment.newInstance(comunViews, category.getId()));
//            }
//        });
//        alertDialog.show();
    }

    public void showDialog(final Category category){
        new LovelyTextInputDialog(getContext())
                .setTopColorRes(R.color.colorPrimary)
                .setCancelable(false)
                .setIcon(R.drawable.ic_action_name)
                .setTitle("Ingrese Nombre")
                .setInputFilter("Por favor introducir nombre", new LovelyTextInputDialog.TextFilter() {
                    @Override
                    public boolean check(String text) {
                        return text.matches("\\w+");
                    }
                })
                .setConfirmButton("Guardar", new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String text) {
                        Util.setPlayerName(prefs, text);
                        comunViews.changeFragment(ListaJuegosFragment.newInstance(comunViews, category.getId()));
                        Toast.makeText(getContext(), "Nombre guardado", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

}
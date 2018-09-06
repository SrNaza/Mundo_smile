package com.example.tecomca.mylogin_seccion05.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.tecomca.mylogin_seccion05.Fragments.InstructionsFragment.AlertFragment;
import com.example.tecomca.mylogin_seccion05.Fragments.categorisFragment.CatergorisFragment;
import com.example.tecomca.mylogin_seccion05.Fragments.EstadisticoFragment.InforFragment;
import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Utils.ComunViews;
import com.example.tecomca.mylogin_seccion05.Utils.Util;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ParcelCreator")
public class MainActivity extends AppCompatActivity implements ComunViews {

    private SharedPreferences prefs;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private List<String> fragments;

    private ComunViews comunViews = this;
    Toolbar toolbar;
    private String nameFromIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_foxy);
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        nameFromIntent = getIntent().getStringExtra("EMAIL");
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);
        this.fragments = new ArrayList<>();
        setFragmentByDefault();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, this.drawerLayout, this.toolbar, 0, 0);
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        chargeNavView();

//        con este listener controlamos el navigation drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.menu_mail:
                        fragments.clear();
                        fragment = CatergorisFragment.newInstance(comunViews);
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_alert:
                        fragments.clear();
                        fragment = AlertFragment.newInstance(comunViews);
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_information:
                        fragments.clear();
                        fragment = InforFragment.newInstance(comunViews);
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_registrar:
                        fragments.clear();
                        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(intent);
//                        fragment = new RegistrarFragment();
//                        fragmentTransaction = true;
                        break;
                    case R.id.menu_logout:
                        fragments.clear();
                        logOut();
                        break;
                    case R.id.menu_forget_logout:
                        fragments.clear();
                        removeSharedPreferences();
                        logOut();
                        break;

                }
                if (fragmentTransaction) {
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    changeFragment(fragment);
                    drawerLayout.closeDrawers();
                }

                return true;
            }
        });
    }




    public void chargeNavView() {
        View view = this.navigationView.getHeaderView(0);
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(Util.getSessionName(this.prefs));
        switch (Util.getSessionType(this.prefs)) {
            case 1: {
                this.navigationView.inflateMenu(R.menu.nav_options);
                break;
            }
            case 2: {
                // algo raro
                this.navigationView.inflateMenu(R.menu.menu_guest);
                break;
            }
        }
    }

    // parte de navigation drawer
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault() {
        changeFragment(CatergorisFragment.newInstance(comunViews));
    }

    @Override
    public void changeFragment(Fragment fragment) {
        getSupportActionBar().setSubtitle(" ");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        if (!this.fragments.isEmpty()) {
            ft.addToBackStack(fragment.getClass().getName());
        }
        this.fragments.add(fragment.getClass().getName());
        ft.commit();
        Log.e("asda", "Tamaño lista: " + getSupportFragmentManager().getBackStackEntryCount());
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.content_frame, fragment)
//                .addToBackStack(fragment.getClass().getName())
//                .commit();
//        this.fragments.add(fragment.getClass().getName());
    }

    @Override
    public void onBackPressed() {
        getSupportActionBar().setSubtitle(" ");
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (this.fragments.size() > 0) {
                this.fragments.remove(this.fragments.size() - 1);
            }
            super.onBackPressed();
        }
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // abrir el menu lateral
//                drawerLayout.openDrawer(GravityCompat.START);
//                return true;
//            case R.id.menu_logout:
//                logOut();
//                return true;
//            case R.id.menu_forget_logout:
//                removeSharedPreferences();
//                logOut();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private void logOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    private void removeSharedPreferences() {
        prefs.edit().clear().apply();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}

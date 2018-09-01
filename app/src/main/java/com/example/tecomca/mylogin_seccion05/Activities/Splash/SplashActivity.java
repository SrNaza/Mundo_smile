package com.example.tecomca.mylogin_seccion05.Activities.Splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.example.tecomca.mylogin_seccion05.Activities.LoginActivity;
import com.example.tecomca.mylogin_seccion05.Activities.MainActivity;
import com.example.tecomca.mylogin_seccion05.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Preferences", MODE_PRIVATE);

        Intent intentLogin = new Intent (this, LoginActivity.class);
        Intent intentMain = new Intent (this, MainActivity.class);

        Log.e("login", "user: " + Util.getSessionEmail(prefs));
        Log.e("login", "pass: " + Util.getSessionPass(prefs));

        if (TextUtils.isEmpty(Util.getSessionEmail(prefs)) &&
                TextUtils.isEmpty(Util.getSessionPass(prefs))) {
            startActivity(intentLogin);
        } else {
            startActivity(intentMain);
        }
        finish(); // matamos el activity
    }
}

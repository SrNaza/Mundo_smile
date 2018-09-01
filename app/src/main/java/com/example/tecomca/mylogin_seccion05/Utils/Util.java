package com.example.tecomca.mylogin_seccion05.Utils;

import android.content.SharedPreferences;

public class Util {

    public static String getSessionName(SharedPreferences prefecences){
        return prefecences.getString("name", "");
    }

    public static String getSessionEmail(SharedPreferences prefecences) {
        return prefecences.getString("email", "");
    }

    public static String getSessionPass(SharedPreferences prefecences) {
        return prefecences.getString("pass", "");
    }

    public static int getSessionType(SharedPreferences prefecences) {
        return prefecences.getInt("type", 0);
    }

    public static void setSessionName(SharedPreferences prefecences, String value) {
        SharedPreferences.Editor editor = prefecences.edit();
        editor.apply();
        editor.putString("name", value);
        editor.commit();
    }

    public static void setSessionEmail(SharedPreferences prefecences, String value) {
        SharedPreferences.Editor editor = prefecences.edit();
        editor.apply();
        editor.putString("email", value);
        editor.commit();
    }

    public static void setSessionPass(SharedPreferences prefecences, String value) {
        SharedPreferences.Editor editor = prefecences.edit();
        editor.apply();
        editor.putString("pass", value);
        editor.commit();
    }

    public static void setSessionType(SharedPreferences prefecences, int value) {
        SharedPreferences.Editor editor = prefecences.edit();
        editor.apply();
        editor.putInt("type", value);
        editor.commit();
    }

}

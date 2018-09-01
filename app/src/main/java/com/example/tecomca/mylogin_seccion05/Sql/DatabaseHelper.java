package com.example.tecomca.mylogin_seccion05.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tecomca.mylogin_seccion05.Model.Category;
import com.example.tecomca.mylogin_seccion05.Model.Characteristics;
import com.example.tecomca.mylogin_seccion05.Model.Games;
import com.example.tecomca.mylogin_seccion05.Model.Instructions;
import com.example.tecomca.mylogin_seccion05.Model.User;
import com.example.tecomca.mylogin_seccion05.Utils.Util;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteAssetHelper {

    private SharedPreferences prefs;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "MundoSonrisa.db";

    // Table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_INSTRUCTIONS = "instructions";
    private static final String TABLE_GAME = "games";
    private static final String TABLE_CHARACTERISTICS = "characteristics";

    //Table users columns
    // User
    private static final String COLUM_USER_ID = "id_user";
    private static final String COLUM_USER_NAME = "name";
    private static final String COLUM_USER_EMAIL = "email";
    private static final String COLUM_USER_PASSWORD = "password";
    private static final String COLUM_USER_TYPE = "type";

    // Categories
    private static final String COLUM_CATEGORY_ID = "id_category";
    private static final String COLUM_CATEGORY_NAME = "name";
    private static final String COLUM_CATEGORY_IMAGE = "image";

    // Instructions
    private static final String COLUM_INSTRUCTION_ID = "id_instruction";
    private static final String COLUM_INSTRUCTION_DESCRIPTION = "description";
    private static final String COLUM_INSTRUCTION_IMAGE = "image";

    // Games
    private static final String COLUM_GAME_ID = "id_game";
    private static final String COLUM_GAME_ID_CATEGORY = "id_category";
    private static final String COLUM_GAME_NAME = "name";
    private static final String COLUM_GAME_IMAGE = "image";
    private static final String COLUM_GAME_DESCRIPTION = "description";

    //Characteristicas
    private static final String COLUM_CHARACTERISTICS_ID = "id_characteristics";
    private static final String COLUM_CHARACTERISTICS_ID_GAME = "id_game";
    private static final String COLUM_CHARACTERISTICS_IMAGE = "image";
    private static final String COLUM_CHARACTERISTICS_ANSWERS = "answers";
    private static final String COLUM_CHARACTERISTICS_TRUE_ANSWER = "true_answer";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        prefs = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

//    @Override
//    public void onCreate(SQLiteDatabase db){
//        db.execSQL(CREATE_USER_TABLE);
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM_USER_NAME, user.getName());
        values.put(COLUM_USER_EMAIL, user.getEmail());
        values.put(COLUM_USER_PASSWORD, user.getPassword());

        // insertar los valores de values en la base de datos
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUser(String email) {
        String[] columns = {
                COLUM_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUM_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = {
                COLUM_USER_ID,
                COLUM_USER_NAME,
                COLUM_USER_TYPE
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUM_USER_EMAIL + " = ?" + " AND " + COLUM_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.moveToFirst();
        if (cursorCount > 0) {
            Util.setSessionName(prefs, cursor.getString(cursor.getColumnIndex("name")));
            Util.setSessionType(prefs, cursor.getInt(cursor.getColumnIndex("type")));
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        String[] columns = {
                COLUM_CATEGORY_ID,
                COLUM_CATEGORY_NAME,
                COLUM_CATEGORY_IMAGE
        };
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CATEGORIES,
                columns,
                null,
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            do {
                Category jefe = new Category(
                        cursor.getInt(cursor.getColumnIndex(COLUM_CATEGORY_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUM_CATEGORY_NAME)),
                        cursor.getBlob(cursor.getColumnIndex(COLUM_CATEGORY_IMAGE)));
                categories.add(jefe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return categories;
    }

    public List<Games> loadGamesCategory(int category) {
        List<Games> games = new ArrayList<>();
        String[] columns = {
                COLUM_GAME_ID,
                COLUM_GAME_ID_CATEGORY,
                COLUM_GAME_NAME,
                COLUM_GAME_IMAGE,
                COLUM_GAME_DESCRIPTION
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUM_GAME_ID_CATEGORY + " = ?";
        String[] selectionArgs = {String.valueOf(category)};
        Cursor cursor = db.query(TABLE_GAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            do {
                Games game = new Games(
                        cursor.getInt(cursor.getColumnIndex(COLUM_GAME_ID)),
                        cursor.getInt(cursor.getColumnIndex(COLUM_GAME_ID_CATEGORY)),
                        cursor.getBlob(cursor.getColumnIndex(COLUM_CATEGORY_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(COLUM_GAME_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUM_GAME_DESCRIPTION)));
                games.add(game);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return games;
    }

    public List<Characteristics> loadGame(int gamess) {
        List<Characteristics> games = new ArrayList<>();
        String[] columns = {
                COLUM_CHARACTERISTICS_ID,
                COLUM_CHARACTERISTICS_ID_GAME,
                COLUM_CHARACTERISTICS_IMAGE,
                COLUM_CHARACTERISTICS_ANSWERS,
                COLUM_CHARACTERISTICS_TRUE_ANSWER
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUM_CHARACTERISTICS_ID_GAME + " = ?";
        String[] selectionArgs = {String.valueOf(gamess)};
        Cursor cursor = db.query(TABLE_CHARACTERISTICS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            do {
                Characteristics game = new Characteristics(
                        cursor.getInt(cursor.getColumnIndex(COLUM_CHARACTERISTICS_ID)),
                        cursor.getInt(cursor.getColumnIndex(COLUM_CHARACTERISTICS_ID_GAME)),
                        cursor.getBlob(cursor.getColumnIndex(COLUM_CHARACTERISTICS_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(COLUM_CHARACTERISTICS_ANSWERS)),
                        cursor.getString(cursor.getColumnIndex(COLUM_CHARACTERISTICS_TRUE_ANSWER)));
                games.add(game);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return games;
    }

    public List<Instructions> getInstruction() {
        List<Instructions> intructions = new ArrayList<>();
        String[] columns = {
                COLUM_INSTRUCTION_ID,
                COLUM_INSTRUCTION_DESCRIPTION,
                COLUM_INSTRUCTION_IMAGE
        };
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_INSTRUCTIONS,
                columns,
                null,
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            do {
                Instructions jefe = new Instructions(
                        cursor.getInt(cursor.getColumnIndex(COLUM_INSTRUCTION_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUM_INSTRUCTION_DESCRIPTION)),
                        cursor.getBlob(cursor.getColumnIndex(COLUM_INSTRUCTION_IMAGE)));
                intructions.add(jefe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return intructions;
    }

//    public List<Category> checkImageCategories() {
//        String[] columns = {
//                COLUM_CATEGORY_ID
//        };
//        SQLiteDatabase db = this.getWritableDatabase();
//        String selection = COLUM_CATEGORY_IMAGE + " = ?" + " AND " + COLUM_CATEGORY_NAME + " = ?";
//
//        Cursor cursor = db.query(TABLE_CATEGORIES,
//                columns,
//                selection,
//                null,
//                null,
//                null,
//                null);
//        List<Category> categories = new ArrayList<>();
//        if (cursor.moveToFirst()) {
//            do {
//                Category jefe = new Category();
//                jefe.setName(cursor.getString(cursor.getColumnIndex("name")));
//                jefe.setImagen(cursor.getBlob(cursor.getColumnIndex("imagen")));
//                categories.add(jefe);
//            } while (cursor.moveToNext());
//        }
//
//
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
//        return categories;
////        if (cursorCount > 0) {
////            return true;
////        }
////        return false;
//    }


}

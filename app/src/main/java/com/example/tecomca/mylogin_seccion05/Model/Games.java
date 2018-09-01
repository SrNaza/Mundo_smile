package com.example.tecomca.mylogin_seccion05.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Games implements Parcelable{

    private int id_game;
    private int id_category;
    private byte[] image;
    private String name;
    private String description;

    public Games(int id_game, int id_category, byte[] image, String name, String description) {
        this.id_game = id_game;
        this.id_category = id_category;
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}
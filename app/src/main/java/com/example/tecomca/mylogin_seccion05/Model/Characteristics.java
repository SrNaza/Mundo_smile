package com.example.tecomca.mylogin_seccion05.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Characteristics implements Parcelable{

    private int id_characteristics;
    private int id_game;
    private byte[] image;
    private String answer;
    private String true_answer;

    public Characteristics(int id_characteristics, int id_game, byte[] image, String answer, String true_answer) {
        this.id_characteristics = id_characteristics;
        this.id_game = id_game;
        this.image = image;
        this.answer = answer;
        this.true_answer = true_answer;
    }

    public int getId_characteristics() {
        return id_characteristics;
    }

    public void setId_characteristics(int id_characteristics) {
        this.id_characteristics = id_characteristics;
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTrue_answer() {
        return true_answer;
    }

    public void setTrue_answer(String true_answer) {
        this.true_answer = true_answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_characteristics);
    }

}
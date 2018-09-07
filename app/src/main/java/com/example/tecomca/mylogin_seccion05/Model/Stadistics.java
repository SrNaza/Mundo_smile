package com.example.tecomca.mylogin_seccion05.Model;

public class Stadistics {

    int id;
    int id_game;
    String namePlayer;
    String nameGame;
    private byte[] image;
    int buenas;
    int malas;

    public Stadistics() {
//        int id, int id_game, String namePlayer, int buenas, int malas
//        this.id = id;
//        this.id_game = id_game;
//        this.namePlayer = namePlayer;
//        this.buenas = buenas;
//        this.malas = malas;
    }

    public Stadistics(int id, int id_game, String namePlayer, String nameGame, byte[] image,int buenas, int malas){
        this.id = id;
        this.id_game = id_game;
        this.namePlayer = namePlayer;
        this.nameGame = nameGame;
        this.image = image;
        this.buenas = buenas;
        this.malas = malas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame= nameGame;
    }

    public int getBuenas() {
        return buenas;
    }

    public void setBuenas(int buenas) {
        this.buenas = buenas;
    }

    public int getMalas() {
        return malas;
    }

    public void setMalas(int malas) {
        this.malas = malas;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
package com.example.tecomca.mylogin_seccion05.Model;

public class Instructions {

    private int id;
    private String description;
    private byte[] imagen;

    public Instructions(int id, String description, byte[] imagen) {
        this.id = id;
        this.description = description;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}
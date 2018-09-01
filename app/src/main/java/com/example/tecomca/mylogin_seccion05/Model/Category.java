package com.example.tecomca.mylogin_seccion05.Model;

public class Category {

    private int id;
    private String name;
    private byte[] imagen;

    public Category(int id, String name, byte[] imagen) {
        this.id = id;
        this.name = name;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}
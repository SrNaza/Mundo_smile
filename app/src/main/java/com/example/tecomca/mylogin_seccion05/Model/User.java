package com.example.tecomca.mylogin_seccion05.Model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private int tipo;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType(){ return tipo;}

    public void setType(int tipo) { this.tipo = tipo;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.tecomca.mylogin_seccion05.Model;

public class Adsurdo {

     String question;
     String answer;
     Integer image;

    public Adsurdo(String question, String answer, Integer image) {
        question = question;
        answer = answer;
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getImage() {
        return image;
    }
}

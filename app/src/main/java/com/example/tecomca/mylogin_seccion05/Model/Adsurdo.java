package com.example.tecomca.mylogin_seccion05.Model;

public class Adsurdo {

     String question;
     boolean answer;
     Integer image;

    public Adsurdo(String question, boolean answer, Integer image) {
        this.question = question;
        this.answer = answer;
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}

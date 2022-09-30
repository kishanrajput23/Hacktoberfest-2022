package com.a9934527599.myplayer;

public class iconModel{
    private  int img;
    private String titel;

    public iconModel(int img, String titel) {
        this.img = img;
        this.titel = titel;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
}

package com.lambda.spark;

import android.media.Image;
import android.widget.Button;

public class RecoItemModel {

    private int reco_img;
    private String reco_title;
    private String reco_price;

    public RecoItemModel(int reco_img, String reco_title, String reco_price) {
        this.reco_img = reco_img;
        this.reco_title = reco_title;
        this.reco_price = reco_price;

    }

    public int getReco_img() {
        return reco_img;
    }

    public void setReco_img(int reco_img) {
        this.reco_img = reco_img;
    }

    public String getReco_title() {
        return reco_title;
    }

    public void setReco_title(String reco_title) {
        this.reco_title = reco_title;
    }


    public String getReco_price() {
        return reco_price;
    }

    public void setReco_price(String reco_price) {
        this.reco_price = reco_price;
    }

}

package com.lambda.spark;
import java.io.Serializable;

import android.os.Parcelable;
import android.widget.TextView;

import java.util.ArrayList;

public class HorizontalProductModel extends ArrayList<Parcelable> {

    private int productImg;
    private String productTitle;
    private String productPrice;
    private String productDept;

    public int getProductImg() {
        return productImg;
    }

    public void setProductImg(int productImg) {
        this.productImg = productImg;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDept() {
        return productDept;
    }

    public void setProductDept(String productDept) {
        this.productDept = productDept;
    }

    public HorizontalProductModel(int productImg, String productTitle, String productPrice, String productDept) {
        this.productImg = productImg;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productDept = productDept;
    }
}

package com.lambda.spark;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecoItemAdapter extends BaseAdapter {

    //using same model as trending today model for reco

    private  List<HorizontalProductModel> horizontalProductModelList;
    private ImageView img_click;
    //img_click = findViewById(R.id.horizontal_product_img);

    public RecoItemAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    View view;
    if(convertView == null){
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_product_item_layout, null);

        ImageView reco_prod_img= view.findViewById(R.id.horizontal_product_img);
        TextView reco_prod_title = view.findViewById(R.id.horizontal_product_title);
        TextView reco_prod_price = view.findViewById(R.id.horizontal_product_price);

        reco_prod_img.setImageResource(horizontalProductModelList.get(position).getProductImg());
        reco_prod_title.setText(horizontalProductModelList.get(position).getProductTitle());
        reco_prod_price.setText(horizontalProductModelList.get(position).getProductPrice());

    }else {
        view = convertView;

    }
        return  view;
    }
}

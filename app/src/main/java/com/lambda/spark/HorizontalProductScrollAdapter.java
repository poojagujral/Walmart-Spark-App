package com.lambda.spark;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    private List<HorizontalProductModel> horizontalProductModelList;
    private OnNoteListener mOnNoteListener;

    public HorizontalProductScrollAdapter(List<HorizontalProductModel> horizontalProductModelList, OnNoteListener onNoteListener) {
        this.horizontalProductModelList = horizontalProductModelList;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_product_item_layout, viewGroup, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder viewHolder, int position) {
        int resource = horizontalProductModelList.get(position).getProductImg();
        String title = horizontalProductModelList.get(position).getProductTitle();
        String price = horizontalProductModelList.get(position).getProductPrice();
        String dept = horizontalProductModelList.get(position).getProductDept();

        viewHolder.setProductImage(resource);
        viewHolder.setProductTitle(title);
        viewHolder.setProdyctPrice(price);
        //viewHolder.setProductDept(dept);

    }

    @Override
    public int getItemCount() {

        return horizontalProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView productImage;
        private TextView productTitle;
        private TextView prodyctPrice;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);


            productImage = itemView.findViewById(R.id.horizontal_product_img);
            productTitle = itemView.findViewById(R.id.horizontal_product_title);
            prodyctPrice = itemView.findViewById(R.id.horizontal_product_price);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);


        }

        private  void setProductImage(int resource){

            productImage.setImageResource(resource);
        }

        private  void setProductTitle(String title){
            productTitle.setText(title);
        }
        private  void setProdyctPrice(String price){
            prodyctPrice.setText(price);
        }
        //private  void setProductDept(String dept){productDept.setText(dept);}


        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}

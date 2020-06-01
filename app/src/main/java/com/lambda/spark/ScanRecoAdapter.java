//package com.lambda.spark;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.List;
//
//public class ScanRecoAdapter extends RecyclerView.Adapter<ScanRecoAdapter.ViewHolder> {
//
//    private List<ScanRecoItemModel> scanRecoItemModelList;
//
//    public ScanRecoAdapter(List<ScanRecoItemModel> scanRecoItemModelList) {
//        this.scanRecoItemModelList = scanRecoItemModelList;
//    }
//
//    @NonNull
//    @Override
//    public ScanRecoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_product_item_layout, viewGroup, false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ScanRecoAdapter.ViewHolder viewHolder, int i) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//}

package com.example.aplikacjakurierska.ActivityClient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.model.Product;

import java.util.List;

public class ProductClientAdapter extends RecyclerView.Adapter<ProductClientAdapter.ViewHolder> {
private Context context;
private List<Product> productList;
private ProductClientAdapter.OnStudyListener onStudyListener;

private ProductClientAdapter(Context context,List<Product> productList,ProductClientAdapter.OnStudyListener onStudyListener){
    this.context = context;
    this.productList = productList;
    this.onStudyListener =onStudyListener;
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_client_adapter_list,parent,false);
        return new ProductClientAdapter.ViewHolder(view,onStudyListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
public ProductClientAdapter.OnStudyListener onStudyListener;
public TextView name,price;
        public ViewHolder(View itemView, ProductClientAdapter.OnStudyListener onStudyListener) {
            super(itemView);
            name = itemView.findViewById(R.id.nameProduct);
            price = itemView.findViewById(R.id.price);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onStudyListener.onStudyClick(getAdapterPosition());
        }
    }
    public interface OnStudyListener {
        void onStudyClick(int position);
    }
}

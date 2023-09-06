package com.example.aplikacjakurierska.ActivityCustomer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.model.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductCustomerAdapter extends RecyclerView.Adapter<ProductCustomerAdapter.ViewHolder>  {
public Context context;
    private static List<Product> productList = new ArrayList<>();
long id;
 ProductCustomerAdapter.OnStudyListener monStudylistener;
    private boolean isEditable;

public ProductCustomerAdapter(List<Product> productList , ProductCustomerAdapter.OnStudyListener monStudylistener,boolean isEditable )
{
    this.isEditable = isEditable;
    this.monStudylistener = monStudylistener;
    this.productList = productList;


    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_customer_adapter_list,parent,false);
        return new ViewHolder(view,monStudylistener);}
    @Override
    public void onBindViewHolder(ProductCustomerAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);

        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(product.getUpdatedAt());
        holder.date.setText(formattedDate);
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(String.valueOf(product.getProductPrice()));
        holder.productDescription.setText(product.getProductDescription());


    }

    @Override
    public int getItemCount() {
        System.out.println("Ilość produktów wynosi:  "+ productList.size());
        return productList.size();

    }



    public  class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


        TextView productName, productPrice, productDescription, date;
        ProductCustomerAdapter.OnStudyListener onStudyListener ;
        LinearLayout mainContainersLayout;
        public ViewHolder(@NonNull View itemView, ProductCustomerAdapter.OnStudyListener onStudyListener) {
            super(itemView);


            date = itemView.findViewById(R.id.date);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productDescription = itemView.findViewById(R.id.productDescription);
            this.onStudyListener = onStudyListener;
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);


            mainContainersLayout = itemView.findViewById(R.id.main_container);

        }
        @Override
        public void onClick(View view) {
if (isEditable){ onStudyListener.onStudyClick(getBindingAdapterPosition());}}
        @Override
        public boolean onLongClick(View view) {
            if (isEditable) {
                onStudyListener.onStudyLongClick(getBindingAdapterPosition(), productList.get(getBindingAdapterPosition()).getId());
            }return true;}}


    public interface OnStudyListener{
        void onStudyClick(int position);
        void onStudyLongClick(int position,long id);





}}








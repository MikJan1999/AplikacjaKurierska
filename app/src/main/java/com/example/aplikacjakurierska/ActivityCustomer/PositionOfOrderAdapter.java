package com.example.aplikacjakurierska.ActivityCustomer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.ActivityClient.ChooseProductAdapter;
import com.example.aplikacjakurierska.R;

import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrderWithProductNameDTO;
import com.example.aplikacjakurierska.retrofit.model.Product;

import java.util.List;

public class PositionOfOrderAdapter extends RecyclerView.Adapter<PositionOfOrderAdapter.ViewHolders> {
    private Context context;
    private List<PositionCustomerOrderWithProductNameDTO> positionCustomerOrderWithProductNameDTOSList;




    public PositionOfOrderAdapter(List<PositionCustomerOrderWithProductNameDTO> positionCustomerOrderWithProductNameDTOSList) {
        this.positionCustomerOrderWithProductNameDTOSList = positionCustomerOrderWithProductNameDTOSList;

    }

    @Override
    public PositionOfOrderAdapter.ViewHolders onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_history_order_view,parent,false);
        return new ViewHolders(view);}

    @Override
    public void onBindViewHolder(ViewHolders holder, int position) {
        PositionCustomerOrderWithProductNameDTO positionCustomerOrderWithProductNameDTO = positionCustomerOrderWithProductNameDTOSList.get(position);
        holder.name.setText(positionCustomerOrderWithProductNameDTO.getProductName());
        holder.desc.setText(positionCustomerOrderWithProductNameDTO.getPcoDesc());
        holder.amount.setText(String.valueOf( positionCustomerOrderWithProductNameDTO.getAmount()));
        holder.price.setText(String.valueOf( positionCustomerOrderWithProductNameDTO.getPriceAll()));
    }

    @Override
    public int getItemCount() {
        System.out.println("Ilość produktów wynosi:  "+ positionCustomerOrderWithProductNameDTOSList.size());
        return positionCustomerOrderWithProductNameDTOSList.size();
    }


    public class ViewHolders extends RecyclerView.ViewHolder  {
        TextView name, desc, amount,price;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.previewHistoryOrderViewName);
            desc = itemView.findViewById(R.id.previewHistoryOrderViewDescription);
            amount= itemView.findViewById(R.id.previewHistoryOrderViewAmount);
            price= itemView.findViewById(R.id.previewHistoryOrderViewPrice);

        }}
}



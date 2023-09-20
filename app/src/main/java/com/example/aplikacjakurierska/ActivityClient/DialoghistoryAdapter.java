package com.example.aplikacjakurierska.ActivityClient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrderWithProductNameDTO;
import com.example.aplikacjakurierska.retrofit.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DialoghistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PositionCustomerOrderWithProductNameDTO> productList;

    private static final int TYPE_LIST = 1;
    private static final int TYPE_HEAD = 0; // Zmieniono wartość na 0

    public DialoghistoryAdapter(List<PositionCustomerOrderWithProductNameDTO> productList) {
        this.productList = productList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_list2, parent, false);
            return new ListViewHolder(view);
        } else if (viewType == TYPE_HEAD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title, parent, false);
            return new HeadViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListViewHolder) {
            PositionCustomerOrderWithProductNameDTO positionCustomerOrderWithProductNameDTO = productList.get(position-1);
            ((ListViewHolder) holder).name.setText(positionCustomerOrderWithProductNameDTO.getProductName());
            ((ListViewHolder) holder).priceAll.setText(String.valueOf(positionCustomerOrderWithProductNameDTO.getPriceAll()));
            ((ListViewHolder) holder).amount.setText(String.valueOf(positionCustomerOrderWithProductNameDTO.getAmount()));
        } else if (holder instanceof HeadViewHolder) {
            ((HeadViewHolder) holder).titleName.setText("Nazwa");
            ((HeadViewHolder) holder).titlePriceAll.setText("Suma [zł]");
            ((HeadViewHolder) holder).titleAmount.setText("Ilość [kg]");
         // Tutaj możesz ustawić teksty dla nagłówków
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEAD : TYPE_LIST;
    }

    @Override
    public int getItemCount() {
        return productList.size()+1;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder  {
        TextView name, priceAll, amount;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productNameCart2);
            priceAll = itemView.findViewById(R.id.productPriceAllCart2);
            amount = itemView.findViewById(R.id.productAmountCard2);

        }


    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {
        TextView titleName, titlePriceAll, titleAmount;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            titleName = itemView.findViewById(R.id.productNameCart22);
            titlePriceAll = itemView.findViewById(R.id.productPriceAllCart22);
            titleAmount = itemView.findViewById(R.id.productAmountCard22);
        }
    }


}

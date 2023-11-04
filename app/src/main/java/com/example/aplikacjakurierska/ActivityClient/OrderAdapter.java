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
import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrderWithProductNameDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_LIST = 1;
    private static final int TYPE_HEAD = 0;
    public Context context;
    private static List<PositionCustomerOrderWithProductNameDTO> productListCart = new ArrayList<>();
    long id;
    OrderAdapter.OnStudyListener monStudylistener;
    public OrderAdapter(List<PositionCustomerOrderWithProductNameDTO> productListCart , OrderAdapter.OnStudyListener monStudylistener)
    {
        this.monStudylistener = monStudylistener;
        this.productListCart = productListCart;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_list, parent, false);
            return new ViewHolder(view, monStudylistener);
        } else if (viewType == TYPE_HEAD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title, parent, false);
            return new HeadViewHolder(view);
        }
        return null;
//       view =LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_list,parent,false);
//        return new ViewHolder(view,monStudylistener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            PositionCustomerOrderWithProductNameDTO positionCustomerOrderWithProductNameDTO = productListCart.get(position-1);
            ((OrderAdapter.ViewHolder) holder).productNameCard.setText(positionCustomerOrderWithProductNameDTO.getProductName());
            ((OrderAdapter.ViewHolder) holder).productAmountCard.setText( String.valueOf(positionCustomerOrderWithProductNameDTO.getAmount()));
            ((OrderAdapter.ViewHolder) holder).productPriceCard.setText( String.valueOf(positionCustomerOrderWithProductNameDTO.getPriceAll()));

        } else if (holder instanceof HeadViewHolder) {
            ((OrderAdapter.HeadViewHolder) holder).titleName.setText("Nazwa");
            ((OrderAdapter.HeadViewHolder) holder).titlePriceAll.setText("Suma [zł]");
            ((OrderAdapter.HeadViewHolder) holder).titleAmount.setText("Ilość [kg]");
        }


    }
    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEAD;
        }else{
            return TYPE_LIST;
        }
    }
    @Override
    public int getItemCount() {
        System.out.println("Ilość pozycji wynosi:  "+ productListCart.size());
        return productListCart.size()+1;
    }
    public  class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView productNameCard, productPriceCard , productAmountCard;
        OrderAdapter.OnStudyListener onStudyListener ;
        LinearLayout mainContainersLayout;
        public ViewHolder(@NonNull View itemView, OrderAdapter.OnStudyListener onStudyListener) {
            super(itemView);
            this.onStudyListener = onStudyListener;
            productNameCard = itemView.findViewById(R.id.productNameCart);
            productPriceCard = itemView.findViewById(R.id.productPriceAllCart);
            productAmountCard = itemView.findViewById(R.id.productAmountCard);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);


            mainContainersLayout = itemView.findViewById(R.id.main_containerCard);

        }




        @Override
        public void onClick(View view) {

            }
        @Override
        public boolean onLongClick(View view) {
            onStudyListener.onStudyLongClick(getBindingAdapterPosition(), productListCart.get(getBindingAdapterPosition()).getPositionId());
            return true;}}

    public interface OnStudyListener{
        void onStudyClick(int position);
        void onStudyLongClick(int position,long id);

    }
       public void updateData(List<PositionCustomerOrderWithProductNameDTO> newList) {
           productListCart.clear();
           productListCart.addAll(newList);
           notifyDataSetChanged();
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
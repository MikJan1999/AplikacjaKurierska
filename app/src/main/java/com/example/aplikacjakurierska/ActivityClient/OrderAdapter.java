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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

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
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;

       view =LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_list,parent,false);
        return new ViewHolder(view,monStudylistener);}



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PositionCustomerOrderWithProductNameDTO positionCustomerOrderWithProductNameDTO = productListCart.get(position);
        holder.productNameCard.setText(positionCustomerOrderWithProductNameDTO.getProductName());
        holder.productPriceCard.setText( String.valueOf(positionCustomerOrderWithProductNameDTO.getPriceAll()));
        holder.productAmountCard.setText( String.valueOf(positionCustomerOrderWithProductNameDTO.getAmount()));
    }
    @Override
    public int getItemCount() {
        System.out.println("Ilość pozycji wynosi:  "+ productListCart.size());
        return productListCart.size();
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
       }
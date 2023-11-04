package com.example.aplikacjakurierska.ActivityCustomer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AllOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<CustomerOrder> customerOrderList;
    AllOrderAdapter.OnStudyListener monStudylistener;

    private static final int TYPE_LIST = 1;
    private static final int TYPE_HEAD = 0;

    public AllOrderAdapter(List<CustomerOrder> customerOrderList, AllOrderAdapter.OnStudyListener monStudylistener) {
        this.monStudylistener = monStudylistener;
        this.customerOrderList = customerOrderList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_list2, parent, false);
            return new ListViewHolder(view,monStudylistener);
        } else if (viewType == TYPE_HEAD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title, parent, false);
            return new HeadViewHolders(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListViewHolder) {
            CustomerOrder customerOrder = customerOrderList.get(position-1);
            ((ListViewHolder) holder).name.setText(customerOrder.getNameAndSurname());
            ((ListViewHolder) holder).data.setText(customerOrder.getDataCreateOrder().toString());
            System.out.println(customerOrder.getDataCreateOrder());
            ((ListViewHolder) holder).status.setText(customerOrder.getStatusOrder().getNazwa());
        } else if (holder instanceof HeadViewHolders) {
            ((HeadViewHolders) holder).titleName.setText("Imię i nazwisko");
            ((HeadViewHolders) holder).titleData.setText("Data");
            ((HeadViewHolders) holder).titleStatus.setText("Status");
        }
    }




    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEAD : TYPE_LIST;
    }

    @Override
    public int getItemCount() {
        return customerOrderList.size()+1;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        TextView name, data, status;
AllOrderAdapter.OnStudyListener onStudyListener;
        public ListViewHolder(@NonNull View itemView, AllOrderAdapter.OnStudyListener onStudyListener) {
            super(itemView);

            name = itemView.findViewById(R.id.productNameCart2);
            data = itemView.findViewById(R.id.productPriceAllCart2);
            status = itemView.findViewById(R.id.productAmountCard2);
            this.onStudyListener = onStudyListener;
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onStudyListener.onStudyClick(getBindingAdapterPosition()-1);
        }

        @Override
        public boolean onLongClick(View view) {
            onStudyListener.onStudyLongClick(getBindingAdapterPosition()-1, customerOrderList.get(getBindingAdapterPosition()-1).getId());
            return true;
        }
    }

    public class HeadViewHolders extends RecyclerView.ViewHolder {
        TextView titleName, titleData, titleStatus;

        public HeadViewHolders(@NonNull View itemView) {
            super(itemView);
            titleName = itemView.findViewById(R.id.productNameCart22);
            titleData = itemView.findViewById(R.id.productPriceAllCart22);
            titleStatus = itemView.findViewById(R.id.productAmountCard22);
        }
    }
    public interface OnStudyListener{
        void onStudyClick(int position);
        void onStudyLongClick(int position,long id);

    }


}

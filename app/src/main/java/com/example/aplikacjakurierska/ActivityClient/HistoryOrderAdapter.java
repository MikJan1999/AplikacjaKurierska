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


import java.util.ArrayList;
import java.util.List;
public class HistoryOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context context;
    private static final int TYPE_LIST = 1;
    private static final int TYPE_HEAD = 0;
    private  List<CustomerOrder> historyCustomerOrderList;

    long id;
    HistoryOrderAdapter.OnStudyListener monStudylistener;

    public HistoryOrderAdapter(List<CustomerOrder> historyCustomerOrderList , HistoryOrderAdapter.OnStudyListener monStudylistener)
    {
        this.monStudylistener = monStudylistener;
        this.historyCustomerOrderList = historyCustomerOrderList;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_order_product_list, parent, false);
            return new ViewHolders(view, monStudylistener);
        }else if (viewType == TYPE_HEAD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_header_order_history, parent, false);
            return new HeadViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolders) {
            CustomerOrder customerOrder = historyCustomerOrderList.get(position-1);
            System.out.println(historyCustomerOrderList + "fewfwfwf");
            ((ViewHolders) holder).historyDate.setText(customerOrder.getDataCreateOrder().toString());
            ((ViewHolders) holder).historyPrice.setText(String.valueOf(customerOrder.getPriceOrder()));
            ((ViewHolders) holder).historyStatus.setText(String.valueOf(customerOrder.getStatusOrder()));
        } else if (holder instanceof HeadViewHolder) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            headViewHolder.titleDate.setText("Data");
            headViewHolder.titlePriceAll.setText("Cena całkowita");
            headViewHolder.titleStatus.setText("Status");

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
        System.out.println("Ilość pozycji wynosi:  "+ historyCustomerOrderList.size());
        return historyCustomerOrderList.size()+1;
    }
    public  class  ViewHolders extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener
    {

        TextView historyDate, historyPrice , historyStatus;
        HistoryOrderAdapter.OnStudyListener onStudyListener ;
        LinearLayout mainContainersLayout;
        public ViewHolders(@NonNull View itemView,HistoryOrderAdapter.OnStudyListener onStudyListener
        ) {
            super(itemView);
            this.onStudyListener = onStudyListener;
            historyDate = itemView.findViewById(R.id.historyOrderDate);
            historyPrice = itemView.findViewById(R.id.historyOrderPrice);
            historyStatus = itemView.findViewById(R.id.historyOrderStatus);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);


            mainContainersLayout = itemView.findViewById(R.id.main_containerHistory);

        }

        //pamiętaj żeby dodać -1 aby nie mieszać pozycji (tego nie było nigdzie w necie)
        @Override
        public void onClick(View view) {
            onStudyListener.onStudyClick(getBindingAdapterPosition()-1);}
        @Override
        public boolean onLongClick(View view) {
            onStudyListener.onStudyLongClick(getBindingAdapterPosition(), historyCustomerOrderList.get(getBindingAdapterPosition()).getId());
            return true;
        }
    }

    public interface OnStudyListener{
        void onStudyClick(int position);
        void onStudyLongClick(int position,long id);

    }
    public void updateData(List<CustomerOrder> newList) {
        historyCustomerOrderList.clear();
        historyCustomerOrderList.addAll(newList);
        notifyDataSetChanged();
    }


    public class HeadViewHolder extends RecyclerView.ViewHolder {
        TextView titleDate, titlePriceAll, titleStatus;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            titleDate = itemView.findViewById(R.id.orderhistoryDateHeader);
            titlePriceAll = itemView.findViewById(R.id.orderhistoryPriceAllHeader);
            titleStatus = itemView.findViewById(R.id.orderhistoryStatusHeader);
        }
    }

}



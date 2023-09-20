package com.example.aplikacjakurierska.ActivityCustomer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.model.GeneralAdvertisement;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrderWithProductNameDTO;
import com.example.aplikacjakurierska.retrofit.model.Product;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.ViewHolder> {

    private List<GeneralAdvertisement> generalAdvertisements;
   private AdvertisementAdapter.OnStudyListener monStudyListener ;

    TextView date;
    TextView advert;
    private boolean isEditable;
    public AdvertisementAdapter(List<GeneralAdvertisement> generalAdvertisements,AdvertisementAdapter.OnStudyListener monStudyListener,boolean isEditable) {
        this.monStudyListener = monStudyListener;
        this.generalAdvertisements = generalAdvertisements;
        this.isEditable = isEditable;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advert_list,parent,false);
        return new ViewHolder(view,monStudyListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
GeneralAdvertisement generalAdvertisement = generalAdvertisements.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDate = dateFormat.format(generalAdvertisement.getUpdatedAt());
        holder.date.setText(formattedDate);
        holder.advert.setText(generalAdvertisement.getAdvertisement());
    }

    @Override
    public int getItemCount() {
        System.out.println("Elementy listy :"+"  "+generalAdvertisements.size());
        return generalAdvertisements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        TextView date;
        AdvertisementAdapter.OnStudyListener onStudyListener ;
        TextView advert;
        public ViewHolder(@NonNull View itemView,AdvertisementAdapter.OnStudyListener onStudyListener) {
            super(itemView);
            this.onStudyListener = onStudyListener;
            date = itemView.findViewById(R.id.date);
            advert = itemView.findViewById(R.id.advert);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (isEditable){
            onStudyListener.onStudyClick(getBindingAdapterPosition(),generalAdvertisements.get(getBindingAdapterPosition()).getId());

        }
        }

        @Override
        public boolean onLongClick(View view) {
            if (isEditable){
            onStudyListener.onStudyLongClick(getBindingAdapterPosition(),generalAdvertisements.get(getBindingAdapterPosition()).getId());
            }
            return true;
        }
    }

    public interface OnStudyListener{
        void onStudyClick(int position,long id);
        void onStudyLongClick(int position,long id);
    }

    public void updateData(List<GeneralAdvertisement> newList) {
        generalAdvertisements.clear();
        generalAdvertisements.addAll(newList);
        notifyDataSetChanged();
    }
}

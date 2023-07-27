package com.example.aplikacjakurierska.ActivityCustomer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.model.GeneralAdvertisement;
import com.example.aplikacjakurierska.retrofit.model.Product;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.ViewHolder> {
    private List<GeneralAdvertisement> generalAdvertisements;

    TextView date;
    TextView advert;

    public AdvertisementAdapter(List<GeneralAdvertisement> generalAdvertisements) {
        this.generalAdvertisements = generalAdvertisements;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advert_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
GeneralAdvertisement generalAdvertisement = generalAdvertisements.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDate = dateFormat.format(generalAdvertisement.getDataOfAdding());
        holder.date.setText(formattedDate);
        holder.advert.setText(generalAdvertisement.getAdvertisement());
    }

    @Override
    public int getItemCount() {
        System.out.println("Elementy listy :"+"  "+generalAdvertisements.size());
        return generalAdvertisements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView advert;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            advert = itemView.findViewById(R.id.advert);


        }

        }
    }

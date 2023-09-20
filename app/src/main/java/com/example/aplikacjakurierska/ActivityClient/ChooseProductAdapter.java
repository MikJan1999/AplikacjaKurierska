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
import com.example.aplikacjakurierska.retrofit.model.Product;
import java.util.ArrayList;
import java.util.List;
public class ChooseProductAdapter extends RecyclerView.Adapter<ChooseProductAdapter.ViewHolder> {


    public Context context;
    private static List<Product> productList = new ArrayList<>();
    long id;
    ChooseProductAdapter.OnStudyListener monStudylistener;
    public ChooseProductAdapter(List<Product> productList , ChooseProductAdapter.OnStudyListener monStudylistener)
    {
        this.monStudylistener = monStudylistener;
        this.productList = productList;
    }
    @Override
    public ChooseProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_product_list,parent,false);
        return new ViewHolder(view,monStudylistener);}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
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
        TextView productName, productPrice, productDescription,wordCart;
        ChooseProductAdapter.OnStudyListener onStudyListener ;
        LinearLayout mainContainersLayout;
        public ViewHolder(@NonNull View itemView, ChooseProductAdapter.OnStudyListener onStudyListener) {
            super(itemView);
            productName = itemView.findViewById(R.id.productNameChoose);
            productPrice = itemView.findViewById(R.id.productPriceChoose);
            productDescription = itemView.findViewById(R.id.productDescriptionChoose);
            this.onStudyListener = onStudyListener;
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);


            mainContainersLayout = itemView.findViewById(R.id.main_container);

        }

// W tej metodzie wywoływana jest metoda onStudyClick
// interfejsu OnStudyListener przekazując pozycję klikniętego elementu.
        @Override
        public void onClick(View view) {
                onStudyListener.onStudyClick(getBindingAdapterPosition());}
        @Override
        public boolean onLongClick(View view) {
                onStudyListener.onStudyLongClick(getBindingAdapterPosition(), productList.get(getBindingAdapterPosition()).getId());
            return true;}}

    public interface OnStudyListener{
        void onStudyClick(int position);
        void onStudyLongClick(int position,long id);

    }}


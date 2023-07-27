package com.example.aplikacjakurierska.ActivityCustomer;
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

    private static List<Product> productList = new ArrayList<>();

    private ProductCustomerAdapter.OnStudyListener monStudylistener;
    //konstruktor przyjmuje dwa elementy testList - przekazywana do adapptera aby dostarczyć dane, context - do różnych operacji takich jak tworzenie widoku


public ProductCustomerAdapter(List<Product> productList , ProductCustomerAdapter.OnStudyListener monStudylistener)
{
    this.monStudylistener = monStudylistener;
    this.productList = productList;

    }
//    public void updateItem(int position, Product product) {
//        if (position >= 0 && position < productList.size()) {
//            productList.set(position, product);
//            notifyItemChanged(position);
//        }
//    }
    @Override
    //wywoływana gdy recyclerview potrzebuje nowego widoku listy
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_customer_adapter_list,parent,false);
        return new ViewHolder(view,monStudylistener);}

//jest wywoływana gdy potrzebuje powiązać dane z konkretnym widokiem VievHolder w metodzie onCreateVievHolder
    //dane pobierane są z listy produktów i ustawiane na odpowiednich elementach interfejsu np textview
   //czyli to co wyświetla się na widoku to inicjalizuje się tutaj

    @Override
    public void onBindViewHolder(ProductCustomerAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(product.getCreatedAt());
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


// Klasa wewn VievHolder pełni rolę adaptera, który łączy dane z listy produktów
// z elementami interfejsu użytkownika
// w widoku RecyclerView. Dzięki temu możesz wyświetlać listę produktów
// w sposób dynamiczny i dostosowywać wygląd każdego elementu listy na
// podstawie danych z listy.



    public static class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {


        TextView productName, productPrice, productDescription, date;
        ProductCustomerAdapter.OnStudyListener
                onStudyListener ;
LinearLayout mainContainersLayout;
        public ViewHolder(@NonNull View itemView,ProductCustomerAdapter.OnStudyListener onStudyListener) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productDescription = itemView.findViewById(R.id.productDescription);
            this.onStudyListener = onStudyListener;

            itemView.setOnClickListener(this);

            mainContainersLayout = itemView.findViewById(R.id.main_container);

        }


        @Override
        public void onClick(View view) {
            onStudyListener.onStudyClick(getBindingAdapterPosition());
        }
    }
    public interface OnStudyListener{
        void onStudyClick(int position);
    }

public List<Product> setProductList(List<Product> productList){
    return productList;
}

}








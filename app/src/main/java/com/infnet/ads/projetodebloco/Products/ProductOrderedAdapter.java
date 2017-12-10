package com.infnet.ads.projetodebloco.Products;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.infnet.ads.projetodebloco.R;

public class ProductOrderedAdapter extends RecyclerView.Adapter<ProductOrderedViewHolder> {

    List<ProductOrdered> productsOrdered;
    Context context;

    public ProductOrderedAdapter (List<ProductOrdered> productsOrdered, Context context) {
        this.productsOrdered = productsOrdered;
        this.context = context;
    }


    @Override
    public ProductOrderedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_ordered_list, parent, false);
        return new ProductOrderedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductOrderedViewHolder viewHolder, int position) {
        ProductOrderedViewHolder holder = viewHolder;
        ProductOrdered productOrdered = productsOrdered.get(position);

        holder.nameCart.setText(productOrdered.nameOrdered);
        holder.descriptionCart.setText(productOrdered.descriptionOrdered);
        holder.priceCart.setText("Valor: R$" + productOrdered.priceOrdered + ",00");
        holder.addCart.setText("Adicionais: " + productOrdered.addOrdered);
        holder.cornerCart.setText("Borda: " + productOrdered.cornerOrdered);
        holder.pastaCart.setText("Massa: " + productOrdered.pastaOrdered);
        holder.psCart.setText("Observações: " + productOrdered.psOrdered);
    }

    @Override
    public int getItemCount() {
        if (productsOrdered!=null)return productsOrdered.size();
    else return 0;
    }
}






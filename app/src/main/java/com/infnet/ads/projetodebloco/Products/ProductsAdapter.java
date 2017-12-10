package com.infnet.ads.projetodebloco.Products;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.infnet.ads.projetodebloco.R;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

    private List<Product> productsList;
    private Context context;
    private OnItemClick onClickItem;

    public ProductsAdapter(List<Product> products, Context context) {
        this.productsList = products;
        this.context = context;
    }

    public void setOnClick(OnItemClick onClickItem) {
        this.onClickItem = onClickItem;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_card, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, final int position) {
        final Product product = productsList.get(position);
        holder.name.setText(product.getName());
        holder.preco.setText("R$: " + product.getPrice() + ",00");
        holder.tamanho.setText(product.getProductType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }
}
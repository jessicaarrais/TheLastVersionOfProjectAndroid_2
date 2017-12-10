package com.infnet.ads.projetodebloco.Products;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.infnet.ads.projetodebloco.R;

public class ProductOrderedViewHolder extends RecyclerView.ViewHolder {

    TextView nameCart;
    TextView descriptionCart;
    TextView priceCart;
    TextView addCart;
    TextView cornerCart;
    TextView pastaCart;
    TextView psCart;

    public ProductOrderedViewHolder(View view) {
        super(view);

        nameCart = view.findViewById(R.id.name_cart);
        descriptionCart = view.findViewById(R.id.description_cart);
        priceCart = view.findViewById(R.id.price_cart);
        addCart = view.findViewById(R.id.add_cart);
        cornerCart = view.findViewById(R.id.corner_cart);
        pastaCart = view.findViewById(R.id.pasta_cart);
        psCart = view.findViewById(R.id.ps_cart);

    }
}

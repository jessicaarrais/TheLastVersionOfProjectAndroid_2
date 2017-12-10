package com.infnet.ads.projetodebloco.Products;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.infnet.ads.projetodebloco.R;

public class ProductsViewHolder  extends RecyclerView.ViewHolder {

    TextView name;
    TextView preco;
    TextView tamanho;

    public ProductsViewHolder(View view) {
        super(view);

        name = view.findViewById(R.id.name_pizza);
        preco = view.findViewById(R.id.preco);
        tamanho = view.findViewById(R.id.tamanho);
    }
}


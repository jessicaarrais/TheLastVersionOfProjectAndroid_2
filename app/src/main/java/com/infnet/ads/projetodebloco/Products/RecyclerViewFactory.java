package com.infnet.ads.projetodebloco.Products;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import com.infnet.ads.projetodebloco.Activities.ProductActivity;
import com.infnet.ads.projetodebloco.DataBase.PizzaDAO;

/**
 * Created by renan on 01/12/2017.
 */
public class RecyclerViewFactory implements OnItemClick{

    private ArrayList products;
    private ProductsAdapter adapter;
    private PizzaDAO pizzaDAO;
    private Context context;
    private String category;
    private String subCategory;

    public RecyclerViewFactory(Context context, RecyclerView recyclerView, String category, String subCategory) {

        RecyclerView.LayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        products = new ArrayList<>();

        pizzaDAO = new PizzaDAO(context);

        this.category = category;

        this.subCategory = subCategory;

        this.context = context;

        listProducts();

        adapter = new ProductsAdapter(products, context);

        adapter.setOnClick(this);

        recyclerView.setAdapter(adapter);
    }

    private void listProducts() {
        Cursor cursor = pizzaDAO.getPizzaCursor();

        for (int i = 0; i < cursor.getCount(); i++) {

            if (cursor.getString(4).equals(category) &&
                    cursor.getString(5).equals(subCategory)) {

                Product product = new Product();

                product.setName(cursor.getString(1));

                product.setPrice(cursor.getString(2));

                product.setDescription(cursor.getString(3));

                product.setProductType(cursor.getString(4));

                product.setProductSubType(cursor.getString(5));

                products.add(product);

            }

            cursor.moveToNext();
        }

        cursor.close();


    }


    @Override
    public void onItemClick(int position) {

        Intent productActivity = new Intent(context, ProductActivity.class);

        productActivity.putExtra("product", (Product) products.get(position));

        context.startActivity(productActivity);
    }
}

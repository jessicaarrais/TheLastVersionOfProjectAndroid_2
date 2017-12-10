package com.infnet.ads.projetodebloco.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.infnet.ads.projetodebloco.Manager.AdmClientList;
import com.infnet.ads.projetodebloco.Products.ProductOrdered;
import com.infnet.ads.projetodebloco.Products.ProductOrderedAdapter;
import com.infnet.ads.projetodebloco.R;

public class CartActivity extends AppCompatActivity {

    static HashSet<String> itemsSet;
    static HashSet<String> totalSet;

    SharedPreferences settings;
    SharedPreferences.Editor editor;
    public static final String PREFS_NAME = "CartStuff";

    // Code stuff
    List<ProductOrdered> productsOrderedList;
    public static boolean hasItemCart;
    public static int items;
    public static int total;
    public static ArrayList<ProductOrdered> productsOrdered;

    // View stuff
    TextView quantityCart;
    TextView totalCart;
    RecyclerView recyclerView;
    Button moreCart;
    Button boughtCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        itemsSet = new HashSet<>();
        totalSet = new HashSet<>();

        settings = getSharedPreferences(PREFS_NAME,0);

        quantityCart = findViewById(R.id.quantity_cart);
        totalCart = findViewById(R.id.total_cart);
        recyclerView = findViewById(R.id.recycler_product_ordered);
        moreCart = findViewById(R.id.more_cart);
        boughtCart = findViewById(R.id.bought_cart);

        quantityCart.setText(String.valueOf(CartActivity.items));
        totalCart.setText(String.valueOf(CartActivity.total) + ",00");

        Intent it = getIntent();
        productsOrderedList = (List<ProductOrdered>) it.getSerializableExtra("confirmation");


        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(new ProductOrderedAdapter(productsOrderedList, this));

        moreCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });


        boughtCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartActivity.hasItemCart = false;

                CartActivity.itemsSet.add(String.valueOf(items));
                CartActivity.totalSet.add(String.valueOf(total));

                editor = settings.edit();
                editor.putStringSet("itemsSet",itemsSet);
                editor.putStringSet("totalSet",totalSet);
                editor.commit();


                CartActivity.items = 0;
                CartActivity.total = 0;

                Intent finishedActivity = new Intent(getApplicationContext(), FinishedActivity.class);
                startActivity(finishedActivity);
            }
        });
    }
}


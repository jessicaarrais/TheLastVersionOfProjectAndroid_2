package com.infnet.ads.projetodebloco.Manager;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.infnet.ads.projetodebloco.Activities.ProductActivity;
import com.infnet.ads.projetodebloco.Products.Product;
import com.infnet.ads.projetodebloco.Products.ProductOrdered;
import com.infnet.ads.projetodebloco.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.infnet.ads.projetodebloco.Activities.ProductActivity.productOrderedList;

/**
 * Created by renan on 08/12/2017.
 */

public class AdmClientItemList extends AppCompatActivity {
    private ListView list;
    public ArrayList<Map<String, Object>> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_client_item_list);

        list = findViewById(R.id.my_list_view_item_adm);

        String[] from = {"name", "description", "add", "corner", "pasta"};

        int[] to = {R.id.nameOrdered, R.id.descriptionOrdered, R.id.addOrdered, R.id.cornerOrdered, R.id.pastaOrdered};

        SimpleAdapter adapter = new SimpleAdapter(this.getApplicationContext(), listingClientsInfo(), R.layout.product_ordered_list_item_model, from, to);

        list.setAdapter(adapter);

    }

    private List<Map<String, Object>> listingClientsInfo() {
        products = new ArrayList<>();

        for (ProductOrdered productOrdered : productOrderedList) {
            Map<String, Object> item = new HashMap<>();

            item.put("name", "Nome da pizza: " + productOrdered.nameOrdered);
            item.put("description", "Descrição: " + productOrdered.descriptionOrdered);
            item.put("add", "Aditivos: " + productOrdered.addOrdered);
            item.put("corner", "Borda: " + productOrdered.cornerOrdered);
            item.put("pasta", "Massa: " + productOrdered.pastaOrdered);

            products.add(item);
        }
        return products;
    }
    }


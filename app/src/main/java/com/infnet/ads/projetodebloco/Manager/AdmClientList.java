package com.infnet.ads.projetodebloco.Manager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.infnet.ads.projetodebloco.Activities.CartActivity;
import com.infnet.ads.projetodebloco.DataBase.PizzaDAO;
import com.infnet.ads.projetodebloco.Products.OnItemClick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.infnet.ads.projetodebloco.Products.ProductOrdered;
import com.infnet.ads.projetodebloco.Products.ProductsAdapter;
import com.infnet.ads.projetodebloco.R;

public class AdmClientList extends AppCompatActivity {

    private ListView list;
    public static final String PREFS_NAME1 = "Preferences";
    public static final String PREFS_NAME2 = "CartStuff";
    SharedPreferences settings;
    public ArrayList<Integer> total = new ArrayList<>();
    public ArrayList<Integer> items = new ArrayList<>();
    public ArrayList<Map<String, Object>> order;
    private String clientEmail;
    Set<String> itemsSet;
    Set<String> totalSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_client_list);

        settings = getSharedPreferences(PREFS_NAME1,0);

        clientEmail = settings.getString("PrefEmail","");

        settings = getSharedPreferences(PREFS_NAME2,0);

        itemsSet = settings.getStringSet("itemsSet",new HashSet<String>());
        totalSet = settings.getStringSet("totalSet",new HashSet<String>());

        list = findViewById(R.id.my_list_view_adm);

        String[] from = {"name", "numberOrders", "totalValue", "addressCombined"};

        int[] to = {R.id.nameOfTheClient, R.id.numberOfOrders, R.id.valueOfTheBought, R.id.addressOfTheClient};

        SimpleAdapter adapter = new SimpleAdapter(this.getApplicationContext(), listingClientsInfo(), R.layout.product_ordered_list_model, from, to);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),AdmClientItemList.class);
                startActivity(intent);

            }
        });



        /*
        -> A exibição de todos os produtos que o cliente inseriu na Compra deve ser exibida (produto por produto) em um listview
        após a seleção de qual Compra o adm clicar.

        ArrayList<ProductOrdered> products = CartActivity.productsOrdered;
        listView = findViewById(R.id.my_list_view_adm);


        for ( ProductOrdered product : products ){

        }

        */
    }

    private List<Map<String, Object>> listingClientsInfo() {

        PizzaDAO clientSearch = new PizzaDAO(getApplicationContext());
        Cursor cursor = clientSearch.getClientCursor();

        order = new ArrayList<>();

        for (int i = 0; i < cursor.getCount(); i++) {
            Map<String, Object> item = new HashMap<>();

            String nameDb = cursor.getString(1);
            String emailDb = cursor.getString(2);
            String streetDb = cursor.getString(3);
            String cityDb = cursor.getString(4);
            String uf = cursor.getString(5);

            if (clientEmail.equals(emailDb)){ // Email travado (Informações do cliente travadas)

                Iterator it = itemsSet.iterator();
                Iterator it2 = totalSet.iterator();

                while(it.hasNext()) {
                    item.put("name", "Nome: "+nameDb);
                    item.put("numberOrders", "Numero de pedidos: "+(String)it.next());
                    item.put("totalValue", "Valor total: " +(String) it2.next());
                    item.put("addressCombined", "Rua: " + streetDb + ", cidade: " + cityDb + " (" + uf + ").");

                    order.add(item);
                }
            }


            cursor.moveToNext(); // Move to next line

        }
        cursor.close();
        return order;
    }

}


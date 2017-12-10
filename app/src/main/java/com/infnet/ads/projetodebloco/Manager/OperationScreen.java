package com.infnet.ads.projetodebloco.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.infnet.ads.projetodebloco.DataBase.PizzaDAO;
import com.infnet.ads.projetodebloco.Fragments.InsertFragment;
import com.infnet.ads.projetodebloco.Fragments.ListFragment;
import com.infnet.ads.projetodebloco.Fragments.RemoveFragment;
import com.infnet.ads.projetodebloco.Fragments.UpdateFragment;
import com.infnet.ads.projetodebloco.Products.Product;
import com.infnet.ads.projetodebloco.R;

public class OperationScreen extends AppCompatActivity {

    private String id;
    private int selectedItem;
    InsertFragment insertFragment;
    RemoveFragment removeFragment;
    UpdateFragment updateFragment;
    ListFragment listFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_operation);

        insertFragment = new InsertFragment();
        removeFragment = new RemoveFragment();
        updateFragment = new UpdateFragment();
        listFragment = new ListFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frameContainer2, listFragment).commit();

        selectedItem = findViewById(R.id.btnInsert).getId();
        getSupportFragmentManager().beginTransaction().add(R.id.frameContainer, insertFragment).commit();

        if (getIntent().hasExtra("Id")){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, updateFragment).commit();
            selectedItem = findViewById(R.id.btnUpdate).getId();

            id = getIntent().getStringExtra("Id");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.client_issues, menu);

        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.clientIssues:
               Intent intent = new Intent(this, AdmClientList.class);
               startActivity(intent);

                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void insert(View view) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, insertFragment).commit();

        selectedItem = view.getId();
    }

    public void remove(View view) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, removeFragment).commit();

        selectedItem = view.getId();
    }

    public void update(View view) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, updateFragment).commit();

        selectedItem = view.getId();
    }

    public void doOperation(View view) {
        PizzaDAO dao = new PizzaDAO(getBaseContext());

        switch (selectedItem) {
            case R.id.btnInsert:

                Product pizzaToBeInserted = new Product();

                pizzaToBeInserted.name = insertFragment.getName();
                pizzaToBeInserted.price = insertFragment.getPrice();
                pizzaToBeInserted.description = insertFragment.getDescription();
                pizzaToBeInserted.productType = insertFragment.getProductType();
                pizzaToBeInserted.productSubType = insertFragment.getProductSubType();

                long itemsInserted = dao.save(pizzaToBeInserted);

                if (itemsInserted > 0) {
                    Toast.makeText(this, R.string.insert_success, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, this.getClass());
                    finish();
                    startActivity(intent);

                } else Toast.makeText(this, R.string.insert_unsuccessful, Toast.LENGTH_LONG).show();

                break;

            case R.id.btnRemove:

                Product pizzaToBeRemoved = new Product();

                pizzaToBeRemoved.id = removeFragment.getID();

                int itemsRemoved = dao.remove(pizzaToBeRemoved);

                if (itemsRemoved > 0) {
                    Toast.makeText(this, R.string.remove_success, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, this.getClass());
                    finish();
                    startActivity(intent);

                } else
                    Toast.makeText(this, R.string.updateOrRemove_unsuccessful, Toast.LENGTH_LONG).show();

                break;

            case R.id.btnUpdate:

                Product pizzaToBeUpdated = new Product();

                String id = updateFragment.getID();
                pizzaToBeUpdated.name = updateFragment.getName();
                pizzaToBeUpdated.price = updateFragment.getPrice();
                pizzaToBeUpdated.description = updateFragment.getDescription();
                pizzaToBeUpdated.productType = updateFragment.getProductType();
                pizzaToBeUpdated.productSubType = updateFragment.getProductSubType();

                int itemsUpdated = dao.update(pizzaToBeUpdated, id);

                if (itemsUpdated > 0) {
                    Toast.makeText(this, R.string.update_success, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, this.getClass());
                    finish();
                    startActivity(intent);

                } else
                    Toast.makeText(this, R.string.updateOrRemove_unsuccessful, Toast.LENGTH_LONG).show();


                break;
        }

    }

    public String getId() { return id; }

    @Override
    public void onBackPressed() {
        finish();
    }
}

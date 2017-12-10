package com.infnet.ads.projetodebloco.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.infnet.ads.projetodebloco.DataBase.PizzaDAO;
import com.infnet.ads.projetodebloco.Manager.OperationScreen;
import com.infnet.ads.projetodebloco.Products.Product;
import com.infnet.ads.projetodebloco.R;

public class ListFragment extends Fragment implements DialogInterface.OnClickListener {

    public ListView list;
    public ArrayList<Map<String, Object>> products;
    private AlertDialog alertDialog;
    private AlertDialog dialogConfirmacao;
    private int produtoSelecionado;
    private PizzaDAO pizzaDAO;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.adm_product_list, container, false);

        list = view.findViewById(R.id.productsList);

        String[] from = {"image", "name", "value", "description", "identity", "category", "subCategory"};

        int[] to = {R.id.photoItem, R.id.nameItem, R.id.valueItem, R.id.descriptionItem, R.id.identityItem, R.id.categoryItem, R.id.subcategoryItem};

        SimpleAdapter adapter = new SimpleAdapter(getContext(), listingProducts(), R.layout.product_list_model, from, to);

        list.setAdapter(adapter);

        this.alertDialog = criarAlertDialog();
        this.dialogConfirmacao = criaDialogConfirmacao();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                produtoSelecionado = position;
                alertDialog.show();
            }
        });


        return view;

    }
    private List<Map<String, Object>> listingProducts() {

        pizzaDAO = new PizzaDAO(getContext());
        Cursor cursor = pizzaDAO.getPizzaCursor();

        products = new ArrayList<>();

        for (int i = 0; i < cursor.getCount(); i++) {

            Map<String, Object> item = new HashMap<>();

            String id = String.valueOf(cursor.getInt(0));
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String description = cursor.getString(3);
            String category = cursor.getString(4);
            String subCategory = cursor.getString(5);

switch (category) {
    case "Pizza grande":case "Pizza média":case "Pizza pequena":
        item.put("image", R.drawable.pizza);
        break;
    case "Bebida":
        item.put("image", R.drawable.bebida);
        break;
    case "Sorvete":
        item.put("image", R.drawable.sorvete);
        break;
    default:
        break;
}
            item.put("name", "Nome: " + "Pizza de " +  name);

            item.put("value", "Preço: " + "R$" +  price);

            item.put("description", "Descrição: " + description);

            item.put("identity", "ID: " + id);

            item.put("category", "Categoria: " +category);

            item.put("subCategory", "Sub-categoria: " +subCategory);

            products.add(item);
            cursor.moveToNext(); // Move to next line
        }

        cursor.close();
        return products;
    }

    private AlertDialog criarAlertDialog() {
        final CharSequence[] items = {
                "remover",
                "atualizar"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Operações");
        builder.setItems(items, this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int item) {
        String msgId = (String) products.get(produtoSelecionado).get("identity");
        String id = msgId.substring(4);

        switch (item) {

            case 0:
                dialogConfirmacao.show();
                break;

            case AlertDialog.BUTTON_POSITIVE:
                Product pizzaToBeRemoved = new Product();
                pizzaToBeRemoved.id = id;
                pizzaDAO.remove(pizzaToBeRemoved);
                Intent intent = new Intent(this.getContext(), OperationScreen.class);
                startActivity(intent);

                break;
            case AlertDialog.BUTTON_NEGATIVE:
                dialogConfirmacao.dismiss();

                break;

            case 1:

                Intent intent2 = new Intent(this.getContext(), OperationScreen.class);
                intent2.putExtra("Id", id);
                startActivity(intent2);
            default:
                break;

        }
    }

    private AlertDialog criaDialogConfirmacao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setMessage("Confirmação da exclusão do produto?");
        builder.setPositiveButton("sim", this);
        builder.setNegativeButton("não", this);
        return builder.create();
    }
}
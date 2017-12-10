package com.infnet.ads.projetodebloco.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.infnet.ads.projetodebloco.Entity.Client;
import com.infnet.ads.projetodebloco.Products.Product;

public class PizzaDAO { // Only this class should have access to the pizza database

    private final String TABLE_PIZZA = "pizza_table";
    private final String TABLE_CLIENT = "client_table";
    private DBGateway gw;
    private Cursor cursor;

    public PizzaDAO(Context ctx){
        gw = DBGateway.getInstance(ctx);
    }

    public long save(Product pizzaInstance){
        ContentValues cv = new ContentValues();

        cv.put("name", pizzaInstance.getName());
        cv.put("price", pizzaInstance.getPrice());
        cv.put("description", pizzaInstance.getDescription());
        cv.put("category", pizzaInstance.getProductType());
        cv.put("subCategory", pizzaInstance.getProductSubType());

        return gw.getDatabase().insert(TABLE_PIZZA,null,cv);
    }

    public long save(Client clientInstance){

        ContentValues cv = new ContentValues();

        cv.put("name", clientInstance.getUserName());
        cv.put("email", clientInstance.getEmail());
        cv.put("street", clientInstance.getStreet());
        cv.put("city", clientInstance.getCity());
        cv.put("uf", clientInstance.getUf());
        cv.put("phone", clientInstance.getPhone());
        cv.put("password", clientInstance.getPassword());

        return gw.getDatabase().insert(TABLE_CLIENT,null,cv);

        // The class which calls this method must store the returned value in the object ID (by using its own setId method)
    }


    public Cursor getPizzaCursor(){

        cursor = gw.getDatabase().rawQuery("SELECT * FROM pizza_table", null);

        cursor.moveToFirst();

        return cursor;
    }

    public Cursor getClientCursor(){

        cursor = gw.getDatabase().rawQuery("SELECT * FROM client_table", null);

        cursor.moveToFirst();

        return cursor;
    }


    public Integer remove(Product pizzaInstance){

        Integer id = gw.getDatabase().delete(TABLE_PIZZA,"id=?", new String[]{pizzaInstance.getId()});

        ContentValues cv = new ContentValues();  // Works but still not perfect
        cv.put("seq", 0);

        gw.getDatabase().update("sqlite_sequence",cv,"name=?",new String[]{TABLE_PIZZA});
        return id;
    }

    public Integer remove(Client clientInstance){

        Integer id = gw.getDatabase().delete(TABLE_CLIENT,"id=?", new String[]{String.valueOf(clientInstance.getClientID())});

        return id;
    }

    public int update(Product pizzaInstance, String id){

        ContentValues cv = new ContentValues();
        cv.put("name", pizzaInstance.getName());
        cv.put("price", pizzaInstance.getPrice());
        cv.put("description", pizzaInstance.getDescription());
        cv.put("category", pizzaInstance.getProductType());
        cv.put("subCategory", pizzaInstance.getProductSubType());

        return gw.getDatabase().update(TABLE_PIZZA,cv,"id=?",new String[]{id});

    }

    public int update(Client clientInstance){

        ContentValues cv = new ContentValues();

        cv.put("name", clientInstance.getUserName());
        cv.put("email", clientInstance.getEmail());
        cv.put("street", clientInstance.getStreet());
        cv.put("city", clientInstance.getCity());
        cv.put("uf", clientInstance.getUf());
        cv.put("phone", clientInstance.getPhone());
        cv.put("password", clientInstance.getPassword());

        return gw.getDatabase().update(TABLE_CLIENT,cv,"id=?",new String[]{String.valueOf(clientInstance.getClientID())});

    }

    public void destroyTable(){ // Implement latter
    }
}
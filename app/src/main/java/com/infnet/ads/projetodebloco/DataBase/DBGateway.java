package com.infnet.ads.projetodebloco.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {

    private static DBGateway gw;
    private SQLiteDatabase db;

    private DBGateway(Context ctx){
        DataBaseHelper helper = new DataBaseHelper(ctx);
        db = helper.getWritableDatabase();

    }

    public static DBGateway getInstance(Context ctx){  // Singleton :-) (Not secure in case of threads, but i'm only copying this anyway...)
        if(gw == null)
            gw = new DBGateway(ctx);
        return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }
}
package com.infnet.ads.projetodebloco.Activities;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.infnet.ads.projetodebloco.DataBase.PizzaDAO;
import com.infnet.ads.projetodebloco.R;

public class ClientSettingsActivity extends AppCompatActivity {

    private PizzaDAO pizzaDAO;
    private Cursor cursor;
    private SharedPreferences settings;
    private String clientEmail;
    private TextView name;
    private TextView email;
    private TextView street;
    private TextView city;
    private TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_settings);

        pizzaDAO = new PizzaDAO(this);

        cursor = pizzaDAO.getClientCursor();

        settings = getSharedPreferences("Preferences",0);

        clientEmail = settings.getString("PrefEmail","");

        name = findViewById(R.id.user_settings);
        email = findViewById(R.id.email_settings);
        street = findViewById(R.id.street_settings);
        city = findViewById(R.id.city_settings);
        number = findViewById(R.id.phone_settings);

        for (int i = 0; i<cursor.getCount(); i++){

            String nameDb = cursor.getString(1);
            String emailDb = cursor.getString(2);
            String streetDb = cursor.getString(3);
            String cityDb = cursor.getString(4);
            String numberDb = cursor.getString(6);

            if (clientEmail.equals(emailDb)){

                name.setText(nameDb);
                email.setText(emailDb);
                street.setText(streetDb);
                city.setText(cityDb);
                number.setText(numberDb);

            }
            cursor.moveToNext();
        }
        cursor.close();
    }


}

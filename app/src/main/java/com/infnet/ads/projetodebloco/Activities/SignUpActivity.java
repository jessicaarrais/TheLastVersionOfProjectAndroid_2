package com.infnet.ads.projetodebloco.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.infnet.ads.projetodebloco.DataBase.PizzaDAO;
import com.infnet.ads.projetodebloco.Entity.Client;
import com.infnet.ads.projetodebloco.R;

public class SignUpActivity extends AppCompatActivity {

    TextView message;
    EditText userName;
    EditText email;
    EditText street;
    EditText city;
    Spinner uf;
    EditText phone;
    EditText password;
    Button create;
    private boolean hasError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        message = findViewById(R.id.email_exist);
        userName = findViewById(R.id.user);
        email = findViewById(R.id.email);
        street = findViewById(R.id.street);
        city = findViewById(R.id.city);
        uf = findViewById(R.id.uf);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.pass);
        create = findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                containsErrors();

                if (!hasError) {
                    PizzaDAO clientDAO = new PizzaDAO(getApplicationContext());
                    Cursor cursor = clientDAO.getClientCursor();

                    cursor.moveToFirst();
                    for (int i = 0; i < cursor.getCount(); i++) {
                        if (cursor.getString(2).equals(email.getText().toString())) {
                            message.setVisibility(View.VISIBLE);
                            hasError = true;
                            break;
                        } else {
                            cursor.moveToNext();
                        }
                    }
                    cursor.close();

                    if (!hasError) {
                        Client client = new Client();

                        client.setUserName(userName.getText().toString());
                        client.setEmail(email.getText().toString());
                        client.setStreet(street.getText().toString());
                        client.setCity(city.getText().toString());
                        client.setUf(ufSpinner(uf));
                        client.setPhone(phone.getText().toString());
                        client.setPassword(password.getText().toString());
                        client.setClientID((int)clientDAO.save(client));

                        LogInActivity.logInOn = true;

                        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainActivity);
                    }
                }
                hasError = false;
            }
        });
    }

    private void containsErrors() {
        if (userName.getText().toString().length() == 0) {
            userName.setError("Campo obrigatório");
            hasError = true;
        }
        if (email.getText().toString().length() == 0) {
            email.setError("Campo obrigatório");
            hasError = true;
        }
        if (street.getText().toString().length() == 0) {
            street.setError("Campo obrigatório");
            hasError = true;
        }
        if (city.getText().toString().length() == 0) {
            city.setError("Campo obrigatório");
            hasError = true;
        }
        if (phone.getText().toString().length() == 0) {
            phone.setError("Campo obrigatório");
            hasError = true;
        }
        if (password.getText().toString().length() == 0) {
            password.setError("Campo obrigatório");
            hasError = true;
        }

        if (!isValidEmail(email.getText().toString())) {
            email.setError("E-mail inválido.");
            hasError = true;
        }
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String ufSpinner(Spinner spinner) {
        return spinner.getSelectedItem().toString();
    }
}
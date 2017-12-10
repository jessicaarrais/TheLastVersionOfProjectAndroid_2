package com.infnet.ads.projetodebloco.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.infnet.ads.projetodebloco.DataBase.PizzaDAO;
import com.infnet.ads.projetodebloco.Manager.OperationScreen;
import com.infnet.ads.projetodebloco.R;

public class LogInActivity extends AppCompatActivity {


    public static final String PREFS_NAME = "Preferences";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    public static boolean logInOn;
    TextView message;
    EditText email;
    EditText password;
    Button logIn;
    TextView recover;
    TextView signUp;
    TextView enter;
    TextView db;
    private boolean hasError;

    public LogInActivity() {}

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        message = findViewById(R.id.invalid);
        email = findViewById(R.id.email_log);
        password = findViewById(R.id.password);
        logIn = findViewById(R.id.login);
        recover = findViewById(R.id.recover);
        signUp = findViewById(R.id.signUp);
        enter = findViewById(R.id.enter);
        db = findViewById(R.id.db);

        settings = getSharedPreferences(PREFS_NAME,0);
        email.setText(settings.getString("PrefEmail",""));

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().length() == 0 | password.getText().toString().length() == 0) {
                    message.setVisibility(View.VISIBLE);
                    hasError = true;
                }


                PizzaDAO clientSearch = new PizzaDAO(getApplicationContext());
                Cursor cursor = clientSearch.getClientCursor();

                if (cursor.getCount() <= 0) { // this is works
                    message.setVisibility(View.VISIBLE);
                    hasError = true;
                }

                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    String emailDb = cursor.getString(2);
                    String passwordDb = cursor.getString(7);

                    hasError = !(email.getText().toString().equals(emailDb) && password.getText().toString().equals(passwordDb));
                    if (!hasError) {
                        logInOn = true;
                        break;
                    } else {
                        cursor.moveToNext(); // Move to next line
                    }
                }
                cursor.close();

                if (hasError) {
                    message.setVisibility(View.VISIBLE);
                }

                if(!hasError) {

                    editor = settings.edit();
                    editor.putString("PrefEmail", email.getText().toString());
                    editor.commit();

                    Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainActivity);
                }
                hasError = false;
            }
        });

        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recoverActivity = new Intent(getApplicationContext(), RecoverFirstStepActivity.class);
                startActivity(recoverActivity);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpActivity = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signUpActivity);
            }
        });

        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dataBase = new Intent(getApplicationContext(), OperationScreen.class);
                startActivity(dataBase);
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }
}
package com.infnet.ads.projetodebloco.Activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.infnet.ads.projetodebloco.DataBase.PizzaDAO;
import com.infnet.ads.projetodebloco.Entity.Client;
import com.infnet.ads.projetodebloco.R;

public class RecoverFirstStepActivity extends AppCompatActivity {

    private static final int uniqueId = 45612;
    NotificationCompat.Builder notification;
    EditText userRecover;
    Button sendPassword;
    TextView message;
    private boolean hasError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_first_step);

        userRecover = findViewById(R.id.user_recover);
        sendPassword = findViewById(R.id.send_password);
        message = findViewById(R.id.invalid_user);

        notification = new NotificationCompat.Builder(getApplicationContext());
        notification.setAutoCancel(true);

        sendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRecover.getText().toString(); // buscar no banco de dados

//                if(nao encontrar no banco de dados) {
//
//                }

                if (userRecover.getText().toString().length() == 0) {
                    message.setVisibility(View.VISIBLE);
                    hasError = true;
                }

                if (!hasError) {
                    int n = (int)(Math.random() *100);

                    saveInData(n);

                    notification.setSmallIcon(R.drawable.logo_acasadapizzaestufada);
                    notification.setTicker("Nova senha definida");
                    notification.setWhen(System.currentTimeMillis());
                    notification.setContentTitle("Sua nova senha");
                    notification.setContentText(String.valueOf(n));

                    Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,
                            intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    notification.setContentIntent(pendingIntent);


                    //Builds notification and issues it (launches it)
                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueId, notification.build());

                }
                hasError = false;
            }
        });
    }

    public void saveInData(int x){
        PizzaDAO pizzaDAO = new PizzaDAO(this);
        Cursor cursor = pizzaDAO.getClientCursor(); //Cursor é só para ler

        for (int i = 0; i < cursor.getCount(); i++){
            String emailDb = cursor.getString(2);

            if (userRecover.getText().toString().equals(emailDb)){
                Client client = new Client();

                client.setClientID(Integer.valueOf(cursor.getString(0)));
                client.setUserName(cursor.getString(1));
                client.setEmail(emailDb);
                client.setStreet(cursor.getString(3));
                client.setCity(cursor.getString(4));
                client.setUf(cursor.getString(5));
                client.setPhone(cursor.getString(6));
                client.setPassword(String.valueOf(x));  // Só quero alterar a password

                pizzaDAO.update(client);

            } else {
                cursor.moveToNext();
            }
        }
    }
}
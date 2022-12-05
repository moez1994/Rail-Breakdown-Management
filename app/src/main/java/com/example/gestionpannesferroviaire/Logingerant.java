package com.example.gestionpannesferroviaire;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Logingerant extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logingerant);
        EditText lg =(EditText) findViewById(R.id.loging);
        EditText pg =(EditText) findViewById(R.id.passwordg);
        Button bg =(Button) findViewById(R.id.bg);
        Button bdg =(Button) findViewById(R.id.bdg);
        AlertDialog.Builder boite;
        boite = new AlertDialog.Builder(this);
        boite.setTitle("ALERT");

        boite.setMessage("error login and  password");
        boite.setPositiveButton("OK",new DialogInterface.OnClickListener()
        { public void onClick(DialogInterface dialog, int which) {
            lg.setText("");
            pg.setText("");
        }});
        String login_gerant="mohamed";
        String password_gerant="moez";
        bdg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                lg.setText("");
                pg.setText("");
            }

        });
        bg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (login_gerant.equals(lg.getText().toString()) && password_gerant.equals(pg.getText().toString())) {

                    Intent t= new Intent(Logingerant.this,Statistiquepanne.class);
                    t.putExtra("login_g",login_gerant);
                    t.putExtra("password_g",password_gerant);
                    startActivity(t);
                }else{
                    boite.show();


                }

            }

        });

    }
}

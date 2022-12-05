package com.example.gestionpannesferroviaire;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class Loginmaintenance extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginchefmaintenance);
        EditText lm =(EditText) findViewById(R.id.loginm);
        EditText pm =(EditText) findViewById(R.id.passwordm);
        Button bm =(Button) findViewById(R.id.bm);
        Button bdm =(Button) findViewById(R.id.bdm);
        AlertDialog.Builder boite;
        boite = new AlertDialog.Builder(this);
        boite.setTitle("ALERT");

        boite.setMessage("error login and  password");
        boite.setPositiveButton("OK",new DialogInterface.OnClickListener()
        { public void onClick(DialogInterface dialog, int which) {
            lm.setText("");
            pm.setText("");
        }});
        String login_chefm="ahmed";
        String password_chefm="123";
        bdm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                lm.setText("");
                pm.setText("");
            }

        });
        bm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (login_chefm.equals(lm.getText().toString()) && password_chefm.equals(pm.getText().toString())) {

                    Intent t= new Intent(Loginmaintenance.this,Interfacemaintenance.class);
                    t.putExtra("login_m",login_chefm);
                    startActivity(t);
                }else{
                    boite.show();


                }

            }

        });
    }
}


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

public class Logintechnicien   extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginechnicien);
        EditText lt =(EditText) findViewById(R.id.logint);
        EditText pt =(EditText) findViewById(R.id.passwordt);
        Button bt =(Button) findViewById(R.id.bt);
        Button bdt =(Button) findViewById(R.id.bdt);
        AlertDialog.Builder boite;
        boite = new AlertDialog.Builder(this);
        boite.setTitle("ALERT");

        boite.setMessage("error login and  password");
        boite.setPositiveButton("OK",new DialogInterface.OnClickListener()
        { public void onClick(DialogInterface dialog, int which) {
            lt.setText("");
            pt.setText("");
        }});
        String login_technicien="ali";
        String password_technicien="123";
        bdt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               lt.setText("");
               pt.setText("");
            }

        });
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (login_technicien.equals(lt.getText().toString()) && password_technicien.equals(pt.getText().toString())) {

                    Intent t= new Intent(Logintechnicien.this,MapsActivity.class);
                    t.putExtra("login_t",login_technicien);
                    startActivity(t);
                }else{
                    boite.show();


                }

            }

        });
    }
}


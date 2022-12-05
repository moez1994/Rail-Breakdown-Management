package com.example.gestionpannesferroviaire;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Button lg= (Button) findViewById(R.id.lg);
        Button lt= (Button) findViewById(R.id.lt);
        Button lcm=(Button) findViewById(R.id.lct);

        lg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent t = new Intent(Home.this, Logingerant.class);
                startActivity(t);
            }

        });
        lt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent t = new Intent(Home.this, Logintechnicien.class);
                startActivity(t);
            }

        });
        lcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent t = new Intent(Home.this,Loginmaintenance.class);
                startActivity(t);
            }
        });




    }
}

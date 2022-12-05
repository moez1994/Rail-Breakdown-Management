package com.example.gestionpannesferroviaire;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Interfacemaintenance extends AppCompatActivity {
    DB db1;
    SQLiteDatabase sqlitedb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfacemaintenance);
        TextView la = (TextView) findViewById(R.id.ilatitude);
        TextView lo = (TextView) findViewById(R.id.ilongitude);
        TextView da = (TextView) findViewById(R.id.idatereperation);
        db1 = new DB(this);
        Intent t = getIntent();
        Bundle pu = t.getExtras();
        String loginm = pu.getString("login_m");
        Toast.makeText(getApplicationContext(), " Salam alaikoum Chef Maintenance Ferroviaire  " + loginm, Toast.LENGTH_LONG).show();
        sqlitedb1 = db1.getReadableDatabase();
        Cursor c1 = sqlitedb1.rawQuery("SELECT id_m,Latitude,Longitude,DateREP FROM MAINTENANCE ", null);
        if (c1.moveToFirst()) {
            do {
                la.append(c1.getString(1));
                la.append("\n");la.append("\n");
                lo.append(c1.getString(2));
                lo.append("\n");lo.append("\n");
                da.append(c1.getString(3));
                da.append("\n");da.append("\n");


        } while (c1.moveToNext());

    }
        c1.close();
        db1.close();
    }
}

package com.example.gestionpannesferroviaire;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Statistiquepanne extends AppCompatActivity {
    DB db;
    SQLiteDatabase sqlitedb;
    String datecalcalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistiquepanne);
        TextView la=(TextView)findViewById(R.id.la);
        TextView lo=(TextView)findViewById(R.id.lo);
        TextView da=(TextView)findViewById(R.id.da);
        TextView ti=(TextView)findViewById(R.id.ti);
        EditText editlat=(EditText)findViewById(R.id.editLatitude);
        EditText editlong=(EditText)findViewById(R.id.editLongitude);
        CalendarView cal = (CalendarView) findViewById(R.id.calendar);
        Button be=(Button)findViewById(R.id.benvoyer);
        Button reset=(Button)findViewById(R.id.res);
        db = new DB(this);
        Intent t = getIntent();
        Bundle pu = t.getExtras();
        String loging = pu.getString("login_g");
        String passwordg = pu.getString("password_g");
        Toast.makeText(getApplicationContext(), " Salam alaikoum gérants " + passwordg+ " et " + loging  , Toast.LENGTH_LONG).show();

        sqlitedb = db.getReadableDatabase();
        Cursor c = sqlitedb.rawQuery("SELECT id,Latitude,Longitude,Date,time FROM INFORMATIONS ", null);
        if (c.moveToFirst()) {
            //String Latitude,Longitude,Date,time;
            do {

                la.append(c.getString(1));
                la.append("\n");

                lo.append(c.getString(2));
                lo.append("\n");

                da.append(c.getString(3));
                da.append("\n");

                ti.append(c.getString(4));
                ti.append("\n");
                /*
                Toast.makeText(getApplicationContext(), " id "+c.getString(0) +
                        "Latitude"+c.getString(1) +
                        "Longitude"+c.getString(2) +
                        "Date"+c.getString(3) +
                        "Time"+c.getString(4) , Toast.LENGTH_LONG).show();
                */

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        cal.setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override

                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {


                                String datecal
                                        = dayOfMonth + "-"
                                        + (month + 1) + "-" + year;


                                //la.setText(datecal);
                                datecalcalendar=datecal;
                            }
                        });


        la.setOnLongClickListener(new View.OnLongClickListener(){

            public boolean onLongClick(View v) {
                editlat.setText(la.getText());
                editlong.setText(lo.getText());
                return false;
            }
        });

        be.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("Latitude", editlat.getText().toString());
                contentValues.put("Longitude", editlong.getText().toString());
                contentValues.put("DateREP", datecalcalendar);
                sqlitedb = db.getWritableDatabase();
                Long recid = sqlitedb.insert("MAINTENANCE", null, contentValues);
                if (recid != null) {
                    Toast.makeText(Statistiquepanne.this, "successfully sent informations to Railway maintenance manager", Toast.LENGTH_SHORT).show();
                    editlat.setText("");
                    editlong.setText("");
                } else {
                    Toast.makeText(Statistiquepanne.this, "something wrong try again", Toast.LENGTH_SHORT).show();
                }
            }

        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editlat.setText("");
                editlong.setText("");
            }

        });

}}

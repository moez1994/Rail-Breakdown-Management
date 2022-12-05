package com.example.gestionpannesferroviaire;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gestionpannesferroviaire.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MapsActivity extends AppCompatActivity {
   SupportMapFragment supportMapFragment;
   FusedLocationProviderClient client;
   DB db;
   SQLiteDatabase sqlitedb;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
      StrictMode.setThreadPolicy(policy);
      super.onCreate(savedInstanceState);
      db = new DB(this);
      Intent t = getIntent();
      Bundle pu = t.getExtras();
      String logint = pu.getString("login_t");

      Toast.makeText(getApplicationContext(), " Salam alaikoum Technicien " + logint , Toast.LENGTH_LONG).show();

      setContentView(R.layout.activity_maps);
      //assign
      supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
      //location
      client = LocationServices.getFusedLocationProviderClient(this);
      //condition permisssion
      if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
              == PackageManager.PERMISSION_GRANTED) {
         //WHEN PERMESSION GRATED
         getCurrentLocation();
      }else{
         //permession denied
         ActivityCompat.requestPermissions(MapsActivity.this,
                 new String[]{Manifest.permission.ACCESS_FINE_LOCATION},45);
      }


   }





   private void getCurrentLocation() {
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         // TODO: Consider calling
         //    ActivityCompat#requestPermissions
         // here to request the missing permissions, and then overriding
         //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
         //                                          int[] grantResults)
         // to handle the case where the user grants the permission. See the documentation
         // for ActivityCompat#requestPermissions for more details.
         return;
      }
      Task<Location> task = client.getLastLocation();
      task.addOnSuccessListener(new OnSuccessListener<Location>() {
         @Override
         public void onSuccess(final Location location) {
            if(location != null){
               supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                  @Override
                  public void onMapReady(GoogleMap googleMap) {
                     LatLng latlng = new LatLng(location.getLatitude(),
                             location.getLongitude());
                     MarkerOptions options = new MarkerOptions().position(latlng).title("Here there is a rail malfunction");
                     //zoom map
                     googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10));
                     //add marker
                     googleMap.addMarker(options);
                     Toast.makeText(getApplicationContext(), "Latitude position is  " + location.getLatitude()+"Longitude position is   "+ location.getLongitude() , Toast.LENGTH_LONG).show();
                     String datev = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                     String timev = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                     ///////////////////////
                     ContentValues contentValues = new ContentValues();
                     contentValues.put("Latitude", location.getLatitude());
                     contentValues.put("Longitude", location.getLongitude());
                     contentValues.put("Date", datev);
                     contentValues.put("time", timev);

                     sqlitedb = db.getWritableDatabase();
                     Long recid = sqlitedb.insert("INFORMATIONS", null, contentValues);
                     if (recid != null) {
                        Toast.makeText(MapsActivity.this, "successfully sent informations to director", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MapsActivity.this, "Railway inspection date " + datev, Toast.LENGTH_SHORT).show();
                        Toast.makeText(MapsActivity.this, "Railway inspection time " + timev, Toast.LENGTH_SHORT).show();
                     } else {
                        Toast.makeText(MapsActivity.this, "something wrong try again", Toast.LENGTH_SHORT).show();
                     }

                  }
               });
            }
         }
      });
   }

   @Override
   public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      if (requestCode ==45){
         if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getCurrentLocation();
         }
      }
   }
}


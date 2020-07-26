package com.example.proyectoindividualmoviles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MyaccountActivity extends AppCompatActivity {


    String latitud;
    String altitud;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        imagen = findViewById(R.id.imageViewFotoMiCuenta);

    }

    /* private void mostrarInfoDeUbicacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permiso == PackageManager.PERMISSION_GRANTED){
            FusedLocationProviderClient fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(this);

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){
                        TextView textView  = findViewById(R.id.textViewLocalizacion);
                        String ubicacion = "Latitud: " + location.getLatitude() + "   Longitud: "
                                + location.getAltitude();
                        textView.setText(ubicacion);
                    }
                }
            });

            fusedLocationProviderClient.getLastLocation().addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }

    }


    public void botonUbicacion(View view){
        mostrarInfoDeUbicacion();
    }
*/
    public void cargarImagen(View view){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),10);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri path =data.getData();
            imagen.setImageURI(path);

        }
    }
}

package com.example.proyectoindividualmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void conCuenta(View view){
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);


    }
}

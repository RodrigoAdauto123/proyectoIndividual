package com.example.proyectoindividualmoviles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void RecuperarContrasenha(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("¿Haz olvidado tu contraseña?");
        alertDialog.setMessage("Introduce tu email y te enviaremos un correo electronico con un enlace para resetearla");
        editText = new EditText(this);
        alertDialog.setView(editText);
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String emailRecuperado = editText.getText().toString();
            }
        });

        alertDialog.show();

    }
    public void mostrarDialogoPersonalisado(View view){
        mostrar();
    }

    public void mostrar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.recuperarcontrasenha,null);
        builder.setView(view);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText( getApplicationContext(),"Enviando Correo", Toast.LENGTH_SHORT).show();
                String emailRecuperado = editText.getText().toString();
            }
        });

        builder.show();
    }
}

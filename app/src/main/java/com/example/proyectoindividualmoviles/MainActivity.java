package com.example.proyectoindividualmoviles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoindividualmoviles.Entity.Curso;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser =firebaseAuth.getCurrentUser();

        if(currentUser != null){

            Button ingresar = findViewById(R.id.button2);
            ingresar.setText("Empezar a aprender");
            ingresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent paginaPrincipal = new Intent(MainActivity.this,PaginaPrincipalActivity.class);
                    startActivity(paginaPrincipal);
                    finish();
                }
            });

        }

        //firebase();

    }

    public void iniciarSesion(View view){
        List<AuthUI.IdpConfig> listaProveedores = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build());

        AuthMethodPickerLayout customLayout = new AuthMethodPickerLayout.Builder(R.layout.activity_inicio)
                .setGoogleButtonId(R.id.google_button)
                .setFacebookButtonId(R.id.login_button)
                .build();

        startActivityForResult(AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAuthMethodPickerLayout(customLayout)
        .setAvailableProviders(listaProveedores)
        .build(),1);

    }


    public void cerrarSesion(){

        LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
    }
    public void firebase(){

        String nivel = "Basico";
        String author = "-"+ "Ezequiel Pena";
        String nombre = "Masa de Pan Vegana " + nivel + author;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Cursos").child(nombre);

             //   .child("Idiomas").child("Español");

        Curso curso = new Curso();
        DatabaseReference ref = databaseReference.push();
        String idCurso = ref.getKey();


        String descripcion = "Compartimos con ustedes una receta del todo especial. ";
        String duracion = "15";
        String categoria = "Cocina";
        String subcategoria = "Vegano";
        ArrayList<String> videos = new ArrayList<String>();
        videos.add("https://virtualeducation.s3.amazonaws.com/videos/cocina/Como+hacer+una+masa+de+pan+vegana.mp4");

        curso.setIdCurso(idCurso);
        curso.setAuthor(author);
        curso.setNombre(nombre);
        curso.setDuracion(duracion);
        curso.setNivel(nivel);
        curso.setDescripcion(descripcion);
        curso.setVideos(videos);
        curso.setCategoria(categoria);
        curso.setSubcategoria(subcategoria);
        databaseReference.setValue(curso);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 1){
                if(resultCode == RESULT_OK){
                    Button ingresar = findViewById(R.id.button2);
                    ingresar.setText("Empezar a aprender");
                    ingresar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent paginaPrincipal = new Intent(MainActivity.this,PaginaPrincipalActivity.class);
                            startActivity(paginaPrincipal);
                            finish();
                        }
                    });
                }
            }
    }

}

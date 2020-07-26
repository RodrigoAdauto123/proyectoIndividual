package com.example.proyectoindividualmoviles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.proyectoindividualmoviles.Entity.Curso;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class PaginaPrincipalActivity extends AppCompatActivity {

    VideoView video;
    Uri path;
    Uri videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);

        Button buttonTecnologia = findViewById(R.id.buttonTecnologia);
        buttonTecnologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = "Tecnologia";
                Intent intent = new Intent(PaginaPrincipalActivity.this,ListaCursosActivity.class);
                intent.putExtra("Categoria",categoria);
                startActivity(intent);

            }
        });

        Button buttonMarketing = findViewById(R.id.buttonMarketing);
        buttonMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = "Marketing";
                Intent intent = new Intent(v.getContext(),ListaCursosActivity.class);
                intent.putExtra("Categoria",categoria);
                startActivity(intent);

            }
        });

        Button buttonIdiomas = findViewById(R.id.buttonIdiomas);
        buttonIdiomas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = "Idiomas";
                Intent intent = new Intent(v.getContext(),ListaCursosActivity.class);
                intent.putExtra("Categoria",categoria);
                startActivity(intent);

            }
        });
        Button buttonCocina = findViewById(R.id.buttonCocina);
        buttonCocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = "Cocina";
                Intent intent = new Intent(v.getContext(),ListaCursosActivity.class);
                intent.putExtra("Categoria",categoria);
                startActivity(intent);

            }
        });
        Button buttonMatematica = findViewById(R.id.buttonMatematica);
        buttonMatematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = "Matematica";
                Intent intent = new Intent(v.getContext(),ListaCursosActivity.class);
                intent.putExtra("Categoria",categoria);
                startActivity(intent);

            }
        });


        Button buttonTodos = findViewById(R.id.buttonTodasCategorias);
        buttonTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = "Todos";
                Intent intent = new Intent(v.getContext(),ListaCursosActivity.class);
                intent.putExtra("Categoria",categoria);
                startActivity(intent);

            }
        });
    }


    public void subirvideo(View view){

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                intent.setType("video/*");
                startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 10){

           path =  data.getData();
           // video.setVideoURI(path);

        }
    }

    public void guardavideo(View view){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        String fileName = "video-"+ "prueba2";
        StorageReference storageRef = firebaseStorage.getReference().child("Video-Cursos/" + fileName);
        UploadTask uploadTask = storageRef.putFile(path);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(PaginaPrincipalActivity.this,"Subida exitosa",Toast.LENGTH_SHORT);
                //firebase();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PaginaPrincipalActivity.this,"Falloo",Toast.LENGTH_SHORT);
            }
        });

    }

//Prueba de subida
    public void firebase() {

        String author = "-"+ "AbcEdu";
        String nombre = "Español" + author;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Cursos").child(nombre);

        //   .child("Idiomas").child("Español");

        Curso curso = new Curso();
        DatabaseReference ref = databaseReference.push();
        String idCurso = ref.getKey();

        String nivel = "Basico";
        String descripcion = "In this course the native Spanish speaking teacher covers the following topics: Spanish speaking, Spanish pronunciation, Spanish writing, Spanish grammar rules and patterns with accent on verbs and tenses, Spanish vocabulary, Spanish conversation and communication skills. The interactive board and the subtitles help the visual learners and the immersion method used for teaching Spanish accelerates the learning speed, the comprehension of the spoken Spanish and the fluency";
        String duracion = "36";
        String categoria = "Tecnologia";
        ArrayList<String> videos = new ArrayList<String>();
        String url = path.getPath();
        //Uri a = Uri.parse(url) ;
        videos.add(path.getPath());
        videos.add(" ");
        videos.add("nuevo");


        curso.setIdCurso(idCurso);
        curso.setAuthor(author);
        curso.setNombre(nombre);
        curso.setDuracion(duracion);
        curso.setNivel(nivel);
        curso.setDescripcion(descripcion);
        curso.setVideos(videos);
        curso.setCategoria(categoria);

        databaseReference.setValue(curso);

    }
}

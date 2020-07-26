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

import com.example.proyectoindividualmoviles.Entity.Curso;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //firebase();
        //firebase2();
        //firebase3();

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


    public void firebase2(){

        String nivel = "Basico";
        String author = "-"+ "Oink Pro";
        String nombre = "Hamburguesa de lenteja " + nivel + author;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Cursos").child(nombre);

        //   .child("Idiomas").child("Español");

        Curso curso = new Curso();
        DatabaseReference ref = databaseReference.push();
        String idCurso = ref.getKey();


        String descripcion = "Prueba esta deliciosa opción nutritiva!";
        String duracion = "5";
        String categoria = "Cocina";
        String subcategoria = "Vegano";
        ArrayList<String> videos = new ArrayList<String>();
        videos.add("https://virtualeducation.s3.amazonaws.com/videos/cocina/HAMBURGUESA+DE+LENTEJA+-+MANOS+A+LA+OBRA.mp4");

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

    public void firebase3(){

        String nivel = "Basico";
        String author = "-"+ "Yovana";
        String nombre = "Comida vegana para la semana " + nivel + author;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Cursos").child(nombre);

        //   .child("Idiomas").child("Español");

        Curso curso = new Curso();
        DatabaseReference ref = databaseReference.push();
        String idCurso = ref.getKey();


        String descripcion = "¿Necesitas Ayuda con la Preparacion de Comida Vegana para la Semana?";
        String duracion = "10";
        String categoria = "Cocina";
        String subcategoria = "Vegano";
        ArrayList<String> videos = new ArrayList<String>();
        videos.add("https://virtualeducation.s3.amazonaws.com/videos/cocina/PREPARACION+DE+COMIDA+VEGANA+PARA+LA+SEMANA.mp4");

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


}

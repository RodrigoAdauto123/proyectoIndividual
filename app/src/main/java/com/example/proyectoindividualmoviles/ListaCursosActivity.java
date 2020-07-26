package com.example.proyectoindividualmoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoindividualmoviles.Entity.Curso;
import com.example.proyectoindividualmoviles.Entity.ListaCurso;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaCursosActivity extends AppCompatActivity {


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listarCursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cursos);


    }



    public void listarCursos(){

        final String cat = getIntent().getStringExtra("Categoria");
        final String todosLista = "Todos";
       // Toast toast = Toast.makeText(getApplicationContext(),cat,Toast.LENGTH_SHORT).show();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cursos");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 ArrayList<Curso> listaC = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()){

                    Curso c = child.getValue(Curso.class);

                    if(cat.equalsIgnoreCase(todosLista)){
                        listaC.add(c);
                    }else if (c.getCategoria().equalsIgnoreCase(cat) ){
                        listaC.add(c);
                    }
                }

                Curso[] listaCur = listaC.toArray(new Curso[listaC.size()]);
                TextView textView = findViewById(R.id.textViewNombreCategoria);
                textView.setText(cat);

                ListaCursoAdapter listaCursoAdapter = new ListaCursoAdapter(listaCur,ListaCursosActivity.this, cat);
                RecyclerView recyclerView = findViewById(R.id.reciclerViewListaCursos);
                recyclerView.setAdapter(listaCursoAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListaCursosActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}

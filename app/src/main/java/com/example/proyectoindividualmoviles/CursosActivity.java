package com.example.proyectoindividualmoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.proyectoindividualmoviles.Entity.Curso;
import com.example.proyectoindividualmoviles.Entity.ListaCurso;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CursosActivity extends AppCompatActivity {

    VideoView videoView;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        listarCursos();

    }

    public void masVideos(View view){
        Intent intent = new Intent(this,PaginaPrincipalActivity.class);
        startActivity(intent);
        finish();
    }


    public void listarCursos(){

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cursos");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Curso> listaC = new ArrayList<>();

                final String cat = getIntent().getStringExtra("cat");
                final String todosLista = "Todos";

                int i = 0;
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Curso c = child.getValue(Curso.class);



                    if(cat.equalsIgnoreCase(todosLista)){
                        listaC.add(c);
                    }else if (c.getCategoria().equalsIgnoreCase(cat) ){
                        listaC.add(c);
                    }


                }

                Curso[] listaCur = listaC.toArray(new Curso[listaC.size()]);

                Intent intent  = getIntent();
                int position = intent.getIntExtra("position",0);




                String nombre =  listaCur[position].getNombre();
                String author =  listaCur[position].getAuthor();
                String descripcion =  listaCur[position].getDescripcion();
                String nivel=  listaCur[position].getNivel();
                String duracion  = listaCur[position].getDuracion();
                String subcategoria = listaCur[position].getSubcategoria();
                List<String> urlArray = listaCur[position].getVideos();
                String urlString = urlArray.get(0);
                /* Uri
                ArrayList<String> pathLista = listaCur[position].getVideos();
                String pathString = pathLista.get(0);
                Uri pathUri = Uri.parse(pathString);
                */
                TextView nombreCurso = findViewById(R.id.textViewNombreCurso);
                nombreCurso.setText(nombre);

                TextView authorCurso = findViewById(R.id.textViewNombreProfesor);
                authorCurso.setText(author);

                TextView descripcionCurso = findViewById(R.id.textViewDescripcionCurso);
                descripcionCurso.setText(descripcion);

                TextView nivelCurso = findViewById(R.id.textViewNivelCurso);
                nivelCurso.setText(nivel);

                TextView duracionCurso = findViewById(R.id.textViewDuracionCurso);
                duracionCurso.setText(duracion);

                TextView subcat = findViewById(R.id.textViewCantidadClases);
                subcat.setText(subcategoria);


                videoView = findViewById(R.id.videoViewCurso);
              //  String path = "android.resource://" + getPackageName()+ "/" + R.raw.fill;
              //  Uri uri = Uri.parse(path);


                Uri uri = Uri.parse(urlString);
                videoView.setVideoURI(uri);




                /* Video prueba de firebase
                videoView = findViewById(R.id.videoViewCurso);

                videoView.setVideoURI(pathUri);
                */
                MediaController mediaController = new MediaController(CursosActivity.this);
                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

package com.example.proyectoindividualmoviles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoindividualmoviles.Entity.Curso;
import com.example.proyectoindividualmoviles.Entity.ListaCurso;
import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaCursosActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle2;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listarCursos();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cursos);



        drawerLayout = findViewById(R.id.drawer2);
        toggle2 = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle2);
        NavigationView navigationView= findViewById(R.id.nav2);
        toggle2.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawer(navigationView);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle2.onOptionsItemSelected(item)){
            TextView nombre = findViewById(R.id.nombre);
            TextView correo =  findViewById(R.id.correoElectronico);

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser =firebaseAuth.getCurrentUser();

            if(currentUser != null){
                nombre.setText(currentUser.getDisplayName());
                correo.setText(currentUser.getEmail());
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawer(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.logout:
                        LoginManager.getInstance().logOut();
                        FirebaseAuth.getInstance().signOut();
                        Intent intento = new Intent(ListaCursosActivity.this,MainActivity.class);
                        startActivity(intento);
                        finish();
                        break;

                }
                return true;
            }
        });
    }

}

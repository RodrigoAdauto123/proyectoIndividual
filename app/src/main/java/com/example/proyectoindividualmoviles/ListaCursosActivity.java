package com.example.proyectoindividualmoviles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cursos);
        listarCursos();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);


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

    public void mostrarTYC(){

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Terminos y Condiciones");
        alertBuilder.setMessage("El presente Política de Privacidad establece los términos en que Virtual Education usa y protege " +
                "la información que es proporcionada por sus usuarios al momento de utilizar su app. Esta " +
                "compañía está comprometida con la seguridad de los datos de sus usuarios. Cuando le pedimos " +
                "llenar los campos de información personal con la cual usted pueda ser identificado, lo hacemos " +
                "asegurando que sólo se empleará de acuerdo con los términos de este documento. Sin embargo " +
                "esta Política de Privacidad puede cambiar con el tiempo o ser actualizada por lo que le " +
                "recomendamos y enfatizamos revisar continuamente esta página para asegurarse que está de " +
                "acuerdo con dichos cambios.\n \n" +
                "Nuestro sitio web podrá recoger información personal por ejemplo: Nombre, información de " +
                "contacto como su dirección de correo electrónica e información demográfica. Así mismo cuando " +
                "sea necesario podrá ser requerida información específica para procesar algún pedido o realizar " +
                "una entrega o facturación.\n \n" +
                "Virtual Education está altamente comprometido para cumplir con el compromiso de mantener su " +
                "información segura. Usamos los sistemas más avanzados y los actualizamos constantemente " +
                "para asegurarnos que no exista ningún acceso no autorizado.\n");
        alertBuilder.setPositiveButton("Conforme", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ListaCursosActivity.this,"Gracias por confiar en nosotros",Toast.LENGTH_SHORT).show();
            }
        });
        alertBuilder.show();

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
                    case R.id.TYC:
                        mostrarTYC();
                        break;

                }
                return true;
            }
        });
    }

}

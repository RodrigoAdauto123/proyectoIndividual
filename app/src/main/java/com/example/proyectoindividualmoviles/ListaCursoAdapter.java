package com.example.proyectoindividualmoviles;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoindividualmoviles.Entity.Curso;
import com.example.proyectoindividualmoviles.Entity.ListaCurso;

public class ListaCursoAdapter extends RecyclerView.Adapter<ListaCursoAdapter.CursoViewHolder> {


    //ListaCurso listaCurso ;
    Curso[] listaCursoNuevo;
    Context contexto;
    String categoria;

    public ListaCursoAdapter(Curso[] lista, Context context, String cat){
    this.listaCursoNuevo = lista;
    this.contexto = context;
    this.categoria = cat;
    }

    public static class CursoViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView descripcion;
        Button botonVerDetalles;
        public CursoViewHolder( View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.textViewRecicleNombreCurso);
            descripcion = itemView.findViewById(R.id.textViewRecicleDescripcion);
            botonVerDetalles = itemView.findViewById(R.id.buttonRecicleVerCurso);

        }
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(contexto).inflate(R.layout.itemrecyclerview,parent,false);
        CursoViewHolder cursoViewHolder = new CursoViewHolder(item);
        return cursoViewHolder;
    }

    @Override
    public void onBindViewHolder(CursoViewHolder holder, final int position) {

      //  listaCursoNuevo = listaCurso.getListaCurso().toArray(new Curso[0]);
      //  Curso curso = listaCursoNuevo[position];
        Curso curso = listaCursoNuevo[position];
        String nombreCurso = curso.getNombre();
        String descripcionCurso = curso.getDescripcion();

        holder.nombre.setText(nombreCurso);
        holder.descripcion.setText(descripcionCurso);
        holder.botonVerDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListaCursosActivity listaCursosActivity = new ListaCursosActivity();
                Intent intent = new Intent(v.getContext(), CursosActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("cat",categoria);
                listaCursosActivity.finish();
                contexto.startActivity(intent);

            }

        });

    }

    @Override
    public int getItemCount() {
        return listaCursoNuevo.length;
    }
}

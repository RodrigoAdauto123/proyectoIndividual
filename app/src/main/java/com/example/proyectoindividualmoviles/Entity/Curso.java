package com.example.proyectoindividualmoviles.Entity;

import java.util.ArrayList;

public class Curso {

    private String idCurso;
    private String nombre;
    private String descripcion;
    private String author;
    private String nivel;
    private String duracion;
    private ArrayList<String> videos;
    private String categoria;
    private String subcategoria;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<String> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<String> videos) {
        this.videos = videos;
    }



    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }
}

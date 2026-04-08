package com.example.utez2epacientesjavafxequipo07.model;

public class Libro {
    private String id;
    private String titulo;
    private String autor;
    private int anio;
    private String genero;
    private boolean disponible;

    public Libro(String id, String titulo, String autor, int anio, String genero, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.genero = genero;
        this.disponible = disponible;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnio() {
        return anio;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

}

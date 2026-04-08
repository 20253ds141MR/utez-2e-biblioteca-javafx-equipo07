package com.example.utez2epacientesjavafxequipo07.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportExport {

    public void exportar(List<Libro> libros) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reporte_catalogo.csv"))) {
            writer.write("ID,Titulo,Autor,Anio,Genero,Disponible");
            writer.newLine();
            for (Libro libro : libros) {
                writer.write(libro.getId() + "," +
                        libro.getTitulo() + "," +
                        libro.getAutor() + "," +
                        libro.getAnio() + "," +
                        libro.getGenero() + "," +
                        libro.isDisponible());
                writer.newLine();
            }
        }
    }
}

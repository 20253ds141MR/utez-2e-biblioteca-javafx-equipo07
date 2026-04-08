package com.example.utez2epacientesjavafxequipo07.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReportExport {

    public void exportar(List<Libro> libros) throws IOException {
        Path ruta = Paths.get(System.getProperty("user.dir"), "data", "reporte_catalogo.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
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

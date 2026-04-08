package com.example.utez2epacientesjavafxequipo07.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportExport {

    public void exportar(List<Libro> libros) throws IOException {

        BufferedWriter writer= new BufferedWriter(new FileWriter("reporteCatalogo.csv"));
        writer.write("ID,Titulo,Autor,Anio,Genero,Disponible");
        writer.newLine();


    }
}

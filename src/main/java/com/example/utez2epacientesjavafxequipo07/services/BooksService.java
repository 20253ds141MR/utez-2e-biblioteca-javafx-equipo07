package com.example.utez2epacientesjavafxequipo07.services;

import com.example.utez2epacientesjavafxequipo07.model.Libro;
import com.example.utez2epacientesjavafxequipo07.repositories.BooksFileRepository;
import com.example.utez2epacientesjavafxequipo07.repositories.BooksFileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Recibe el listado
public class BooksService {

    BooksFileRepository repo = new BooksFileRepository();

    //Logica de negocio (EXAMEN)
    public List<String> loadDataForListView() throws IOException {

        List<String> lines= repo.readAllLines();
        List<String> result= new ArrayList<>();



        //Devuelve un arreglo separado por comas
        for(String line:lines){
            if(line==null||line.isBlank())continue;

            String[] parts = line.split(",");
            String titulo = parts[0];
            String email = parts[1];
            int edad = Integer.parseInt(parts[2]);
            result.add(titulo +" - "+ email+" - "+ edad);
        }
        return result;
    }

    public void updatePerson(int index, String titulo, String email, String edad) throws IOException {
        validate(titulo,email, Integer.parseInt(edad));
        if(index<0) {
            throw new IllegalArgumentException("El indice es inválido");
        }
        List<String> data=getCleanLines();
        data.set(index,titulo+","+email+","+edad);
        repo.saveFile(data);
    }

    public void deletePerson(int index) throws IOException {
        List<String> data = getCleanLines();

        if (index < 0 || index >= data.size()) {
            throw new IllegalArgumentException("El índice es inválido o está fuera de rango");
        }
        data.remove(index);
        repo.saveFile(data);
    }


    private List<String> getCleanLines() throws IOException {
        List<String> lines= repo.readAllLines();
        List<String> cleanLines = new ArrayList<>();
        for(String line:lines){
            if(line!=null && !line.isBlank()){
                cleanLines.add(line);

            }
        }

        return cleanLines;
    }

    public void addLibro (Libro libro) throws IOException {
     validate(libro);
     repo.appenNewLine(
             libro.getIsbn() + "," +
                     libro.getTitulo() + "," +
                     libro.getAutor() + "," +
                     libro.getAnio() + "," +
                     libro.getGenero() + "," +
                     libro.isDisponible()
     );
    }

    private void validate(Libro libro) throws IOException {
       if(libro.getTitulo().length() <3) throw new IllegalArgumentException("Titulo muy corto");

       if (libro.getAutor().length() <3) throw new IllegalArgumentException("Autor muy corto");

       int currentYear = java.time.Year.now().getValue();

       if (libro.getAnio() < 1500 || libro.getAnio() > currentYear) throw new IllegalArgumentException("Año inválido");

    }


}

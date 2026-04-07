package com.example.utez2epacientesjavafxequipo07.services;

import com.example.utez2epacientesjavafxequipo07.model.Libro;
import com.example.utez2epacientesjavafxequipo07.repositories.BooksFileRepository;
import com.example.utez2epacientesjavafxequipo07.repositories.BooksFileRepository;

import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

//Recibe el listado
public class BooksService {

    BooksFileRepository repo = new BooksFileRepository();

    //Logica de negocio (EXAMEN)
    public List<Libro> getLibros() throws IOException{
        List<String> lines = repo.readAllLines();
        List<Libro> libros = new ArrayList<>();

       for(int i = 0; i < lines.size(); i++){
           String line = lines.get(i);
           if(line==null||line.isBlank()){
           continue;
           }

           String[] split = line.split(";");
           if(split.length<6) continue;

           libros.add(new Libro(
                   split[0],
                   split[1],
                   split[2],
                   Integer.parseInt(split[3]),
                   split[4],
                   Boolean.parseBoolean(split[5])
           ));
       }
        return libros;
    }

    public void addLibro (String id, String titulo, String autor, int anio, String genero, boolean disponible) throws IOException{

        validate(id, titulo, autor, anio);
        List<String> data = getCleanLines();

        for(String line : data){
            String[] parts = line.split(",");
            if(parts[0].equals(id)){
                throw new IllegalArgumentException("El ID ya existe");
            }
        }

        repo.appendNewLine(id + "," + titulo + "," + autor + "," + anio + "," + genero + "," + disponible);

    }

  public void updateLibro(int index, String id, String titulo, String autor, int anio, String genero, boolean disponible) throws IOException{

        validate(id,titulo,autor,anio);

        List<String> data = getCleanLines();
        if(index < 0 || index >= data.size()){
            throw new IllegalArgumentException("inválido");
        }

       String lineaActualizada = id + "," + titulo + "," + autor +"," + anio + "," + genero + "," + disponible;
        data.set(index + 1, lineaActualizada);

        repo.saveFile(data);
  }

  public void deleteLibro(int index) throws IOException{
        List<String> data = getCleanLines();
        if(index < 0 || index >= data.size()){
            throw new IllegalArgumentException("inválido");
        }

        data.remove(index + 1);
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



    private void validate(String id, String titulo, String autor, int anio) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID obligatorio");
        }

        if (titulo == null || titulo.length() < 3) {
            throw new IllegalArgumentException("Título muy corto");
        }

        if (autor == null || autor.length() < 3) {
            throw new IllegalArgumentException("Autor muy corto");
        }

        int currentYear = Year.now().getValue();

        if (anio < 1500 || anio > currentYear) {
            throw new IllegalArgumentException("Año inválido");
        }
    }


}

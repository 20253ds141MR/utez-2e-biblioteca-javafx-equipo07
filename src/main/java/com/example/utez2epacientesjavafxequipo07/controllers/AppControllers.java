package com.example.utez2epacientesjavafxequipo07.controllers;

import com.example.utez2epacientesjavafxequipo07.model.Libro;
import com.example.utez2epacientesjavafxequipo07.services.BooksService;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.awt.print.Book;
import java.io.IOException;
import java.util.List;


public class AppControllers {

    @FXML private TableView<Libro> tablaLibros;

   @FXML private TableColumn<Libro,String> colID;
   @FXML private TableColumn<Libro,String> colTitulo;
   @FXML private TableColumn<Libro,String> colAutor;
   @FXML private TableColumn<Libro,String> colAnio;
   @FXML private TableColumn<Libro,String> colGenero;
   @FXML private TableColumn<Libro,String> colDisponible;
    @FXML
    private ObservableList<String> data =FXCollections.observableArrayList();


    private BooksService service = new BooksService();
    @FXML
    public void initialize(){
       colID.setCellValueFactory(new PropertyValueFactory<>("isbn"));
       colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
       colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
       colAnio.setCellValueFactory(new PropertyValueFactory<>("año"));
       colGenero.setCellValueFactory(new PropertyValueFactory<>("género"));
       colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

       cargarTabla();
    }







    private void cargarTabla(){
       try {
           List<String> lines = service.loadDataForListView();
           ObservableList<Libro> lista = FXCollections.observableArrayList();
           for (String line : lines) {
               String[] data = line.split(",");

               Libro libro = new Libro(
                       parts[0], //ID
                       parts[1], //titulo
                       parts[2], //autor
                       Integer.parseInt(parts[3]),
                       parts[4],
                       Boolean.parseBoolean(parts[5])
               );
               lista.add(libro);
           }
           tablaLibros.setItems(lista);

       } catch (Exception e){
           lblMsg.setText("Error al cargar tabla");
       }
    }

}

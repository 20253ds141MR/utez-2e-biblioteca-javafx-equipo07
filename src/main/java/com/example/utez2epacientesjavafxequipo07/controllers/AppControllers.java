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

   @FXML private Label lblMsg;
   @FXML private TableColumn<Libro,String> colID;
   @FXML private TableColumn<Libro,String> colTitulo;
   @FXML private TableColumn<Libro,String> colAutor;
   @FXML private TableColumn<Libro,Integer> colAnio;
   @FXML private TableColumn<Libro,String> colGenero;
   @FXML private TableColumn<Libro,Boolean> colDisponible;

   @FXML private TextField txtID;
   @FXML private TextField txtTitulo;
   @FXML private TextField txtAutor;
   @FXML private TextField txtAnio;
   @FXML private TextField txtGenero;
   @FXML private CheckBox chkDisponible;
   @FXML private ObservableList<Libro> listaLibros;

    @FXML
    private ObservableList<String> parts =FXCollections.observableArrayList();


    private BooksService service = new BooksService();
    @FXML
    public void initialize() throws IOException {
        try {
            colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
            colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
            colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

            listaLibros = FXCollections.observableArrayList(service.getLibros());
            tablaLibros.setItems(listaLibros);

            configurarSeleccionTabla();

            tablaLibros.setPlaceholder(new Label("No se encontro el libro"));

        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("Error al cargar libros", "red");
        }
    }

    private void mostrarMensaje(String mensaje, String color){
        lblMsg.setText(mensaje);
        lblMsg.setStyle("-fx-text-fill: " + color + ";");
    }


    @FXML
    public void editar() {
        try{
           int index = tablaLibros.getSelectionModel().getSelectedIndex();

           if(index < 0){
           mostrarMensaje("Selecciona un libro", "purple");
           return;
           }

           String id = txtID.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String genero = txtGenero.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            boolean disponible = chkDisponible.isSelected();

            service.updateLibro(index,id,titulo,autor,anio,genero,disponible);
            cargarTabla();
            limpiar();
            mostrarMensaje("Libro actualizado", "green");
        } catch (Exception e){
            mostrarMensaje("Error al editar", "red");
        }
        }

        private void cargarFormulario(Libro libro){
        txtID.setText(libro.getId());
        txtTitulo.setText(libro.getTitulo());
        txtAutor.setText(libro.getAutor());

        txtAnio.setText(String.valueOf(libro.getAnio()));
        txtGenero.setText(String.valueOf(libro.getGenero()));
        chkDisponible.setSelected(libro.isDisponible());
        }

        private void configurarSeleccionTabla(){
        tablaLibros.getSelectionModel().selectedItemProperty().addListener((obs, libroAnterior, libroSeleccionado) -> {
            if(libroSeleccionado !=null){
                cargarFormulario(libroSeleccionado);
            }
            });
        }

    @FXML
    public void nuevo() {
        try {
            String id = txtID.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String genero = txtGenero.getText();

            if (txtAnio.getText().isEmpty()){
                mostrarMensaje("El año es obligatorio", "red");
                return;
            }

            int anio = Integer.parseInt(txtAnio.getText());
            boolean disponible = chkDisponible.isSelected();

            service.addLibro(id,titulo,autor,anio,genero,disponible);
            listaLibros.setAll(service.getLibros());
            limpiar();
            mostrarMensaje("Libro agregadoo", "green");


        } catch (Exception e){
            e.printStackTrace();
            mostrarMensaje("Error al agregar", "red");
        }
    }

    @FXML
    public void eliminar(){
        try{
            int index =  tablaLibros.getSelectionModel().getSelectedIndex();
            if(index < 0){
                mostrarMensaje("Selecciona un libro", "orange");
                return;
            }


            service.deleteLibro(index);
            cargarTabla();
            limpiar();
            mostrarMensaje("Libro eliminado", "orange");

        } catch (Exception e) {
            mostrarMensaje("Error, intente de nuevo", "red");
        }
    }


    private void cargarTabla(){
        try {
            ObservableList<Libro> listaLibros = FXCollections.observableArrayList(service.getLibros());
            tablaLibros.setItems(listaLibros);

        } catch (Exception e){
            lblMsg.setText("Error al cargar tabla");
        }
    }


    private void limpiar(){
        txtID.clear();
        txtTitulo.clear();
        txtAutor.clear();
        txtAnio.clear();
        txtGenero.clear();
        chkDisponible.setSelected(false);
    }
}


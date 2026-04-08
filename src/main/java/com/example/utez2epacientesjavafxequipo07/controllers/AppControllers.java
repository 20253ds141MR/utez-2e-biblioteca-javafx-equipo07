package com.example.utez2epacientesjavafxequipo07.controllers;

import com.example.utez2epacientesjavafxequipo07.model.Libro;
import com.example.utez2epacientesjavafxequipo07.model.ReportExport;
import com.example.utez2epacientesjavafxequipo07.services.BooksService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;


public class AppControllers {

    @FXML private TableView<Libro> tablaLibros;

   @FXML private Label lblMsg;
   @FXML private TableColumn<Libro,String> colID;
   @FXML private TableColumn<Libro,String> colTitulo;
   @FXML private TableColumn<Libro,String> colAutor;
   @FXML private TableColumn<Libro,Integer> colAnio;
   @FXML private TableColumn<Libro,String> colGenero;
   @FXML private TableColumn<Libro,Boolean> colDisponible;

   @FXML private Button btonEditar;
   @FXML private Button btonEliminar;


   private ObservableList<Libro> listaLibros;
   private final BooksService service = new BooksService();

    @FXML
    public void initialize() {
        //Utilización de lambdas para la visualización de libros en el ListView
        try {
            colID.setCellValueFactory(c ->
                    new javafx.beans.property.SimpleStringProperty(c.getValue().getId()));

            colTitulo.setCellValueFactory(c ->
                    new javafx.beans.property.SimpleStringProperty(c.getValue().getTitulo()));

            colAutor.setCellValueFactory(c ->
                    new javafx.beans.property.SimpleStringProperty(c.getValue().getAutor()));

            colAnio.setCellValueFactory(c ->
                    new javafx.beans.property.SimpleIntegerProperty(
                            c.getValue().getAnio()
                    ).asObject());

            colGenero.setCellValueFactory(c ->
                    new javafx.beans.property.SimpleStringProperty(c.getValue().getGenero()));

            colDisponible.setCellValueFactory(c ->
                    new javafx.beans.property.SimpleBooleanProperty(
                            c.getValue().isDisponible()
                    ).asObject());

            listaLibros = FXCollections.observableArrayList(service.getLibros());
            tablaLibros.setItems(listaLibros);


            tablaLibros.setPlaceholder(new Label("No se encontro el libro"));

            configurarSeleccionTabla();

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
    public void editar()  {

        try {
            Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
            if (seleccionado == null) return;

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/utez2epacientesjavafxequipo07/views/formulario.fxml"
                    )
            );
            Parent root = loader.load();

            FormularioController controller = loader.getController();
            controller.setLibro(seleccionado);

            Stage stage = new Stage();
            stage.setTitle("Editar libro");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            cargarTabla();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void nuevo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/utez2epacientesjavafxequipo07/views/formulario.fxml"));
            Parent root = loader.load();

            FormularioController controller = loader.getController();
            controller.setLibro(null);

            Stage stage = new Stage();
            stage.setTitle("Nuevo libro");
            stage.setScene(new Scene(root));

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            cargarTabla();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminar(){

        try {
            int index = tablaLibros.getSelectionModel().getSelectedIndex();
            if (index < 0) return;

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("¿Eliminar libro?");
            alert.setContentText("Esta acción no se puede deshacer.");

            if (alert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
                return;
            }

            service.deleteLibro(index);
            cargarTabla();

            btonEditar.setDisable(true);
            btonEliminar.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void detalles(){
        try{
            Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
            if (seleccionado == null){
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/utez2epacientesjavafxequipo07/views/Detalles.fxml"));
            Parent root = loader.load();
            DetalleController controller = loader.getController();
            controller.setLibro(seleccionado);

            Stage stage = new Stage();
            stage.setTitle("Detalles de libro");
            stage.setScene(new Scene(root));

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarTabla(){
        try {
            listaLibros.setAll(service.getLibros());

        } catch (Exception e){
            lblMsg.setText("Error al cargar tabla");
            e.printStackTrace();
        }
    }

    private void configurarSeleccionTabla(){
        btonEditar.setDisable(true);
        btonEliminar.setDisable(true);

        tablaLibros.getSelectionModel().selectedItemProperty().addListener((obs,anterior,seleccionado)->{
            boolean haySeleccion = seleccionado != null;

            btonEditar.setDisable(!haySeleccion);
            btonEliminar.setDisable(!haySeleccion);
        });
    }



    @FXML
    private void actualizarTabla(){
        cargarTabla();
    }

    @FXML
    private void exportarReporte() {
        try {
            ReportExport reporte = new ReportExport();
            reporte.exportar(service.getLibros());
            mostrarMensaje("Reporte exportado: reporte_catalogo.csv", "green");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("Error al exportar reporte", "red");
        }
    }
}


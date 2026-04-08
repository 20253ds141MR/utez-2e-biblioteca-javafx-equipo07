package com.example.utez2epacientesjavafxequipo07.controllers;

import com.example.utez2epacientesjavafxequipo07.model.Libro;
import com.example.utez2epacientesjavafxequipo07.services.BooksService;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormularioController {

    @FXML private TextField txtId;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtAutor;
    @FXML private TextField txtAnio;
    @FXML private TextField txtGenero;
    @FXML private CheckBox checkDisponible;
    @FXML private Label lblError;

    private Libro libro;
    private final BooksService service = new BooksService();


    public void setLibro(Libro libro) {
        this.libro = libro;

        if (libro != null) {
            txtId.setText(libro.getId());
            txtTitulo.setText(libro.getTitulo());
            txtAutor.setText(libro.getAutor());
            txtAnio.setText(String.valueOf(libro.getAnio()));
            txtGenero.setText(libro.getGenero());
            checkDisponible.setSelected(libro.isDisponible());
        } else {

            limpiarCampos();
        }
    }

    @FXML
    private void agregar(){
        try{
            service.addLibro(
                    txtId.getText(),
                    txtTitulo.getText(),
                    txtAutor.getText(),
                    Integer.parseInt(txtAnio.getText()),
                    txtGenero.getText(),
                    checkDisponible.isSelected()
            );
            cerrarVentana();
        } catch (NumberFormatException e) {
            lblError.setText("El año debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            lblError.setText(e.getMessage());
        } catch (Exception e) {
            lblError.setText("Error al guardar el libro.");
            e.printStackTrace();
        }
    }


    @FXML
    private void guardar() {
        try {
            if (libro == null) return;
            service.updateLibro(
                    libro,
                    txtId.getText(),
                    txtTitulo.getText(),
                    txtAutor.getText(),
                    Integer.parseInt(txtAnio.getText()),
                    txtGenero.getText(),
                    checkDisponible.isSelected()
            );
            cerrarVentana();
        } catch (NumberFormatException e) {
            lblError.setText("El año debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            lblError.setText(e.getMessage());
        } catch (Exception e) {
            lblError.setText("Error al actualizar el libro.");
            e.printStackTrace();
        }
    }



    @FXML
    private void cerrarVentana(){
        Stage stage = (Stage) txtId.getScene().getWindow();
        stage.close();
    }

    private void limpiarCampos(){
        txtId.clear();
        txtTitulo.clear();
        txtAutor.clear();
        txtAnio.clear();
        txtGenero.clear();
        checkDisponible.setSelected(false);
    }
}
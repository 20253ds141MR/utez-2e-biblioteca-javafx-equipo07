package com.example.utez2epacientesjavafxequipo07.controllers;

import com.example.utez2epacientesjavafxequipo07.model.Libro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetalleController {

    @FXML private Label lblId;
    @FXML private Label lblTitulo;
    @FXML private Label lblAutor;
    @FXML private Label lblAnio;
    @FXML private Label lblGenero;
    @FXML private Label lblDisponible;

    public void setLibro(Libro l) {
        lblId.setText("ID: " + l.getId());
        lblTitulo.setText("Título: " + l.getTitulo());
        lblAutor.setText("Autor: " + l.getAutor());
        lblAnio.setText("Año: " + l.getAnio());
        lblGenero.setText("Género: " + l.getGenero());
        lblDisponible.setText("Disponible: " + (l.isDisponible() ? "Sí" : "No"));
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) lblId.getScene().getWindow();
        stage.close();
    }
}


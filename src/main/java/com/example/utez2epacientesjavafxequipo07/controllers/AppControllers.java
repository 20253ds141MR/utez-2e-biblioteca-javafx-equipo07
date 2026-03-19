package com.example.utez2epacientesjavafxequipo07.controllers;

import com.example.utez2epacientesjavafxequipo07.services.BooksService;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.util.List;


public class AppControllers {

    @FXML
    private Label lblMsg;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCampoYear;

    @FXML
    private ObservableList<String> data =FXCollections.observableArrayList();


    private BooksService service = new BooksService();
    @FXML
    public void initialize(){
        loadFromFile();
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                    loadDataToForm(newValue);
                }
        );
        listView.setItems(data);
    }

    @FXML
    public void onReload(){
        loadFromFile();
    }
    @FXML
    public void onAdd() {
        try {
            String titulo = txtTitulo.getText();
            String email = txtEmail.getText();


            if (txtCampoYear.getText().isEmpty()) {
                lblMsg.setText("Debe ingresar un año válido");
                lblMsg.setStyle("-fx-text-fill: red");
                return;
            }

            int edad = Integer.parseInt(txtCampoYear.getText());



            service.addPerson(titulo, email, edad);
            lblMsg.setText("Libro creado con éxito");
            lblMsg.setStyle("-fx-text-fill: purple");
            txtEmail.clear();
            txtTitulo.clear();
            txtCampoYear.clear();
            loadFromFile();

        } catch (NumberFormatException e) {
            lblMsg.setText("La edad debe ser un número válido");
            lblMsg.setStyle("-fx-text-fill: red");
        } catch (IOException e) {
            lblMsg.setText("Error en el archivo");
            lblMsg.setStyle("-fx-text-fill: red");
        } catch (IllegalArgumentException e) {
            lblMsg.setText("Error en los datos");
            lblMsg.setStyle("-fx-text-fill: red");
        }


    }

    public void OnUpdate(){
        try {
            int index = listView.getSelectionModel().getSelectedIndex();
            String titulo = txtTitulo.getText();
            String email = txtEmail.getText();

            if (txtCampoYear.getText().isEmpty()) {
                lblMsg.setText("Actualizado con éxito");
                lblMsg.setStyle("-fx-text-fill: red");
                return;
            }


            int edad = Integer.parseInt(txtCampoYear.getText());

            service.updatePerson(index,titulo, email, String.valueOf(edad));
            lblMsg.setText("Actualizada con éxito");
            lblMsg.setStyle("-fx-text-fill: purple");
            txtEmail.clear();
            txtTitulo.clear();
            txtCampoYear.clear();
            loadFromFile();

        } catch (NumberFormatException e) {
            lblMsg.setText("La edad debe ser un número válido");
            lblMsg.setStyle("-fx-text-fill: red");
        } catch (IOException e) {
            lblMsg.setText("Error en el archivo");
            lblMsg.setStyle("-fx-text-fill: red");
        } catch (IllegalArgumentException e) {
            lblMsg.setText("Error en los datos");
            lblMsg.setStyle("-fx-text-fill: red");
        }
    }

    public void OnDelete(){
        try {
            int index = listView.getSelectionModel().getSelectedIndex();
            String titulo = txtTitulo.getText();
            String email = txtEmail.getText();

            if (txtCampoYear.getText().isEmpty()) {
                lblMsg.setText("Eliminado con éxito");
                lblMsg.setStyle("-fx-text-fill: pink");
                return;
            }

            int edad = Integer.parseInt(txtCampoYear.getText());

            service.deletePerson(index);
            lblMsg.setText("Eliminado con éxito");
            lblMsg.setStyle("-fx-text-fill: purple");
            txtEmail.clear();
            txtTitulo.clear();
            txtCampoYear.clear();
            loadFromFile();

        } catch (NumberFormatException e) {
            lblMsg.setText("La edad debe ser un número válido");
            lblMsg.setStyle("-fx-text-fill: red");
        } catch (IOException e) {
            lblMsg.setText("Error en el archivo");
            lblMsg.setStyle("-fx-text-fill: red");
        } catch (IllegalArgumentException e) {
            lblMsg.setText("Error en los datos");
            lblMsg.setStyle("-fx-text-fill: red");
        }
    }

    private void loadFromFile () {
        try {
            List<String> items = service.loadDataForListView();
            data.setAll(items);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadDataToForm(String data){
        String[] parts = data.split(" - ");
        txtTitulo.setText(parts[0]);
        txtEmail.setText(parts[1]);
        txtCampoYear.setText(parts[2]);
    }

}

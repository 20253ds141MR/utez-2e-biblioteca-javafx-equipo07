module com.example.utez2epacientesjavafxequipo07 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.utez2epacientesjavafxequipo07 to javafx.fxml;
    opens com.example.utez2epacientesjavafxequipo07.controllers to javafx.fxml;

    exports com.example.utez2epacientesjavafxequipo07;
    exports com.example.utez2epacientesjavafxequipo07.controllers;
}
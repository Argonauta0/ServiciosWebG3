module ni.edu.uam.g3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens ni.edu.uam.g3 to javafx.fxml;
    opens ni.edu.uam.g3.controller to javafx.fxml; // <-- Necesario para @FXML
    opens ni.edu.uam.g3.model to javafx.base;      // <-- Necesario para PropertyValueFactory

    exports ni.edu.uam.g3;
}
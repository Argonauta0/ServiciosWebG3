package ni.edu.uam.g3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuariosApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UsuariosApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 570);
        stage.setTitle("Consumo de Servicios Web - G3");
        stage.setScene(scene);
        stage.show();
    }
}
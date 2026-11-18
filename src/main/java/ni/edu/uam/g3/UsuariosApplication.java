package ni.edu.uam.g3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuariosApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UsuariosApplication.class.getResource("usuarios-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 537);
        stage.setTitle("Prueba");
        stage.setScene(scene);
        stage.show();
    }
}

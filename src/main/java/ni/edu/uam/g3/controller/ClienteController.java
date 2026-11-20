package ni.edu.uam.g3.controller;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.edu.uam.g3.model.Cliente;
import ni.edu.uam.g3.service.ClienteService;

import java.util.List;

public class ClienteController {

    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colPais;
    @FXML private TableColumn<Cliente, String> colCiudad;
    @FXML private TableColumn<Cliente, Double> colIngreso;
    @FXML private TableColumn<Cliente, Integer> colScore;
    @FXML private TableColumn<Cliente, String> colEstado;

    @FXML private Button btnConsultar;
    @FXML private Label lblEstado;

    private final ClienteService service = new ClienteService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colIngreso.setCellValueFactory(new PropertyValueFactory<>("ingresoAnual"));
        colScore.setCellValueFactory(new PropertyValueFactory<>("creditScore"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    @FXML
    private void consultarClientes() {
        Task<List<Cliente>> task = new Task<>() {
            @Override
            protected List<Cliente> call() throws Exception {
                return service.obtenerClientes();
            }
        };

        btnConsultar.disableProperty().bind(task.runningProperty());
        lblEstado.setText("Consultando Supabase...");

        task.setOnSucceeded(event -> {
            List<Cliente> clientes = task.getValue();
            tblClientes.setItems(FXCollections.observableArrayList(clientes));
            lblEstado.setText("Clientes encontrados: " + clientes.size());
        });

        task.setOnFailed(event -> {
            Throwable error = task.getException();
            lblEstado.setText(error.getMessage());
        });

        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }
}
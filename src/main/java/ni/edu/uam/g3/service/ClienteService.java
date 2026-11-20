package ni.edu.uam.g3.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ni.edu.uam.g3.model.Cliente;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private static final String URL = "https://sqpvqkkdfgtzdgrjrgxs.supabase.co/rest/v1/customers";
    private static final String API_KEY = System.getenv("SUPABASE_API_KEY");

    public List<Cliente> obtenerClientes() throws IOException, InterruptedException {
        if (API_KEY == null || API_KEY.isBlank()) {
            throw new IOException("Falta configurar la variable de entorno SUPABASE_API_KEY en IntelliJ.");
        }

        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .timeout(Duration.ofSeconds(15))
                .header("apikey", API_KEY)
                .header("Authorization", "Bearer " + API_KEY)
                .GET()
                .build();

        HttpResponse<String> response = cliente.send(peticion,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error HTTP " + response.statusCode() + ": " + response.body());
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode datos = mapper.readTree(response.body());

        if (!datos.isArray()) {
            throw new IOException("La respuesta no contiene un arreglo JSON válido");
        }

        List<Cliente> clientes = new ArrayList<>();
        for (JsonNode nodo : datos) {
            String nombreCompleto = nodo.path("first_name").asText() + " " + nodo.path("last_name").asText();

            Cliente c = new Cliente(
                    nodo.path("id").asInt(),
                    nombreCompleto,
                    nodo.path("email").asText(),
                    nodo.path("phone").asText(),
                    nodo.path("country").asText(),
                    nodo.path("city").asText(),
                    nodo.path("annual_income").asDouble(),
                    nodo.path("credit_score").asInt(),
                    nodo.path("status").asText()
            );
            clientes.add(c);
        }
        return clientes;
    }
}
package ni.edu.uam.g3.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ni.edu.uam.g3.model.Usuario;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

// Encargado de hacer la peticion al sitio web.
public class UsuarioService {
    private static final String URL = "https://jsonplaceholder.typicode.com/users";

    // obtener los registros de usuario.
    public List<Usuario> obtenerUsuario() throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create(URL))
                .timeout(Duration.ofSeconds(15)).GET().build();
        HttpResponse <String> response = cliente.send(peticion,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() !=200) {
            throw new IOException("Eror al obtener el usuario");
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode datos = mapper.readTree(response.body());

        if (!datos.isArray()){
            throw new IOException("La respuesta no contiene un formato JSON");
        }

        List<Usuario> usuarios = new ArrayList<>();
        for (JsonNode nodo: datos){
            Usuario usuario = new Usuario (
                    nodo.path("id").asInt(),
                    nodo.path("name").asText(),
                    nodo.path("username").asText(),
                    nodo.path("email").asText(),
                    nodo.path("phone").asText(),
                    nodo.path("address").path("city").asText(),
                    nodo.path("company").path("name").asText()
            );
            usuarios.add(usuario);
        }
        return usuarios;
    }
}

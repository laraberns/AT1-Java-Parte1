package infnet.app;

import io.javalin.Javalin;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncionarioControllerTest {

    private static Javalin server;
    private static FuncionarioController fc;

    @BeforeAll
    static void beforeAll() {
        server = Javalin.create().start(7000);
        fc = new FuncionarioController(server);
    }

    @AfterAll
    static void afterAll() {
        server.stop();
    }

    @Test
    public void testCriarMensalistaComSucesso() throws Exception {
        URL url = new URL("http://localhost:7000/funcionarios/mensalista");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInput = """
                {
                    "matricula": 3055,
                    "nome": "Fernanda Souza",
                    "cargo": "Gerente de Projetos",
                    "salario": 15000.0
                }
                """;

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int status = connection.getResponseCode();
        assertEquals(201, status, "O código de status deve ser 201 - criado com sucesso");
    }

    @Test
    public void testBuscarMensalistaPorId() throws Exception {
        URL postUrl = new URL("http://localhost:7000/funcionarios/mensalista");
        HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);

        String jsonInput = """
                {
                    "matricula": 9999,
                    "nome": "Patrícia Almeida",
                    "cargo": "Analista de Sistemas",
                    "salario": 9800.0
                }
                """;


        try (OutputStream os = postConnection.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int postStatus = postConnection.getResponseCode();
        assertEquals(201, postStatus, "O código de status deve ser 201 - criado com sucesso");

        URL getUrl = new URL("http://localhost:7000/funcionarios/mensalista/9999");
        HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
        getConnection.setRequestMethod("GET");

        int getStatus = getConnection.getResponseCode();
        assertEquals(200, getStatus, "O código de status deve ser 200 - encontrado com sucesso");
    }

    @Test
    public void testListarTodosMensalistas() throws Exception {
        URL url = new URL("http://localhost:7000/funcionarios/mensalistas");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();
        assertEquals(200, status, "O código de status deve ser 200 - lista retornada com sucesso");
    }

}

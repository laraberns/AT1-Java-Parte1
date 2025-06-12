package infnet.app;

import io.javalin.Javalin;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloTest {

    private static Javalin app;

    @BeforeAll
    public static void startServer() {
        app = Javalin.create()
                .get("/hello", ctx -> ctx.result("Hello, Javalin!"))
                .start(7000);
    }

    @AfterAll
    public static void stopServer() {
        app.stop();
    }

    @Test
    public void testHelloEndpoint() throws Exception {
        URL url = new URL("http://localhost:7000/hello");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();
        assertEquals(200, status, "O código de status deve ser 200");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = in.readLine();
        in.close();

        assertEquals("Hello, Javalin!", response, "O corpo da resposta deve ser 'Hello, Javalin!'");
    }
}

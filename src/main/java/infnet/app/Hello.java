package infnet.app;

import io.javalin.Javalin;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Hello {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7002);

        app.get("/hello", ctx -> ctx.result("Hello, Javalin!"));

        app.get("/status", ctx -> {
            Map<String, String> response = new HashMap<>();
            response.put("status", "ok");
            response.put("timestamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
            ctx.json(response);
        });

        app.post("/echo", ctx -> {
            try {
                Map<String, Object> request = ctx.bodyAsClass(Map.class);

                if (request == null || !request.containsKey("mensagem")) {
                    ctx.status(400).result("Erro: JSON deve conter a chave 'mensagem'");
                    return;
                }

                Object mensagem = request.get("mensagem");
                if (mensagem == null) {
                    ctx.status(400).result("Erro: valor de 'mensagem' não pode ser nulo");
                    return;
                }

                Map<String, Object> response = new HashMap<>();
                response.put("mensagem", mensagem);

                ctx.json(response);
            } catch (Exception e) {
                ctx.status(400).result("Erro: corpo da requisição inválido ou formato JSON incorreto");
            }
        });

        app.get("/saudacao/{nome}", ctx -> {
            String nome = ctx.pathParam("nome");
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Olá, " + nome + "!");
            ctx.json(response);
        });
    }
}

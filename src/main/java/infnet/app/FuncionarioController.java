package infnet.app;

import infnet.domain.Funcionario;
import infnet.domain.Mensalista;
import infnet.infra.FuncionarioRepository;
import io.javalin.Javalin;

import java.util.List;

public class FuncionarioController {

    private Javalin server;
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioController(Javalin server) {
        this.server = server;
        funcionarioRepository = new FuncionarioRepository();

        //--------------------------------------------------
        //Rotas de serviço

        server.get("/funcionarios", ctx -> {
            List<Funcionario> funcionarios = funcionarioRepository.findAll();
            if (funcionarios != null && !funcionarios.isEmpty()) {
                ctx.json(funcionarios);
                ctx.status(200);
            } else {
                ctx.status(404);
            }
        });

        server.post("/funcionarios/mensalista", ctx -> {
            try {
                Mensalista mensalista = ctx.bodyAsClass(Mensalista.class);

                if (mensalista.getNome() == null || mensalista.getNome().isBlank()) {
                    ctx.status(400).result("Nome é obrigatório.");
                    return;
                }
                if (mensalista.getCargo() == null || mensalista.getCargo().isBlank()) {
                    ctx.status(400).result("Cargo é obrigatório.");
                    return;
                }
                if (mensalista.getSalario() <= 0) {
                    ctx.status(400).result("Salário deve ser maior que zero.");
                    return;
                }

                if (funcionarioRepository.matriculaExists(mensalista.getMatricula())) {
                    ctx.status(409).result("Matrícula já cadastrada.");
                    return;
                }

                funcionarioRepository.saveMensalista(mensalista);
                ctx.status(201).result("Mensalista cadastrado com sucesso.");

            } catch (Exception e) {
                ctx.status(500).result("Erro interno ao cadastrar mensalista.");
                e.printStackTrace();
            }
        });

        server.get("/funcionarios/mensalistas", ctx -> {
            List<Mensalista> mensalistas = funcionarioRepository.findAllMensalistas();
            if (mensalistas != null && !mensalistas.isEmpty()) {
                ctx.json(mensalistas);
                ctx.status(200);
            } else {
                ctx.status(404).result("Nenhum mensalista encontrado.");
            }
        });

        server.get("/funcionarios/mensalista/{id}", ctx -> {
            try {
                long id = Long.parseLong(ctx.pathParam("id"));
                Mensalista mensalista = funcionarioRepository.findMensalistaById(id);
                if (mensalista != null) {
                    ctx.json(mensalista);
                    ctx.status(200);
                } else {
                    ctx.status(404).result("Mensalista não encontrado.");
                }
            } catch (NumberFormatException e) {
                ctx.status(400).result("ID inválido.");
            } catch (Exception e) {
                ctx.status(500).result("Erro interno.");
                e.printStackTrace();
            }
        });



    }
}

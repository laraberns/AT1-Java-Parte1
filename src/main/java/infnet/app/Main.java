package infnet.app;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        //-------------------------------------------------
        //Inicialização do Servidor
        Javalin server = Javalin.create().start(8000);
        //-------------------------------------------------
        //Inicialização dos controllers
        FuncionarioController funcionarioController = new FuncionarioController(server);

    }
}

package Raiz.Acesso;

import Cadastro.Database.dataSet;

public class Running {

    private static final dataSet<?> banco;

    static {
        banco = new dataSet<>();
    }

    public static void main(String[] args) {
        Login Start = new Login(banco);
        Start.run();
    }
}

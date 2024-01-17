package Raiz.Acesso;

import Cadastro.Database.DataSet;

public class Running {

    private static final DataSet<?> banco;

    static {
        banco = new DataSet<>();
    }

    public static void main(String[] args) {
        Login Start = new Login(banco);
        Start.run();
    }
}

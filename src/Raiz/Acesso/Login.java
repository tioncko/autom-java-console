package Raiz.Acesso;

import Cadastro.Database.AdminAccess;
import Cadastro.Database.DataSet;
import Cadastro.Database.Metodos.*;

public class Login {

    MenuPrincipal mp;
    MetodosUsuario musr;
    //AdminAccess DT;
    DataSet<?> banco;

    public Login(DataSet<?> DS) {
        this.mp = new MenuPrincipal(DS);
        this.musr = new MetodosUsuario(DS);
        //this.DT = new AdminAccess(DS);
        this.banco = DS;
    }

    private static String usr;

    public static String getUsr() {
        return usr;
    }
    public void setUsr(String usr) {
        Login.usr = usr;
    }

    public void run() {

        System.out.println("\n---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------- @@@@ -- @@@ - @@@ @@@@@@@@@  @@@@@@@  @@@@@  @@@@@ -------");
        System.out.println("--------- @@@@@@ - @@@ - @@@ @@@@@@@@@ @@@ - @@@ @@@@@@@@@@@@ -------");
        System.out.println("-------- @@@  @@@  @@@ - @@@ -- @@@ -- @@@ - @@@ @@@ @@@@ @@@ -------");
        System.out.println("------- @@@@@@@@@@ @@@ - @@@ -- @@@ -- @@@ - @@@ @@@  @@  @@@ -------");
        System.out.println("------ @@@ ---- @@@ @@@@@@@ --- @@@ --- @@@@@@@  @@@ ---- @@@ -------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------\n");

        int x = 0;
        do{
            String login = mp.ReadText("Login: ").toLowerCase();
            String pass = mp.ReadText("Senha: ");

            if (musr.validLoginUsuario(login, pass)){//, DT.DTUsers())) {
                setUsr(login);
                mp.paginaInicial(banco);
                x = 1;
            } else {
                System.out.println("\nUsuário inexistente ou senha incorreta.\nTente novamente.\n");
            }
        } while (x == 0);
    }
}

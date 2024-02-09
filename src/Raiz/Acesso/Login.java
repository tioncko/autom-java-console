package Raiz.Acesso;

import Cadastro.Database.dataSet;
import Cadastro.Database.Metodos.*;

public class Login {

    menuPrincipal mp;
    metodosUsuarios musr;
    //rootAccess DT;
    dataSet<?> banco;

    public Login(dataSet<?> DS) {
        this.mp = new menuPrincipal(DS);
        this.musr = new metodosUsuarios(DS);
        //this.DT = new rootAccess(DS);
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
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------- @@@@ -- @@@ - @@@ @@@@@@@@@  @@@@@@@  @@@@@  @@@@@ -------");
        System.out.println("--------- @@@@@@ - @@@ - @@@ @@@@@@@@@ @@@ - @@@ @@@@@@@@@@@@ -------");
        System.out.println("-------- @@@  @@@  @@@ - @@@ -- @@@ -- @@@ - @@@ @@@ @@@@ @@@ -------");
        System.out.println("------- @@@@@@@@@@ @@@ - @@@ -- @@@ -- @@@ - @@@ @@@  @@  @@@ -------");
        System.out.println("------ @@@ ---- @@@ @@@@@@@ --- @@@ --- @@@@@@@  @@@ ---- @@@ -------");
        System.out.println("--------------------------------------------------------v1.19--------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------\n");

        int x = 0;
        do{
            String login = mp.readSentence("Login: ").toLowerCase();
            String pass = mp.readSentence("Senha: ");

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

package AutomRoot;

import Database.DataTables;
import Database.Metodos.*;

public class Login extends MetodosUsuario {

    MenuPrincipal mp;
    DataTables DT;

    public Login() {
        this.mp = new MenuPrincipal();
        this.DT = new DataTables();
    }

    private static String usr;

    public static String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        Login.usr = usr;
    }

    public void run() throws Exception {

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

            if (validLoginUsuario(login, pass, DT.DTUsers())) {
                setUsr(login);
                mp.paginaInicial();
                x = 1;
            } else {
                System.out.println("\nUsuário inexistente ou senha incorreta.\nTente novamente.\n");
            }
        } while (x == 0);
    }
}

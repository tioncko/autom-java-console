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
        this.usr = usr;
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
        System.out.println("---------------------------------------------------------------------");

        int x = 0;
        do{
            DT.DTUsers();

            String login = mp.ReadText("Login: ");
            String pass = mp.ReadText("Senha: ");

            if (validUsuario(login, pass, DT.DTUsers())) {
                setUsr(login);
                mp.paginaInicial();
                x = 1;
            } else {
                System.out.println("\nUsuário inexistente ou senha incorreta.\nTente novamente.\n");
                x = 0;
            }
        } while (x == 0);
    }
}

package AutomRoot;

import NovosDados.AreasMenu.AreaCadastroCliente;
import NovosDados.AreasMenu.AreaCadastroUsuario;
import Utils.Objetos.LeitorDados;

public class MenuPrincipal extends LeitorDados {

    public MenuPrincipal() {
    }

    public void paginaInicial() throws Exception {
        System.out.println
                ("\n=========================================================\n" +
                        Login.getUsr() +
                        "\n=========================================================");
        System.out.println("\nMenu:");
        System.out.println("1 - Cadastro");
        System.out.println("2 - Loja");
        //System.out.println("\n\033[3m- Digitar código do menu para suas ações -\033[0m\n");

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        switch (id) {
            case "1":
                menuCadastro();
                break;
            case "2":
                //menuloja();
                break;
            default:
                break;
        }
    }

    public void menuCadastro() throws Exception {
        System.out.println("\nCadastro:");
        System.out.println("1 - Cliente");
        System.out.println("2 - Fornecedor");
        System.out.println("3 - Usuário");
        System.out.println("4 - Produto");
        System.out.println("5 - Serviço");

        AreaCadastroCliente acad = new AreaCadastroCliente();
        AreaCadastroUsuario acau = new AreaCadastroUsuario();

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        switch (id) {
            case "1":
                acad.menuCadastroCliente();
                break;
            case "2":
                //acaf.menuCadastroFornecedor();
                break;
            case "3":
                acau.menuCadastroUsuario();
                break;
            case "4":
                //acap.menuCadastroProduto();
                break;
            case "5":
                //acas.menuCadastroServico();
                break;
        }
    }
}

//#region notes
    /*
    public static void main(String[] args) {

        Cadastro cad = new Cadastro();
        Cadastro.AcoesCliente ac = cad.new AcoesCliente();

        ac.cadastrarCliente("Jorge", 22, "04472205484", "teste@olos.com.br", "014585445489", "04472205", 38);
        ac.listarClientes();
    }*/
//#endregion

package AutomRoot;

import Database.DTO;
import Database.Metodos.*;
import NovosDados.AreasMenu.*;
import NovosDados.Repositorio.Enums.Permissao;
import Utils.*;

public class MenuPrincipal extends LeitorDados {

    DTO dtb;
    MetodosUsuario mu ;

    public MenuPrincipal() {
        this.mu = new MetodosUsuario();
        this.dtb = new DTO();
    }

    public void paginaInicial() throws Exception {

        String logged = Login.getUsr();
        char[] charLogin = logged.toCharArray();
        String tab = "============================================";
        String ovtab = (tab.substring(0, String.valueOf(charLogin).length()));
        String supTab = (tab.substring(String.valueOf(charLogin).length() + 1));

        StringBuilder currentLogin = new StringBuilder();
        int i = 0;
        for (char c : ovtab.toCharArray()){
            c = charLogin[i];
            currentLogin.append(c);
            i++;
        };

        if(MetodosUtils.Tools.Daily().equals("Bom dia")){
            System.out.println
                    ("\n=====================================================================\n" +
                            "============= " + MetodosUtils.Tools.Daily() + ", " + currentLogin + " " + supTab + "==" +
                            "\n=====================================================================");
        }
        if(MetodosUtils.Tools.Daily().equals("Boa tarde") || MetodosUtils.Tools.Daily().equals("Boa noite")){
            System.out.println
                    ("\n=====================================================================\n" +
                            "============= " + MetodosUtils.Tools.Daily() + ", " + currentLogin + " " + supTab +
                            "\n=====================================================================");
        }

        System.out.println("\nMenu:");
        System.out.println("1 - Cadastro");
        System.out.println("2 - Loja");

        String access = String.valueOf(mu.validPermissao(logged, dtb.DTUsers()));
        Integer userId = mu.UserId(logged, dtb.DTUsers());

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        switch (id) {
            case "1":
                if(!(access.equals(String.valueOf(Permissao.USER)))) {
                    menuCadastro(userId);
                }
                break;
            case "2":
                //menuloja();
                break;
            default:
                break;
        }
    }

    public void menuCadastro(Integer userId) throws Exception {
        System.out.println("\nCadastro:");
        System.out.println("1 - Cliente");
        System.out.println("2 - Fornecedor");
        System.out.println("3 - Usuário");
        System.out.println("4 - Produto");
        System.out.println("5 - Serviço");
        System.out.println("* - Voltar para a tela anterior");

        AreaCadastroCliente acad = new AreaCadastroCliente();
        AreaCadastroUsuario acau = new AreaCadastroUsuario();
        AreaCadastroFornecedor acaf = new AreaCadastroFornecedor();
        AreaCadastroProduto acap = new AreaCadastroProduto();
        //AreaCadastroServico acas = new AreaCadastroServico();

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        switch (id) {
            case "1":
                acad.menuCadastroCliente(userId);
                break;
            case "2":
                acaf.menuCadastroFornecedor(userId);
                break;
            case "3":
                acau.menuCadastroUsuario(userId);
                break;
            case "4":
               // acap.menuCadastroProduto(userId);
                break;
            case "5":
                //acas.menuCadastroServico();
                break;
            case "*":
                paginaInicial();
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

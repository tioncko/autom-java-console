package Raiz.Acesso;

import Cadastro.Database.AdminAccess;
import Cadastro.Database.DataSet;
import Cadastro.Database.Metodos.*;
import Cadastro.NovosDados.Areas.*;
import Cadastro.NovosDados.Repositorio.Enums.permissao;
import Raiz.Utils.*;

public class MenuPrincipal extends LeitorDados {

    //AdminAccess dtb;
    MetodosUsuario mu;
    DataSet<?> banco;
/*
    public MenuPrincipal() {
        this.mu = new MetodosUsuario();
        this.dtb = new AdminAccess();
    }
 */

    public MenuPrincipal(DataSet<?> DS) {
        this.mu = new MetodosUsuario(DS);
        //this.dtb = new AdminAccess(DS);
        this.banco = DS;
    }

    public void paginaInicial(DataSet<?> banco) {

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

        if(SmartTools.DiaAtual.Daily().equals("Bom dia")){
            System.out.println
            ("\n=====================================================================\n" +
             "============= " + SmartTools.DiaAtual.Daily() + ", \u001B[31m" + currentLogin + " " + supTab + "\u001B[0m==" +
             "\n=====================================================================");
        }
        if(SmartTools.DiaAtual.Daily().equals("Boa tarde") || SmartTools.DiaAtual.Daily().equals("Boa noite")){
            System.out.println
            ("\n=====================================================================\n" +
             "============= " + SmartTools.DiaAtual.Daily() + ", \u001B[31m" + currentLogin + "\u001B[0m " + supTab +
             "\n=====================================================================");
        }

        System.out.println("\n\u001B[36mMenu:\u001B[0m");
        System.out.println("1 - Cadastro");
        System.out.println("2 - Loja");

        String access = String.valueOf(mu.validPermissao(logged));//, dtb.DTUsers()));
        Integer userId = mu.UserId(logged);//, dtb.DTUsers());

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        switch (id) {
            case "1":
                if(!(access.equals(String.valueOf(permissao.USER)))) {
                    menuCadastro(userId, banco);
                }
                break;
            case "2":
                //menuloja();
                break;
            default:
                break;
        }
    }

    public void menuCadastro(Integer userId, DataSet<?> banco) {
        System.out.println("\n\u001B[36mCadastro:\u001B[0m");
        System.out.println("1 - Cliente");
        System.out.println("2 - Fornecedor");
        System.out.println("3 - Usuário");
        System.out.println("4 - Produto");
        System.out.println("5 - Serviço");
        System.out.println("* - Voltar para a tela anterior");

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        switch (id) {
            case "1":
                AreaCadastroCliente acad = new AreaCadastroCliente(banco);
                acad.menuCadastroCliente(userId);
                break;
            case "2":
                AreaCadastroFornecedor acaf = new AreaCadastroFornecedor(banco);
                acaf.menuCadastroFornecedor(userId);
                break;
            case "3":
                AreaCadastroUsuario acau = new AreaCadastroUsuario(banco);
                acau.menuCadastroUsuario(userId);
                break;
            case "4":
                AreaCadastroProduto acap = new AreaCadastroProduto(banco);
                acap.menuCadastroProduto(userId);
                break;
            case "5":
                AreaCadastroServico acas = new AreaCadastroServico(banco);
                acas.menuCadastroServico(userId);
                break;
            case "*":
                paginaInicial(banco);
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

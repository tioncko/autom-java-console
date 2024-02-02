package Raiz.Acesso;

import Cadastro.Database.dataSet;
import Cadastro.Database.Metodos.*;
import Cadastro.NovosDados.Areas.*;
import Cadastro.NovosDados.Repositorio.Enums.permissao;
import Raiz.Utils.*;

public class menuPrincipal extends leitorDados {

    metodosUsuarios mu;
    dataSet<?> banco;

    public menuPrincipal(dataSet<?> DS) {
        this.mu = new metodosUsuarios(DS);
        this.banco = DS;
    }

    public void paginaInicial(dataSet<?> banco) {

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

        if(smartTools.DiaAtual.Daily().equals("Bom dia")){
            System.out.println
            ("\n=====================================================================\n" +
             "============= " + smartTools.DiaAtual.Daily() + ", \u001B[31m" + currentLogin + "\u001B[0m " + supTab + "==" +
             "\n=====================================================================");
        }
        if(smartTools.DiaAtual.Daily().equals("Boa tarde") || smartTools.DiaAtual.Daily().equals("Boa noite")){
            System.out.println
            ("\n=====================================================================\n" +
             "============= " + smartTools.DiaAtual.Daily() + ", \u001B[31m" + currentLogin + "\u001B[0m " + supTab +
             "\n=====================================================================");
        }

        System.out.println("\n\u001B[36mMenu:\u001B[0m");
        System.out.println("1 - Cadastro");
        System.out.println("2 - Loja");

        String access = String.valueOf(mu.validPermissao(logged));//, dtb.DTUsers()));
        Integer userId = mu.userId(logged);//, dtb.DTUsers());

        System.out.print("\n-----------------------------------------");
        String id = readText("\n\033[3mDigite código do menu para suas ações: \033[0m");
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

    public void menuCadastro(Integer userId, dataSet<?> banco) {
        System.out.println("\n\u001B[36mCadastro:\u001B[0m");
        System.out.println("1 - Clientes");
        System.out.println("2 - Fornecedores");
        System.out.println("3 - Usuários");
        System.out.println("4 - Produtos");
        System.out.println("5 - Serviços");
        System.out.println("6 - Funcionários");
        System.out.println("7 - Carros");
        System.out.println("* - Voltar para a tela anterior");

        System.out.print("\n-----------------------------------------");
        String id = readText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        switch (id) {
            case "1":
                areaCadastroCliente acad = new areaCadastroCliente(banco);
                acad.menuAreaCadastro(userId);
                break;
            case "2":
                areaCadastroFornecedor acaf = new areaCadastroFornecedor(banco);
                acaf.menuAreaCadastro(userId);
                break;
            case "3":
                areaCadastroUsuario acau = new areaCadastroUsuario(banco);
                acau.menuAreaCadastro(userId);
                break;
            case "4":
                areaCadastroProduto acap = new areaCadastroProduto(banco);
                acap.menuAreaCadastro(userId);
                break;
            case "5":
                areaCadastroServico acas = new areaCadastroServico(banco);
                acas.menuAreaCadastro(userId);
                break;
            case "6":
                areaCadastroFuncionario acan = new areaCadastroFuncionario(banco);
                acan.menuAreaCadastro(userId);
                break;
            case "7":
                areaCadastroCarro acar = new areaCadastroCarro(banco);
                acar.menuAreaCadastro(userId);
                break;
            case "*":
                paginaInicial(banco);
                break;
        }
    }
}
    //#region rascunho
    /*

    //rootAccess dtb;
    public menuPrincipal() {
        this.mu = new metodosUsuarios();
        this.dtb = new rootAccess();
    }

        //this.dtb = new rootAccess(DS);

    public static void main(String[] args) {

        Cadastro cad = new Cadastro();
        Cadastro.AcoesClientes ac = cad.new AcoesClientes();

        ac.cadastrarCliente("Jorge", 22, "04472205484", "teste@olos.com.br", "014585445489", "04472205", 38);
        ac.listarClientes();
    }*/
//#endregion

package NovosDados.Areas;

import AutomRoot.MenuPrincipal;
import InterfaceInical.Cadastro;

public class AreaCadastroCliente {

    MenuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesCliente ac;

    public AreaCadastroCliente() {
        this.mp = new MenuPrincipal();
        this.cad = new Cadastro();
        this.ac = cad.new AcoesCliente();
    }

    public void menuCadastroCliente() {
        System.out.println("Cliente: \n");
        System.out.println("1 - cadastrarCliente");
        System.out.println("2 - alterarCliente");
        System.out.println("3 - excluirCliente");
        System.out.println("4 - localizarCliente");
        System.out.println("5 - localizarMaisClientes");
        System.out.println("6 - removerMaisClientes");
        System.out.println("7 - listarClientes");

        String id = "1";
        AcoesCadastroCliente(id);
    }

    void AcoesCadastroCliente(String id) {
        boolean session = true;
        while (session) {
            switch (id) {
                case "1":
                    System.out.println("\nCadastrar novo cliente:\n");
                    ac.cadastrarCliente("Jorge", 22, "04472205484", "teste@olos.com.br", "014585445489", "04472205", 38);

                    System.out.println("Cadastro concluído!");
                    System.out.println("O que deseja? (1) Permanecer na tela de cadastro do cliente;\n(2) Retornar ao menu principal\n(3)Ir para o menu de cadastro geral?");

                    Integer opcaoCadCli = 2;
                    if (opcaoCadCli == 1) menuCadastroCliente();
                    if (opcaoCadCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoCadCli == 3) mp.menuCadastro();
                    break;
                case "2":
                    System.out.println("\nAlterar um cliente:\n");
                    ac.listarClientes();
                    ac.alterarCliente(4, "Nome", "Nayara");

                    System.out.println("Alteração concluída!");
                    System.out.println("O que deseja? (1) Permanecer na tela de cadastro do cliente;\n(2) Retornar ao menu principal\n(3)Ir para o menu de cadastro geral?");

                    Integer opcaoAltCli = 2;
                    if (opcaoAltCli == 1) menuCadastroCliente();
                    if (opcaoAltCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoAltCli == 3) mp.menuCadastro();
                    break;
                case "3":
                    System.out.println("\nExcluir um cliente:\n");
                    ac.excluirCliente(1);

                    System.out.println("Exclusão concluída!");
                    System.out.println("O que deseja? (1) Permanecer na tela de cadastro do cliente;\n(2) Retornar ao menu principal\n(3)Ir para o menu de cadastro geral?");

                    Integer opcaoExcCli = 2;
                    if (opcaoExcCli == 1) menuCadastroCliente();
                    if (opcaoExcCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoExcCli == 3) mp.menuCadastro();
                    break;
                case "4":
                    System.out.println("\nLocalizar um cliente:\n");
                    ac.localizarCliente(1);

                    System.out.println("O que deseja? (1) Permanecer na tela de cadastro do cliente;\n(2) Retornar ao menu principal\n(3)Ir para o menu de cadastro geral?");

                    Integer opcaoLocCli = 2;
                    if (opcaoLocCli == 1) menuCadastroCliente();
                    if (opcaoLocCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoLocCli == 3) mp.menuCadastro();
                    break;
                case "5":
                    System.out.println("\nLocalizar vários clientes:\n");
                    ac.localizarMaisClientes(1, 3);

                    System.out.println("O que deseja? (1) Permanecer na tela de cadastro do cliente;\n(2) Retornar ao menu principal\n(3)Ir para o menu de cadastro geral?");

                    Integer opcaoLocMCli = 2;
                    if (opcaoLocMCli == 1) menuCadastroCliente();
                    if (opcaoLocMCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoLocMCli == 3) mp.menuCadastro();
                    break;
                case "6":
                    System.out.println("\nRemover vários clientes:\n");
                    ac.removerMaisClientes(1, 3);

                    System.out.println("O que deseja? (1) Permanecer na tela de cadastro do cliente;\n(2) Retornar ao menu principal\n(3)Ir para o menu de cadastro geral?");

                    Integer opcaoRemMCli = 2;
                    if (opcaoRemMCli == 1) menuCadastroCliente();
                    if (opcaoRemMCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoRemMCli == 3) mp.menuCadastro();
                    break;
                default:
                    break;
            }
        }
    }
}


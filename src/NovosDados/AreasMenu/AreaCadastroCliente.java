package NovosDados.AreasMenu;

import AutomRoot.MenuPrincipal;
import InterfaceInical.Cadastro;
import Utils.Objetos.LeitorDados;

public class AreaCadastroCliente extends LeitorDados {

    MenuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesCliente ac;

    public AreaCadastroCliente() throws Exception {
        this.mp = new MenuPrincipal();
        this.cad = new Cadastro();
        this.ac = cad.new AcoesCliente();
    }

    public void menuCadastroCliente() throws Exception {
        System.out.println("\nCliente:");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Alterar Cliente");
        System.out.println("3 - Excluir Cliente");
        System.out.println("4 - Localizar Cliente");
        System.out.println("5 - Localizar Mais Clientes");
        System.out.println("6 - Remover Mais Clientes");
        System.out.println("7 - Listar Clientes");

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        AcoesCadastroCliente(id);
    }

    void AcoesCadastroCliente(String id) throws Exception {
        boolean session = true;
        while (session) {
            switch (id) {
                case "1":
                    System.out.println("\n# Cadastrar novo cliente #\n");
                    //ac.cadastrarCliente("Jorge", 22, "04472205484", "teste@olos.com.br", "014585445489", "04472205", 38);
                    ac.cadastrarCliente(
                            ReadText("Nome: "),
                            ReadInt("Idade: "),
                            ReadText("CPF: "),
                            ReadText("E-mail: "),
                            ReadText("Telefone: "),
                            ReadText("CEP: "),
                            ReadInt("Nùmero da residência: ")
                    );
                    System.out.println("\nCadastro concluído!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoCadCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoCadCli == 1) menuCadastroCliente();
                    if (opcaoCadCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoCadCli == 3) mp.menuCadastro();
                    if (opcaoCadCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "2":
                    System.out.println("\n# Alterar um cliente #\n");
                    ac.listarClientes();
                    ac.alterarCliente(
                            ReadInt("Id: "),
                            ReadText("Campo: "),
                            ReadText("Alteração: ")
                    );
                    System.out.println("\nAlteração concluída!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral?" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoAltCli == 1) menuCadastroCliente();
                    if (opcaoAltCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoAltCli == 3) mp.menuCadastro();
                    if (opcaoAltCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "3":
                    System.out.println("\n# Excluir um cliente #\n");
                    ac.excluirCliente(
                            ReadInt("Id: ")
                    );
                    System.out.println("\nExclusão concluída!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoExcCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoExcCli == 1) menuCadastroCliente();
                    if (opcaoExcCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoExcCli == 3) mp.menuCadastro();
                    if (opcaoExcCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "4":
                    System.out.println("\n# Localizar um cliente #\n");
                    ac.localizarCliente(
                            ReadInt("Id: ")
                    );

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocCli == 1) menuCadastroCliente();
                    if (opcaoLocCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoLocCli == 3) mp.menuCadastro();
                    if (opcaoLocCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "5":
                    System.out.println("\n# Localizar vários clientes #\n");
                    ac.localizarMaisClientes(
                            ReadInt("Início: "),
                            ReadInt("Fim: ")
                    );

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocMCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocMCli == 1) menuCadastroCliente();
                    if (opcaoLocMCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoLocMCli == 3) mp.menuCadastro();
                    if (opcaoLocMCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "6":
                    System.out.println("\n# Remover vários clientes #\n");
                    ac.removerMaisClientes(
                            ReadInt("Início: "),
                            ReadInt("Fim: ")
                    );

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemMCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoRemMCli == 1) menuCadastroCliente();
                    if (opcaoRemMCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoRemMCli == 3) mp.menuCadastro();
                    if (opcaoRemMCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "7":
                    System.out.println("\n# Lista de clientes #\n");
                    ac.listarClientes();

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoListUsr = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoListUsr == 1) menuCadastroCliente();
                    if (opcaoListUsr == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoListUsr == 3) mp.menuCadastro();
                    if (opcaoListUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                default:
                    break;
            }
        }
    }
}


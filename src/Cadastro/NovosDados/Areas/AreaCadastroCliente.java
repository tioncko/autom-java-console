package Cadastro.NovosDados.Areas;

import Cadastro.Database.DataSet;
import Cadastro.Database.Metodos.MetodosCliente;
import Cadastro.Database.Metodos.MetodosFornecedor;
import Cadastro.Database.Metodos.MetodosServico;
import Raiz.Acesso.MenuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.LeitorDados;

public class AreaCadastroCliente extends LeitorDados implements IAreaCadastro.IClientes {

    MenuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesCliente ac;
    DataSet<?> banco;

    public AreaCadastroCliente(DataSet<?> DS) {
        this.mp = new MenuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.ac = cad.new AcoesCliente();
        this.banco = DS;
    }

    public void menuCadastroCliente(Integer userId)  {
        System.out.println("\n\u001B[34mCliente:\u001B[0m");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Alterar Cliente");
        System.out.println("3 - Excluir Cliente");
        System.out.println("4 - Localizar Cliente");
        System.out.println("5 - Localizar Mais Clientes");
        System.out.println("6 - Remover Mais Clientes");
        System.out.println("7 - Listar Clientes");
        System.out.println("* - Mais");

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        AcoesCadastroCliente(id, userId);
    }

    public void AcoesCadastroCliente(String id, Integer userId) {
        boolean session = true;
        while (session) {
            switch (id) {
                case "1":
                    //#region Cadastrar novo cliente
                    System.out.println("\n# Cadastrar novo cliente #\n");
                    //ac.cadastrarCliente("Jorge", 22, "04472205484", "teste@olos.com.br", "014585445489", "04472205", 38);
                    ac.cadastrarCliente(
                            ReadSentence("Nome: "),
                            ReadInt("Idade: "),
                            ReadMask("CPF: "),
                            ReadText("E-mail: "),
                            ReadSentence("Telefone: "),
                            ReadText("CEP: ").replace("-",""),
                            ReadInt("Nùmero da residência: ")
                    );
                    if (!(MetodosCliente.message == null))
                    System.out.println(MetodosCliente.message);
                    else System.out.println("\nCadastro concluído!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoCadCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoCadCli == 1) menuCadastroCliente(userId);
                    if (opcaoCadCli == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoCadCli == 3) mp.menuCadastro(userId, banco);
                    if (opcaoCadCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "2":
                    //#region Alterar um cliente
                    System.out.println("\n# Alterar um cliente #\n");
                    if(ac.listarClientes()){
                        System.out.println();

                        int alterId = ReadInt("Id: ");
                        if (ac.validarId(alterId)) {
                            String field = ReadText("Campo: ");

                            if (field.equalsIgnoreCase("CPF")){
                                String document = ReadMask("Alteração (" + field.toUpperCase() + "): ");

                                if(document != null) {
                                    ac.alterarCliente(
                                            alterId,
                                            field,
                                            document
                                    );

                                    if (!(MetodosCliente.message == null))
                                        System.out.println(MetodosCliente.message);
                                    else System.out.println("\nAlteração concluída!");
                                    ac.localizarCliente(alterId);
                                }
                                else System.out.println("\nNão foi possível realizar a alteração solicitada.");
                            } else {
                                ac.alterarCliente(
                                        alterId,
                                        field,
                                        ReadSentence("Alteração (" + field.toUpperCase() + "): ")
                                );

                                if (!(MetodosCliente.message == null))
                                    System.out.println(MetodosCliente.message);
                                else System.out.println("\nAlteração concluída!");
                                ac.localizarCliente(alterId);
                            }
                        }
                        if (!(MetodosCliente.message == null))
                            System.out.println(MetodosCliente.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral?" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoAltCli == 1) menuCadastroCliente(userId);
                    if (opcaoAltCli == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoAltCli == 3) mp.menuCadastro(userId, banco);
                    if (opcaoAltCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "3":
                    //#region Excluir um cliente
                    System.out.println("\n# Excluir um cliente #\n");
                    if(ac.listarClientes()) {
                        System.out.println();

                        int remoId = ReadInt("Id: ");
                        if (ac.validarId(remoId)) {
                            ac.excluirCliente(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(MetodosCliente.message == null))
                                System.out.println(MetodosCliente.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(MetodosCliente.message == null))
                            System.out.println(MetodosCliente.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoExcCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoExcCli == 1) menuCadastroCliente(userId);
                    if (opcaoExcCli == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoExcCli == 3) mp.menuCadastro(userId, banco);
                    if (opcaoExcCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "4":
                    //#region Localizar um cliente
                    System.out.println("\n# Localizar um cliente #\n");
                    if(ac.listarClientes()){
                        System.out.println();

                        int findId = ReadInt("Id: ");
                        if (ac.validarId(findId)) {
                            ac.localizarCliente(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(MetodosCliente.message == null))
                                System.out.println(MetodosCliente.message);
                        }
                        if (!(MetodosCliente.message == null))
                            System.out.println(MetodosCliente.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocCli == 1) menuCadastroCliente(userId);
                    if (opcaoLocCli == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoLocCli == 3) mp.menuCadastro(userId, banco);
                    if (opcaoLocCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "5":
                    //#region Localizar vários clientes
                    System.out.println("\n# Localizar vários clientes #\n");
                    if(ac.listarClientes()){
                        System.out.println();

                        int findId = ReadInt("Início: ");
                        if (ac.validarId(findId)) {
                            ac.localizarMaisClientes(
                                    findId, //ReadInt("Início: "),
                                    ReadInt("Fim: "));
                            if (!(MetodosCliente.message == null))
                                System.out.println(MetodosCliente.message);
                        }
                        if (!(MetodosCliente.message == null))
                            System.out.println(MetodosCliente.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocMCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocMCli == 1) menuCadastroCliente(userId);
                    if (opcaoLocMCli == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoLocMCli == 3) mp.menuCadastro(userId, banco);
                    if (opcaoLocMCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "6":
                    //#region Remover vários clientes
                    System.out.println("\n# Remover vários clientes #\n");
                    if(ac.listarClientes()){
                        System.out.println();

                        int remoId = ReadInt("Início: ");
                        if (ac.validarId(remoId)) {
                            ac.removerMaisClientes(
                                    remoId, //ReadInt("Início: "),
                                    ReadInt("Fim: ")
                            );
                            if (!(MetodosCliente.message == null))
                                System.out.println(MetodosCliente.message);
                        }
                        if (!(MetodosCliente.message == null))
                            System.out.println(MetodosCliente.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemMCli = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoRemMCli == 1) menuCadastroCliente(userId);
                    if (opcaoRemMCli == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoRemMCli == 3) mp.menuCadastro(userId, banco);
                    if (opcaoRemMCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "7":
                    //#region Lista de clientes
                    System.out.println("\n# Lista de clientes #\n");
                    ac.listarClientes();

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoListUsr = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoListUsr == 1) menuCadastroCliente(userId);
                    if (opcaoListUsr == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoListUsr == 3) mp.menuCadastro(userId, banco);
                    if (opcaoListUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "*":
                    //#region Retorno ao menu
                    Integer opcaoVoltar = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoVoltar == 1) menuCadastroCliente(userId);
                    if (opcaoVoltar == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoVoltar == 3) mp.menuCadastro(userId, banco);
                    if (opcaoVoltar == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                default:
                    break;
            }
        }
    }
}


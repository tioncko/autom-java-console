package Cadastro.NovosDados.Areas;

import Cadastro.Database.dataSet;
import Cadastro.Database.Metodos.metodosCliente;
import Raiz.Acesso.menuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.leitorDados;

public class areaCadastroCliente extends leitorDados implements IAreaCadastro.IClientes {

    menuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesCliente ac;
    dataSet<?> banco;

    public areaCadastroCliente(dataSet<?> DS) {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.ac = cad.new AcoesCliente();
        this.banco = DS;
    }

    public void menuCadastroCliente(Integer userId) {
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
        String id = readText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        acoesCadastroCliente(id, userId);
    }

    public void acoesCadastroCliente(String id, Integer userId) {
        boolean session = true;
        while (session) {
            switch (id) {
                case "1":
                    //#region Cadastrar novo cliente
                    System.out.println("\n# Cadastrar novo cliente #\n");
                    //ac.cadastrarCliente("Jorge", 22, "04472205484", "teste@olos.com.br", "014585445489", "04472205", 38);
                    ac.cadastrarCliente(
                            readSentence("Nome: "),
                            readInt("Idade: "),
                            readMask("CPF: "),
                            readText("E-mail: "),
                            readSentence("Telefone: "),
                            readText("CEP: ").replace("-",""),
                            readInt("Nùmero da residência: ")
                    );
                    if (!(metodosCliente.message == null))
                    System.out.println(metodosCliente.message);
                    else System.out.println("\nCadastro concluído!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoCadCli = readInt("\n\033[3mO que deseja?" +
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

                        int alterId = readInt("Id: ");
                        if (ac.validarId(alterId)) {
                            String field = readText("Campo: ");

                            if (field.equalsIgnoreCase("CPF")){
                                String document = readMask("Alteração (" + field.toUpperCase() + "): ");

                                if(document != null) {
                                    ac.alterarCliente(
                                            alterId,
                                            field,
                                            document
                                    );

                                    if (!(metodosCliente.message == null))
                                        System.out.println(metodosCliente.message);
                                    else System.out.println("\nAlteração concluída!");
                                    ac.localizarCliente(alterId);
                                }
                                else System.out.println("\nNão foi possível realizar a alteração solicitada.");
                            } else {
                                ac.alterarCliente(
                                        alterId,
                                        field,
                                        readSentence("Alteração (" + field.toUpperCase() + "): ")
                                );

                                if (!(metodosCliente.message == null))
                                    System.out.println(metodosCliente.message);
                                else System.out.println("\nAlteração concluída!");
                                ac.localizarCliente(alterId);
                            }
                        }
                        if (!(metodosCliente.message == null))
                            System.out.println(metodosCliente.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltCli = readInt("\n\033[3mO que deseja?" +
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

                        int remoId = readInt("Id: ");
                        if (ac.validarId(remoId)) {
                            ac.excluirCliente(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(metodosCliente.message == null))
                                System.out.println(metodosCliente.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosCliente.message == null))
                            System.out.println(metodosCliente.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoExcCli = readInt("\n\033[3mO que deseja?" +
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

                        int findId = readInt("Id: ");
                        if (ac.validarId(findId)) {
                            ac.localizarCliente(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(metodosCliente.message == null))
                                System.out.println(metodosCliente.message);
                        }
                        if (!(metodosCliente.message == null))
                            System.out.println(metodosCliente.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocCli = readInt("\n\033[3mO que deseja?" +
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

                        int findId = readInt("Início: ");
                        if (ac.validarId(findId)) {
                            ac.localizarMaisClientes(
                                    findId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosCliente.message == null))
                                System.out.println(metodosCliente.message);
                        }
                        if (!(metodosCliente.message == null))
                            System.out.println(metodosCliente.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocMCli = readInt("\n\033[3mO que deseja?" +
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

                        int remoId = readInt("Início: ");
                        if (ac.validarId(remoId)) {
                            ac.removerMaisClientes(
                                    remoId, //ReadInt("Início: "),
                                    readInt("Fim: ")
                            );
                            if (!(metodosCliente.message == null))
                                System.out.println(metodosCliente.message);
                        }
                        if (!(metodosCliente.message == null))
                            System.out.println(metodosCliente.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemMCli = readInt("\n\033[3mO que deseja?" +
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
                    Integer opcaoListUsr = readInt("\n\033[3mO que deseja?" +
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
                    Integer opcaoVoltar = readInt("\n\033[3mO que deseja?" +
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


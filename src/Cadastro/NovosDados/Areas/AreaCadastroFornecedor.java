package Cadastro.NovosDados.Areas;

import Cadastro.Database.dataSet;
import Cadastro.Database.Metodos.metodosFornecedor;
import Raiz.Acesso.menuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.leitorDados;

public class areaCadastroFornecedor extends leitorDados implements IAreaCadastro.IFornecedores {

    menuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesFornecedor af;
    dataSet<?> banco;

    public areaCadastroFornecedor(dataSet<?> DS) {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.af = cad.new AcoesFornecedor();
        this.banco = DS;
    }

    public void menuCadastroFornecedor(Integer userId){
        System.out.println("\n\u001B[34mFornecedor:\u001B[0m");
        System.out.println("1 - Cadastrar Fornecedor");
        System.out.println("2 - Alterar Fornecedor");
        System.out.println("3 - Excluir Fornecedor");
        System.out.println("4 - Localizar Fornecedor");
        System.out.println("5 - Localizar Mais Fornecedores");
        System.out.println("6 - Remover Mais Fornecedores");
        System.out.println("7 - Listar Fornecedores");
        System.out.println("* - Mais");

        System.out.print("\n-----------------------------------------");
        String id = readText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        acoesCadastroFornecedor(id, userId);
    }

    public void acoesCadastroFornecedor(String id, Integer userId)  {
        boolean session = true;
        while (session) {
            switch (id) {
                case "1":
                    //#region Cadastrar novo fornecedor
                    System.out.println("\n# Cadastrar novo fornecedor #\n");
                    //af.cadastrarFornecedor("Jorge", "22", "04472205484", "teste@olos.com.br", "014585445489", "2555555", "04472205", 38, String.valueOf(ReadStrList("try: ")));

                    af.cadastrarFornecedor(
                            readSentence("Razão Social: "),
                            readSentence("Nome Fantasia: "),
                            readMask("CNPJ: "),
                            readText("E-mail: "),
                            readText("Inscrição Estadual: "),
                            readSentence("Telefone: "),
                            readText("CEP: ").replace("-",""),
                            readInt("Nùmero da residência: "),
                            String.valueOf(readStrList("Informar atividades abaixo [para finalizar, digite SAIR] "))
                    );
                    if (!(metodosFornecedor.message == null))
                        System.out.println(metodosFornecedor.message);
                    else System.out.println("\nCadastro concluído!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoCadForn = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do fornecedor" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoCadForn == 1) menuCadastroFornecedor(userId);
                    if (opcaoCadForn == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoCadForn == 3) mp.menuCadastro(userId, banco);
                    if (opcaoCadForn == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                //#endregion
                case "2":
                    //#region Alterar um fornecedor
                    System.out.println("\n# Alterar um fornecedor #\n");
                    if (af.listarFornecedores()) {
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (af.validarId(alterId)) {
                            String field = readText("Campo: ");
                            boolean fieldValid = (field.equalsIgnoreCase("atividades"));

                            if (fieldValid && !(af.retorno(alterId))) {
                                af.alterarFornecedor(
                                        alterId,
                                        field,
                                        readSentence("Alteração dado antigo (" + field.toUpperCase() + "): "),
                                        readSentence("Alteração dado novo (" + field.toUpperCase() + "): ")
                                );
                                if (!(metodosFornecedor.message == null))
                                    System.out.println(metodosFornecedor.message);
                                else System.out.println("\nAlteração concluída!");
                                af.localizarFornecedor(alterId);
                            }

                            if (fieldValid && af.retorno(alterId)) {
                                af.alterarFornecedor(
                                        alterId,
                                        field,
                                        String.valueOf(readStrList("Informar atividades abaixo [para finalizar, digite SAIR] "))
                                );
                                if (!(metodosFornecedor.message == null))
                                    System.out.println(metodosFornecedor.message);
                                else System.out.println("\nAlteração concluída!");
                                af.localizarFornecedor(alterId);
                            }

                            if (!(fieldValid)) {
                                if (field.equalsIgnoreCase("CNPJ")){
                                    String document = readMask("Alteração (" + field.toUpperCase() + "): ");

                                    if(document != null) {
                                        af.alterarFornecedor(
                                                alterId,
                                                field,
                                                document
                                        );

                                        if (!(metodosFornecedor.message == null))
                                            System.out.println(metodosFornecedor.message);
                                        else System.out.println("\nAlteração concluída!");
                                        af.localizarFornecedor(alterId);
                                    } else System.out.println("\nNão foi possível realizar a alteração solicitada.");
                                } else {
                                    af.alterarFornecedor(
                                            alterId,
                                            field,
                                            readSentence("Alteração (" + field.toUpperCase() + "): ")
                                    );

                                    if (!(metodosFornecedor.message == null))
                                        System.out.println(metodosFornecedor.message);
                                    else System.out.println("\nAlteração concluída!");
                                    af.localizarFornecedor(alterId);
                                }
                            }
                        }
                        if (!(metodosFornecedor.message == null))
                            System.out.println(metodosFornecedor.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltForn = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do fornecedor" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral?" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoAltForn == 1) menuCadastroFornecedor(userId);
                    if (opcaoAltForn == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoAltForn == 3) mp.menuCadastro(userId, banco);
                    if (opcaoAltForn == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "3":
                    //#region Excluir um fornecedor
                    System.out.println("\n# Excluir um fornecedor #\n");
                    if (af.listarFornecedores()) {
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (af.validarId(remoId)) {
                            af.excluirFornecedor(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(metodosFornecedor.message == null))
                                System.out.println(metodosFornecedor.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosFornecedor.message == null))
                            System.out.println(metodosFornecedor.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoExcForn = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do fornecedor" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoExcForn == 1) menuCadastroFornecedor(userId);
                    if (opcaoExcForn == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoExcForn == 3) mp.menuCadastro(userId, banco);
                    if (opcaoExcForn == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "4":
                    //#region Localizar um fornecedor
                    System.out.println("\n# Localizar um fornecedor #\n");
                    if (af.listarFornecedores()) {
                        System.out.println();

                        int findId = readInt("Id: ");
                        if (af.validarId(findId)) {
                            af.localizarFornecedor(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(metodosFornecedor.message == null))
                                System.out.println(metodosFornecedor.message);
                        }
                        if (!(metodosFornecedor.message == null))
                            System.out.println(metodosFornecedor.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocForn = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do fornecedor" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocForn == 1) menuCadastroFornecedor(userId);
                    if (opcaoLocForn == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoLocForn == 3) mp.menuCadastro(userId, banco);
                    if (opcaoLocForn == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "5":
                    //#region Localizar vários fornecedores
                    System.out.println("\n# Localizar vários fornecedores #\n");
                    if (af.listarFornecedores()) {
                        System.out.println();

                        int findId = readInt("Início: ");
                        if (af.validarId(findId)) {
                            af.localizarMaisFornecedores(
                                    findId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosFornecedor.message == null))
                                System.out.println(metodosFornecedor.message);
                        }
                        if (!(metodosFornecedor.message == null))
                            System.out.println(metodosFornecedor.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocMForn = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do fornecedor" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocMForn == 1) menuCadastroFornecedor(userId);
                    if (opcaoLocMForn == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoLocMForn == 3) mp.menuCadastro(userId, banco);
                    if (opcaoLocMForn == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "6":
                    //#region Remover vários fornecedores
                    System.out.println("\n# Remover vários fornecedores #\n");
                    if (af.listarFornecedores()) {
                        System.out.println();

                        int remoId = readInt("Início: ");
                        if (af.validarId(remoId)) {
                            af.removerMaisFornecedores(
                                    remoId, //ReadInt("Início: "),
                                    readInt("Fim: ")
                            );
                            if (!(metodosFornecedor.message == null))
                                System.out.println(metodosFornecedor.message);
                        }
                        if (!(metodosFornecedor.message == null))
                            System.out.println(metodosFornecedor.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemMForn = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do fornecedor" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoRemMForn == 1) menuCadastroFornecedor(userId);
                    if (opcaoRemMForn == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoRemMForn == 3) mp.menuCadastro(userId, banco);
                    if (opcaoRemMForn == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "7":
                    //#region Lista de fornecedor
                    System.out.println("\n# Lista de fornecedor #\n");
                    af.listarFornecedores();

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoListFornr = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do fornecedor" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoListFornr == 1) menuCadastroFornecedor(userId);
                    if (opcaoListFornr == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoListFornr == 3) mp.menuCadastro(userId, banco);
                    if (opcaoListFornr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "*":
                    //#region Retorno ao menu
                    System.out.print("\n----------------------------------------------");
                    Integer opcaoVoltar = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do fornecedor" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoVoltar == 1) menuCadastroFornecedor(userId);
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

        //#region rascunho
        /*

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
                            ReadText("CEP: ").replace("-",""),
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

                    if (opcaoCadCli == 1) menuCadastroCliente(userId);
                    if (opcaoCadCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoCadCli == 3) mp.menuCadastro(userId);
                    if (opcaoCadCli == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "2":
                    System.out.println("\n# Alterar um cliente #\n");
                    ac.listarClientes();
                    System.out.println();

                    int alterId = ReadInt("Id: ");
                    String field = ReadText("Campo: ");
                    ac.alterarCliente(
                            alterId,
                            field,
                            ReadText("Alteração (" + field.toUpperCase() + "): ")
                    );
                    System.out.println("\nAlteração concluída!");
                    ac.localizarCliente(alterId);

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
                        mp.paginaInicial();
                    }
                    if (opcaoAltCli == 3) mp.menuCadastro(userId);
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

                    if (opcaoExcCli == 1) menuCadastroCliente(userId);
                    if (opcaoExcCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoExcCli == 3) mp.menuCadastro(userId);
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

                    if (opcaoLocCli == 1) menuCadastroCliente(userId);
                    if (opcaoLocCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoLocCli == 3) mp.menuCadastro(userId);
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

                    if (opcaoLocMCli == 1) menuCadastroCliente(userId);
                    if (opcaoLocMCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoLocMCli == 3) mp.menuCadastro(userId);
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

                    if (opcaoRemMCli == 1) menuCadastroCliente(userId);
                    if (opcaoRemMCli == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoRemMCli == 3) mp.menuCadastro(userId);
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

                    if (opcaoListUsr == 1) menuCadastroCliente(userId);
                    if (opcaoListUsr == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoListUsr == 3) mp.menuCadastro(userId);
                    if (opcaoListUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                default:
                    break;
            }
        }
    */
        //#endregion
    }
}

package Cadastro.NovosDados.Areas;

import Cadastro.Database.Metodos.metodosFornecedores;
import Cadastro.Database.dataSet;
import Cadastro.NovosDados.Repositorio.DTO.Fornecedores;
import Raiz.Acesso.menuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.leitorDados;
import Raiz.Utils.smartTools.*;

public class areaCadastroFornecedor extends leitorDados implements IAreaCadastro {

    menuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesFornecedores af;
    dataSet<?> banco;

    public areaCadastroFornecedor(dataSet<?> DS) {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.af = cad.new AcoesFornecedores();
        this.banco = DS;
    }

    @Override
    public void menuAreaCadastro(Integer userId){
        System.out.println("\n\u001B[34mFornecedor:\u001B[0m");
        System.out.println("1 - Cadastrar Fornecedores");
        System.out.println("2 - Alterar Fornecedores");
        System.out.println("3 - Excluir Fornecedores");
        System.out.println("4 - Localizar Fornecedores");
        System.out.println("5 - Localizar Mais Fornecedores");
        System.out.println("6 - Remover Mais Fornecedores");
        System.out.println("7 - Listar Fornecedores");
        System.out.println("* - Mais");

        System.out.print("\n-----------------------------------------");
        String id = readText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        acoesAreaCadastro(id, userId);
    }

    boolean session = true;
    @Override
    public void acoesAreaCadastro(String id, Integer userId) {
        while (session) {
            switch (id) {
                case "1":
                    //#region Cadastrar novo fornecedor
                    System.out.println("\n# Cadastrar novo fornecedor #\n");
                    //af.cadastrarFornecedor("Jorge", "22", "04472205484", "teste@olos.com.br", "014585445489", "2555555", "04472205", 38, String.valueOf(ReadStrList("try: ")));

                    af.cadastrar(
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
                    if (!(metodosFornecedores.message == null))
                        System.out.println(metodosFornecedores.message);
                    else System.out.println("\nCadastro concluído!");

                    opcoesAreaCadastro(Fornecedores.class.getName(), userId);
                    break;
                    //#endregion
                case "2":
                    //#region Alterar um fornecedor
                    System.out.println("\n# Alterar um fornecedor #\n");
                    if (af.listar()) {
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (af.validarId(alterId)) {
                            String field = readText("Campo: ");
                            boolean fieldValid = (field.equalsIgnoreCase("atividades"));

                            if (fieldValid && af.retorno(alterId)) {
                                af.alterar(
                                        alterId,
                                        field,
                                        String.valueOf(readStrList("Informar atividades abaixo [para finalizar, digite SAIR] "))
                                );
                                if (!(metodosFornecedores.message == null))
                                    System.out.println(metodosFornecedores.message);
                                else System.out.println("\nAlteração concluída!");
                                af.localizar(alterId);
                            }

                            if (fieldValid && !(af.retorno(alterId))) {
                                int cond = readInt(
                                        "(1) Alterar atividades" +
                                          "\n(2) Incrementar atividades" +
                                          "\n(3) Remover atividades: ");

                                if (cond == 1) {
                                    af.alterar(
                                            alterId,
                                            field,
                                            readSentence("Alteração dado antigo (" + field.toUpperCase() + "): "),
                                            readSentence("Alteração dado novo (" + field.toUpperCase() + "): ")
                                    );
                                    if (!(metodosFornecedores.message == null))
                                        System.out.println(metodosFornecedores.message);
                                    else System.out.println("\nAlteração concluída!");
                                    af.localizar(alterId);

                                } else if (cond == 2) {
                                    String add = "INCREMENTAR";
                                    af.alterar(
                                            alterId,
                                            field,
                                            add,
                                            String.valueOf(readStrList("Informar atividades abaixo [para finalizar, digite SAIR] "))
                                    );
                                    if (!(metodosFornecedores.message == null))
                                        System.out.println(metodosFornecedores.message);
                                    else System.out.println("\nAlteração concluída!");
                                    af.localizar(alterId);

                                } else if (cond == 3) {
                                    String rem = "REMOVER";
                                    af.alterar(
                                            alterId,
                                            field,
                                            rem,
                                            String.valueOf(readStrList("Informar atividades abaixo [para finalizar, digite SAIR] "))
                                    );
                                    if (!(metodosFornecedores.message == null))
                                        System.out.println(metodosFornecedores.message);
                                    else System.out.println("\nAlteração concluída!");
                                    af.localizar(alterId);
                                } else System.out.println("\nNão houve alterações nesse registro!");
                            }

                            if (!(fieldValid)) {
                                if (field.equalsIgnoreCase("CNPJ")){
                                    String document = readMask("Alteração (" + field.toUpperCase() + "): ");

                                    if(document != null) {
                                        af.alterar(
                                                alterId,
                                                field,
                                                document
                                        );

                                        if (!(metodosFornecedores.message == null))
                                            System.out.println(metodosFornecedores.message);
                                        else System.out.println("\nAlteração concluída!");
                                        af.localizar(alterId);
                                    } else System.out.println("\nNão foi possível realizar a alteração solicitada.");
                                } else {
                                    af.alterar(
                                            alterId,
                                            field,
                                            readSentence("Alteração (" + field.toUpperCase() + "): ")
                                    );

                                    if (!(metodosFornecedores.message == null))
                                        System.out.println(metodosFornecedores.message);
                                    else System.out.println("\nAlteração concluída!");
                                    af.localizar(alterId);
                                }
                            }
                        }
                        if (!(metodosFornecedores.message == null))
                            System.out.println(metodosFornecedores.message);
                    }

                    opcoesAreaCadastro(Fornecedores.class.getName(), userId);
                    break;
                    //#endregion
                case "3":
                    //#region Excluir um fornecedor
                    System.out.println("\n# Excluir um fornecedor #\n");
                    if (af.listar()) {
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (af.validarId(remoId)) {
                            af.excluir(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(metodosFornecedores.message == null))
                                System.out.println(metodosFornecedores.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosFornecedores.message == null))
                            System.out.println(metodosFornecedores.message);
                    }

                    opcoesAreaCadastro(Fornecedores.class.getName(), userId);
                    break;
                    //#endregion
                case "4":
                    //#region Localizar um fornecedor
                    System.out.println("\n# Localizar um fornecedor #\n");
                    if (af.listar()) {
                        System.out.println();

                        int findId = readInt("Id: ");
                        if (af.validarId(findId)) {
                            af.localizar(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(metodosFornecedores.message == null))
                                System.out.println(metodosFornecedores.message);
                        }
                        if (!(metodosFornecedores.message == null))
                            System.out.println(metodosFornecedores.message);
                    }

                    opcoesAreaCadastro(Fornecedores.class.getName(), userId);
                    break;
                    //#endregion
                case "5":
                    //#region Localizar vários fornecedores
                    System.out.println("\n# Localizar vários fornecedores #\n");
                    if (af.listar()) {
                        System.out.println();

                        int findId = readInt("Início: ");
                        if (af.validarId(findId)) {
                            af.localizarMais(
                                    findId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosFornecedores.message == null))
                                System.out.println(metodosFornecedores.message);
                        }
                        if (!(metodosFornecedores.message == null))
                            System.out.println(metodosFornecedores.message);
                    }

                    opcoesAreaCadastro(Fornecedores.class.getName(), userId);
                    break;
                    //#endregion
                case "6":
                    //#region Remover vários fornecedores
                    System.out.println("\n# Remover vários fornecedores #\n");
                    if (af.listar()) {
                        System.out.println();

                        int remoId = readInt("Início: ");
                        if (af.validarId(remoId)) {
                            af.removerMais(
                                    remoId, //ReadInt("Início: "),
                                    readInt("Fim: ")
                            );
                            if (!(metodosFornecedores.message == null))
                                System.out.println(metodosFornecedores.message);
                        }
                        if (!(metodosFornecedores.message == null))
                            System.out.println(metodosFornecedores.message);
                    }

                    opcoesAreaCadastro(Fornecedores.class.getName(), userId);
                    break;
                    //#endregion
                case "7":
                    //#region Lista de fornecedor
                    System.out.println("\n# Lista de fornecedor #\n");
                    af.listar();

                    opcoesAreaCadastro(Fornecedores.class.getName(), userId);
                    break;
                    //#endregion
                case "*":
                    //#region Retorno ao menu
                    opcoesAreaCadastro(Fornecedores.class.getName(), userId);
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

    @Override
    public void opcoesAreaCadastro(String classe, Integer userId) {
        objetosAuxiliares obj = new objetosAuxiliares();
        System.out.print("\n----------------------------------------------");
        Integer opcao = obj.optionMenu(classe);
        System.out.println("----------------------------------------------");

        if (opcao == 1) menuAreaCadastro(userId);
        if (opcao == 2) {
            session = false;
            mp.paginaInicial(banco);
        }
        if (opcao == 3) {
            session = false;
            mp.menuCadastro(userId, banco);
        }
        if (opcao == 4) {
            System.out.println("\nAplicação encerrada.");
            System.exit(0);
        }
    }
}

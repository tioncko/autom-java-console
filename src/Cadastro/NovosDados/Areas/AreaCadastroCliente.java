package Cadastro.NovosDados.Areas;

import Cadastro.Database.dataSet;
import Cadastro.Database.Metodos.metodosCliente;
import Cadastro.NovosDados.Repositorio.DTO.Clientes;
import Raiz.Acesso.menuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.leitorDados;
import Raiz.Utils.smartTools.*;

public class areaCadastroCliente extends leitorDados implements IAreaCadastro {

    menuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesClientes ac;
    dataSet<?> banco;

    public areaCadastroCliente(dataSet<?> DS) {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.ac = cad.new AcoesClientes();
        this.banco = DS;
    }

    @Override
    public void menuAreaCadastro(Integer userId) {
        System.out.println("\n\u001B[34mCliente:\u001B[0m");
        System.out.println("1 - Cadastrar Clientes");
        System.out.println("2 - Alterar Clientes");
        System.out.println("3 - Excluir Clientes");
        System.out.println("4 - Localizar Clientes");
        System.out.println("5 - Localizar Mais Clientes");
        System.out.println("6 - Remover Mais Clientes");
        System.out.println("7 - Listar Clientes");
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
                    //#region Cadastrar novo cliente
                    System.out.println("\n# Cadastrar novo cliente #\n");
                    //ac.cadastrarCliente("Jorge", 22, "04472205484", "teste@olos.com.br", "014585445489", "04472205", 38);
                    ac.cadastrar(
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

                    opcoesAreaCadastro(Clientes.class.getName(), userId);
                    break;
                    //#endregion
                case "2":
                    //#region Alterar um cliente
                    System.out.println("\n# Alterar um cliente #\n");
                    if(ac.listar()){
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (ac.validarId(alterId)) {
                            String field = readText("Campo: ");

                            if (field.equalsIgnoreCase("CPF")){
                                String document = readMask("Alteração (" + field.toUpperCase() + "): ");

                                if(document != null) {
                                    ac.alterar(
                                            alterId,
                                            field,
                                            document
                                    );

                                    if (!(metodosCliente.message == null))
                                        System.out.println(metodosCliente.message);
                                    else System.out.println("\nAlteração concluída!");
                                    ac.localizar(alterId);
                                }
                                else System.out.println("\nNão foi possível realizar a alteração solicitada.");
                            } else {
                                ac.alterar(
                                        alterId,
                                        field,
                                        readSentence("Alteração (" + field.toUpperCase() + "): ")
                                );

                                if (!(metodosCliente.message == null))
                                    System.out.println(metodosCliente.message);
                                else System.out.println("\nAlteração concluída!");
                                ac.localizar(alterId);
                            }
                        }
                        if (!(metodosCliente.message == null))
                            System.out.println(metodosCliente.message);
                    }

                    opcoesAreaCadastro(Clientes.class.getName(), userId);
                    break;
                    //#endregion
                case "3":
                    //#region Excluir um cliente
                    System.out.println("\n# Excluir um cliente #\n");
                    if(ac.listar()) {
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (ac.validarId(remoId)) {
                            ac.excluir(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(metodosCliente.message == null))
                                System.out.println(metodosCliente.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosCliente.message == null))
                            System.out.println(metodosCliente.message);
                    }

                    opcoesAreaCadastro(Clientes.class.getName(), userId);
                    break;
                    //#endregion
                case "4":
                    //#region Localizar um cliente
                    System.out.println("\n# Localizar um cliente #\n");
                    if(ac.listar()){
                        System.out.println();

                        int findId = readInt("Id: ");
                        if (ac.validarId(findId)) {
                            ac.localizar(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(metodosCliente.message == null))
                                System.out.println(metodosCliente.message);
                        }
                        if (!(metodosCliente.message == null))
                            System.out.println(metodosCliente.message);
                    }

                    opcoesAreaCadastro(Clientes.class.getName(), userId);
                    break;
                    //#endregion
                case "5":
                    //#region Localizar vários clientes
                    System.out.println("\n# Localizar vários clientes #\n");
                    if(ac.listar()){
                        System.out.println();

                        int findId = readInt("Início: ");
                        if (ac.validarId(findId)) {
                            ac.localizarMais(
                                    findId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosCliente.message == null))
                                System.out.println(metodosCliente.message);
                        }
                        if (!(metodosCliente.message == null))
                            System.out.println(metodosCliente.message);
                    }

                    opcoesAreaCadastro(Clientes.class.getName(), userId);
                    break;
                    //#endregion
                case "6":
                    //#region Remover vários clientes
                    System.out.println("\n# Remover vários clientes #\n");
                    if(ac.listar()){
                        System.out.println();

                        int remoId = readInt("Início: ");
                        if (ac.validarId(remoId)) {
                            ac.removerMais(
                                    remoId, //ReadInt("Início: "),
                                    readInt("Fim: ")
                            );
                            if (!(metodosCliente.message == null))
                                System.out.println(metodosCliente.message);
                        }
                        if (!(metodosCliente.message == null))
                            System.out.println(metodosCliente.message);
                    }

                    opcoesAreaCadastro(Clientes.class.getName(), userId);
                    break;
                    //#endregion
                case "7":
                    //#region Lista de clientes
                    System.out.println("\n# Lista de clientes #\n");
                    ac.listar();

                    opcoesAreaCadastro(Clientes.class.getName(), userId);
                    break;
                    //#endregion
                case "*":
                    //#region Retorno ao menu
                    opcoesAreaCadastro(Clientes.class.getName(), userId);
                    break;
                    //#endregion
                default:
                    break;
            }
        }
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


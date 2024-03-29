package Cadastro.NovosDados.Areas;

import Cadastro.Database.dataSet;
import Cadastro.Database.Metodos.metodosCarros;
import Cadastro.NovosDados.Repositorio.DTO.Carros;
import Cadastro.NovosDados.Repositorio.DTO.Clientes;
import Raiz.Acesso.menuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.leitorDados;
import Raiz.Utils.smartTools;

public class areaCadastroCarro extends leitorDados implements IAreaCadastro {

    menuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesCarros as;
    dataSet<?> banco;

    public areaCadastroCarro(dataSet<?> DS) {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.as = cad.new AcoesCarros();
        this.banco = DS;
    }

    Clientes customer = null;
    public areaCadastroCarro(dataSet<?> DS, Clientes cus) {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.as = cad.new AcoesCarros();
        this.banco = DS;
        this.customer = cus;
    }

    @Override
    public void menuAreaCadastro(Integer userId) {
        System.out.println("\n\u001B[34mCarros:\u001B[0m");
        System.out.println("1 - Cadastrar Carros");
        System.out.println("2 - Alterar Carros");
        System.out.println("3 - Excluir Carros");
        System.out.println("4 - Localizar Carros");
        System.out.println("5 - Localizar Mais Carros");
        System.out.println("6 - Remover Mais Carros");
        System.out.println("7 - Listar Carros");
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
                    //#region Cadastrar novo carro
                    System.out.println("\n# Cadastrar novo carro #\n");
                    String marca = readBrand("Marca: ");
                    String modelo;

                    if (!marca.isEmpty()) {
                        modelo = readModels(marca);
                        if (modelo.equalsIgnoreCase("manual")) modelo = readSentence("Nome: ");

                        if(customer != null) {
                        String cusName = customer.getNome();
                        System.out.println("Cliente: \u001B[33m" + cusName + "\u001B[0m");

                            as.cadastrar(
                                    modelo,
                                    readText("Placa: "),
                                    readSentence("Origem: "),
                                    cusName.toUpperCase(),
                                    marca
                            );
                        } else {
                            Cadastro cad = new Cadastro(banco);
                            Cadastro.AcoesClientes acc = cad.new AcoesClientes();
                            System.out.println("# Lista de clientes #\n");
                            acc.listar();
                            System.out.println();
                            int cusId = readInt("Informar id do cliente: ");

                            Clientes cli = acc.retClientes(cusId);
                            String cusName = cli.getNome();

                            System.out.println("Cliente: \u001B[33m" + cusName + "\u001B[0m");

                            as.cadastrar(
                                    modelo,
                                    readText("Placa: "),
                                    readSentence("Origem: "),
                                    cusName.toUpperCase(),
                                    marca
                            );
                        }

                        System.out.println("\nCadastro concluído!");
                        customer = null;
                    } else System.out.println("\nNão foi possível efetuar o cadastro!");

                    opcoesAreaCadastro(Carros.class.getName(), userId);
                    break;
                //#endregion
                case "2":
                    //#region Alterar um carro
                    System.out.println("\n# Alterar um carro #\n");
                    if (as.listar()) {
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (as.validarId(alterId)) {
                            String field = readText("Campo: ");

                            if (!field.equalsIgnoreCase("marca") && !field.equalsIgnoreCase("modelo") && !field.equalsIgnoreCase("cliente")) {
                                as.alterar(
                                        alterId,
                                        field,
                                        readSentence("Alteração (" + field.toUpperCase() + "): ")
                                );
                                if (!(metodosCarros.message == null))
                                    System.out.println(metodosCarros.message);
                                else System.out.println("\nAlteração concluída!");
                                as.localizar(alterId);
                            }

                            if (field.equalsIgnoreCase("marca")) {
                                String brand = readBrand("Alteração (" + field.toUpperCase() + "): ");
                                if (brand != null) {
                                    as.alterar(
                                            alterId,
                                            field,
                                            brand
                                    );
                                    if (!(metodosCarros.message == null))
                                        System.out.println(metodosCarros.message);
                                    else System.out.println("\nAlteração concluída!");
                                    as.localizar(alterId);
                                }
                                else {
                                    System.out.println("\nNão houve alteração!");
                                    as.localizar(alterId);
                                }
                            }

                            if (field.equalsIgnoreCase("modelo")) {
                                System.out.println("Alteração (" + field.toUpperCase() + ")\n-----------------------------------------");
                                String altMarca = readBrand("Marca: ");
                                String altNome;

                                if (!altMarca.isEmpty()) {
                                    altNome = readModels(altMarca);
                                    if (altNome.equalsIgnoreCase("manual")) altNome = readSentence("Nome: ");
                                    as.alterar(
                                            alterId,
                                            field,
                                            altNome
                                    );

                                    as.alterar(
                                            alterId,
                                            "marca",
                                            altMarca
                                    );
                                    if (!(metodosCarros.message == null))
                                        System.out.println(metodosCarros.message);
                                    else System.out.println("\nAlteração concluída!");
                                    as.localizar(alterId);
                                }
                                else {
                                    System.out.println("\nNão houve alteração!");
                                    as.localizar(alterId);
                                }
                            }

                            if(field.equalsIgnoreCase("cliente")) {
                                Cadastro cad = new Cadastro(banco);
                                Cadastro.AcoesClientes acc = cad.new AcoesClientes();
                                System.out.println("*****************************************\n\n# Lista de clientes #\n");
                                acc.listar();
                                System.out.print("\n*****************************************\n\nAlteração (" + field.toUpperCase() + ") ");
                                int cusId = readInt("[Informar id do cliente]: ");

                                Clientes cli = acc.retClientes(cusId);
                                String cusName = cli.getNome();

                                as.alterar(
                                        alterId,
                                        field,
                                        cusName
                                );
                                if (!(metodosCarros.message == null))
                                    System.out.println(metodosCarros.message);
                                else System.out.println("\nAlteração concluída!");
                                as.localizar(alterId);
                            }
                        }
                        if (!(metodosCarros.message == null))
                            System.out.println(metodosCarros.message);
                    }

                    opcoesAreaCadastro(Carros.class.getName(), userId);
                    break;
                //#endregion
                case "3":
                    //#region Excluir um carro
                    System.out.println("\n# Excluir um carro #\n");
                    if(as.listar()) {
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (as.validarId(remoId)) {
                            as.excluir(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(metodosCarros.message == null))
                                System.out.println(metodosCarros.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosCarros.message == null))
                            System.out.println(metodosCarros.message);
                    }

                    opcoesAreaCadastro(Carros.class.getName(), userId);
                    break;
                //#endregion
                case "4":
                    //#region Localizar um carro
                    System.out.println("\n# Localizar um carro #\n");
                    if(as.listar()){
                        System.out.println();

                        int findId = readInt("Id: ");
                        if (as.validarId(findId)) {
                            as.localizar(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(metodosCarros.message == null))
                                System.out.println(metodosCarros.message);
                        }
                        if (!(metodosCarros.message == null))
                            System.out.println(metodosCarros.message);
                    }

                    opcoesAreaCadastro(Carros.class.getName(), userId);
                    break;
                //#endregion
                case "5":
                    //#region Localizar vários carros
                    System.out.println("\n# Localizar vários carros #\n");
                    if(as.listar()){
                        System.out.println();

                        int findId = readInt("Início: ");
                        if (as.validarId(findId)) {
                            as.localizarMais(
                                    findId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosCarros.message == null))
                                System.out.println(metodosCarros.message);
                        }
                        if (!(metodosCarros.message == null))
                            System.out.println(metodosCarros.message);
                    }

                    opcoesAreaCadastro(Carros.class.getName(), userId);
                    break;
                //#endregion
                case "6":
                    //#region Remover vários carros
                    System.out.println("\n# Remover vários carros #\n");
                    if(as.listar()){
                        System.out.println();

                        int remoId = readInt("Início: ");
                        if (as.validarId(remoId)) {
                            as.removerMais(
                                    remoId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosCarros.message == null))
                                System.out.println(metodosCarros.message);
                        }
                        if (!(metodosCarros.message == null))
                            System.out.println(metodosCarros.message);
                    }

                    opcoesAreaCadastro(Carros.class.getName(), userId);
                    break;
                //#endregion
                case "7":
                    //#region Lista de carros
                    System.out.println("\n# Lista de carros #\n");
                    as.listar();

                    opcoesAreaCadastro(Carros.class.getName(), userId);
                    break;
                //#endregion
                case "*":
                    //#region Retorno ao menu
                    opcoesAreaCadastro(Carros.class.getName(), userId);
                    break;
                //#endregion
                default:
                    break;
            }
        }
    }

    @Override
    public void opcoesAreaCadastro(String classe, Integer userId) {
        smartTools.objetosAuxiliares obj = new smartTools.objetosAuxiliares();
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

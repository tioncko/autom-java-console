package Cadastro.NovosDados.Areas;

import Cadastro.Database.dataSet;
import Cadastro.Database.Metodos.metodosServico;
import Cadastro.NovosDados.Repositorio.DTO.Servicos;
import Raiz.Acesso.menuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.leitorDados;
import Raiz.Utils.smartTools.*;
import Cadastro.Database.JSON.JsonTools.jsonExtraction;

public class areaCadastroServico extends leitorDados implements IAreaCadastro {

    menuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesServicos as;
    dataSet<?> banco;

    public areaCadastroServico(dataSet<?> DS) {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.as = cad.new AcoesServicos();
        this.banco = DS;
    }

    @Override
    public void menuAreaCadastro(Integer userId)  {
        System.out.println("\n\u001B[34mServico:\u001B[0m");
        System.out.println("1 - Cadastrar Servico");
        System.out.println("2 - Alterar Servico");
        System.out.println("3 - Excluir Servico");
        System.out.println("4 - Localizar Servico");
        System.out.println("5 - Localizar Mais Servicos");
        System.out.println("6 - Remover Mais Servicos");
        System.out.println("7 - Listar Servicos");
        System.out.println("* - Mais");

        System.out.print("\n-----------------------------------------");
        String id = readText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        acoesAreaCadastro(id, userId);
    }

    boolean session = true;
    @Override
    public void acoesAreaCadastro(String id, Integer userId) {
        jsonExtraction.coletaJsonDados cjd = new jsonExtraction.coletaJsonDados();

        while (session) {
            switch (id) {
                case "1":
                    //#region Cadastrar novo serviço
                    System.out.println("\n# Cadastrar novo serviço #\n");
                    //as.cadastrarServico("Jorge", "22", "04472205484", "teste@olos.com.br", "014585445489", "2555555", "04472205", 38, String.valueOf(ReadStrList("try: ")));

                    as.cadastrar(
                            readSentence("Nome do serviço: "),
                            readDbl("Preco: "),
                            cjd.nomeCategoria(readInt("Categoria: "), Servicos.class),
                            cjd.nomeGrupo(readInt("Grupo: "), Servicos.class));
                    System.out.println("\nCadastro concluído!");

                    opcoesAreaCadastro(Servicos.class.getName(), userId);
                    break;
                    //#endregion
                case "2":
                    //#region Alterar um serviço
                    System.out.println("\n# Alterar um serviço #\n");
                    if (as.listar()) {
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (as.validarId(alterId)) {
                            String field = readText("Campo: ");
                            as.alterar(
                                    alterId,
                                    field,
                                    readSentence("Alteração (" + field.toUpperCase() + "): "));

                            if (!(metodosServico.message == null))
                                System.out.println(metodosServico.message);
                            else System.out.println("\nAlteração concluída!");
                            as.localizar(alterId);
                        }
                        if (!(metodosServico.message == null))
                            System.out.println(metodosServico.message);
                    }

                    opcoesAreaCadastro(Servicos.class.getName(), userId);
                    break;
                    //#endregion
                case "3":
                    //#region Excluir um serviço
                    System.out.println("\n# Excluir um serviço #\n");
                    if(as.listar()) {
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (as.validarId(remoId)) {
                            as.excluir(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(metodosServico.message == null))
                                System.out.println(metodosServico.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosServico.message == null))
                            System.out.println(metodosServico.message);
                    }

                    opcoesAreaCadastro(Servicos.class.getName(), userId);
                    break;
                    //#endregion
                case "4":
                    //#region Localizar um serviço
                    System.out.println("\n# Localizar um serviço #\n");
                    if(as.listar()){
                        System.out.println();

                        int findId = readInt("Id: ");
                        if (as.validarId(findId)) {
                            as.localizar(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(metodosServico.message == null))
                                System.out.println(metodosServico.message);
                        }
                        if (!(metodosServico.message == null))
                            System.out.println(metodosServico.message);
                    }

                    opcoesAreaCadastro(Servicos.class.getName(), userId);
                    break;
                    //#endregion
                case "5":
                    //#region Localizar vários serviços
                    System.out.println("\n# Localizar vários serviços #\n");
                    if(as.listar()){
                        System.out.println();

                        int findId = readInt("Início: ");
                        if (as.validarId(findId)) {
                            as.localizarMais(
                                    findId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosServico.message == null))
                                System.out.println(metodosServico.message);
                        }
                        if (!(metodosServico.message == null))
                            System.out.println(metodosServico.message);
                    }

                    opcoesAreaCadastro(Servicos.class.getName(), userId);
                    break;
                    //#endregion
                case "6":
                    //#region Remover vários serviços
                    System.out.println("\n# Remover vários serviço #\n");
                    if(as.listar()){
                        System.out.println();

                        int remoId = readInt("Início: ");
                        if (as.validarId(remoId)) {
                            as.removerMais(
                                    remoId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosServico.message == null))
                                System.out.println(metodosServico.message);
                        }
                        if (!(metodosServico.message == null))
                            System.out.println(metodosServico.message);
                    }

                    opcoesAreaCadastro(Servicos.class.getName(), userId);
                    break;
                    //#endregion
                case "7":
                    //#region Lista de serviços
                    System.out.println("\n# Lista de serviços #\n");
                    as.listar();

                    opcoesAreaCadastro(Servicos.class.getName(), userId);
                    break;
                    //#endregion
                case "*":
                    //#region Retorno ao menu
                    opcoesAreaCadastro(Servicos.class.getName(), userId);
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

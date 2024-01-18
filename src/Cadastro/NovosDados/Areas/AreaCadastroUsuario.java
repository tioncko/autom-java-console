package Cadastro.NovosDados.Areas;

import Cadastro.Database.dataSet;
import Cadastro.Database.Metodos.metodosServico;
import Cadastro.Database.Metodos.metodosUsuario;
import Raiz.Acesso.menuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.leitorDados;

public class areaCadastroUsuario extends leitorDados implements IAreaCadastro.IUsuarios {

    menuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesUsuario ac;
    dataSet<?> banco;

    public areaCadastroUsuario(dataSet<?> DS) {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.ac = cad.new AcoesUsuario();
        this.banco = DS;
    }

    public void menuCadastroUsuario(Integer userId) {
        System.out.println("\n\u001B[34mUsuário:\u001B[0m");
        System.out.println("1 - Cadastrar Usuario");
        System.out.println("2 - Alterar Usuario");
        System.out.println("3 - Excluir Usuario");
        System.out.println("4 - Localizar Usuario");
        System.out.println("5 - Localizar Mais Usuarios");
        System.out.println("6 - Remover Mais Usuarios");
        System.out.println("7 - Listar Usuarios");
        System.out.println("8 - Alterar Permissão de Usuarios");
        System.out.println("9 - Remover Permissão de Usuarios");
        System.out.println("* - Mais");

        System.out.print("\n-----------------------------------------");
        String id = readText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        acoesCadastroUsuario(id, userId);
    }

    public void acoesCadastroUsuario(String id, Integer userId) {
        boolean session = true;
        while (session) {
            switch (id) {
                case "1":
                    //#region Cadastrar novo usuário
                    System.out.println("\n# Cadastrar novo usuário #\n");
                    //ac.cadastrarUsuario("Keyla", "1234", "Keyla Nascimento", "Juridico");
                    Integer novoId = ac.returnNextId();
                    ac.cadastrarUsuario(
                            readText("Login: "),
                            readText("Senha: "),
                            readSentence("Nome: "),
                            readSentence("Depto.: ")
                    );
                    ac.associarPermissao(novoId, readText("Permissão [ADMIN(2), USER(3)]: "));
                    if (!(metodosServico.message == null))
                        System.out.println(metodosServico.message);
                    else System.out.println("\nCadastro concluído!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoCadUsr = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do usuário" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoCadUsr == 1) menuCadastroUsuario(userId);
                    if (opcaoCadUsr == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoCadUsr == 3) mp.menuCadastro(userId, banco);
                    if (opcaoCadUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "2":
                    //#region Alterar um usuário
                    System.out.println("\n# Alterar um usuário #\n");
                    if(ac.listarUsuario()){
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (ac.validarId(alterId)) {
                            String field = readText("Campo: ");
                            ac.alterarUsuario(
                                    alterId,
                                    field,
                                    readSentence("Alteração (" + field.toUpperCase() + "): ")
                            );
                            if (!(metodosUsuario.message == null))
                                System.out.println(metodosUsuario.message);
                            else System.out.println("\nAlteração concluída!");
                            ac.localizarUsuario(alterId);
                        }
                        if (!(metodosUsuario.message == null))
                            System.out.println(metodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltUsr = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do usuário" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoAltUsr == 1) menuCadastroUsuario(userId);
                    if (opcaoAltUsr == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoAltUsr == 3) mp.menuCadastro(userId, banco);
                    if (opcaoAltUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "3":
                    //#region Excluir um usuário
                    System.out.println("\n# Excluir um usuário #\n");
                    if(ac.listarUsuario()){
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (ac.validarId(remoId)) {
                            ac.excluirUsuario(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(metodosUsuario.message == null))
                                System.out.println(metodosUsuario.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosUsuario.message == null))
                            System.out.println(metodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoExcUsr = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do usuário" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoExcUsr == 1) menuCadastroUsuario(userId);
                    if (opcaoExcUsr == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoExcUsr == 3) mp.menuCadastro(userId, banco);
                    if (opcaoExcUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "4":
                    //#region Localizar um usuário
                    System.out.println("\n# Localizar um usuário #\n");
                    if(ac.listarUsuario()){
                        System.out.println();

                        int findId = readInt("Id: ");
                        if (ac.validarId(findId)) {
                            ac.localizarUsuario(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(metodosUsuario.message == null))
                                System.out.println(metodosUsuario.message);
                        }
                        if (!(metodosUsuario.message == null))
                            System.out.println(metodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocUsr = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do usuário" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocUsr == 1) menuCadastroUsuario(userId);
                    if (opcaoLocUsr == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoLocUsr == 3) mp.menuCadastro(userId, banco);
                    if (opcaoLocUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "5":
                    //#region Localizar vários usuários
                    System.out.println("\n# Localizar vários usuários #\n");
                    if(ac.listarUsuario()){
                        System.out.println();

                        int findId = readInt("Início: ");
                        if (ac.validarId(findId)) {
                            ac.localizarMaisUsuarios(
                                    findId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosUsuario.message == null))
                                System.out.println(metodosUsuario.message);
                        }
                        if (!(metodosUsuario.message == null))
                            System.out.println(metodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocMUsr = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do usuário" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocMUsr == 1) menuCadastroUsuario(userId);
                    if (opcaoLocMUsr == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoLocMUsr == 3) mp.menuCadastro(userId, banco);
                    if (opcaoLocMUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "6":
                    //#region Remover vários usuários
                    System.out.println("\n# Remover vários usuários #\n");
                    if(ac.listarUsuario()){
                        System.out.println();

                        int remoId = readInt("Início: ");
                        if (ac.validarId(remoId)) {
                            ac.removerMaisUsuarios(
                                    remoId, //ReadInt("Início: "),
                                    readInt("Fim: ")
                            );
                            if (!(metodosUsuario.message == null))
                                System.out.println(metodosUsuario.message);
                        }
                        if (!(metodosUsuario.message == null))
                            System.out.println(metodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemMUsr = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do usuário" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoRemMUsr == 1) menuCadastroUsuario(userId);
                    if (opcaoRemMUsr == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoRemMUsr == 3) mp.menuCadastro(userId, banco);
                    if (opcaoRemMUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "7":
                    //#region Lista de usuários
                    System.out.println("\n# Lista de usuários #\n");
                    ac.listarUsuario();

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoListUsr = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do usuário" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoListUsr == 1) menuCadastroUsuario(userId);
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
                case "8":
                    //#region Alteração de permissão de usuários
                    System.out.println("\n# Alteração de permissão de usuários #\n");
                    if(ac.listarUsuario()){
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (ac.validarId(userId) && ac.validarId(alterId)) {
                            ac.alterarPermissao(
                                    userId,
                                    alterId,
                                    readText("Permissão: ")
                            );
                            if (!(metodosUsuario.message == null))
                                System.out.println(metodosUsuario.message);
                            else System.out.println("\nAlteração concluída!");
                        }
                        if (!(metodosUsuario.message == null))
                            System.out.println(metodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltPerm = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do usuário" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoAltPerm == 1) menuCadastroUsuario(userId);
                    if (opcaoAltPerm == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoAltPerm == 3) mp.menuCadastro(userId, banco);
                    if (opcaoAltPerm == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "9":
                    //#region Remoção de permissão de usuários
                    System.out.println("\n# Remoção de permissão de usuários #\n");
                    if(ac.listarUsuario()){
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (ac.validarId(remoId)) {
                            ac.removerPermissao(
                                    remoId //ReadInt("id: ")
                            );
                            if (!(metodosUsuario.message == null))
                                System.out.println(metodosUsuario.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosUsuario.message == null))
                            System.out.println(metodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemPerm = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do usuário" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoRemPerm == 1) menuCadastroUsuario(userId);
                    if (opcaoRemPerm == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoRemPerm == 3) mp.menuCadastro(userId, banco);
                    if (opcaoRemPerm == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;
                    //#endregion
                case "*":
                    //#region Retorno ao menu
                    Integer opcaoVoltar = readInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do usuário" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoVoltar == 1) menuCadastroUsuario(userId);
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

package Cadastro.NovosDados.Areas;

import Cadastro.Database.DataSet;
import Cadastro.Database.Metodos.MetodosCliente;
import Cadastro.Database.Metodos.MetodosFornecedor;
import Cadastro.Database.Metodos.MetodosServico;
import Cadastro.Database.Metodos.MetodosUsuario;
import Raiz.Acesso.MenuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.LeitorDados;

public class AreaCadastroUsuario extends LeitorDados implements IAreaCadastro.IUsuarios {

    MenuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesUsuario ac;
    DataSet<?> banco;

    public AreaCadastroUsuario(DataSet<?> DS) {
        this.mp = new MenuPrincipal(DS);
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
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        AcoesCadastroUsuario(id, userId);
    }

    public void AcoesCadastroUsuario(String id, Integer userId) {
        boolean session = true;
        while (session) {
            switch (id) {
                case "1":
                    //#region Cadastrar novo usuário
                    System.out.println("\n# Cadastrar novo usuário #\n");
                    //ac.cadastrarUsuario("Keyla", "1234", "Keyla Nascimento", "Juridico");
                    Integer novoId = ac.returnNextId();
                    ac.cadastrarUsuario(
                            ReadText("Login: "),
                            ReadText("Senha: "),
                            ReadSentence("Nome: "),
                            ReadSentence("Depto.: ")
                    );
                    ac.associarPermissao(novoId, ReadText("Permissão [ADMIN(2), USER(3)]: "));
                    if (!(MetodosServico.message == null))
                        System.out.println(MetodosServico.message);
                    else System.out.println("\nCadastro concluído!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoCadUsr = ReadInt("\n\033[3mO que deseja?" +
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

                        int alterId = ReadInt("Id: ");
                        if (ac.validarId(alterId)) {
                            String field = ReadText("Campo: ");
                            ac.alterarUsuario(
                                    alterId,
                                    field,
                                    ReadSentence("Alteração (" + field.toUpperCase() + "): ")
                            );
                            if (!(MetodosUsuario.message == null))
                                System.out.println(MetodosUsuario.message);
                            else System.out.println("\nAlteração concluída!");
                            ac.localizarUsuario(alterId);
                        }
                        if (!(MetodosUsuario.message == null))
                            System.out.println(MetodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltUsr = ReadInt("\n\033[3mO que deseja?" +
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

                        int remoId = ReadInt("Id: ");
                        if (ac.validarId(remoId)) {
                            ac.excluirUsuario(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(MetodosUsuario.message == null))
                                System.out.println(MetodosUsuario.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(MetodosUsuario.message == null))
                            System.out.println(MetodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoExcUsr = ReadInt("\n\033[3mO que deseja?" +
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

                        int findId = ReadInt("Id: ");
                        if (ac.validarId(findId)) {
                            ac.localizarUsuario(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(MetodosUsuario.message == null))
                                System.out.println(MetodosUsuario.message);
                        }
                        if (!(MetodosUsuario.message == null))
                            System.out.println(MetodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocUsr = ReadInt("\n\033[3mO que deseja?" +
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

                        int findId = ReadInt("Início: ");
                        if (ac.validarId(findId)) {
                            ac.localizarMaisUsuarios(
                                    findId, //ReadInt("Início: "),
                                    ReadInt("Fim: "));
                            if (!(MetodosUsuario.message == null))
                                System.out.println(MetodosUsuario.message);
                        }
                        if (!(MetodosUsuario.message == null))
                            System.out.println(MetodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocMUsr = ReadInt("\n\033[3mO que deseja?" +
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

                        int remoId = ReadInt("Início: ");
                        if (ac.validarId(remoId)) {
                            ac.removerMaisUsuarios(
                                    remoId, //ReadInt("Início: "),
                                    ReadInt("Fim: ")
                            );
                            if (!(MetodosUsuario.message == null))
                                System.out.println(MetodosUsuario.message);
                        }
                        if (!(MetodosUsuario.message == null))
                            System.out.println(MetodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemMUsr = ReadInt("\n\033[3mO que deseja?" +
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
                    Integer opcaoListUsr = ReadInt("\n\033[3mO que deseja?" +
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

                        int alterId = ReadInt("Id: ");
                        if (ac.validarId(userId) && ac.validarId(alterId)) {
                            ac.alterarPermissao(
                                    userId,
                                    alterId,
                                    ReadText("Permissão: ")
                            );
                            if (!(MetodosUsuario.message == null))
                                System.out.println(MetodosUsuario.message);
                            else System.out.println("\nAlteração concluída!");
                        }
                        if (!(MetodosUsuario.message == null))
                            System.out.println(MetodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltPerm = ReadInt("\n\033[3mO que deseja?" +
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

                        int remoId = ReadInt("Id: ");
                        if (ac.validarId(remoId)) {
                            ac.removerPermissao(
                                    remoId //ReadInt("id: ")
                            );
                            if (!(MetodosUsuario.message == null))
                                System.out.println(MetodosUsuario.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(MetodosUsuario.message == null))
                            System.out.println(MetodosUsuario.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemPerm = ReadInt("\n\033[3mO que deseja?" +
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
                    Integer opcaoVoltar = ReadInt("\n\033[3mO que deseja?" +
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

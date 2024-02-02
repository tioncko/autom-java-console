package Cadastro.NovosDados.Areas;

import Cadastro.Database.Metodos.metodosUsuarios;
import Cadastro.Database.dataSet;
import Cadastro.NovosDados.Repositorio.DTO.Usuarios;
import Raiz.Acesso.menuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.leitorDados;
import Raiz.Utils.smartTools.*;

public class areaCadastroUsuario extends leitorDados implements IAreaCadastro {

    menuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesUsuarios ac;
    dataSet<?> banco;

    public areaCadastroUsuario(dataSet<?> DS) {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.ac = cad.new AcoesUsuarios();
        this.banco = DS;
    }

    @Override
    public void menuAreaCadastro(Integer userId) {
        System.out.println("\n\u001B[34mUsuário:\u001B[0m");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Alterar Usuário");
        System.out.println("3 - Excluir Usuário");
        System.out.println("4 - Localizar Usuário");
        System.out.println("5 - Localizar Mais Usuários");
        System.out.println("6 - Remover Mais Usuários");
        System.out.println("7 - Listar Usuários");
        System.out.println("8 - Alterar Permissão de Usuários");
        System.out.println("9 - Remover Permissão de Usuários");
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
                    //#region Cadastrar novo usuário
                    System.out.println("\n# Cadastrar novo usuário #\n");
                    //ac.cadastrarUsuario("Keyla", "1234", "Keyla Nascimento", "Jurídico");
                    Integer novoId = ac.returnNextId();
                    ac.cadastrar(
                            readText("Login: "),
                            readText("Senha: "),
                            readSentence("Nome: "),
                            readSentence("Depto.: ")
                    );
                    ac.associarPermissao(novoId, readText("Permissão [ADMIN(2), USER(3)]: "));
                    if (!(metodosUsuarios.message == null))
                        System.out.println(metodosUsuarios.message);
                    else System.out.println("\nCadastro concluído!");

                    opcoesAreaCadastro(Usuarios.class.getName(), userId);
                    break;
                    //#endregion
                case "2":
                    //#region Alterar um usuário
                    System.out.println("\n# Alterar um usuário #\n");
                    if(ac.listar()){
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (ac.validarId(alterId)) {
                            String field = readText("Campo: ");
                            ac.alterar(
                                    alterId,
                                    field,
                                    readSentence("Alteração (" + field.toUpperCase() + "): ")
                            );
                            if (!(metodosUsuarios.message == null))
                                System.out.println(metodosUsuarios.message);
                            else System.out.println("\nAlteração concluída!");
                            ac.localizar(alterId);
                        }
                        if (!(metodosUsuarios.message == null))
                            System.out.println(metodosUsuarios.message);
                    }

                    opcoesAreaCadastro(Usuarios.class.getName(), userId);
                    break;
                    //#endregion
                case "3":
                    //#region Excluir um usuário
                    System.out.println("\n# Excluir um usuário #\n");
                    if(ac.listar()){
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (ac.validarId(remoId)) {
                            ac.excluir(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(metodosUsuarios.message == null))
                                System.out.println(metodosUsuarios.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosUsuarios.message == null))
                            System.out.println(metodosUsuarios.message);
                    }

                    opcoesAreaCadastro(Usuarios.class.getName(), userId);
                    break;
                    //#endregion
                case "4":
                    //#region Localizar um usuário
                    System.out.println("\n# Localizar um usuário #\n");
                    if(ac.listar()){
                        System.out.println();

                        int findId = readInt("Id: ");
                        if (ac.validarId(findId)) {
                            ac.localizar(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(metodosUsuarios.message == null))
                                System.out.println(metodosUsuarios.message);
                        }
                        if (!(metodosUsuarios.message == null))
                            System.out.println(metodosUsuarios.message);
                    }

                    opcoesAreaCadastro(Usuarios.class.getName(), userId);
                    break;
                    //#endregion
                case "5":
                    //#region Localizar vários usuários
                    System.out.println("\n# Localizar vários usuários #\n");
                    if(ac.listar()){
                        System.out.println();

                        int findId = readInt("Início: ");
                        if (ac.validarId(findId)) {
                            ac.localizarMais(
                                    findId, //ReadInt("Início: "),
                                    readInt("Fim: "));
                            if (!(metodosUsuarios.message == null))
                                System.out.println(metodosUsuarios.message);
                        }
                        if (!(metodosUsuarios.message == null))
                            System.out.println(metodosUsuarios.message);
                    }

                    opcoesAreaCadastro(Usuarios.class.getName(), userId);
                    break;
                    //#endregion
                case "6":
                    //#region Remover vários usuários
                    System.out.println("\n# Remover vários usuários #\n");
                    if(ac.listar()){
                        System.out.println();

                        int remoId = readInt("Início: ");
                        if (ac.validarId(remoId)) {
                            ac.removerMais(
                                    remoId, //ReadInt("Início: "),
                                    readInt("Fim: ")
                            );
                            if (!(metodosUsuarios.message == null))
                                System.out.println(metodosUsuarios.message);
                        }
                        if (!(metodosUsuarios.message == null))
                            System.out.println(metodosUsuarios.message);
                    }

                    opcoesAreaCadastro(Usuarios.class.getName(), userId);
                    break;
                    //#endregion
                case "7":
                    //#region Lista de usuários
                    System.out.println("\n# Lista de usuários #\n");
                    ac.listar();

                    opcoesAreaCadastro(Usuarios.class.getName(), userId);
                    break;
                    //#endregion
                case "8":
                    //#region Alteração de permissão de usuários
                    System.out.println("\n# Alteração de permissão de usuários #\n");
                    if(ac.listar()){
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (ac.validarId(userId) && ac.validarId(alterId)) {
                            ac.alterarPermissao(
                                    userId,
                                    alterId,
                                    readText("Permissão: ")
                            );
                            if (!(metodosUsuarios.message == null))
                                System.out.println(metodosUsuarios.message);
                            else System.out.println("\nAlteração concluída!");
                        }
                        if (!(metodosUsuarios.message == null))
                            System.out.println(metodosUsuarios.message);
                    }

                    opcoesAreaCadastro(Usuarios.class.getName(), userId);
                    break;
                    //#endregion
                case "9":
                    //#region Remoção de permissão de usuários
                    System.out.println("\n# Remoção de permissão de usuários #\n");
                    if(ac.listar()){
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (ac.validarId(remoId)) {
                            ac.removerPermissao(
                                    remoId //ReadInt("id: ")
                            );
                            if (!(metodosUsuarios.message == null))
                                System.out.println(metodosUsuarios.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosUsuarios.message == null))
                            System.out.println(metodosUsuarios.message);
                    }

                    opcoesAreaCadastro(Usuarios.class.getName(), userId);
                    break;
                    //#endregion
                case "*":
                    //#region Retorno ao menu
                    opcoesAreaCadastro(Usuarios.class.getName(), userId);
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

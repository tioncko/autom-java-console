package NovosDados.AreasMenu;

import AutomRoot.MenuPrincipal;
import InterfaceInical.Cadastro;
import Utils.Objetos.LeitorDados;

public class AreaCadastroUsuario extends LeitorDados {

    MenuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesUsuario ac;

    public AreaCadastroUsuario() throws Exception {
        this.mp = new MenuPrincipal();
        this.cad = new Cadastro();
        this.ac = cad.new AcoesUsuario();
    }

    public void menuCadastroUsuario() throws Exception {
        System.out.println("\nUsuário:");
        System.out.println("1 - Cadastrar Usuario");
        System.out.println("2 - Alterar Usuario");
        System.out.println("3 - Excluir Usuario");
        System.out.println("4 - Localizar Usuario");
        System.out.println("5 - Localizar Mais Usuarios");
        System.out.println("6 - Remover Mais Usuarios");
        System.out.println("7 - Listar Usuarios");

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        AcoesCadastroUsuario(id);
    }

    void AcoesCadastroUsuario(String id) throws Exception {
        boolean session = true;
        while (session) {
            switch (id) {
                case "1":
                    System.out.println("\n# Cadastrar novo usuário #\n");
                    //ac.cadastrarUsuario("Keyla", "1234", "Keyla Nascimento", "Juridico");
                    ac.cadastrarUsuario(
                            ReadText("Login: "),
                            ReadText("Senha: "),
                            ReadText("Nome: "),
                            ReadText("Depto.: ")
                    );
                    System.out.println("\nCadastro concluído!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoCadUsr = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoCadUsr == 1) menuCadastroUsuario();
                    if (opcaoCadUsr == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoCadUsr == 3) mp.menuCadastro();
                    if (opcaoCadUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "2":
                    System.out.println("\n# Alterar um usuário #\n");
                    ac.listarUsuario();
                    System.out.println();

                    int alterId = ReadInt("Id: ");
                    String field = ReadText("Campo: ");
                    ac.alterarUsuario(
                            alterId,
                            field,
                            ReadText("Alteração (" + field.toUpperCase() + "): ")
                    );
                    System.out.println("\nAlteração concluída!");
                    ac.localizarUsuario(alterId);

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltUsr = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoAltUsr == 1) menuCadastroUsuario();
                    if (opcaoAltUsr == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoAltUsr == 3) mp.menuCadastro();
                    if (opcaoAltUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "3":
                    System.out.println("\n# Excluir um usuário #\n");
                    ac.excluirUsuario(
                            ReadInt("Id: ")
                    );
                    System.out.println("\nExclusão concluída!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoExcUsr = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoExcUsr == 1) menuCadastroUsuario();
                    if (opcaoExcUsr == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoExcUsr == 3) mp.menuCadastro();
                    if (opcaoExcUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "4":
                    System.out.println("\n# Localizar um usuário #\n");
                    ac.localizarUsuario(
                            ReadInt("Id: ")
                    );

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocUsr = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocUsr == 1) menuCadastroUsuario();
                    if (opcaoLocUsr == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoLocUsr == 3) mp.menuCadastro();
                    if (opcaoLocUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "5":
                    System.out.println("\n# Localizar vários usuários #\n");
                    ac.localizarMaisUsuarios(
                            ReadInt("Início: "),
                            ReadInt("Fim: ")
                    );

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocMUsr = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocMUsr == 1) menuCadastroUsuario();
                    if (opcaoLocMUsr == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoLocMUsr == 3) mp.menuCadastro();
                    if (opcaoLocMUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "6":
                    System.out.println("\n# Remover vários usuários #\n");
                    ac.removerMaisUsuarios(
                            ReadInt("Início: "),
                            ReadInt("Fim: ")
                    );

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemMUsr = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoRemMUsr == 1) menuCadastroUsuario();
                    if (opcaoRemMUsr == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoRemMUsr == 3) mp.menuCadastro();
                    if (opcaoRemMUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "7":
                    System.out.println("\n# Lista de usuários #\n");
                    ac.listarUsuario();

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoListUsr = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do cliente" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoListUsr == 1) menuCadastroUsuario();
                    if (opcaoListUsr == 2) {
                        session = false;
                        mp.paginaInicial();
                    }
                    if (opcaoListUsr == 3) mp.menuCadastro();
                    if (opcaoListUsr == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                default:
                    break;
            }
        }
    }
}

package Cadastro.NovosDados.Areas;

import Cadastro.Database.DataSet;
import Cadastro.Database.JSON.JsonTools.JsonExtraction;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Cadastro.Database.Metodos.MetodosFornecedor;
import Cadastro.Database.Metodos.MetodosServico;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Cadastro.NovosDados.Repositorio.DTO.Servicos;
import Raiz.Acesso.MenuPrincipal;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.LeitorDados;

public class AreaCadastroServico extends LeitorDados implements IAreaCadastro.IServicos {

    MenuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesServicos as;
    DataSet<?> banco;

    public AreaCadastroServico(DataSet<?> DS) throws Exception {
        this.mp = new MenuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.as = cad.new AcoesServicos();
        this.banco = DS;
    }

    @Override
    public void menuCadastroServico(Integer userId) throws Exception {
        System.out.println("\nServico:");
        System.out.println("1 - Cadastrar Servico");
        System.out.println("2 - Alterar Servico");
        System.out.println("3 - Excluir Servico");
        System.out.println("4 - Localizar Servico");
        System.out.println("5 - Localizar Mais Servicos");
        System.out.println("6 - Remover Mais Servicos");
        System.out.println("7 - Listar Servicos");
        System.out.println("* - Mais");

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        AcoesCadastroServico(id, userId);
    }

    @Override
    public void AcoesCadastroServico(String id, Integer userId) throws Exception {
        boolean session = true;
        JsonExtraction.ColetaJsonDados cjd = new JsonExtraction.ColetaJsonDados();

        while (session) {
            switch (id) {
                case "1":
                    System.out.println("\n# Cadastrar novo serviço #\n");
                    //as.cadastrarServico("Jorge", "22", "04472205484", "teste@olos.com.br", "014585445489", "2555555", "04472205", 38, String.valueOf(ReadStrList("try: ")));

                    as.cadastrarServico(
                            ReadSentence("Nome do serviço: "),
                            ReadDbl("Preco: "),
                            cjd.nomeCategoria(ReadInt("Categoria: "), Servicos.class),
                            cjd.nomeGrupo(ReadInt("Grupo: "), Servicos.class));
                    System.out.println("\nCadastro concluído!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoCadProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do serviço" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoCadProd == 1) menuCadastroServico(userId);
                    if (opcaoCadProd == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoCadProd == 3) mp.menuCadastro(userId, banco);
                    if (opcaoCadProd == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "2":
                    System.out.println("\n# Alterar um serviço #\n");
                    if (as.listarServicos()) {
                        System.out.println();

                        int alterId = ReadInt("Id: ");
                        String field = ReadText("Campo: ").toUpperCase();
                        as.alterarServico(
                                alterId,
                                field,
                                ReadSentence("Alteração (" + field.toUpperCase() + "): ")
                        );

                        System.out.println("\nAlteração concluída!");
                        as.localizarServico(alterId);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do serviço" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral?" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoAltProd == 1) menuCadastroServico(userId);
                    if (opcaoAltProd == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoAltProd == 3) mp.menuCadastro(userId, banco);
                    if (opcaoAltProd == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "3":
                    System.out.println("\n# Excluir um produto #\n");
                    if(as.listarServicos()) {
                        System.out.println();

                        as.excluirServico(
                                ReadInt("Id: ")
                        );
                        System.out.println("\nExclusão concluída!");
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoExcProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do serviço" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoExcProd == 1) menuCadastroServico(userId);
                    if (opcaoExcProd == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoExcProd == 3) mp.menuCadastro(userId, banco);
                    if (opcaoExcProd == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "4":
                    System.out.println("\n# Localizar um produto #\n");
                    if(as.listarServicos()){
                        System.out.println();

                        as.localizarServico(
                                ReadInt("Id: ")
                        );
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do serviço" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocProd == 1) menuCadastroServico(userId);
                    if (opcaoLocProd == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoLocProd == 3) mp.menuCadastro(userId, banco);
                    if (opcaoLocProd == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "5":
                    System.out.println("\n# Localizar vários produtos #\n");
                    if(as.listarServicos()){
                        System.out.println();

                        as.localizarMaisServicos(
                                ReadInt("Início: "),
                                ReadInt("Fim: ")
                        );
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocMProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do serviço" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocMProd == 1) menuCadastroServico(userId);
                    if (opcaoLocMProd == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoLocMProd == 3) mp.menuCadastro(userId, banco);
                    if (opcaoLocMProd == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "6":
                    System.out.println("\n# Remover vários produtos #\n");
                    if(as.listarServicos()){
                        System.out.println();

                        as.removerMaisServicos(
                                ReadInt("Início: "),
                                ReadInt("Fim: ")
                        );
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemMProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do serviço" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoRemMProd == 1) menuCadastroServico(userId);
                    if (opcaoRemMProd == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoRemMProd == 3) mp.menuCadastro(userId, banco);
                    if (opcaoRemMProd == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "7":
                    System.out.println("\n# Lista de produtos #\n");
                    as.listarServicos();

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoListProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do serviço" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoListProd == 1) menuCadastroServico(userId);
                    if (opcaoListProd == 2) {
                        session = false;
                        mp.paginaInicial(banco);
                    }
                    if (opcaoListProd == 3) mp.menuCadastro(userId, banco);
                    if (opcaoListProd == 4) {
                        System.out.println("\nAplicação encerrada.");
                        System.exit(0);
                    }
                    break;

                case "*":
                    Integer opcaoVoltar = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do serviço" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoVoltar == 1) menuCadastroServico(userId);
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

                default:
                    break;
            }
        }
    }
}

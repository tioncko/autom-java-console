package Cadastro.NovosDados.Areas;

import Cadastro.Database.DataSet;
import Cadastro.Database.Metodos.MetodosCliente;
import Cadastro.Database.Metodos.MetodosProduto;
import Cadastro.Database.Metodos.MetodosServico;
import Raiz.Acesso.MenuPrincipal;
import Cadastro.Database.JSON.JsonTools.JsonExtraction;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Cadastro.Database.Metodos.MetodosFornecedor;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.LeitorDados;

public class AreaCadastroProduto extends LeitorDados implements IAreaCadastro.IProdutos {

    MenuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesProdutos ap;
    DataSet<?> banco;

    public AreaCadastroProduto(DataSet<?> DS)  {
        this.mp = new MenuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.ap = cad.new AcoesProdutos();
        this.banco = DS;
    }

    public void menuCadastroProduto(Integer userId){
        System.out.println("\n\u001B[34mProduto:\u001B[0m");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Alterar Produto");
        System.out.println("3 - Excluir Produto");
        System.out.println("4 - Localizar Produto");
        System.out.println("5 - Localizar Mais Produtos");
        System.out.println("6 - Remover Mais Produtos");
        System.out.println("7 - Listar Produtos");
        System.out.println("* - Mais");

        System.out.print("\n-----------------------------------------");
        String id = ReadText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        AcoesCadastroProduto(id, userId);
    }

    public void AcoesCadastroProduto(String id, Integer userId)  {
        boolean session = true;
        JsonExtraction.ColetaJsonDados cjd = new JsonExtraction.ColetaJsonDados();
        MetodosFornecedor forn = new MetodosFornecedor(banco);//cjd.DTForn());

        while (session) {
            switch (id) {
                case "1":
                    //#region Cadastrar novo produto
                    System.out.println("\n# Cadastrar novo produto #\n");
                    //af.cadastrarFornecedor("Jorge", "22", "04472205484", "teste@olos.com.br", "014585445489", "2555555", "04472205", 38, String.valueOf(ReadStrList("try: ")));

                    ap.cadastrarProduto(
                            ReadSentence("Nome do produto: "),
                            ReadDbl("Preco: "),
                            ReadInt("Quantidade: "),
                            forn.fornecedorProd(ReadInt("Fornecedor: ")).getNomeFantasia(),
                            cjd.nomeCategoria(ReadInt("Categoria: "), Produtos.class),
                            cjd.nomeGrupo(ReadInt("Grupo: "), Produtos.class)
                    );
                    System.out.println("\nCadastro concluído!");

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoCadProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do produto" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoCadProd == 1) menuCadastroProduto(userId);
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
                    //#endregion
                case "2":
                    //#region Alterar um produto
                    System.out.println("\n# Alterar um produto #\n");
                    if (ap.listarProdutos()) {
                        System.out.println();

                        int alterId = ReadInt("Id: ");
                        if (ap.validarId(alterId)) {
                            String field = ReadText("Campo: ").toUpperCase();
                            if (field.equals("fornecedor".toUpperCase())) {
                                ap.alterarProduto(
                                    alterId,
                                    field,
                                    forn.fornecedorProd(ReadInt("Alteração (" + field.toUpperCase() + "): ")).getNomeFantasia()
                                );
                            } else {
                                ap.alterarProduto(
                                    alterId,
                                    field,
                                    ReadSentence("Alteração (" + field.toUpperCase() + "): ")
                                );
                            }
                            if (!(MetodosProduto.message == null))
                                System.out.println(MetodosProduto.message);
                            else System.out.println("\nAlteração concluída!");
                            ap.localizarProduto(alterId);
                        }
                        if (!(MetodosProduto.message == null))
                            System.out.println(MetodosProduto.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoAltProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do produto" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral?" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoAltProd == 1) menuCadastroProduto(userId);
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
                    //#endregion
                case "3":
                    //#region Excluir um produto
                    System.out.println("\n# Excluir um produto #\n");
                    if(ap.listarProdutos()) {
                        System.out.println();

                        int remoId = ReadInt("Id: ");
                        if (ap.validarId(remoId)) {
                            ap.excluirProduto(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(MetodosServico.message == null))
                                System.out.println(MetodosProduto.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(MetodosFornecedor.message == null))
                            System.out.println(MetodosFornecedor.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoExcProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do produto" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoExcProd == 1) menuCadastroProduto(userId);
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
                    //#endregion
                case "4":
                    //#region Localizar um produto
                    System.out.println("\n# Localizar um produto #\n");
                    if(ap.listarProdutos()){
                        System.out.println();

                        int findId = ReadInt("Id: ");
                        if (ap.validarId(findId)) {
                            ap.localizarProduto(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(MetodosProduto.message == null))
                                System.out.println(MetodosProduto.message);
                        }
                        if (!(MetodosProduto.message == null))
                            System.out.println(MetodosProduto.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do produto" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocProd == 1) menuCadastroProduto(userId);
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
                //#endregion
                case "5":
                    //#region Localizar vários produtos
                    System.out.println("\n# Localizar vários produtos #\n");
                    if(ap.listarProdutos()){
                        System.out.println();

                        int findId = ReadInt("Início: ");
                        if (ap.validarId(findId)) {
                            ap.localizarMaisProdutos(
                                findId, //ReadInt("Início: "),
                                ReadInt("Fim: ")
                            );
                            if (!(MetodosProduto.message == null))
                                System.out.println(MetodosProduto.message);
                        }
                        if (!(MetodosProduto.message == null))
                            System.out.println(MetodosProduto.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoLocMProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do produto" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoLocMProd == 1) menuCadastroProduto(userId);
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
                //#endregion
                case "6":
                    //#region Remover vários produtos
                    System.out.println("\n# Remover vários produtos #\n");
                    if(ap.listarProdutos()){
                        System.out.println();

                        int remoId = ReadInt("Início: ");
                        if (ap.validarId(remoId)) {
                            ap.removerMaisProdutos(
                                remoId, //ReadInt("Início: "),
                                ReadInt("Fim: ")
                            );
                            if (!(MetodosProduto.message == null))
                                System.out.println(MetodosProduto.message);
                        }
                        if (!(MetodosProduto.message == null))
                            System.out.println(MetodosProduto.message);
                    }

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoRemMProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do produto" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoRemMProd == 1) menuCadastroProduto(userId);
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
                //#endregion
                case "7":
                    //#region Lista de produtos
                    System.out.println("\n# Lista de produtos #\n");
                    ap.listarProdutos();

                    System.out.print("\n----------------------------------------------");
                    Integer opcaoListProd = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do produto" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoListProd == 1) menuCadastroProduto(userId);
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
                //#endregion
                case "*":
                    //#region Retorno ao menu
                    System.out.print("\n----------------------------------------------");
                    Integer opcaoVoltar = ReadInt("\n\033[3mO que deseja?" +
                            "\n(1) Permanecer na tela de cadastro do produto" +
                            "\n(2) Retornar ao menu principal" +
                            "\n(3) Ir para o menu de cadastro geral" +
                            "\n(4) Sair da aplicação?: \033[0m");
                    System.out.println("----------------------------------------------");

                    if (opcaoVoltar == 1) menuCadastroProduto(userId);
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

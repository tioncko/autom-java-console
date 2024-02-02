package Cadastro.NovosDados.Areas;

import Cadastro.Database.Metodos.metodosFornecedores;
import Cadastro.Database.dataSet;
import Cadastro.Database.Metodos.metodosProduto;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Raiz.Acesso.menuPrincipal;
import Cadastro.Database.Metodos.Interfaces.IAreaCadastro;
import Raiz.Inicio.Cadastro;
import Raiz.Utils.leitorDados;
import Raiz.Utils.smartTools.*;
import Cadastro.Database.JSON.JsonTools.jsonExtraction;

public class areaCadastroProduto extends leitorDados implements IAreaCadastro {

    menuPrincipal mp;
    Cadastro cad;
    Cadastro.AcoesProdutos ap;
    dataSet<?> banco;

    public areaCadastroProduto(dataSet<?> DS)  {
        this.mp = new menuPrincipal(DS);
        this.cad = new Cadastro(DS);
        this.ap = cad.new AcoesProdutos();
        this.banco = DS;
    }

    @Override
    public void menuAreaCadastro(Integer userId){
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
        String id = readText("\n\033[3mDigite código do menu para suas ações: \033[0m");
        System.out.println("-----------------------------------------");
        acoesAreaCadastro(id, userId);
    }

    boolean session = true;
    @Override
    public void acoesAreaCadastro(String id, Integer userId)  {
        jsonExtraction.coletaJsonDados cjd = new jsonExtraction.coletaJsonDados();
        metodosFornecedores forn = new metodosFornecedores(banco);//cjd.DTForn());

        while (session) {
            switch (id) {
                case "1":
                    //#region Cadastrar novo produto
                    System.out.println("\n# Cadastrar novo produto #\n");
                    //af.cadastrarFornecedor("Jorge", "22", "04472205484", "teste@olos.com.br", "014585445489", "2555555", "04472205", 38, String.valueOf(ReadStrList("try: ")));

                    ap.cadastrar(
                            readSentence("Nome do produto: "),
                            readDbl("Preco: "),
                            readInt("Quantidade: "),
                            forn.fornecedorProd(readInt("Fornecedores: ")).getNomeFantasia(),
                            cjd.nomeCategoria(readInt("Categoria: "), Produtos.class),
                            cjd.nomeGrupo(readInt("Grupo: "), Produtos.class)
                    );
                    System.out.println("\nCadastro concluído!");

                    opcoesAreaCadastro(Produtos.class.getName(), userId);
                    break;
                    //#endregion
                case "2":
                    //#region Alterar um produto
                    System.out.println("\n# Alterar um produto #\n");
                    if (ap.listar()) {
                        System.out.println();

                        int alterId = readInt("Id: ");
                        if (ap.validarId(alterId)) {
                            String field = readText("Campo: ").toUpperCase();
                            if (field.equals("fornecedor".toUpperCase())) {
                                ap.alterar(
                                    alterId,
                                    field,
                                    forn.fornecedorProd(readInt("Alteração (" + field.toUpperCase() + "): ")).getNomeFantasia()
                                );
                            } else {
                                ap.alterar(
                                    alterId,
                                    field,
                                    readSentence("Alteração (" + field.toUpperCase() + "): ")
                                );
                            }
                            if (!(metodosProduto.message == null))
                                System.out.println(metodosProduto.message);
                            else System.out.println("\nAlteração concluída!");
                            ap.localizar(alterId);
                        }
                        if (!(metodosProduto.message == null))
                            System.out.println(metodosProduto.message);
                    }

                    opcoesAreaCadastro(Produtos.class.getName(), userId);
                    break;
                    //#endregion
                case "3":
                    //#region Excluir um produto
                    System.out.println("\n# Excluir um produto #\n");
                    if(ap.listar()) {
                        System.out.println();

                        int remoId = readInt("Id: ");
                        if (ap.validarId(remoId)) {
                            ap.excluir(
                                    remoId //ReadInt("Id: ")
                            );
                            if (!(metodosProduto.message == null))
                                System.out.println(metodosProduto.message);
                            else System.out.println("\nExclusão concluída!");
                        }
                        if (!(metodosFornecedores.message == null))
                            System.out.println(metodosFornecedores.message);
                    }

                    opcoesAreaCadastro(Produtos.class.getName(), userId);
                    break;
                    //#endregion
                case "4":
                    //#region Localizar um produto
                    System.out.println("\n# Localizar um produto #\n");
                    if(ap.listar()){
                        System.out.println();

                        int findId = readInt("Id: ");
                        if (ap.validarId(findId)) {
                            ap.localizar(
                                    findId //ReadInt("Id: ")
                            );
                            if (!(metodosProduto.message == null))
                                System.out.println(metodosProduto.message);
                        }
                        if (!(metodosProduto.message == null))
                            System.out.println(metodosProduto.message);
                    }

                    opcoesAreaCadastro(Produtos.class.getName(), userId);
                    break;
                //#endregion
                case "5":
                    //#region Localizar vários produtos
                    System.out.println("\n# Localizar vários produtos #\n");
                    if(ap.listar()){
                        System.out.println();

                        int findId = readInt("Início: ");
                        if (ap.validarId(findId)) {
                            ap.localizarMais(
                                findId, //ReadInt("Início: "),
                                readInt("Fim: ")
                            );
                            if (!(metodosProduto.message == null))
                                System.out.println(metodosProduto.message);
                        }
                        if (!(metodosProduto.message == null))
                            System.out.println(metodosProduto.message);
                    }

                    opcoesAreaCadastro(Produtos.class.getName(), userId);
                    break;
                //#endregion
                case "6":
                    //#region Remover vários produtos
                    System.out.println("\n# Remover vários produtos #\n");
                    if(ap.listar()){
                        System.out.println();

                        int remoId = readInt("Início: ");
                        if (ap.validarId(remoId)) {
                            ap.removerMais(
                                remoId, //ReadInt("Início: "),
                                readInt("Fim: ")
                            );
                            if (!(metodosProduto.message == null))
                                System.out.println(metodosProduto.message);
                        }
                        if (!(metodosProduto.message == null))
                            System.out.println(metodosProduto.message);
                    }

                    opcoesAreaCadastro(Produtos.class.getName(), userId);
                    break;
                //#endregion
                case "7":
                    //#region Lista de produtos
                    System.out.println("\n# Lista de produtos #\n");
                    ap.listar();

                    opcoesAreaCadastro(Produtos.class.getName(), userId);
                    break;
                //#endregion
                case "*":
                    //#region Retorno ao menu
                    opcoesAreaCadastro(Produtos.class.getName(), userId);
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

package Cadastro.Database.Metodos;
import java.util.*;
import java.util.logging.Logger;

import Cadastro.Database.dataSet;
import Cadastro.Database.JSON.JsonTools.jsonExtraction.*;
import Cadastro.NovosDados.Repositorio.DTO.*;
import Cadastro.NovosDados.Repositorio.Enums.camposItens;
import Raiz.Core.impressaoLog;

public class metodosProduto extends Produtos {

    private final coletaJsonDados loja = new coletaJsonDados();
    private final dataSet<Produtos> DS;
    public static String message;

    impressaoLog.logGenerico<metodosProduto> printLog = new impressaoLog.logGenerico<>();
    @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<metodosProduto>) (Object) (metodosProduto.class));

    /**
     * Construtor
     */
    @SuppressWarnings("unchecked")
    public metodosProduto(dataSet<?> banco) {
        this.DS = (dataSet<Produtos>) banco;
    }

    /**
     * Inserir novo produto
     */
    public void novoProduto(Integer id, Produtos prod) {
        DS.insert(prod.setId(id), new Produtos(prod.getnomeProd(), prod.getPreco(), prod.getQtd(), prod.getForn(), prod.getCategoria(), prod.getGrupo()), Produtos.class);
    }

    /**
     * Alterar um produto
     */
    public void alterProduto(Integer id, String Campo, String update) {
        if(!DS.select(Produtos.class).isEmpty()){
            if (DS.select(Produtos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
            camposItens getCampo = camposItens.valueOf(Campo.toUpperCase());

            Set<Map.Entry<Integer, Produtos>> getProd = DS.select(Produtos.class).entrySet();
            getProd.stream()
                    .filter(x -> x.getKey().equals(id))
                    .forEach(prod -> {
                        switch (getCampo) {
                            case DESCRICAO  -> DS.insert(id, new Produtos(update, prod.getValue().getPreco(), prod.getValue().getQtd(), prod.getValue().getForn(), prod.getValue().getCategoria(), prod.getValue().getGrupo()), Produtos.class);
                            case PRECO      -> DS.insert(id, new Produtos(prod.getValue().getnomeProd(), Double.parseDouble(update), prod.getValue().getQtd(), prod.getValue().getForn(), prod.getValue().getCategoria(), prod.getValue().getGrupo()), Produtos.class);
                            case QTD        -> DS.insert(id, new Produtos(prod.getValue().getnomeProd(), prod.getValue().getPreco(), Integer.parseInt(update), prod.getValue().getForn(), prod.getValue().getCategoria(), prod.getValue().getGrupo()), Produtos.class);
                            case FORNECEDOR -> DS.insert(id, new Produtos(prod.getValue().getnomeProd(), prod.getValue().getPreco(), prod.getValue().getQtd(), update, prod.getValue().getCategoria(), prod.getValue().getGrupo()), Produtos.class);
                            case CATEGORIA  -> {
                                try {
                                    DS.insert(id, new Produtos(prod.getValue().getnomeProd(), prod.getValue().getPreco(), prod.getValue().getQtd(), prod.getValue().getForn(), loja.nomeCategoria(Integer.parseInt(update), Produtos.class), prod.getValue().getGrupo()), Produtos.class);
                                } catch (Exception e) {
                                    log.warning("[" + metodosProduto.class.getSimpleName() + "] " + e.getMessage());
                                }
                            }
                            case GRUPO      -> {
                                try {
                                    DS.insert(id, new Produtos(prod.getValue().getnomeProd(), prod.getValue().getPreco(), prod.getValue().getQtd(), prod.getValue().getForn(), prod.getValue().getCategoria(), loja.nomeGrupo(Integer.parseInt(update), Produtos.class)), Produtos.class);
                                } catch (Exception e) {
                                    log.warning("[" + metodosProduto.class.getSimpleName() + "] " + e.getMessage());
                                }
                            }
                        }
                    });
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de produtos está vazia.";
    }

    /**
     * Remover um produto
     */
    public void remoProduto(Integer id) {
        if (!DS.select(Produtos.class).isEmpty()) {
            if (DS.select(Produtos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                DS.select(Produtos.class).remove(id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de produtos está vazia.";
    }

    /**
     * Localizar um produto
     */
    public void findProduto(Integer id) {
        if (!DS.select(Produtos.class).isEmpty()) {
            if (DS.select(Produtos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
            Set<Map.Entry<Integer, Produtos>>
                    getProd = DS.select(Produtos.class).entrySet();

            getProd.stream()
                    .filter(setid -> setid.getKey().equals(id))
                    .forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de produtos está vazia.";
    }

    /**
     * Listar produtos por ids
     */
    public void listbyIdProduto(Integer ini_id, Integer fim_id) {
        if (!DS.select(Produtos.class).isEmpty()) {
            if (DS.select(Produtos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
            Set<Map.Entry<Integer, Produtos>>
                    getProd = DS.select(Produtos.class).entrySet();

            getProd.stream()
                    .filter(id -> id.getKey() >= ini_id && id.getKey() <= fim_id)
                    .forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de produtos está vazia.";
    }

    /**
     * Remover produtos por ids
     */
    public void remobyIdProduto(Integer ini_id, Integer fim_id) {
        if (!DS.select(Produtos.class).isEmpty()) {
            if (DS.select(Produtos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Set<Map.Entry<Integer, Produtos>>
                    getProd = DS.select(Produtos.class).entrySet();

                getProd.removeIf(id -> id.getKey() >= ini_id && id.getKey() <= fim_id);
            //getCli.stream().forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de produtos está vazia.";
    }

    /**
     * Imprimir produtos que estão na lista no momento
     */
    public boolean printMapWithSet() {
        if (!DS.select(Produtos.class).isEmpty()) {
            Set<Map.Entry<Integer, Produtos>>
                    getProd = DS.select(Produtos.class).entrySet();

            for (Map.Entry<Integer, Produtos> setProd : getProd) {
                Integer key = setProd.getKey();
                Produtos cli = setProd.getValue();
                System.out.println("id{" + key + "}, " + cli);
            }
            return true;
        } else {
            System.out.println("A tabela de produtos está vazia.");
            return false;
        }
    }

    /**
     * Retorna um novo id unico
     */
    public Integer nextId() {
        Integer maxnum = null;
        if (!DS.select(Produtos.class).isEmpty()) {
            Set<Map.Entry<Integer, Produtos>>
                    getCli = DS.select(Produtos.class).entrySet();

            maxnum = getCli.stream().mapToInt(Map.Entry::getKey).max().getAsInt();
            //ou maxnum = getCli.stream().mapToInt(x -> x.getKey()).max().getAsInt();
        } else {
            maxnum = 0;
        }
        return maxnum + 1;
    }

    /**
     * Valida se o id existe
     */
    public boolean userValid (Integer id) {
        boolean valid = false;
        if (!DS.select(Produtos.class).isEmpty()) {
            if (DS.select(Produtos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {

                valid = true;
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de produtos está vazia.";

        return valid;
    }

    //#region rascunho
    /*
//private Map<Integer, Produtos> tabProdutos;
public metodosProduto() {
   this.tabProdutos = new HashMap<>();
}
public metodosProduto(metodosProduto prod) {
   this.tabProdutos = prod.tabProdutos;
}

    private enum fieldProd {
    DESCRICAO, PRECO, QTD, FORNECEDOR, CATEGORIA, GRUPO
}

        //tabProdutos.put(prod.setId(id), new Produtos(prod.getnomeProd(), prod.getPreco(), prod.getQtd(), prod.getForn(), prod.getCategoria(), prod.getGrupo()));

    public static void main(String[] args) throws Exception {

        metodosFornecedor forn = new metodosFornecedor();
        metodosProduto prod = new metodosProduto();

        coletaJsonDados<Produtos> cat = new coletaJsonDados<>();

        forn.setNomeFantasia("Motul");
        forn.novoFornecedor(forn.setId(1), forn);

        prod.setnomeProd("Coifa");
        prod.setPreco(257.47);
        prod.setQtd(8);
        prod.setForn(forn.fornecedorProd(1).getNomeFantasia());
        prod.setCategoria(cat.nomeCategoria(1, Produtos.class));
        prod.setGrupo(cat.nomeGrupo(2, Produtos.class));
        prod.novoProduto(prod.setId(1), prod);
        prod.PrintMapWithSet();

        prod.alterProduto(1, "GRUPO", "6");
        prod.PrintMapWithSet();
        //System.out.println(cat.nomeCategoria(1, Produtos.class));
    }
    */
    //#endregion
}

package Cadastro.Database.Metodos;
import java.util.*;
import java.util.logging.Logger;

import Cadastro.Database.DataSet;
import Cadastro.Database.JSON.JsonTools.JsonExtraction.*;
import Cadastro.Database.JSON.JsonTools.JsonResponse;
import Cadastro.NovosDados.Repositorio.DTO.*;
import Cadastro.NovosDados.Repositorio.Enums.camposItens;
import Raiz.Core.ImpressaoLog;

public class MetodosProduto extends Produtos {

    //private Map<Integer, Produtos> tabProdutos;
    private final ColetaJsonDados loja = new ColetaJsonDados();
    private final DataSet<Produtos> DS;
    public static String message;

    ImpressaoLog.LogGenerico<MetodosProduto> printLog = new ImpressaoLog.LogGenerico<>();
    @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<MetodosProduto>) (Object) (MetodosProduto.class));
    /*
    public MetodosProduto() {
       this.tabProdutos = new HashMap<>();
    }
    public MetodosProduto(MetodosProduto prod) {
       this.tabProdutos = prod.tabProdutos;
    }
     */
    @SuppressWarnings("unchecked")
    public MetodosProduto(DataSet<?> banco) {
        this.DS = (DataSet<Produtos>) banco;
    }

    /*
    private enum fieldProd {
        DESCRICAO, PRECO, QTD, FORNECEDOR, CATEGORIA, GRUPO
    }
    */

    public void novoProduto(Integer id, Produtos prod) {
        //tabProdutos.put(prod.setId(id), new Produtos(prod.getnomeProd(), prod.getPreco(), prod.getQtd(), prod.getForn(), prod.getCategoria(), prod.getGrupo()));
        DS.insert(prod.setId(id), new Produtos(prod.getnomeProd(), prod.getPreco(), prod.getQtd(), prod.getForn(), prod.getCategoria(), prod.getGrupo()), Produtos.class);
    }

    public void alterProduto(Integer id, String Campo, String update) {
        if(!DS.select(Produtos.class).isEmpty()){
            if (DS.select(Produtos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
            //fieldProd getCampo = fieldProd.valueOf(Campo.toUpperCase());
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
                                    //System.out.println(e.getMessage());
                                    log.warning("[" + MetodosProduto.class.getSimpleName() + "] " + e.getMessage());
                                }
                            }
                            case GRUPO      -> {
                                try {
                                    DS.insert(id, new Produtos(prod.getValue().getnomeProd(), prod.getValue().getPreco(), prod.getValue().getQtd(), prod.getValue().getForn(), prod.getValue().getCategoria(), loja.nomeGrupo(Integer.parseInt(update), Produtos.class)), Produtos.class);
                                } catch (Exception e) {
                                    //System.out.println(e.getMessage());
                                    log.warning("[" + MetodosProduto.class.getSimpleName() + "] " + e.getMessage());
                                }
                            }
                        }
                    });
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de produtos está vazia.";
    }

    public void remoProduto(Integer id) {
        if (!DS.select(Produtos.class).isEmpty()) {
            if (DS.select(Produtos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                DS.select(Produtos.class).remove(id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de produtos está vazia.";
    }

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

    public boolean PrintMapWithSet() {
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
            System.out.println("\nA tabela de produtos está vazia.");
            return false;
        }
    }

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

    public boolean userValid (Integer id) {
        boolean valid = false;

        if (!DS.select(Produtos.class).isEmpty()) {
            if (DS.select(Produtos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                valid = true;
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de produtos está vazia.";

        return valid;
    }

    /*
    public static void main(String[] args) throws Exception {

        MetodosFornecedor forn = new MetodosFornecedor();
        MetodosProduto prod = new MetodosProduto();

        ColetaJsonDados<Produtos> cat = new ColetaJsonDados<>();

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
}

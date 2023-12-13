package Database.Metodos;
import java.util.*;

import NovosDados.Repositorio.Cadastro.Produtos;

public class MetodosProduto extends Produtos {

    private final Map<Integer, Produtos> tabProdutos;
    private final MetodosFornecedor forn;

    public MetodosProduto() {
        this.tabProdutos = new HashMap<>();
        this.forn = new MetodosFornecedor();
    }

    private enum fieldProd {
        DESCRICAO, PRECO, QTD, FORNECEDOR, CATEGORIA, GRUPO
    }

    public void novoProduto(Integer id, Produtos prod) {
        tabProdutos.put(prod.setId(id), new Produtos(prod.getnomeProd(), prod.getPreco(), prod.getQtd(), prod.getForn(), prod.getCategoria(), prod.getGrupo()));
    }

    public void alterProduto(Integer id, String Campo, String update){
        if(!tabProdutos.isEmpty()){
            fieldProd getCampo = fieldProd.valueOf(Campo.toUpperCase());
            Set<Map.Entry<Integer, Produtos>> getProd = tabProdutos.entrySet();
            getProd.stream()
                    .filter(x -> x.getKey().equals(id))
                    .forEach(prod -> {
                        switch (getCampo) {
                            case DESCRICAO  -> tabProdutos.put(id, new Produtos(update, prod.getValue().getPreco(), prod.getValue().getQtd(), prod.getValue().getForn(), prod.getValue().getCategoria(), prod.getValue().getGrupo()));
                            case PRECO      -> tabProdutos.put(id, new Produtos(prod.getValue().getnomeProd(), Double.parseDouble(update), prod.getValue().getQtd(), prod.getValue().getForn(), prod.getValue().getCategoria(), prod.getValue().getGrupo()));
                            case QTD        -> tabProdutos.put(id, new Produtos(prod.getValue().getnomeProd(), prod.getValue().getPreco(), Integer.parseInt(update), prod.getValue().getForn(), prod.getValue().getCategoria(), prod.getValue().getGrupo()));
                            case FORNECEDOR -> tabProdutos.put(id, new Produtos(prod.getValue().getnomeProd(), prod.getValue().getPreco(), prod.getValue().getQtd(), forn.fornecedorProd(Integer.parseInt(update)), prod.getValue().getCategoria(), prod.getValue().getGrupo()));
                            //case CATEGORIA  -> tabProdutos.put(id, new Produtos(prod.getValue().getnomeProd(), prod.getValue().getPreco(), prod.getValue().getQtd(), prod.getValue().getForn(), update, prod.getValue().getGrupo()));
                            //case GRUPO      -> tabProdutos.put(id, new Produtos(prod.getValue().getnomeProd(), prod.getValue().getPreco(), prod.getValue().getQtd(), prod.getValue().getForn(), prod.getValue().getCategoria(), update));
                        }
                    });
        } else System.out.println("A tabela de produtos está vazia.");
    }
/*
    public static void main(String[] args) {

        MetodosFornecedor forn = new MetodosFornecedor();
        MetodosProduto prod = new MetodosProduto();


        forn.setNomeFantasia("Motul");
        forn.novoFornecedor(forn.setId(1), forn);

        Fornecedor sup;
        sup = forn.fornecedorProd(1);


        prod.setnomeProd("Coifa");
        prod.setQtd(8);
        prod.setPreco(257.47);
        prod.setForn(sup);
        prod.novoProduto(prod.setId(1), prod);

        System.out.println(prod);
    }

 */
}

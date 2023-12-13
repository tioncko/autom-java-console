package NovosDados.Repositorio.Cadastro;

import NovosDados.Repositorio.Abstratos.Gondola;
import NovosDados.Repositorio.Auxiliar.API.Referencias.*;

public class Produtos extends Gondola {

    private int id;
    private String nome;
    private int qtd;
    private Fornecedor forn;

    public Produtos(String nomeProd, double preco, int qtd, Fornecedor forn, Categoria categoria, Grupos grupo) {
        this.nome = nomeProd;
        this.setPreco(preco);
        this.qtd = qtd;
        this.forn = forn;
        this.setCategoria(categoria);
        this.setGrupo(grupo);
    }

    public Produtos() {
    }

    public int getId() {
        return id;
    }

    public Integer setId(int id) {
        return this.id = id;
    }

    public String getnomeProd() {
        return nome;
    }

    public void setnomeProd(String nomeProd) {
        this.nome = nomeProd;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Fornecedor getForn() {
        return forn;
    }

    public void setForn(Fornecedor forn) {
        this.forn = forn;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "nome='" + nome + '\'' +
                ", preco=" + getPreco() +
                ", qtd=" + qtd +
                ", forn=" + forn.getNomeFantasia() +
                ", tipoProduto='" + getCategoria() + '\'' +
                ", grupoProduto='" + getGrupo() + '\'' +
                '}';
    }
}


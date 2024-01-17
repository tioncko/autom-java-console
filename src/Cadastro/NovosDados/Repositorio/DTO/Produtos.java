package Cadastro.NovosDados.Repositorio.DTO;

import Cadastro.NovosDados.Repositorio.Abstratos.Gondola;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;

public class Produtos extends Gondola {

    private int id;
    private String nome;
    private int qtd;
    private String forn;

    public Produtos(String nomeProd, double preco, int qtd, String forn, Categoria categoria, Grupos grupo) {
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

    public String getForn() {
        return forn;
    }
    public void setForn(String forn) {
        this.forn = forn;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "nome='" + nome + '\'' +
                ", preco=" + getPreco() +
                ", qtd=" + qtd +
                ", fornecedor=" + forn +
                ", categoria='" + getCategoria() + '\'' +
                ", grupo='" + getGrupo() + '\'' +
                '}';
    }
}


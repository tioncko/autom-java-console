package Cadastro.NovosDados.Repositorio.DTO;

import Cadastro.NovosDados.Repositorio.Abstratos.Gondola;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;

public class Servicos extends Gondola {

    private int id;
    private String nomeServ;

    public Servicos(String nome, double preco, Categoria categoria, Grupos grupo) {
        this.nomeServ = nome;
        this.setPreco(preco);
        this.setCategoria(categoria);
        this.setGrupo(grupo);
    }

    public Servicos() {
    }

    public int getId() {
        return id;
    }
    public Integer setId(int id) {
        return this.id = id;
    }

    public String getNomeServ() {
        return nomeServ;
    }
    public void setNomeServ(String nome) {
        this.nomeServ = nome;
    }

    @Override
    public String toString() {
        return "Servicos{" +
                "nome='" + nomeServ + '\'' +
                ", preço=" + getPreco()  +
                ", categoria='" + getCategoria() + '\'' +
                ", grupo='" + getGrupo() + '\'' +
                '}';
    }
}

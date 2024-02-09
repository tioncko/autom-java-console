package Cadastro.NovosDados.Repositorio.DTO;

import Cadastro.NovosDados.Repositorio.Abstratos.Gondola;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;

public class Servicos extends Gondola {

    private int id;
    private String nome;

    public Servicos(String nomeServ, double preco, Categoria categoria, Grupos grupo) {
        this.nome = nomeServ;
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

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Servicos{" +
                "nome='" + nome + '\'' +
                ", preço=" + getPreco()  +
                ", categoria='" + getCategoria() + '\'' +
                ", grupo='" + getGrupo() + '\'' +
                '}';
    }
}

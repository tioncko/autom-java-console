package NovosDados.Repositorio.Abstratos;

import NovosDados.Repositorio.Auxiliar.API.Referencias.*;

public abstract class Gondola {

    private double preco;
    private Categoria categoria;
    private Grupos grupo;

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Grupos getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupos grupo) {
        this.grupo = grupo;
    }
}

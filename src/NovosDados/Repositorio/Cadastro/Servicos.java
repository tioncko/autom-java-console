package NovosDados.Repositorio.Cadastro;

import NovosDados.Repositorio.Abstratos.Gondola;

public class Servicos extends Gondola {

    private String id;
    private String nome;

    public Servicos(String nome, double preco, int qtd, String tipoItem) {
        this.nome = nome;
        this.setPreco(preco);
        //Employee
        //this.setTipoItem(tipoItem);
    }

    public Servicos() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", preço=" + getPreco()  +
                '}';
    }
}

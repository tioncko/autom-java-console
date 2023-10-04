package NovosDados.Repositorio;

public class Servicos {

    private String id;
    private String nome;
    private double preço;

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

    public double getPreço() {
        return preço;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    @Override
    public String toString() {
        return "Servicos{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", preço=" + preço +
                '}';
    }
}

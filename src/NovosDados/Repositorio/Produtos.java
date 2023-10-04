package NovosDados.Repositorio;

public class Produtos {

    private String id;
    private String nome;
    private double preço;
    private int qtd;

    public Produtos() {
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

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", preço=" + preço +
                ", qtd=" + qtd +
                '}';
    }
}

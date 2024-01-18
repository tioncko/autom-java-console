package Cadastro.NovosDados.Repositorio.Auxiliar;

public class validarCEP {

    private String CEP;
    private String endereco;
    private int Num;
    private String bairro;
    private String cidade;
    private String UF;

    public String getCEP() {
        return CEP;
    }
    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {this.bairro = bairro;}

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }
    public void setUF(String UF) {
        this.UF = UF;
    }

    public int getNum() {return Num;}
    public void setNum(int num) {
        this.Num = num;
    }

    @Override
    public String toString() {
        return "CEP='" + CEP + '\'' +
                ", endereco='" + endereco + '\'' +
                ", num=" + Num +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", UF='" + UF + '\'';
    }
}

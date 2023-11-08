package NovosDados.Repositorio;

import Utils.MetodosUtils;
import Utils.Objetos.ValidCEP;

public class Fornecedor extends MetodosUtils {

    private int id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String email;
    private String telefone;
    private ValidCEP infoCEP;

    public Fornecedor(String razaoSocial, String nomeFantasia, String cnpj, String email, String telefone, ValidCEP infoCEP) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.infoCEP = infoCEP;
    }

    public Fornecedor() {
    }

    public int getId() {
        return id;
    }

    public Integer setId(int id) {
        return this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ValidCEP getInfoCEP() {
        return infoCEP;
    }

    public void setInfoCEP(ValidCEP infoCEP) {
        this.infoCEP = infoCEP;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", cnpj=" + cnpj +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", " + infoCEP +
                '}';
    }
}

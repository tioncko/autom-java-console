package NovosDados.Repositorio.Cadastro;

import NovosDados.Repositorio.Abstratos.OutrosDados;
import NovosDados.Repositorio.Auxiliar.ValidCEP;

public class Fornecedor extends OutrosDados {

    private int id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    //private String email;
    private String inscEstadual;
    private String inscMunicipal;
    //private String telefone;
    //private ValidCEP infoCEP;

    public Fornecedor(String razaoSocial, String nomeFantasia, String cnpj, String email, String inscEstadual, String inscMunicipal, String telefone, ValidCEP infoCEP) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.setEmail(email);
        this.inscEstadual = inscEstadual;
        this.inscMunicipal = inscMunicipal;
        this.setTelefone(telefone);
        this.setInfoCEP(infoCEP);
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
/*
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
*/
    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }

    public String getInscMunicipal() {
        return inscMunicipal;
    }

    public void setInscMunicipal(String inscMunicipal) {
        this.inscMunicipal = inscMunicipal;
    }
/*
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
*/

    @Override
    public String toString() {
        return "Fornecedor{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", cnpj=" + cnpj + '\'' +
                ", email='" + getEmail() + '\'' +
                ", inscEstadual='" + inscEstadual + '\'' +
                ", inscMunicipal='" + inscMunicipal + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", " + getInfoCEP() +
                '}';
    }
}

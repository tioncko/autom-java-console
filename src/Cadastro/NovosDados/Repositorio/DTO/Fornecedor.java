package Cadastro.NovosDados.Repositorio.DTO;

import Cadastro.NovosDados.Repositorio.Abstratos.outrosDados;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;
import Cadastro.NovosDados.Repositorio.Auxiliar.validarCEP;
import Raiz.Utils.smartTools.genericCollects.*;

import java.util.Objects;

public class Fornecedor extends outrosDados {

    private int id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String inscEstadual;
    private genericSet<Grupos> atividades;

    //#region rascunho
    /*
    //private String email;
    //private String telefone;
    //private validarCEP infoCEP;
     */
    //#endregion

    public Fornecedor(String razaoSocial, String nomeFantasia, String cnpj, String email, String inscEstadual, String telefone, validarCEP infoCEP, genericSet<Grupos> atividades) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.setEmail(email);
        this.inscEstadual = inscEstadual;
        this.setTelefone(telefone);
        this.setInfoCEP(infoCEP);
        this.atividades = atividades;
    }

    public Fornecedor(String razaoSocial, String nomeFantasia, String cnpj, String email, String inscEstadual, String telefone, validarCEP infoCEP) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.setEmail(email);
        this.inscEstadual = inscEstadual;
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

    public String getInscEstadual() {
        return inscEstadual;
    }
    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }

    public genericSet<Grupos> getAtividades() {
        return atividades;
    }
    public void setAtividades(genericSet<Grupos> atividades) {
        this.atividades = atividades;
    }

    //#region rascunho
/*
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
*/

/*
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public validarCEP getInfoCEP() {
        return infoCEP;
    }

    public void setInfoCEP(validarCEP infoCEP) {
        this.infoCEP = infoCEP;
    }
*/
//#endregion

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Fornecedor forn)) return false;
        return Objects.equals(this.getCnpj(), forn.getCnpj());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCnpj());
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", cnpj=" + cnpj + '\'' +
                ", email='" + getEmail() + '\'' +
                ", inscEstadual='" + inscEstadual + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", " + getInfoCEP() +
                ", atividades='" + atividades + "'" +
                '}';
    }
}


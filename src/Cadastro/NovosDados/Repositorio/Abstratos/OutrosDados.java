package Cadastro.NovosDados.Repositorio.Abstratos;

import Cadastro.NovosDados.Repositorio.Auxiliar.ValidCEP;

public abstract class OutrosDados {

    private String email;
    private String telefone;
    private ValidCEP infoCEP;

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
}

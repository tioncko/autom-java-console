package Cadastro.NovosDados.Repositorio.Abstratos;

import Cadastro.NovosDados.Repositorio.Auxiliar.validarCEP;

public abstract class outrosDados {

    private String email;
    private String telefone;
    private validarCEP infoCEP;

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

    public validarCEP getInfoCEP() {
        return infoCEP;
    }
    public void setInfoCEP(validarCEP infoCEP) {
        this.infoCEP = infoCEP;
    }

}

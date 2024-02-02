package Cadastro.NovosDados.Repositorio.Abstratos;

import Cadastro.NovosDados.Repositorio.Auxiliar.validarCEP;

import java.util.Objects;

public abstract class outrosDados {

    private String documento;
    private String email;
    private String telefone;
    private validarCEP infoCEP;

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof outrosDados cli)) return false;
        return Objects.equals(this.getDocumento(), cli.getDocumento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumento());
    }
}

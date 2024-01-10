package Cadastro.NovosDados.Repositorio.Auxiliar;

public class Criptografia {

    private String Encrypting;

    public String getEncrypting() {
        return Encrypting;
    }

    public void setEncrypting(String encrypting) {
        this.Encrypting = encrypting;
    }

    @Override
    public String toString() {
        return Encrypting;
    }
}

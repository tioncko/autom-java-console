package Utils.Objetos;

public class Criptografia {

    private String Encrypting;

    public Criptografia() {

    }

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

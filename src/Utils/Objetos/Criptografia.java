package Utils.Objetos;

public class Criptografia {

    private String Encrypting;

    public String getEncrypting() {
        return Encrypting;
    }

    public void setEncrypting(String encrypting) {
        Encrypting = encrypting;
    }

    @Override
    public String toString() {
        return "Criptografia{" +
                "Encrypting='" + Encrypting + '\'' +
                '}';
    }
}

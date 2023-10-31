package NovosDados.Repositorio;

import Utils.MetodosUtils;
import Utils.Objetos.Criptografia;
import Utils.Objetos.PermissaoUsuario;

public class Usuario extends MetodosUtils {

    private int id;
    private String login;
    private Criptografia password;
    private String nome;
    private String depto;
    private PermissaoUsuario access;

    public Usuario(String login, Criptografia password, String nome, String depto, PermissaoUsuario access) {
        this.login = login;
        this.password = password;
        this.nome = nome;
        this.depto = depto;
        this.access = access;
    }

    public Usuario(String login, Criptografia password, String nome, String depto) {
        this.login = login;
        this.password = password;
        this.nome = nome;
        this.depto = depto;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public Integer setId(int id) {
        return this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Criptografia getPassword() {
        return password;
    }

    public void setPassword(Criptografia password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public PermissaoUsuario getAccess(){
        return access;
    }

    public void setAccess(PermissaoUsuario access){
        this.access = access;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", depto='" + depto + '\'' +
                ", permissao='" + access + '\'' +
                '}';
    }
}

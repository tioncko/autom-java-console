package Utils.Objetos;

import NovosDados.Repositorio.Usuario;

public class PermissaoUsuario extends Usuario {

    private String Permissao;

    public PermissaoUsuario(String permissao) {
        Permissao = permissao;
    }

    public PermissaoUsuario(String login, Criptografia password, String nome, String depto, String permissao) {
        super(login, password, nome, depto);
        Permissao = permissao;
    }

    public PermissaoUsuario() {
    }

    public String getPermissao() {
        return Permissao;
    }

    public void setPermissao(String permissao) {
        Permissao = permissao;
    }


    public String toString() {
        return Permissao;
    }
}

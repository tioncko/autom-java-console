package Utils.Objetos;

import NovosDados.Repositorio.Usuario;

public class PermissaoUsuario extends Usuario {

    private final String Permissao;

    public PermissaoUsuario(String permissao) {
        this.Permissao = permissao;
    }

    public String toString() {
        return Permissao;
    }
}

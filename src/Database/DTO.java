package Database;

import Database.Metodos.MetodosUsuario;
import NovosDados.Repositorio.Enums.Permissao;
import Utils.MetodosUtils.*;

public class DTO {

    MetodosUsuario musr;

    public DTO() {
        this.musr = new MetodosUsuario();
    }

    public MetodosUsuario DTUsers() {

        Integer idSup = musr.nextId();
        musr.setId(idSup);
        musr.setLogin("supervisor".toLowerCase());
        musr.setPassword(Senha.Encrypt("@autom123"));
        musr.setNome("Supervisor");
        musr.setDepto("TI");
        musr.novoUsuario(idSup, musr);
        musr.rootPermissao(idSup, String.valueOf(Permissao.ROOT));

        Integer idAdm = musr.nextId();
        musr.setId(idAdm);
        musr.setLogin("admin".toLowerCase());
        musr.setPassword(Senha.Encrypt("@admin123"));
        musr.setNome("Admin");
        musr.setDepto("TI");
        musr.novoUsuario(idAdm, musr);
        musr.darPermissao(idAdm, String.valueOf(Permissao.ADMIN));
        return musr;
    }
}

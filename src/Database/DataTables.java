package Database;

import Database.Metodos.MetodosUsuario;
import Utils.MetodosUtils.*;

public class DataTables {

    MetodosUsuario musr;

    public DataTables() {
        this.musr = new MetodosUsuario();
    }

    public MetodosUsuario DTUsers() {
        musr.setLogin("supervisor".toLowerCase());
        musr.setPassword(Senha.Encrypt("@autom123"));
        musr.setNome("Supervisor");
        musr.setDepto("TI");
        musr.novoUsuario(musr.nextId(), musr);

        return musr;
    }
}

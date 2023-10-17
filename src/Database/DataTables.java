package Database;

import Database.Metodos.MetodosUsuario;

import static Utils.MetodosUtils.Encrypt;

public class DataTables {

    MetodosUsuario musr;

    public DataTables() {
        this.musr = new MetodosUsuario();
    }

    public MetodosUsuario DTUsers() throws Exception {
        musr.setLogin("supervisor");
        musr.setPassword(Encrypt("@autom123"));
        musr.setNome("Supervisor");
        musr.setDepto("Juridico");
        musr.novoUsuario(musr.nextId(), musr);

        musr.setLogin("user");
        musr.setPassword(Encrypt("@user123"));
        musr.setNome("Paula Matos");
        musr.setDepto("TI");
        musr.novoUsuario(musr.nextId(), musr);

        return musr;
    }
}

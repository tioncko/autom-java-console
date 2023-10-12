package Database;

import Database.Metodos.MetodosUsuario;

import static Utils.MetodosUtils.Encrypt;

public class DataTables {

    MetodosUsuario musr;

    public DataTables() {
        this.musr = new MetodosUsuario();
    }

    public MetodosUsuario DTUsers() throws Exception {
        musr.setLogin("Keyla");
        musr.setPassword(Encrypt("1234"));
        musr.setNome("Keyla Nascimento");
        musr.setDepto("Juridico");
        musr.novoUsuario(musr.nextId(), musr);

        musr.setLogin("Paula");
        musr.setPassword(Encrypt("5845"));
        musr.setNome("Paula Matos");
        musr.setDepto("TI");
        musr.novoUsuario(musr.nextId(), musr);

        musr.setLogin("Rose");
        musr.setPassword(Encrypt("8754"));
        musr.setNome("Rose Barros");
        musr.setDepto("TI");
        musr.novoUsuario(musr.nextId(), musr);

        musr.setLogin("Tabata");
        musr.setPassword(Encrypt("9687"));
        musr.setNome("Tabata Amaral");
        musr.setDepto("Governança");
        musr.novoUsuario(musr.nextId(), musr);

        return musr;
    }
}

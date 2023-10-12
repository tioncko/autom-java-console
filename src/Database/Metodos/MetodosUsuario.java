package Database.Metodos;

import NovosDados.Repositorio.Usuario;

import java.util.*;

public class MetodosUsuario extends Usuario {

    private Map<Integer, Usuario> tabUsuario;

    public MetodosUsuario() {
        this.tabUsuario = new HashMap<Integer, Usuario>();
    }

    public void novoUsuario(Integer id, Usuario user) {
        tabUsuario.put(user.setId(id), new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto()));
    }

    public void alterUsuario(Integer id, String Campo, String update) throws Exception {
        Set<Map.Entry<Integer, Usuario>>
                getUser = tabUsuario.entrySet();

        for (Map.Entry<Integer, Usuario> setUser : getUser) {
            Usuario user = setUser.getValue();


            if (setUser.getKey().equals(id)) {

                switch (Campo) {
                    case "Login":
                        tabUsuario.put(id, new Usuario(update, user.getPassword(), user.getNome(), user.getDepto()));
                        break;
                    case "Senha":
                        tabUsuario.put(id, new Usuario(user.getLogin(), Encrypt(update), user.getNome(), user.getDepto()));
                        break;
                    case "Nome":
                        tabUsuario.put(id, new Usuario(user.getLogin(), user.getPassword(), update, user.getDepto()));
                        break;
                    case "Depto":
                        tabUsuario.put(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), update));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void remoUsuario(Integer id) {
        tabUsuario.remove(id);
    }

    public void findUsuario(Integer id) {
        Set<Map.Entry<Integer, Usuario>>
                getUser = tabUsuario.entrySet();

        getUser.stream()
                .filter(setid -> setid.getKey().equals(id))
                .forEach(y -> System.out.println("id{" + y.getKey() + "}, " + y.getValue()));
    }

    public void listbyIdUsuario(Integer ini_id, Integer fim_id) {
        Set<Map.Entry<Integer, Usuario>>
                getUser = tabUsuario.entrySet();

        getUser.stream()
                .filter(setid -> setid.getKey() >= ini_id && setid.getKey() <= fim_id)
                .forEach(y -> System.out.println("id{" + y.getKey() + "}, " + y.getValue()));
    }

    public void remobyIdUsuario(Integer ini_id, Integer fim_id) {
        Set<Map.Entry<Integer, Usuario>>
                getUser = tabUsuario.entrySet();

        getUser.removeIf(setid -> setid.getKey() >= ini_id && setid.getKey() <= fim_id);
    }

    public Integer nextId() {

        Integer maxnum = null;
        if(!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>>
                    getUser = tabUsuario.entrySet();

            maxnum = getUser.stream().mapToInt(Map.Entry::getKey).max().getAsInt();
        }
        else maxnum = 0;

        return maxnum + 1;
    }

    public boolean validUsuario(String login, String pass, MetodosUsuario dt) throws Exception {

        boolean valid = false;

        this.tabUsuario = dt.tabUsuario;
        String decrypt = String.valueOf(Encrypt(pass));

        Set<Map.Entry<Integer, Usuario>>
                getUser = tabUsuario.entrySet();

        for (Map.Entry<Integer, Usuario> setUser : getUser) {
            Usuario user = setUser.getValue();

            if ((user.getLogin().equals(login)) && (user.getPassword().toString().equals(decrypt))) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    public void PrintMapWithSet() {
        Set<Map.Entry<Integer, Usuario>>
                getUser = tabUsuario.entrySet();

        for (Map.Entry<Integer, Usuario> setUser : getUser) {
            Integer key = setUser.getKey();
            Usuario user = setUser.getValue();
            System.out.println("id{" + key + "}, " + user);
        }
    }

//#region notes
/*
    public static void main(String[] args) throws Exception {

        MetodosUsuario musr = new MetodosUsuario();

        System.out.println("# Lista de clientes #");

        musr.setLogin("Keyla");
        musr.setPassword(Encrypt("1234"));
        musr.setNome("Keyla Nascimento");
        musr.setDepto("Juridico");
        musr.novoUsuario(1, musr);

        musr.setLogin("Paula");
        musr.setPassword(Encrypt("5845"));
        musr.setNome("Paula Matos");
        musr.setDepto("TI");
        musr.novoUsuario(2, musr);

        musr.setLogin("Rose");
        musr.setPassword(Encrypt("8754"));
        musr.setNome("Rose Barros");
        musr.setDepto("TI");
        musr.novoUsuario(3, musr);

        musr.setLogin("Tabata");
        musr.setPassword(Encrypt("9687"));
        musr.setNome("Tabata Amaral");
        musr.setDepto("Governança");
        musr.novoUsuario(4, musr);

        musr.PrintMapWithSet();
        System.out.println();

        System.out.println("# Alteração na lista de clientes #");
        musr.alterUsuario(4, "Login", "Amaral");
        musr.alterUsuario(1, "Nome", "Nayara");
        musr.alterUsuario(3, "Depto", "RH");
        musr.alterUsuario(2, "Senha", "azul");
        musr.PrintMapWithSet();
        System.out.println();

        System.out.println("# Remover na lista de clientes #");
        musr.remoUsuario(1);
        musr.PrintMapWithSet();
        System.out.println();

        System.out.println("# Localizar na lista de clientes por id #");
        musr.findUsuario(3);
        System.out.println();

        System.out.println("# Listar por ids na lista de clientes #");
        musr.listbyIdUsuario(2, 3);
        System.out.println();

        System.out.println("# Remover por ids na lista de clientes #");
        musr.remobyIdUsuario(1, 3);
        musr.PrintMapWithSet();

        if (musr.validUsuario("Amaral", "9687")) System.out.println("\n" + "Usuário EXISTE na base de dados");
        else System.out.println("\n" + "Usuário não existe na base de dados");

        System.out.println("\nPróximo id: " + musr.nextId());
    }

 */
//#endregion
}

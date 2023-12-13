package Database.Metodos;

import NovosDados.Repositorio.Auxiliar.PermissaoUsuario;
import NovosDados.Repositorio.Cadastro.Usuario;
import NovosDados.Repositorio.Enums.Permissao;
import Utils.Numeros;

import java.util.*;

public class MetodosUsuario extends Usuario {

    private Map<Integer, Usuario> tabUsuario;
    public static int cod = 0;

    /**
     * Construtor
     */
    public MetodosUsuario() {
        this.tabUsuario = new HashMap<>();
    }
    public MetodosUsuario(MetodosUsuario usr) {
        this.tabUsuario = usr.tabUsuario;
    }

    /**
     * Inserir novo usuário
     */
    public void novoUsuario(Integer id, Usuario user) {
        var mu = new MetodosUsuario();
        mu.tabUsuario = this.tabUsuario;

        if (!validUsuario(user.getLogin(), mu)) {
            tabUsuario.put(user.setId(id), new Usuario(user.getLogin().toLowerCase(), user.getPassword(), user.getNome(), user.getDepto()));
        } else cod = 1;
    }

    /**
     * Alterar um usuário
     */
    public void alterUsuario(Integer id, String Campo, String update) {
        var mu = new MetodosUsuario();
        mu.tabUsuario = this.tabUsuario;

        if (!tabUsuario.isEmpty()) {
            fieldUser getCampo = fieldUser.valueOf(Campo.toUpperCase());

            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Usuario user = setUser.getValue();
                if (setUser.getKey().equals(id)) {
                    switch (getCampo) {
                        case LOGIN:
                            if (!validUsuario(user.getLogin(), mu)) {
                                tabUsuario.put(id, new Usuario(update.toLowerCase(), user.getPassword(), user.getNome(), user.getDepto()));
                                cod = 1;
                            } else cod = 2;
                            break;
                        case SENHA:
                            tabUsuario.put(id, new Usuario(user.getLogin(), Senha.Encrypt(update), user.getNome(), user.getDepto()));
                            break;
                        case NOME:
                            tabUsuario.put(id, new Usuario(user.getLogin(), user.getPassword(), update, user.getDepto()));
                            break;
                        case DEPTO:
                            tabUsuario.put(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), update));
                            break;
                        default:
                            break;
                    }
                }
            }
        } else System.out.println("A tabela de usuário está vazia.");
    }
    //#region lambda
/*
    getUser.stream()
    .filter(x-> x.getKey().equals(id))
    .forEach(user -> {
        switch (getCampo) {
            case LOGIN -> {
                if (!validUsuario(user.getValue().getLogin(), mu)) {
                    tabUsuario.put(id, new Usuario(update.toLowerCase(), user.getValue().getPassword(),
                            user.getValue().getNome(), user.getValue().getDepto()));
                }
                cod = 1;
            }
            case SENHA ->
                    tabUsuario.put(id, new Usuario(user.getValue().getLogin(), Senha.Encrypt(update),
                            user.getValue().getNome(), user.getValue().getDepto()));
            case NOME ->
                    tabUsuario.put(id, new Usuario(user.getValue().getLogin(), user.getValue().getPassword(), update,
                            user.getValue().getDepto()));
            case DEPTO ->
                    tabUsuario.put(id, new Usuario(user.getValue().getLogin(), user.getValue().getPassword(),
                            user.getValue().getNome(), update));
        }
    });
*/
    //#endregion

    /**
     * Remover um usuário
     */
    public void remoUsuario(Integer id) {
        if (!tabUsuario.isEmpty()) {
            tabUsuario.remove(id);
        } else System.out.println("A tabela de usuário está vazia.");
    }

    /**
     * Localizar um usuário
     */
    public void findUsuario(Integer id) {
        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            getUser.stream()
                    .filter(setid -> setid.getKey().equals(id))
                    .forEach(y -> System.out.println("id{" + y.getKey() + "}, " + y.getValue()));
        } else System.out.println("A tabela de usuário está vazia.");
    }

    /**
     * Listar usuários por ids
     */
    public void listbyIdUsuario(Integer ini_id, Integer fim_id) {
        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            getUser.stream()
                    .filter(setid -> setid.getKey() >= ini_id && setid.getKey() <= fim_id)
                    .forEach(y -> System.out.println("id{" + y.getKey() + "}, " + y.getValue()));
        } else System.out.println("A tabela de usuário está vazia.");
    }

    /**
     * Remover usuários por ids
     */
    public void remobyIdUsuario(Integer ini_id, Integer fim_id) {
        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            getUser.removeIf(setid -> setid.getKey() >= ini_id && setid.getKey() <= fim_id);
        } else System.out.println("A tabela de usuário está vazia.");
    }

    /**
     * Retorna um novo id unico
     */
    public Integer nextId() {
        Integer maxnum = null;
        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            maxnum = getUser.stream().mapToInt(Map.Entry::getKey).max().getAsInt();
        } else  maxnum = 0;

        return maxnum + 1;
    }


    public boolean validUsuario(String login, MetodosUsuario dt) {
        boolean valid = false;
        this.tabUsuario = dt.tabUsuario;

        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            valid = getUser.stream().anyMatch(x -> x.getValue().getLogin().equals(login.strip()));
        }

        return valid;
    }

    /**
     * Validar usuário para acesso a plataforma
     */
    public boolean validLoginUsuario(String login, String pass, MetodosUsuario dt) {
        boolean valid = false;
        this.tabUsuario = dt.tabUsuario;

        if (!tabUsuario.isEmpty()) {
            String encrypt = String.valueOf(Senha.Encrypt(pass));
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Usuario user = setUser.getValue();
                if ((user.getLogin().equals(login)) && (user.getPassword().toString().equals(encrypt))) {
                    valid = true;
                    break;
                }
            }
        } else System.out.println("A tabela de usuário está vazia.");
        return valid;
    }

    /**
     * Imprimir usuários que estão na lista no momento
     */
    public boolean PrintMapWithSet() {
        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Integer key = setUser.getKey();
                Usuario user = setUser.getValue();
                System.out.println("id{" + key + "}, " + user);
            }
            return true;
        } else {
            System.out.println("A tabela de usuário está vazia.");
            return false;
        }
    }

    /**
     * Enum para o campo de alteração da tabUsuario
     */
    private enum fieldUser {
        LOGIN, SENHA, NOME, DEPTO;
    }

    public Integer UserId(String access, MetodosUsuario dt) {
        this.tabUsuario = dt.tabUsuario;
        Integer retrn = null;

        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Usuario user = setUser.getValue();
                if (user.getLogin().equals(access.toLowerCase())) {
                    retrn = setUser.getKey();
                    break;
                }
            }
        } else System.out.println("A tabela de usuário está vazia.");
        return retrn;
    }

    /**
     * Validando permissão de acesso ao usuario
     */
    public PermissaoUsuario validPermissao(String login, MetodosUsuario dt){
        PermissaoUsuario access = null;
        this.tabUsuario = dt.tabUsuario;

        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Usuario user = setUser.getValue();
                if (user.getLogin().equals(login)) {
                    access = user.getAccess();
                    break;
                }
            }
        } else System.out.println("A tabela de usuário está vazia.");
        return access;
    }

    /**
     * Usuário root chumbado
     */
    public void rootPermissao(Integer id, String access) {
        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Integer kid = setUser.getKey();
                Usuario user = setUser.getValue();
                PermissaoUsuario root = new PermissaoUsuario(String.valueOf(Permissao.ROOT));
                if (id.equals(1) && kid.equals(id) && user.getLogin().equals("supervisor".toLowerCase()) && access.equals(String.valueOf(root))) {

                    Permissao acessosup;
                    acessosup = Numeros.isNumeric(access) ? Permissao.getAccess(Integer.parseInt(access)) : Permissao.valueOf(access.toUpperCase());
                    PermissaoUsuario perm = new PermissaoUsuario(String.valueOf(acessosup).toUpperCase());

                    tabUsuario.put(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto(), perm));
                }
            }
        } else System.out.println("Não foi possível acessar o sistema.");
    }

    /**
     * Dar permissão a um usuário
     */
    public void darPermissao(Integer id, String access) {
        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            Permissao acesso = Numeros.isNumeric(access) ? Permissao.getAccess(Integer.parseInt(access)) : Permissao.valueOf(access.toUpperCase());

            if ((!access.toUpperCase().equals(String.valueOf(Permissao.ROOT))) || (!String.valueOf(acesso).equals(String.valueOf(1)))) {
                for (Map.Entry<Integer, Usuario> setUser : getUser) {

                    Usuario user = setUser.getValue();
                    if (setUser.getKey().equals(id)) {

                        PermissaoUsuario perm = new PermissaoUsuario(String.valueOf(acesso).toUpperCase());
                        tabUsuario.put(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto(), perm));
                    }
                }
            } else System.out.println("Permissão raiz indisponível.");
        } else System.out.println("A tabela de usuário está vazia.");
    }

    /**
     * Alterar permissão de um usuário
     */
    public void altPermissao(Integer idAdm, Integer id, String access) {
        var mu = new MetodosUsuario();
        mu.tabUsuario = this.tabUsuario;

        if (!tabUsuario.isEmpty()) {
            if(!access.toUpperCase().equals(String.valueOf(Permissao.ROOT))) {
                PermissaoUsuario admin = new PermissaoUsuario(String.valueOf(Permissao.ADMIN));
                PermissaoUsuario root = new PermissaoUsuario(String.valueOf(Permissao.ROOT));
                Permissao acesso = Numeros.isNumeric(access) ? Permissao.getAccess(Integer.parseInt(access)) : Permissao.valueOf(access.toUpperCase());
                PermissaoUsuario perm = new PermissaoUsuario(String.valueOf(acesso).toUpperCase());

                Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
                getUser.stream()
                        .filter(idA -> idA.getKey().equals(idAdm))
                        .filter(adm -> (Objects.equals(String.valueOf(adm.getValue().getAccess()), String.valueOf(admin))) || (Objects.equals(String.valueOf(adm.getValue().getAccess()), String.valueOf(root))))
                        .filter(kid -> kid.getKey().equals(id))
                        .filter(usr -> (!Objects.equals(String.valueOf(usr.getValue().getAccess()), String.valueOf(root))))
                        .forEach(user -> tabUsuario.put(id, new Usuario(user.getValue().getLogin(), user.getValue().getPassword(), user.getValue().getNome(), user.getValue().getDepto(), perm)));

            } else System.out.println("Permissão raiz indisponível.");
        } else System.out.println("A tabela de usuário está vazia.");
    }

    /**
     * Remover permissão de um usuário
     */
    public void remPermissao(Integer id) {
        if (!tabUsuario.isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = tabUsuario.entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Integer kid = setUser.getKey();
                Usuario user = setUser.getValue();
                PermissaoUsuario root = new PermissaoUsuario(String.valueOf(Permissao.ROOT));

                if (kid.equals(id) && (!Objects.equals(String.valueOf(user.getAccess()), String.valueOf(root)))) {
                    tabUsuario.put(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto()));
                } else System.out.println("Não é possível retirar permissão raiz deste usuário.");
            }
        } else System.out.println("A tabela de usuário está vazia.");
    }
    //#region notes
/*
    public static void main(String[] args) throws Exception {

        MetodosUsuario musr = new MetodosUsuario();

        System.out.println("# Lista de clientes #");

        musr.setLogin("Keyla");
        musr.setPassword(Senha.Encrypt("1234"));
        musr.setNome("Keyla Nascimento");
        musr.setDepto("Juridico");
        musr.novoUsuario(1, musr);

        musr.setLogin("Paula");
        musr.setPassword(Senha.Encrypt("5845"));
        musr.setNome("Paula Matos");
        musr.setDepto("TI");
        musr.novoUsuario(2, musr);

        musr.setLogin("Rose");
        musr.setPassword(Senha.Encrypt("8754"));
        musr.setNome("Rose Barros");
        musr.setDepto("TI");
        musr.novoUsuario(3, musr);

        musr.setLogin("Tabata");
        musr.setPassword(Senha.Encrypt("9687"));
        musr.setNome("Tabata Amaral");
        musr.setDepto("Governança");
        musr.novoUsuario(4, musr);

        musr.setLogin("supervisor");
        musr.setPassword(Senha.Encrypt("5474"));
        musr.setNome("Perola Pardo");
        musr.setDepto("Business");
        musr.novoUsuario(5, musr);

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

        System.out.println();
        musr.darPermissao(5, "root");
        musr.darPermissao(4, "user");
        musr.PrintMapWithSet();

        System.out.println();
        System.out.println("# Alterando permissao #");
        musr.altPermissao(5,4, "admin");
        System.out.println();
        musr.PrintMapWithSet();

        //if (musr.validUsuario("Amaral", "9687")) System.out.println("\n" + "Usuário EXISTE na base de dados");
        //else System.out.println("\n" + "Usuário não existe na base de dados");

        System.out.println("\nPróximo id: " + musr.nextId());
    }
*/

    //#endregion
}

package Cadastro.Database.Metodos;

import Cadastro.Database.dataSet;
import Cadastro.NovosDados.Repositorio.Auxiliar.permissaoUsuario;
import Cadastro.NovosDados.Repositorio.DTO.Usuario;
import Cadastro.NovosDados.Repositorio.Enums.camposUsuario;
import Cadastro.NovosDados.Repositorio.Enums.permissao;
import Raiz.Utils.smartTools;

import java.util.*;

public class metodosUsuario extends Usuario {

    private dataSet<Usuario> DS;
    public static String message;
    public static int cod = 0;

    /**
     * Construtor
     */
    @SuppressWarnings("unchecked")
    public metodosUsuario(dataSet<?> banco) {
        this.DS = (dataSet<Usuario>) banco;
    }

    /**
     * Inserir novo usuário
     */
    public void novoUsuario(Integer id, Usuario user) {
        if (!validUsuario(user.getLogin())) {
            DS.insert(user.setId(id), new Usuario(user.getLogin().toLowerCase(), user.getPassword(), user.getNome(), user.getDepto()), Usuario.class);
        } else cod = 1;
    }

    /**
     * Alterar um usuário
     */
    public void alterUsuario(Integer id, String Campo, String update) {
        if (!DS.select(Usuario.class).isEmpty()) {
            if (DS.select(Usuario.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                camposUsuario getCampo = camposUsuario.valueOf(Campo.toUpperCase());

                Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
                for (Map.Entry<Integer, Usuario> setUser : getUser) {

                    Usuario user = setUser.getValue();
                    if (setUser.getKey().equals(id)) {
                        switch (getCampo) {
                            case LOGIN:
                                if (!validUsuario(user.getLogin())) {
                                    DS.insert(id, new Usuario(update.toLowerCase(), user.getPassword(), user.getNome(), user.getDepto()), Usuario.class);
                                    cod = 1;
                                } else cod = 2;
                                break;
                            case SENHA:
                                DS.insert(id, new Usuario(user.getLogin(), smartTools.Senha.Encrypt(update), user.getNome(), user.getDepto()), Usuario.class);
                                break;
                            case NOME:
                                DS.insert(id, new Usuario(user.getLogin(), user.getPassword(), update, user.getDepto()), Usuario.class);
                                break;
                            case DEPTO:
                                DS.insert(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), update), Usuario.class);
                                break;
                            default:
                                break;
                        }
                    }
                }
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de usuários está vazia.";
    }

    /**
     * Remover um usuário
     */
    public void remoUsuario(Integer id) {
        if (!DS.select(Usuario.class).isEmpty()) {
            if (DS.select(Usuario.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                DS.select(Usuario.class).remove(id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de usuários está vazia.";
    }

    /**
     * Localizar um usuário
     */
    public void findUsuario(Integer id) {
        if (!DS.select(Usuario.class).isEmpty()) {
            if (DS.select(Usuario.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();

                getUser.stream().filter(setid -> setid.getKey().equals(id)).forEach(y -> System.out.println("id{" + y.getKey() + "}, " + y.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de usuários está vazia.";
    }

    /**
     * Listar usuários por ids
     */
    public void listbyIdUsuario(Integer ini_id, Integer fim_id) {
        if (!DS.select(Usuario.class).isEmpty()) {
            if (DS.select(Usuario.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();

                getUser.stream().filter(setid -> setid.getKey() >= ini_id && setid.getKey() <= fim_id).forEach(y -> System.out.println("id{" + y.getKey() + "}, " + y.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de usuários está vazia.";
    }

    /**
     * Remover usuários por ids
     */
    public void remobyIdUsuario(Integer ini_id, Integer fim_id) {
        if (!DS.select(Usuario.class).isEmpty()) {
            if (DS.select(Usuario.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();

                getUser.removeIf(setid -> setid.getKey() >= ini_id && setid.getKey() <= fim_id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de usuários está vazia.";
    }

    /**
     * Retorna um novo id unico
     */
    public Integer nextId() {
        Integer maxnum = null;
        if (DS.select(Usuario.class) != null) {
            Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
            maxnum = getUser.stream().mapToInt(Map.Entry::getKey).max().getAsInt();
        } else
            maxnum = 0;

        return maxnum + 1;
    }

    /**
     * Valida se o usuário já existe
     */
    public boolean validUsuario(String login) {//}, metodosUsuario dt) {
        boolean valid = false;

        if (!DS.select(Usuario.class).isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
            valid = getUser.stream().anyMatch(x -> x.getValue().getLogin().equals(login.strip()));
        }

        return valid;
    }

    /**
     * Validar login para acesso à plataforma (autenticação)
     */
    public boolean validLoginUsuario(String login, String pass) {//}, metodosUsuario dt) {
        boolean valid = false;

        if (!DS.select(Usuario.class).isEmpty()) {
            String encrypt = String.valueOf(smartTools.Senha.Encrypt(pass));
            Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Usuario user = setUser.getValue();
                if ((user.getLogin().equals(login)) && (user.getPassword().toString().equals(encrypt))) {
                    valid = true;
                    break;
                }
            }
        } else
            System.out.println("\nA tabela de usuários está vazia.");
        return valid;
    }

    /**
     * Imprimir usuários que estão na lista no momento
     */
    public boolean printMapWithSet() {
        if (!DS.select(Usuario.class).isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Integer key = setUser.getKey();
                Usuario user = setUser.getValue();
                System.out.println("id{" + key + "}, " + user);
            }
            return true;
        } else {
            System.out.println("A tabela de usuários está vazia.");
            return false;
        }
    }

    /**
     * Enum para o campo de alteração da tabUsuario
     */
    public Integer userId(String access) {//}, metodosUsuario dt) {
        Integer retrn = null;

        if (!DS.select(Usuario.class).isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Usuario user = setUser.getValue();
                if (user.getLogin().equals(access.toLowerCase())) {
                    retrn = setUser.getKey();
                    break;
                }
            }
        } else
            System.out.println("\nA tabela de usuários está vazia.");
        return retrn;
    }

    /**
     * Validando permissão de acesso ao usuario
     */
    public permissaoUsuario validPermissao(String login) {//}, metodosUsuario dt){
        permissaoUsuario access = null;

        if (!DS.select(Usuario.class).isEmpty()) {
            Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
            for (Map.Entry<Integer, Usuario> setUser : getUser) {

                Usuario user = setUser.getValue();
                if (user.getLogin().equals(login)) {
                    access = user.getAccess();
                    break;
                }
            }
        } else
            System.out.println("\nA tabela de usuários está vazia.");
        return access;
    }

    /**
     * Usuário root chumbado
     */
    public void rootPermissao(Integer id, String access) {
        if (!DS.select(Usuario.class).isEmpty()) {
            if (DS.select(Usuario.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
                for (Map.Entry<Integer, Usuario> setUser : getUser) {

                    Integer kid = setUser.getKey();
                    Usuario user = setUser.getValue();
                    permissaoUsuario root = new permissaoUsuario(String.valueOf(permissao.ROOT));
                    if (id.equals(1) && kid.equals(id) && user.getLogin().equals("supervisor".toLowerCase()) && access.equals(String.valueOf(root))) {

                        permissao acessosup;
                        acessosup = smartTools.Numeros.isNumeric(access) ? permissao.getAccess(Integer.parseInt(access)) : permissao.valueOf(access.toUpperCase());
                        permissaoUsuario perm = new permissaoUsuario(String.valueOf(acessosup).toUpperCase());

                        DS.insert(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto(), perm), Usuario.class);
                    }
                }
            } else message = "\nRegistro inexistente na tabela.";
        } else System.out.println("\nNão foi possível acessar o sistema.");
    }

    /**
     * Dar permissão a um usuário
     */
    public void darPermissao(Integer id, String access) {
        if (!DS.select(Usuario.class).isEmpty()) {
            if (DS.select(Usuario.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
                permissao acesso = smartTools.Numeros.isNumeric(access) ? permissao.getAccess(Integer.parseInt(access)) : permissao.valueOf(access.toUpperCase());

                if ((!access.toUpperCase().equals(String.valueOf(permissao.ROOT))) || (!String.valueOf(acesso).equals(String.valueOf(1)))) {
                    for (Map.Entry<Integer, Usuario> setUser : getUser) {

                        Usuario user = setUser.getValue();
                        if (setUser.getKey().equals(id)) {

                            permissaoUsuario perm = new permissaoUsuario(String.valueOf(acesso).toUpperCase());
                            DS.insert(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto(), perm), Usuario.class);
                        }
                    }
                } else message = "\nPermissão raiz indisponível.";
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de usuários está vazia.";
    }

    /**
     * Alterar permissão de um usuário
     */
    public void altPermissao(Integer idAdm, Integer id, String access) {

        if (!DS.select(Usuario.class).isEmpty()) {
            if (DS.select(Usuario.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id) && x.getKey().equals(idAdm))) {
                if (!access.toUpperCase().equals(String.valueOf(permissao.ROOT))) {
                    permissaoUsuario admin = new permissaoUsuario(String.valueOf(permissao.ADMIN));
                    permissaoUsuario root = new permissaoUsuario(String.valueOf(permissao.ROOT));
                    permissaoUsuario perm = validEnumPermissao(access);

                    Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
                    boolean valid = getUser.stream().anyMatch(get -> get.getKey().equals(idAdm)
                            && (Objects.equals(String.valueOf(get.getValue().getAccess()), String.valueOf(admin)))
                            || (Objects.equals(String.valueOf(get.getValue().getAccess()), String.valueOf(root))));
                    if (valid) {
                        getUser.stream().filter(set -> set.getKey().equals(id) &&
                                (!Objects.equals(String.valueOf(set.getValue().getAccess()), String.valueOf(root))))
                                .forEach(user -> DS.insert(id, new Usuario(user.getValue().getLogin(), user.getValue().getPassword(), user.getValue().getNome(), user.getValue().getDepto(), perm), Usuario.class));
                    } else message = "\nUsuário sem permissão";
                } else message = "\nPermissão raiz indisponível.";
            } else message = "\nRegistros inexistentes na tabela.";
        } else message = "\nA tabela de usuários está vazia.";
    }

    /**
     * Retorna a permissão do usuário
     */
    protected permissaoUsuario validEnumPermissao(String access) {
        permissao acesso = smartTools.Numeros.isNumeric(access) ? permissao.getAccess(Integer.parseInt(access)) : permissao.valueOf(access.toUpperCase());
        return new permissaoUsuario(String.valueOf(acesso).toUpperCase());
    }

    /**
     * Remover permissão de um usuário
     */
    public void remPermissao(Integer id) {
        if (!DS.select(Usuario.class).isEmpty()) {
            if (DS.select(Usuario.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                Set<Map.Entry<Integer, Usuario>> getUser = DS.select(Usuario.class).entrySet();
                for (Map.Entry<Integer, Usuario> setUser : getUser) {

                    Integer kid = setUser.getKey();
                    Usuario user = setUser.getValue();
                    permissaoUsuario root = new permissaoUsuario(String.valueOf(permissao.ROOT));

                    if (kid.equals(id) && (!Objects.equals(String.valueOf(user.getAccess()), String.valueOf(root)))) {
                        DS.insert(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto()), Usuario.class);
                    } else message = "\nNão é possível retirar permissão raiz deste usuário.";
                }
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de usuários está vazia.";
    }

    /**
     * Valida se o id existe
     */
    public boolean userValid (Integer id) {
        boolean valid = false;

        if (!DS.select(Usuario.class).isEmpty()) {
            if (DS.select(Usuario.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                valid = true;
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de usuários está vazia.";

        return valid;
    }

    //#region rascunho

    /*
    //private Map<Integer, Usuario> tabUsuario;
    //public metodosUsuario() {
        //    this.tabUsuario = new HashMap<>();
    //}

    public metodosUsuario(metodosUsuario usr) {
        this.tabUsuario = usr.tabUsuario;
    }

            //var mu = new metodosUsuario();
        //mu.tabUsuario = this.tabUsuario;

    private enum fieldUser {
        LOGIN, SENHA, NOME, DEPTO;
    }

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


    public static void main(String[] args) throws Exception {

        metodosUsuario musr = new metodosUsuario();

        System.out.println("# Lista de clientes #");

        musr.setLogin("Keyla");
        musr.setPassword(smartTools.Senha.Encrypt("1234"));
        musr.setNome("Keyla Nascimento");
        musr.setDepto("Juridico");
        musr.novoUsuario(1, musr);

        musr.setLogin("Paula");
        musr.setPassword(smartTools.Senha.Encrypt("5845"));
        musr.setNome("Paula Matos");
        musr.setDepto("TI");
        musr.novoUsuario(2, musr);

        musr.setLogin("Rose");
        musr.setPassword(smartTools.Senha.Encrypt("8754"));
        musr.setNome("Rose Barros");
        musr.setDepto("TI");
        musr.novoUsuario(3, musr);

        musr.setLogin("Tabata");
        musr.setPassword(smartTools.Senha.Encrypt("9687"));
        musr.setNome("Tabata Amaral");
        musr.setDepto("Governança");
        musr.novoUsuario(4, musr);

        musr.setLogin("supervisor");
        musr.setPassword(smartTools.Senha.Encrypt("5474"));
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
//*/

    //#endregion
}

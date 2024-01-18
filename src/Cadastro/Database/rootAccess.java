package Cadastro.Database;

import Cadastro.Database.Metodos.metodosUsuario;
import Cadastro.NovosDados.Repositorio.Auxiliar.permissaoUsuario;
import Cadastro.NovosDados.Repositorio.DTO.Usuario;
import Cadastro.NovosDados.Repositorio.Enums.permissao;
import Raiz.Utils.smartTools;
import Raiz.Utils.smartTools.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class rootAccess {

    dataSet<?> banco;
    public rootAccess() {
    }

    //#region rascunho
/*
    //metodosUsuario musr;
    public rootAccess() {
        //this.musr = new metodosUsuario();
    }

    //public rootAccess(dataSet<?> DS) {
    //    this.musr = new metodosUsuario(DS);
    //    this.banco = DS;
    //}
    public metodosUsuario DTUsers() {

        Integer idSup = musr.nextId();
        musr.setId(idSup);
        musr.setLogin("supervisor".toLowerCase());
        musr.setPassword(Senha.Encrypt("@autom123"));
        musr.setNome("Supervisor");
        musr.setDepto("TI");
        musr.novoUsuario(idSup, musr);
        musr.rootPermissao(idSup, String.valueOf(permissao.ROOT));

        Integer idAdm = musr.nextId();
        musr.setId(idAdm);
        musr.setLogin("admin".toLowerCase());
        musr.setPassword(Senha.Encrypt("@admin123"));
        musr.setNome("Admin");
        musr.setDepto("TI");
        musr.novoUsuario(idAdm, musr);
        musr.darPermissao(idAdm, String.valueOf(permissao.ADMIN));
        return musr;
    }
 */
//#endregion

    /**
     * Cria os dois usuários padrão para acesso a interface
     */
    public Map<Integer, Usuario> MapUsr() {
        Map<Integer, Usuario> usuarioMap = new HashMap<>();
        metodosUsuario sup = new metodosUsuario(banco);

        int idSup = 1;
        sup.setId(idSup);
        sup.setLogin("supervisor".toLowerCase());
        sup.setPassword(Senha.Encrypt("@autom123"));
        sup.setNome("Supervisor");
        sup.setDepto("TI");
        usuarioMap.put(idSup, sup);

        metodosUsuario adm = new metodosUsuario(banco);
        int idAdm = 2;
        adm.setId(idAdm);
        adm.setLogin("admin".toLowerCase());
        adm.setPassword(Senha.Encrypt("@admin123"));
        adm.setNome("Admin");
        adm.setDepto("TI");
        usuarioMap.put(idAdm, adm);

        return usuarioMap;
    }

    /**
     * Insere o usuario root no banco de dados
     */
    public Map<Integer, Usuario> root(Map<Integer, Usuario> usr, Integer id, String access) {
        Map<Integer, Usuario> usuario = new HashMap<>();
        Set<Map.Entry<Integer, Usuario>> getUsr = usr.entrySet();

        getUsr.forEach(setUser -> {
            Integer kid = setUser.getKey();
            Usuario user = setUser.getValue();
            permissaoUsuario root = new permissaoUsuario(String.valueOf(permissao.ROOT));
            if (id.equals(1) && kid.equals(id) && user.getLogin().equals("supervisor".toLowerCase()) && access.equals(String.valueOf(root))) {

                permissao acessosup;
                acessosup = smartTools.Numeros.isNumeric(access) ? permissao.getAccess(Integer.parseInt(access)) : permissao.valueOf(access.toUpperCase());
                permissaoUsuario perm = new permissaoUsuario(String.valueOf(acessosup).toUpperCase());

                usuario.put(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto(), perm));
            }
        });

        return usuario;
    }

    /**
     * Insere o usuario admin no banco de dados
     */
    public Map<Integer, Usuario> admin(Map<Integer, Usuario> usr, Integer id, String access) {
        Map<Integer, Usuario> usuario = new HashMap<>();
        Set<Map.Entry<Integer, Usuario>> getUsr = usr.entrySet();
        permissao acesso;
            acesso = smartTools.Numeros.isNumeric(access) ? permissao.getAccess(Integer.parseInt(access)) : permissao.valueOf(access.toUpperCase());

        if ((!access.toUpperCase().equals(String.valueOf(permissao.ROOT))) || (!String.valueOf(acesso).equals(String.valueOf(1)))) {
            for (Map.Entry<Integer, Usuario> setUser : getUsr) {

                Usuario user = setUser.getValue();
                if (setUser.getKey().equals(id)) {

                    permissaoUsuario perm = new permissaoUsuario(String.valueOf(acesso).toUpperCase());
                    usuario.put(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto(), perm));
                }
            }
        } else
            System.out.println("Permissão raiz indisponível.");

        return usuario;
    }

    /**
     * Método que dá a permissão aos usuários criados acima
     */
    public Map<Integer, Usuario> givePermission() {

        Map<Integer, Usuario> usuarioMap = MapUsr();
        Map<Integer, Usuario> musr = new HashMap<>();

        Set<Map.Entry<Integer, Usuario>> root = root(usuarioMap, 1, String.valueOf(permissao.ROOT)).entrySet();
        Set<Map.Entry<Integer, Usuario>> admin = admin(usuarioMap, 2, String.valueOf(permissao.ADMIN)).entrySet();

        root.forEach(x -> musr.put(1, x.getValue()));
        admin.forEach(x -> musr.put(2, x.getValue()));

        return musr;
    }
}


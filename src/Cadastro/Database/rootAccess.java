package Cadastro.Database;

import Cadastro.Database.Metodos.metodosUsuarios;
import Cadastro.NovosDados.Repositorio.Auxiliar.permissaoUsuario;
import Cadastro.NovosDados.Repositorio.DTO.Usuarios;
import Cadastro.NovosDados.Repositorio.Enums.permissao;
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
    //metodosUsuarios musr;
    public rootAccess() {
        //this.musr = new metodosUsuarios();
    }

    //public rootAccess(dataSet<?> DS) {
    //    this.musr = new metodosUsuarios(DS);
    //    this.banco = DS;
    //}
    public metodosUsuarios DTUsers() {

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
    public Map<Integer, Usuarios> MapUsr() {
        Map<Integer, Usuarios> usuarioMap = new HashMap<>();
        metodosUsuarios sup = new metodosUsuarios(banco);

        int idSup = 1;
        sup.setId(idSup);
        sup.setLogin("supervisor".toLowerCase());
        sup.setPassword(Senha.Encrypt("@autom123"));
        sup.setNome("Supervisor");
        sup.setDepto("TI");
        usuarioMap.put(idSup, sup);

        metodosUsuarios adm = new metodosUsuarios(banco);
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
    public Map<Integer, Usuarios> root(Map<Integer, Usuarios> usr, Integer id, String access) {
        Map<Integer, Usuarios> usuario = new HashMap<>();
        Set<Map.Entry<Integer, Usuarios>> getUsr = usr.entrySet();

        getUsr.forEach(setUser -> {
            Integer kid = setUser.getKey();
            Usuarios user = setUser.getValue();
            permissaoUsuario root = new permissaoUsuario(String.valueOf(permissao.ROOT));
            if (id.equals(1) && kid.equals(id) && user.getLogin().equals("supervisor".toLowerCase()) && access.equals(String.valueOf(root))) {

                permissao acessosup;
                acessosup = objetosAuxiliares.isNumeric(access) ? permissao.getAccess(Integer.parseInt(access)) : permissao.valueOf(access.toUpperCase());
                permissaoUsuario perm = new permissaoUsuario(String.valueOf(acessosup).toUpperCase());

                usuario.put(id, new Usuarios(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto(), perm));
            }
        });

        return usuario;
    }

    /**
     * Insere o usuario admin no banco de dados
     */
    public Map<Integer, Usuarios> admin(Map<Integer, Usuarios> usr, Integer id, String access) {
        Map<Integer, Usuarios> usuario = new HashMap<>();
        Set<Map.Entry<Integer, Usuarios>> getUsr = usr.entrySet();
        permissao acesso;
            acesso = objetosAuxiliares.isNumeric(access) ? permissao.getAccess(Integer.parseInt(access)) : permissao.valueOf(access.toUpperCase());

        if ((!access.toUpperCase().equals(String.valueOf(permissao.ROOT))) || (!String.valueOf(acesso).equals(String.valueOf(1)))) {
            for (Map.Entry<Integer, Usuarios> setUser : getUsr) {

                Usuarios user = setUser.getValue();
                if (setUser.getKey().equals(id)) {

                    permissaoUsuario perm = new permissaoUsuario(String.valueOf(acesso).toUpperCase());
                    usuario.put(id, new Usuarios(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto(), perm));
                }
            }
        } else
            System.out.println("Permissão raiz indisponível.");

        return usuario;
    }

    /**
     * Método que dá a permissão aos usuários criados acima
     */
    public Map<Integer, Usuarios> givePermission() {

        Map<Integer, Usuarios> usuarioMap = MapUsr();
        Map<Integer, Usuarios> musr = new HashMap<>();

        Set<Map.Entry<Integer, Usuarios>> root = root(usuarioMap, 1, String.valueOf(permissao.ROOT)).entrySet();
        Set<Map.Entry<Integer, Usuarios>> admin = admin(usuarioMap, 2, String.valueOf(permissao.ADMIN)).entrySet();

        root.forEach(x -> musr.put(1, x.getValue()));
        admin.forEach(x -> musr.put(2, x.getValue()));

        return musr;
    }
}


package Cadastro.Database;

import Cadastro.Database.Metodos.MetodosUsuario;
import Cadastro.NovosDados.Repositorio.Auxiliar.PermissaoUsuario;
import Cadastro.NovosDados.Repositorio.DTO.Usuario;
import Cadastro.NovosDados.Repositorio.Enums.permissao;
import Raiz.Utils.SmartTools;
import Raiz.Utils.SmartTools.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AdminAccess {

    //MetodosUsuario musr;
    DataSet<?> banco;

    public AdminAccess() {
        //this.musr = new MetodosUsuario();
    }

    //public AdminAccess(DataSet<?> DS) {
    //    this.musr = new MetodosUsuario(DS);
    //    this.banco = DS;
    //}
/*
    public MetodosUsuario DTUsers() {

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

    public Map<Integer, Usuario> MapUsr() {

        Map<Integer, Usuario> usuarioMap = new HashMap<>();

        MetodosUsuario sup = new MetodosUsuario(banco);
        int idSup = 1;
        sup.setId(idSup);
        sup.setLogin("supervisor".toLowerCase());
        sup.setPassword(Senha.Encrypt("@autom123"));
        sup.setNome("Supervisor");
        sup.setDepto("TI");
        usuarioMap.put(idSup, sup);

        MetodosUsuario adm = new MetodosUsuario(banco);
        int idAdm = 2;
        adm.setId(idAdm);
        adm.setLogin("admin".toLowerCase());
        adm.setPassword(Senha.Encrypt("@admin123"));
        adm.setNome("Admin");
        adm.setDepto("TI");
        usuarioMap.put(idAdm, adm);

        return usuarioMap;
    }

    public Map<Integer, Usuario> root(Map<Integer, Usuario> usr, Integer id, String access) {
        Map<Integer, Usuario> usuario = new HashMap<>();
        Set<Map.Entry<Integer, Usuario>> getUsr = usr.entrySet();

        getUsr.forEach(setUser -> {
            Integer kid = setUser.getKey();
            Usuario user = setUser.getValue();
            PermissaoUsuario root = new PermissaoUsuario(String.valueOf(permissao.ROOT));
            if (id.equals(1) && kid.equals(id) && user.getLogin().equals("supervisor".toLowerCase()) && access.equals(String.valueOf(root))) {

                permissao acessosup;
                acessosup = SmartTools.Numeros.isNumeric(access) ? permissao.getAccess(Integer.parseInt(access)) : permissao.valueOf(access.toUpperCase());
                PermissaoUsuario perm = new PermissaoUsuario(String.valueOf(acessosup).toUpperCase());

                usuario.put(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto(), perm));
            }
        });

        return usuario;
    }

    public Map<Integer, Usuario> admin(Map<Integer, Usuario> usr, Integer id, String access) {
        Map<Integer, Usuario> usuario = new HashMap<>();
        Set<Map.Entry<Integer, Usuario>> getUsr = usr.entrySet();
        permissao acesso;
            acesso = SmartTools.Numeros.isNumeric(access) ? permissao.getAccess(Integer.parseInt(access)) : permissao.valueOf(access.toUpperCase());

        if ((!access.toUpperCase().equals(String.valueOf(permissao.ROOT))) || (!String.valueOf(acesso).equals(String.valueOf(1)))) {
            for (Map.Entry<Integer, Usuario> setUser : getUsr) {

                Usuario user = setUser.getValue();
                if (setUser.getKey().equals(id)) {

                    PermissaoUsuario perm = new PermissaoUsuario(String.valueOf(acesso).toUpperCase());
                    usuario.put(id, new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto(), perm));
                }
            }
        } else
            System.out.println("Permissão raiz indisponível.");

        return usuario;
    }

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
/*

assunto: cpf

pg efetuado

meupagamento@itau-unibanco.com.br

até 5 dias

tel contado

*/

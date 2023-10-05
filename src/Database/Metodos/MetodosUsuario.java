package Database.Metodos;

import NovosDados.Repositorio.Usuario;

import java.util.*;
import java.util.Map;

public class MetodosUsuario {

    private Map<Integer, Usuario> tabUsuario;

    public MetodosUsuario() {
        this.tabUsuario = new HashMap<Integer, Usuario>();
    }

    public void novoUsuario(Integer id, Usuario user){
        tabUsuario.put(user.setId(id), new Usuario(user.getLogin(), user.getPassword(), user.getNome(), user.getDepto()));
    }

    public void alterUsuario(Integer id, String Campo, String update){
        Set<Map.Entry<Integer, Usuario>>
                getUser = tabUsuario.entrySet();

        for (Map.Entry<Integer, Usuario> setUser: getUser){
            Usuario user = setUser.getValue();

            if (setUser.getKey().equals(id)){

            }
        }
    }

    public void remoUsuario(){

    }

    public void findUsuario(){

    }

    public void listbyIdUsuario(){

    }

    public void remobyIdUsuario(){

    }

    public void validUsuario(){

    }

    public void PrintMapWithSet(){

    }

}

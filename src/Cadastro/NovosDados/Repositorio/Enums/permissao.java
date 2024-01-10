package Cadastro.NovosDados.Repositorio.Enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum permissao {
    ROOT(1), ADMIN(2), USER(3);

    private final Integer id;
    private static final Map<Integer, permissao> ENUM_MAP;

    //construtor
    permissao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    static {
        Map<Integer, permissao> blockMap = new ConcurrentHashMap<>();
        for (permissao perm : permissao.values()){
            blockMap.put(perm.getId(), perm);
        }
        ENUM_MAP = Collections.unmodifiableMap(blockMap);
    }

    public static permissao getAccess(Integer id){
        return ENUM_MAP.get(id);
    }
}

/*

public class EnumDemo {

   public static void main(String args[]) {

      System.out.println("User List:");
      for(permissao m : permissao.values()) {
         System.out.println("User{" + m + "}, Id{" + m.getId() + "}");
      }

      permissao ret;
      ret = permissao.valueOf("ROOT");
      System.out.println("Selected : " + ret);
   }
}

CellPhone List:
ROOT costs 1 dollars
ADMIN costs 2 dollars
USER costs 3 dollars
Selected : ROOT
*/
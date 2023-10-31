package NovosDados.Repositorio.Enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Permissao {
    ROOT(1), ADMIN(2), USER(3);

    private final Integer id;
    private static final Map<Integer, Permissao> ENUM_MAP;

    //construtor
    Permissao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    static {
        Map<Integer, Permissao> blockMap = new ConcurrentHashMap<>();
        for (Permissao perm : Permissao.values()){
            blockMap.put(perm.getId(), perm);
        }
        ENUM_MAP = Collections.unmodifiableMap(blockMap);
    }

    public static Permissao getAccess(Integer id){
        return ENUM_MAP.get(id);
    }
}

/*

public class EnumDemo {

   public static void main(String args[]) {

      System.out.println("User List:");
      for(Permissao m : Permissao.values()) {
         System.out.println("User{" + m + "}, Id{" + m.getId() + "}");
      }

      Permissao ret;
      ret = Permissao.valueOf("ROOT");
      System.out.println("Selected : " + ret);
   }
}

CellPhone List:
ROOT costs 1 dollars
ADMIN costs 2 dollars
USER costs 3 dollars
Selected : ROOT
*/
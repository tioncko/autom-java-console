package Utils.Objetos;

import Database.InterfaceCRUD;

import java.util.Scanner;

public abstract class LeitorDados implements InterfaceCRUD.IReader {

    private static final Scanner txt;

    static {
        txt = new Scanner(System.in);
    }

    public String ReadText(String str){
        System.out.print(str);
        return txt.next();
    }

    public Integer ReadInt(String str){
        System.out.print(str);
        return txt.nextInt();
    }

    public Double ReadDbl(String str){
        System.out.print(str);
        return txt.nextDouble();
    }
}

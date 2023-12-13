package Utils;

import Database.InterfaceCRUD;

import java.util.Arrays;
import java.util.Objects;
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

    public String ReadSentence(String str) {
        System.out.print(str);

        StringBuilder ret = new StringBuilder();
        while (txt.hasNextLine()){
            String value = txt.nextLine();
            if(value.trim().isEmpty()) continue;

            String[] sentence = value.split("\t");
            for (String txt : sentence) ret.append(txt);//Arrays.toString(sentence);

            if (!ret.isEmpty()) break;
        }
        return ret.toString();
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

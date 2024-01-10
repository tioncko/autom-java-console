package Raiz.Utils;

import Cadastro.Database.Metodos.Interfaces.INovosDados;

import Raiz.Utils.SmartTools.GenericCollects.*;

import java.util.*;

public abstract class LeitorDados implements INovosDados.IReader {

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

    public GenericList<String> ReadStrList(String str) {
        System.out.println(str);
        GenericList<String> numbers = new GenericList<>();
        while (txt.hasNextLine()){
            String value = txt.nextLine();
            if(value.trim().isEmpty()) continue;

            String[] sentence = value.split("\t\n");
            if (value.equals("sair".toUpperCase())) break;
            else numbers.addAll(Arrays.asList(sentence));
        }
        return numbers;
    }

    public List<String> StrList(String str) {
        System.out.println(str);
        List<String> numbers = new ArrayList<>();

        while (txt.hasNextLine()){
            String value = txt.nextLine();
            if(value.trim().isEmpty()) continue;

            String[] sentence = value.split("\t\n");
            if (value.equals("sair".toUpperCase())) break;
            else numbers.addAll(Arrays.asList(sentence));
        }
        return numbers;
    }


    @SuppressWarnings("unchecked")
    public <T, R> GenericSet<T> ReadStrSet(R str) {
        System.out.println(str);
        GenericSet<T> numbers = new GenericSet<>();
        while (txt.hasNextLine()){
            String value = txt.nextLine();
            if(value.trim().isEmpty()) continue;

            String[] sentence = value.split("\t\n");
            if (value.equals("sair".toUpperCase())) break;
            else numbers.addAll((Collection<? extends T>) Arrays.asList(sentence));
        }
        return numbers;
    }

    public Integer ReadInt(String str){
        System.out.print(str);
        return txt.nextInt();
    }

    public Double ReadDbl(String str){
        System.out.print(str);
        return txt.nextDouble();
    }

    protected <T> T getClassNewInstance(Class<T> classe) throws Exception {
        return classe.getDeclaredConstructor().newInstance();
    }
}

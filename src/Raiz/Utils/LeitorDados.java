package Raiz.Utils;

import Cadastro.Database.Metodos.Interfaces.INovosDados;
import Raiz.Core.impressaoLog;
import Raiz.Utils.smartTools.genericCollects.*;
import java.util.*;
import java.util.logging.Logger;

import static Raiz.Utils.smartTools.validacaoDigitos.*;

public abstract class leitorDados implements INovosDados.IReader {

    impressaoLog.logGenerico<leitorDados> printLog = new impressaoLog.logGenerico<>();
    @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<leitorDados>) (Object) (leitorDados.class));

    private static final Scanner txt;

    static {
        txt = new Scanner(System.in);
    }

    /**
     * Leitura via Scanner de uma String
     */
    public String readText(String str){
        System.out.print(str);
        return txt.next();
    }

    /**
     * Leitura via Scanner de uma String permitindo ser uma frase com espaços
     */
    public String readSentence(String str) {
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

    /**
     * Leitura via Scanner de várias Strings e armazena em uma lista
     */
    public genericList<String> readStrList(String str) {
        System.out.println(str);
        genericList<String> numbers = new genericList<>();
        while (txt.hasNextLine()){
            String value = txt.nextLine();
            if(value.trim().isEmpty()) continue;

            String[] sentence = value.split("\t\n");
            if (value.equalsIgnoreCase("sair")) break;
            else numbers.addAll(Arrays.asList(sentence));
        }
        return numbers;
    }

    /**
     * Leitura via Scanner de várias Strings (sem uso até o momento)
     */
    public List<String> StrList(String str) {
        System.out.println(str);
        List<String> numbers = new ArrayList<>();

        while (txt.hasNextLine()){
            String value = txt.nextLine();
            if(value.trim().isEmpty()) continue;

            String[] sentence = value.split("\t\n");
            if (value.equalsIgnoreCase("sair")) break;
            else numbers.addAll(Arrays.asList(sentence));
        }
        return numbers;
    }

    /**
     * Leitura via Scanner de várias Strings e armazena em uma lista Set
     */
    @SuppressWarnings("unchecked")
    public <T, R> genericSet<T> ReadStrSet(R str) {
        System.out.println(str);
        genericSet<T> numbers = new genericSet<>();
        while (txt.hasNextLine()){
            String value = txt.nextLine();
            if(value.trim().isEmpty()) continue;

            String[] sentence = value.split("\t\n");
            if (value.equalsIgnoreCase("sair")) break;
            else numbers.addAll((Collection<? extends T>) Arrays.asList(sentence));
        }
        return numbers;
    }

    /**
     * Leitura via Scanner de um inteiro
     */
    public Integer readInt(String str){
        System.out.print(str);
        return txt.nextInt();
    }

    /**
     * Leitura via Scanner de um double
     */
    public Double readDbl(String str){
        System.out.print(str);
        return txt.nextDouble();
    }

    /**
     * Leitura via Scanner de uma String delimitada por regra de CPF e CNPJ
     */
    public String readMask(String str){
        String doc = null;
        boolean auth = false;
        if (str.toUpperCase().contains("CPF")) {
            System.out.println("*************************************************");
            System.out.print(str);
            int cont = 0;

            while (!auth) {
                doc = txt.next();

                if (doc.length() == 11) {
                    if (!CPF(doc).equals("Documento inválido.")) auth = true;
                    else {
                        System.out.println("Documento inválido. Digite um CPF válido.");
                        cont++;
                    }
                }
                if (doc.length() != 11 && !(doc.equalsIgnoreCase("SAIR"))) {
                    System.out.println("Quantidade de dígitos diferente de 11.");
                    cont++;
                }
                if (cont >= 3) {
                    System.out.println("Para prosseguir com o cadastro do cliente, é necessário um CPF válido. Caso queira sair, digite SAIR.");
                    cont = 0;
                }
                if (doc.equalsIgnoreCase("SAIR")) {
                    doc = null;
                    System.out.println("*************************************************");
                    break;
                }
            }
        }

        if (str.toUpperCase().contains("CNPJ")) {
            System.out.println("*************************************************");
            System.out.print(str);
            int cont = 0;

            while (!auth) {
                doc = txt.next();

                if (doc.length() == 14) {
                    if (!CNPJ(doc).equals("Documento inválido.")) auth = true;
                    else {
                        System.out.println("Documento inválido. Digite um CNPJ válido.");
                        cont++;
                    }
                }
                if (doc.length() != 14 && !(doc.equalsIgnoreCase("SAIR"))) {
                    System.out.println("Quantidade de dígitos excedentes.");
                    cont++;
                }
                if (cont >= 3) {
                    System.out.println("Para prosseguir com o cadastro do cliente, é necessário um CNPJ válido. Caso queira sair, digite SAIR.");
                    cont = 0;
                }
                if (doc.equalsIgnoreCase("SAIR")) {
                    doc = null;
                    System.out.println("*************************************************");
                    break;
                }
            }
        }
        return doc;
    }

    /**
     * Retorna o tipo de instância passada via parâmetro
     */
    protected <T> T getClassNewInstance(Class<T> classe) throws Exception {
        return classe.getDeclaredConstructor().newInstance();
    }
}

package Raiz.Utils;

import Cadastro.Database.JSON.JsonTools.JsonResponse;
import Cadastro.Database.Metodos.Interfaces.INovosDados;
import Raiz.Core.ImpressaoLog;
import Raiz.Utils.SmartTools.GenericCollects.*;
import java.util.*;
import java.util.logging.Logger;

import static Raiz.Utils.SmartTools.validacaoDigitos.*;

public abstract class LeitorDados implements INovosDados.IReader {

    ImpressaoLog.LogGenerico<LeitorDados> printLog = new ImpressaoLog.LogGenerico<>();
    @SuppressWarnings("unchecked")
    Logger log = printLog.getLogRetorno((Class<LeitorDados>) (Object) (LeitorDados.class));

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
            if (value.equalsIgnoreCase("sair")) break;
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
            if (value.equalsIgnoreCase("sair")) break;
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
            if (value.equalsIgnoreCase("sair")) break;
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


    public String ReadMask(String str){
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


    protected <T> T getClassNewInstance(Class<T> classe) throws Exception {
        return classe.getDeclaredConstructor().newInstance();
    }
}

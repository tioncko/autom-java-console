package RoomLab.ItWorked;

import java.util.*;

public class MapValidate {

    public static class MyConsumingClass {



        public static void main(String[] args) {

            Map<String, List<String>> pp = new HashMap<>();
            List<String> g = new ArrayList<>();
            g.add("teste");
            g.add("teste2");
            g.add("teste3");
            g.add("teste4");
            g.add("teste5");

            pp.put("1", g);
            pp.values().forEach(System.out::println);
            Set<Map.Entry<String, List<String>>> gett = pp.entrySet();
            gett.forEach(y -> System.out.println("id{" + y.getKey() + "}, list{" + y.getValue() + "}"));
            System.out.println("{1} --------------------\n");




            Set<String> tx = new TreeSet<>();
            tx.add("teste");
            tx.add("teste2");
            tx.add("teste3");
            tx.add("teste4");
            tx.add("teste5");
            System.out.println(tx);
            tx.remove("teste");
            tx.add("TRANSFORMS");
            System.out.println(tx);
            System.out.println("{2} --------------------\n");


            TestGenericCollects.MyClass myClass = new TestGenericCollects.MyClass();
            TestGenericCollects.GenericListArray<String> k = new TestGenericCollects.GenericListArray<>(new ArrayList<>());
            k.add("teste");
            k.add("teste2");
            k.add("teste3");
            k.add("teste4");
            k.add("teste5");
            myClass.setMySettings(new HashMap<>());
            myClass.getMySettings().put("1", k);
            myClass.getMySettings().values().forEach(System.out::println);
            Set<Map.Entry<String, TestGenericCollects.GenericListArray<String>>> getStr = myClass.getMySettings().entrySet();
            getStr.forEach(y -> System.out.println("id{" + y.getKey() + "}, list{" + y.getValue() + "}"));
            System.out.println("{3} -------------------\n");





            Map<String, TestGenericCollects.GenericListArray<String>> tt = new HashMap<>();
            TestGenericCollects.GenericListArray<String> gn = new TestGenericCollects.GenericListArray<>(new ArrayList<>());
            gn.add("teste");
            gn.add("teste2");
            gn.add("teste3");
            gn.add("teste4");
            gn.add("teste5");

            tt.put("1", gn);
            tt.values().forEach(System.out::println);
            Set<Map.Entry<String, TestGenericCollects.GenericListArray<String>>> genn = tt.entrySet();
            genn.forEach(y -> System.out.println("id{" + y.getKey() + "}, list{" + y.getValue() + "}"));
            System.out.println("{4} -------------------\n");

            gn.add(3, "ADD");
            tt.values().forEach(System.out::println);
            genn.forEach(y -> System.out.println("id{" + y.getKey() + "}, list{" + y.getValue() + "}"));
            System.out.println("{5} -------------------\n");

            gn.set(gn.indexOf("teste"), "TRANSFORMS");
            tt.values().forEach(System.out::println);
            genn.forEach(y -> System.out.println("id{" + y.getKey() + "}, list{" + y.getValue() + "}"));
            System.out.println("{6} -------------------\n");





            Map<String, List<String>> ppu = new HashMap<>();
            List<String> gu = new ArrayList<>();
            gu.add("teste");
            gu.add("teste2");
            gu.add("teste3");
            gu.add("teste4");
            gu.add("teste5");

            ppu.put("1", gu);
            ppu.values().forEach(System.out::println);
            Set<Map.Entry<String, List<String>>> gettu = ppu.entrySet();
            gettu.forEach(y -> System.out.println("id{" + y.getKey() + "}, list{" + y.getValue() + "}"));
            System.out.println("{-} -------------------\n");
            gu.add(1, "TRANSFORMS");
            ppu.values().forEach(System.out::println);
            gettu.forEach(y -> System.out.println("id{" + y.getKey() + "}, list{" + y.getValue() + "}"));
            System.out.println("{7} -------------------\n");


            Map<String, List<String>> hh = new HashMap<>();
            List<String> gd = new ArrayList<>();
            gd.add("teste");
            gd.add("teste2");
            gd.add("teste3");
            gd.add("teste4");
            gd.add("teste5");

            hh.put("1", gd);

            hh.values().forEach(System.out::println);
            Set<Map.Entry<String, List<String>>> telta = hh.entrySet();
            for (Map.Entry<String, List<String>> setx : telta) {
                List<String> go = setx.getValue();

                if(setx.getKey().equals("1")){
                    go.set(go.indexOf("teste3"), "TRANSFORMS");
                }
            }
            telta.forEach(y -> System.out.println("id{" + y.getKey() + "}, list{" + y.getValue() + "}"));
            System.out.println("{8} -------------------\n");


            TestGenericCollects.GenericSetTree<String> txt = new TestGenericCollects.GenericSetTree<>(new HashSet<>());
            txt.add("teste");
            txt.add("teste2");
            txt.add("teste3");
            txt.add("teste4");
            txt.add("teste5");
            System.out.println(txt);
            txt.remove("teste");
            txt.add("TRANSFORMS");
            System.out.println(txt);
            System.out.println("{9} --------------------\n");
        }
    }

    private static final Scanner txt;

    static {
        txt = new Scanner(System.in);
    }

    public static void main(String[] args) {

        int i = 0;
        for (String s : ReadStr("Lista: ")) {
            System.out.println("item {" + i + "} = " + s);
            i++;
        }
    }

    public static List<String> ReadStr(String str) {
        System.out.println(str);

        List<String> numbers = new ArrayList<>();
        while (txt.hasNextLine()){
            String value = txt.nextLine();
            if(value.trim().isEmpty()) continue;

            String[] sentence = value.split("\t");
            if (value.equals("end")) break;
            else numbers.addAll(Arrays.asList(sentence));
            /*{
                for (String txt : sentence) {
                    numbers.add(txt);
                }
            }*/
        }
        return numbers;
    }

    public static List<String> ReadStrList(String str) {
        System.out.print(str);
        List<String> numbers = new ArrayList<>();
        while (txt.hasNextInt()) {
            numbers.add(txt.next());
        }
        return numbers;
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
}

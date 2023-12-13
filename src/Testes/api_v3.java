package Testes;

//import AcessoALoja.Produtos;
//import Database.Metodos.MetodosProduto;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.function.Predicate;

public class api_v3{

    //criar classe genérica para consumo dos dados da loja (Servicos e produtos)

    public static void main(String[] args) throws Exception {


        listed();
    }

    private static void listed(){
        List<String> list = returned();


        //for (String j : list){
        //     System.out.println(j);
        //}

        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String x = it.next();

            //if(x.contains("Servicos Automotivos"))
            //    System.out.println(x);

            //if(!x.contains("Servicos Automotivos"))
            //    System.out.println(x);


        }


        Predicate<String> p = c -> (!c.contains("Servicos Automotivos"));
        list.stream().filter(p).forEach(System.out::println);
        //System.out.println(returned());

    }

    private static List<String> returned(){
        String fileName = api_v3.getProperties(NameSettings.Loja.getProperty());//"src/Store.json";
        Path path = Paths.get(fileName);
        List<String> ret = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            JsonParser parser = new JsonParser();
            JsonElement tree = parser.parse(reader);

            JsonArray array = tree.getAsJsonArray();

            for (JsonElement element: array) {

                if (element.isJsonObject()) {

                    JsonObject car = element.getAsJsonObject();

                    ret.add("Store{" +
                            "Nome='" + car.get("Nome").getAsString() + '\'' +
                            ", Quantidade='" + car.get("Quantidade").getAsString() + '\'' +
                            ", Preco='" + car.get("Preco").getAsString() + '\'' +
                            ", Categoria='" + car.get("Categoria").getAsString() + '\'' +
                            ", Grupo='" + car.get("Grupo").getAsString() + '\'' +
                            '}');

                    //System.out.println(ret);

                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
       return ret;
    }

    private static String getProperties(String param) {

        String pr = null;
        try {
            String configFilePath = "src/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);

            pr = "src/" + prop.getProperty(param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pr;
    }

    private enum NameSettings {
        Loja("store_class");

        private final String property;

        NameSettings(String param){
            this.property = param;
        }

        public String getProperty(){
            return property;
        }
    }
}

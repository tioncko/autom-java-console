package Lab;

import NovosDados.Repositorio.Auxiliar.API.Referencias.*;
import NovosDados.Repositorio.Cadastro.Produtos;
import Propriedades.Config;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class api_v4<T> {

    private final List<T> items;

    public api_v4() {
        this.items = new ArrayList<>();
    }

    public static Logger log = Logger.getLogger(Config.class.getName());

    public List<T> addItemToBox(T t) {
        items.add(t);
        return items;
    }

    /*
        public static void main(String[] args) {
            api_v4<Integer> k = new api_v4<>();

            List<Integer> list = k.addItemToBox(10);;
            Iterator<Integer> it = list.iterator();

            while (it.hasNext()) {
                Integer x = it.next();
                if(x > 0) System.out.println(x);
            }
        }
    */
    public static void main(String[] args) throws Exception {

        api_v4<Produtos> x = new api_v4<>();
        List<Produtos> novos = x.returned(Produtos.class.getDeclaredConstructor().newInstance());
        //novos.stream().filter(p -> p.toString().contains("Servicos")).forEach(System.out::println);

        Predicate<Produtos> pred = c -> (!c.toString().contains("Servicos Automotivos"));
        novos.stream().filter(pred).forEach(System.out::println);

        System.out.print("+++++++++++++++++++++++++++++++++++++++++++++\n");

        List<String> u = new ArrayList<>();
        for (Produtos p : novos) {
            //System.out.println(p.getGrupo());
            u.add(p.getGrupo().toString());
        }
        u.stream().distinct().toList().forEach(System.out::println);

        //List<Produtos> u = (x.returned("Produto")).stream().distinct().toList();
        //System.out.println(u);
    }

    @SuppressWarnings("unchecked")
    private List<T> returned(T store) {
        String fileName = api_v4.getProperties(api_v4.NameSettings.Loja.getProperty());//"src/Store.json";
        Path path = Paths.get(fileName);

        api_v4<T> k = new api_v4<>();
        List<T> ret = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            JsonParser parser = new JsonParser();
            JsonElement tree = parser.parse(reader);
            JsonArray array = tree.getAsJsonArray();

            for (JsonElement element : array) {
                if (element.isJsonObject()) {
                    JsonObject car = element.getAsJsonObject();

                    if (store instanceof Produtos) {
                        Produtos prod = new Produtos();
                        prod.setnomeProd(car.get("Nome").getAsString());
                        prod.setQtd(car.get("Quantidade").getAsInt());
                        prod.setPreco(car.get("Preco").getAsDouble());

                        Categoria cat = new Categoria();
                        cat.setCategoria(car.get("Categoria").getAsString());
                        prod.setCategoria(cat);

                        Grupos gp = new Grupos();
                        gp.setGrupo(car.get("Grupo").getAsString());
                        prod.setGrupo(gp);

                        ret = k.addItemToBox((T) prod);
                    }
                }
            }
            //ret.stream().filter(d -> (!d.toString().contains("Servicos"))).forEach(System.out::println);
        } catch (Exception e) {
            log.log(Level.WARNING, e.getMessage(), e);
            log.log(Level.SEVERE, e.getMessage(), e);
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

            log.log(Level.WARNING, e.getMessage(), e);
            log.log(Level.SEVERE, e.getMessage(), e);
        }
        return pr;
    }

    private enum NameSettings {
        Loja("store_class");

        private final String property;

        NameSettings(String param) {
            this.property = param;
        }

        public String getProperty() {
            return property;
        }
    }
}


/*

 */
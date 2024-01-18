package Lab.ItWorked;

import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Raiz.Core.Config;
import Raiz.Core.impressaoLog;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class api_v3{

    //criar classe genérica para consumo dos dados da loja (Servicos e produtos)
    static final  Logger log = Logger.getLogger(Config.class.getName());

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

    private static List<String> returned() {

        String fileName = "";
        List<String> ret = new ArrayList<>();

        try {
            fileName = Config.getProperties(arquivoConfig.Loja.getPropriedade());//"src/Store.json";
        } catch (Exception e) {
            log.log(Level.WARNING, e.getMessage(), e);
        }

        if (fileName != null) {
            try {
                Path path = Paths.get(fileName);
                try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

                    JsonParser parser = new JsonParser();
                    JsonElement tree = parser.parse(reader);
                    JsonArray array = tree.getAsJsonArray();

                    for (JsonElement element : array) {

                        if (element.isJsonObject()) {

                            JsonObject car = element.getAsJsonObject();

                            ret.add("Store{" + "Nome='" + car.get("Nome").getAsString() + '\'' + ", Quantidade='" + car.get("Quantidade").getAsString() + '\'' + ", Preco='" + car.get("Preco").getAsString() + '\'' + ", Categoria='" + car.get("Categoria").getAsString() + '\'' + ", Grupo='" + car.get("Grupo").getAsString() + '\'' + '}');

                            //System.out.println(ret);

                        }
                    }
                } catch (Exception e) {
                    log.log(Level.WARNING, e.getMessage(), e);
                }
            } catch (Exception e) {
                log.log(Level.WARNING, e.getMessage(), e);
            }
        }
        return ret;
    }

    private static String getProperties(String param) {

        String pr = null;
        try {
            String configFilePath = "srcconfig.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);

            pr = "src/" + prop.getProperty(param);
        } catch (IOException e) {
            //log.log(Level.WARNING, e.getMessage(), e);

            //SimpleFormatter fmt = new SimpleFormatter();
            //StreamHandler sh = new StreamHandler(System.out, fmt);
            //log.setUseParentHandlers(false);
            //log.addHandler(sh);
            //log.info(e.getMessage());

            //LoggerAPI.tried<api_v3> x = new LoggerAPI.tried<>();
            //Logger logg = x.retorno(api_v3.class);

            impressaoLog.logGenerico<api_v3> x = new impressaoLog.logGenerico<>();
            Logger logg = x.getLogRetorno(api_v3.class);

            logg.info("in MyClass: " + e.getMessage());
            logg.warning("a test warning: " + e.getMessage());
            logg.severe("a test warning: " + e.getMessage());


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

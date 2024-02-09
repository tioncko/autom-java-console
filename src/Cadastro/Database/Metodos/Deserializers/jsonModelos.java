package Cadastro.Database.Metodos.Deserializers;

import Cadastro.NovosDados.Repositorio.Auxiliar.Marcas;
import Cadastro.NovosDados.Repositorio.DTO.Carros;
import Cadastro.NovosDados.Repositorio.DTO.Carros.*;
import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Raiz.Core.Config;
import Raiz.Utils.jsonPerGson;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class jsonModelos extends jsonPerGson {

    private static class deserializeModels {

        /**
         * Método que retorna um objeto Modelos a partir de um json deserializado
         */
        protected Modelos getNestedModelos(JsonArray array, String element) {

            Carros car = new Carros();
            Modelos modelos = car.new Modelos();
            List<Carros> cars = new ArrayList<>();

            for (int i = 0; i < array.size(); i++) {
                JsonObject object = array.get(i).getAsJsonObject();
                cars.add(new Carros(object.get(element).getAsString()));
            }
            modelos.setModelo(cars);
            return modelos;
        }
    }

    public static class jsonDeserializerModelos implements JsonDeserializer<Carros.Modelos> {

        /**
         * Método que utiliza a biblioteca Gson para deserializar um objeto Json que esteja em algum endpoint
         */
        @Override
        public Modelos deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement childObject = jsonObject.get("modelos");
            JsonArray array = childObject.getAsJsonArray();

            Carros car = new Carros();
            Modelos mod = car.new Modelos();
            deserializeModels des = new deserializeModels();

            mod.setModelo(des.getNestedModelos(array, "nome").getModelo());
            return mod;
        }
    }

    public static class coletaModelos {

        /**
         * Método protegido que concatena os parâmetros informados
         */
        protected static String getConfig(int id, String param, String classe) {
            return param + "/" + id + "/" + classe;
        }

        /**
         * Método responsável por retornar uma lista alinhada de Modelos por meio do método requestJson
         */
        public Map<Integer, List<String>> retornoModelosById(Integer id) {
            jsonDeserializerModelos json = new jsonDeserializerModelos();
            String configParam = arquivoConfig.Modelos.getPropriedade();
            String endpointJson = getConfig(id, Config.getProperties(configParam), Modelos.class.getSimpleName().toLowerCase());

            Modelos modelo = requestNestedJson(endpointJson, Modelos.class, json);

            List<String> listModelos = new ArrayList<>();
            for (int i = 0; i < modelo.getModelo().size(); i++)
                listModelos.add(modelo.getModelo().get(i).getNome());

            Map<Integer, List<String>> mapModelos = new HashMap<>();
            mapModelos.put(id, listModelos);
            return mapModelos;
        }

        /**
         * Método responsável por retornar uma lista de Modelos por meio da marca solicitada
         */
        public Map<Integer, String> getListModelos(String ret) {
            jsonMarcas.coletarMarca json = new jsonMarcas.coletarMarca();
            Supplier<Stream<Map.Entry<Integer, Marcas>>> supMarcas = () -> json.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().equals(ret));
            int id = supMarcas.get().map(Map.Entry::getValue).findFirst().orElseThrow().getApiCode();

            if (id != 0) {
                List<String> list = new ArrayList<>();
                Set<Map.Entry<Integer, List<String>>> setModelos = retornoModelosById(id).entrySet();
                setModelos.forEach(x -> list.addAll(x.getValue()));

                Map<Integer, String> map = new HashMap<>();
                int i = 0;
                for (String s : list) {
                    map.put(i + 1, s);
                    i++;
                }
                return map;
            }
            return new HashMap<>();
        }
    }
    //#region rascunho
    /*
        public static void main(String[] args) {

        jsonMarcas.coletarMarca json = new jsonMarcas.coletarMarca();
        String ret = null;
        boolean auth = false;

        List<String> listMarcas = json.stringToList(json.stringsMarcas("Isuzu")).stream().toList();

        if (listMarcas.size() > 1) {
            //System.out.println(listMarcas);

            String desc = "volkswagen";
            int i = 0;
            for (String marca : listMarcas) {
                if (marca.toLowerCase().contains(desc.toLowerCase())) {
                    int j = i;
                    ret = listMarcas.get(j);
                } i++;
            }
        }
        if (listMarcas.size() == 1) ret = listMarcas.get(0);
        if (listMarcas.isEmpty()) auth = true;

        if(!auth) {
            jsonModelos mod = new jsonModelos();

            Map<Integer, String> over = mod.getListModelos(ret);
            System.out.printf("ID | Modelos da marca *%s*\n", ret);
            System.out.println("---------------------------------");
            over.forEach((K, V) -> System.out.println(K + " | " + V));
        }
    }
     */
    //#endregion
}

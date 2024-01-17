package Cadastro.Database.Metodos.Deserializers;

import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Raiz.Core.Config;
import Cadastro.NovosDados.Repositorio.Auxiliar.Marcas;
import Raiz.Utils.JsonPerGson;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonMarcas extends JsonPerGson {

    private static class DeserializeMarcas {
        protected Marcas getJsonMarcas(JsonObject object) {

            String nome = object.get("nome").getAsString();

            Marcas brand = new Marcas();
            brand.setMarca(nome);
            return brand;
        }
    }

    private static class JsonDeserializerMarcas implements JsonDeserializer<Marcas> {

        @Override
        public Marcas deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            DeserializeMarcas dm = new DeserializeMarcas();
            JsonObject getObj = json.getAsJsonObject();
            return dm.getJsonMarcas(getObj);
        }
    }

    public Map<Integer, Marcas> ListaMarcas()  {

        Map<Integer, Marcas> lista = new HashMap<>();
        //String configParam = Config.NameSettings.Marcas.getProperty();
        String configParam = arquivoConfig.Marcas.getPropriedade();
        String endpointJson = Config.getProperties(configParam);

        JsonDeserializerMarcas json = new JsonDeserializerMarcas();
        List<Marcas> marcas = requestJson(endpointJson, Marcas.class, json);

        Integer i = 1;
        for (Marcas item : marcas){
            lista.put(i, new Marcas(item.getMarca()));
            i++;
        }

        return lista;
    }

    public Marcas nomeMarca (Integer id) {

        Marcas mar;
        Set<Map.Entry<Integer, Marcas>> getMar = ListaMarcas().entrySet();

        mar = getMar.stream()
                .filter(setid -> setid.getKey().equals(id))
                .findFirst().orElseThrow().getValue();
        return mar;
    }
}

/*

    public static void main(String[] args) throws Exception {

        //String configParam = Config.arquivoConfig.Marcas.getProperty();
        //String endpointJson = Config.getProperties(configParam);

        //JsonDeserializerMarcas json = new JsonDeserializerMarcas();
        //List<Marcas> pp = requestJson(endpointJson, Marcas.class, json);
        //pp.forEach(p -> System.out.println(p.getMarca().toUpperCase()));

        JsonMarcas k = new JsonMarcas();
        k.ListaMarcas().forEach((K, V)
                -> System.out.println("Marcas{id=" + K + ", Nome='" + V + "'}"));


        //pp.forEach(System.out::println);
        System.out.println(k.nomeMarca(1));
    }

*/

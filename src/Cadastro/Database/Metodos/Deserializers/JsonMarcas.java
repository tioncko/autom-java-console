package Cadastro.Database.Metodos.Deserializers;

import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Raiz.Core.Config;
import Cadastro.NovosDados.Repositorio.Auxiliar.Marcas;
import Raiz.Utils.jsonPerGson;
import Raiz.Utils.smartTools.genericCollects.*;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.*;

public class jsonMarcas extends jsonPerGson {

        private static class deserializeMarcas {

            /**
             * Método que retorna um objeto Marca a partir de um json deserializado
             */
            protected Marcas getJsonMarcas(JsonObject object) {

                String nome = object.get("nome").getAsString();
                int apiCode = object.get("apiCode").getAsInt();

                Marcas brand = new Marcas();
                brand.setMarca(nome);
                brand.setApiCode(apiCode);

                return brand;
            }
        }

    private static class jsonDeserializerMarcas implements JsonDeserializer<Marcas> {

        /**
         * Método que utiliza a biblioteca Gson para deserializar um objeto Json que esteja em algum endpoint
         */
        @Override
        public Marcas deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            deserializeMarcas dm = new deserializeMarcas();
            JsonObject getObj = json.getAsJsonObject();
            return dm.getJsonMarcas(getObj);
        }
    }

    public static class coletarMarca {
        /**
         * Método responsável por retornar uma lista de Marcas por meio do método requestJson
         */
        public Map<Integer, Marcas> ListaMarcas()  {

            Map<Integer, Marcas> lista = new HashMap<>();
            //String configParam = Config.NameSettings.Marcas.getProperty();
              String configParam = arquivoConfig.Marcas.getPropriedade();
                String endpointJson = Config.getProperties(configParam);

                jsonDeserializerMarcas json = new jsonDeserializerMarcas();
                List<Marcas> marcas = requestListJson(endpointJson, Marcas.class, json);

                Integer i = 1;
                for (Marcas item : marcas){
                    // if (item.getMarca().length() > 2) {
                    if (item.getMarca().indexOf("- ") > 0) {
                        int v = item.getMarca().indexOf("- ") + 2;
                        String value = item.getMarca().replace(item.getMarca().substring(0, v), "");
                        lista.put(i, new Marcas(value, item.getApiCode()));

                    } else lista.put(i, new Marcas(item.getMarca().trim(), item.getApiCode()));
                    i++;
               // }
            }

            return lista;
        }

        /**
         * Método responsável por retornar uma marca por meio do seu id
         */
        public Marcas nomeMarca (Integer id) {
            Set<Map.Entry<Integer, Marcas>> getMar = ListaMarcas().entrySet();
            return getMar.stream()
                    .filter(setid -> setid.getKey().equals(id))
                    .findFirst().orElseThrow().getValue();
        }

        /**
         * Método responsável por coletar uma marca ou uma lista de marcas pelo seu index (dois primeiros digitos)
         */
        public List<Marcas> indexMarcas(String index, Integer max) {
            coletarMarca json = new coletarMarca();
            List<Marcas> marca = new ArrayList<>();
            json.ListaMarcas().forEach((K, V) -> {
                if(V.getMarca().toLowerCase()
                        .startsWith(index.substring(0, max).toLowerCase())) marca.add(V);
            });
            return marca;
        }

        /**
         * Método responsável por retornar uma marca ou uma lista de marcas pelo seu index (dois primeiros digitos)
         */
        public String[] stringsMarcas(String value) {
            coletarMarca json = new coletarMarca();
            String empty = "Não foi localizado a marca informada";

            boolean hasInList = json.ListaMarcas().entrySet().stream().anyMatch(x -> x.getValue().getMarca().toLowerCase().equals(value));
            boolean hasAnyIndex;
            String[] marcas;
            int max = 2;

            if (!hasInList) {
                hasAnyIndex = json.indexMarcas(value, max).stream().anyMatch(x -> (!x.getMarca().isEmpty()));
                marcas = hasAnyIndex
                    ? new String[]{ String.valueOf(json.indexMarcas(value, max)) }
                    : json.ListaMarcas().entrySet().stream().anyMatch(x -> x.getValue().getMarca().toLowerCase().equals(value))
                    ? new String[]{ String.valueOf(json.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().toLowerCase().equals(value)).findFirst().get().getValue().getMarca()) }
                    : new String[]{ empty };

                return marcas;

            } else {
                return new String[]{
                        String.valueOf(json.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().toLowerCase().equals(value)).findFirst().get().getValue().getMarca())
                };
            }
        }

        /**
         * Método responsável por transformar converte uma string em lista
         */
        public genericSet<String> stringToList(String... obj) {
            genericSet<String> set = new genericSet<>();
            String[] list = null;
            for (String item : obj) {
                list = item.trim().strip().split(", ");
            }

            assert list != null;
            for (String item : list) {
                set.add(item.replaceAll("\\[", "").replaceAll("]", ""));
            }

            return set;
        }
    }
    //#region rascunho
    /*
    public String over (String value) {
        jsonMarcas k = new jsonMarcas();

        String status = "Não foi localizado a marca informada";
        boolean w = k.ListaMarcas().entrySet().stream()
                .anyMatch(x -> x.getValue().getMarca().toLowerCase().equals(value));
        //--
        boolean z;
        String q;
        if (!w) {
            z = k.indexMarcas(value, 2).stream()
                    .anyMatch(x -> (!x.getMarca().isEmpty()));

            q = z
                    ? String.valueOf(k.indexMarcas(value, 2).get(0))
                    : k.ListaMarcas().entrySet().stream().anyMatch(x -> x.getValue().getMarca().toLowerCase().equals(value))
                    ? String.valueOf(k.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().toLowerCase().equals(value)).findFirst().get().getValue().getMarca())
                    : status;
            return q;
        } else return k.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().toLowerCase().equals(value)).findFirst().get().getValue().getMarca();
        //boolean o = false;
        //int i = 0;
        //if (!q.equalsIgnoreCase(status)) {
        //i = z
        //    ? k.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().equals(q)).mapToInt(Map.Entry::getKey).max().getAsInt()
        //    : k.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().equals(value)).mapToInt(Map.Entry::getKey).max().getAsInt();
        //--
        //  o = Arrays.stream(new Marcas[] { k.nomeMarca(i) }).anyMatch(x -> (!x.getMarca().isEmpty()));
        //}
        //--

        //if(w) return k.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().toLowerCase().equals(value)).findFirst().get().getValue().getMarca();
        //if(z) return String.valueOf(k.indexMarcas(value).get(0));
        //if(o) return String.valueOf(k.nomeMarca(i));

        //return status;
    }

     public static void main(String[] args) throws Exception {
         //String configParam = Config.arquivoConfig.Marcas.getProperty();
         //String endpointJson = Config.getProperties(configParam);
         //JsonDeserializerMarcas json = new JsonDeserializerMarcas();
         //List<Marcas> pp = requestJson(endpointJson, Marcas.class, json);
         //pp.forEach(p -> System.out.println(p.getMarca().toUpperCase()));
         coletarMarca k = new coletarMarca();
         k.ListaMarcas().forEach((K, V) -> System.out.println("Marcas{id=" + K + ", Nome='" + V + "'}"));
         //pp.forEach(System.out::println);
         //System.out.println(k.nomeMarca(1));
         //}
        /*
        String value = "acu";
        boolean w = k.ListaMarcas().entrySet().stream()
                .anyMatch(x -> x.getValue().getMarca().toLowerCase().equals(value));
        //--
        boolean z = k.indexMarcas(value.substring(0, 3).toLowerCase()).stream()
                .anyMatch(x -> (!x.getMarca().isEmpty()));
        String q = z
                ? String.valueOf(k.indexMarcas(value).get(0))
                : k.ListaMarcas().entrySet().stream().anyMatch(x -> x.getValue().getMarca().toLowerCase().equals(value))
                ? String.valueOf(k.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().toLowerCase().equals(value)).findFirst().get().getValue().getMarca())
                : "Não foi localizado a marca informada";
        int i = z
                ? k.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().equals(q)).mapToInt(Map.Entry::getKey).max().getAsInt()
                : k.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().equals(value)).mapToInt(Map.Entry::getKey).max().getAsInt();
        //--
        boolean o = Arrays.stream(new Marcas[] { k.nomeMarca(i) }).anyMatch(x -> (!x.getMarca().isEmpty()));
        //--
        if(w) System.out.println("Wok {" + i + "} " + k.ListaMarcas().entrySet().stream().filter(x -> x.getValue().getMarca().toLowerCase().equals(value)).findFirst().get().getValue().getMarca());
        else System.out.println("Wnot");

        if(z) System.out.println("Zok {" + i + "} " + k.indexMarcas(value).get(0));
        else System.out.println("Znot");

        if(o) System.out.println("Ook {" + i + "} " + k.nomeMarca(i));
        else System.out.println("Onot");


    //System.out.println(k.over("mazd"));
    List<String> u = k.demand(k.ride("mazd")).stream().toList();
        if (u.size() > 1) {
        String tx = "mazda";
        //for (String e : u) System.out.println(e);
        //System.out.println(u);
        int i = 0;
        for (String e : u) {
            if (e.toLowerCase().contains(tx)) {
                int j = i;
                System.out.println(u.get(j));
            }
            i++;
        }
    } else System.out.println("{" + u.get(0) + "}");
}
/
/

        //public static void main(String[] args) throws Exception {
            coletarMarca json = new coletarMarca();
            List<String> listMarcas = json.stringToList(json.stringsMarcas("mazd")).stream().toList();
            if (listMarcas.size() > 1) {
                System.out.println(listMarcas);

                String desc = "mazda";
                int i = 0;
                for (String marca : listMarcas) {
                    if (marca.toLowerCase().contains(desc)) {
                        int j = i;
                        System.out.println(listMarcas.get(j));
                    } i++;
                }
            } else System.out.println("{" + listMarcas.get(0) + "}");



        }
     */
         //#endregion
}
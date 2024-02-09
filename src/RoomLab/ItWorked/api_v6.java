package RoomLab.ItWorked;

import Cadastro.NovosDados.Repositorio.Auxiliar.Marcas;
import Raiz.Utils.jsonPerGson;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class api_v6 extends jsonPerGson {

    public static class InnerFoo {

        private List<String> nome;

        public List<String> getNome() {
            return nome;
        }
        public void setNome(List<String> nome) {
            this.nome = nome;
        }
    }

    private static class deserializeModelos {

        protected List<String> getJsonModelos(JsonArray array) {

            List<String> nome = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JsonObject element = array.get(i).getAsJsonObject();
                nome.add(element.get("nome").getAsString());
            }
            return nome;
        }

        protected InnerFoo getNestedModelos(JsonArray array) {

            InnerFoo inn = new InnerFoo();
            List<String> lst = new ArrayList<>();

            for (int i = 0; i < array.size(); i++) {
                JsonObject element = array.get(i).getAsJsonObject();
                lst.add(element.get("nome").getAsString());
            }
            inn.setNome(lst);
            return inn;
        }
    }

    public static class JsonDeserializerBrand implements JsonDeserializer<InnerFoo> {

        public InnerFoo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement childObject = jsonObject.get("modelos");
            JsonArray array = childObject.getAsJsonArray();

            InnerFoo foo = new InnerFoo();
            deserializeModelos mod = new deserializeModelos();
            //foo.setNome(mod.getJsonModelos(array));
            foo.setNome(mod.getNestedModelos(array).nome);

            return foo;
        }
        //https://mocki.io/fake-json-api
    }

    private static String fetchContent(String uri) throws IOException {

        final int OK = 200;
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();
        if (responseCode == OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        }

        return null;
    }

    public static void main(String[] args) throws Exception {

        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/56/modelos";

        String content = fetchContent(url);

        Type BrandList = new TypeToken<InnerFoo>() {}.getType();
        //Gson gson = new GsonBuilder().registerTypeAdapter(InnerFoo.class, new JsonDeserializerBrand()).serializeNulls().create(); //ou
        Gson gson = new GsonBuilder().registerTypeAdapter(BrandList, new JsonDeserializerBrand()).serializeNulls().create();
        InnerFoo brand = gson.fromJson(content, BrandList);
        assert brand != null;

        for (int i = 0; i < brand.nome.size(); i++) {
            System.out.println(brand.nome.get(i));
        }

        System.out.println("***********************************");
        /*
        System.out.println();
        System.out.println("***********************************");
        JsonDeserializerBrand json = new JsonDeserializerBrand();
        InnerFoo brand2 = requestNestedJson(url, InnerFoo.class ,json);

        for (int i = 0; i < brand2.nome.size(); i++) {
            System.out.println(brand2.nome.get(i));
        }
        */
    }
}


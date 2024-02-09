package RoomLab.ItWorked;

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

public class api_v7 extends jsonPerGson {

    public static class InnerFoo {

        private String nome;

        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    public static class JsonDeserializerBrand implements JsonDeserializer<InnerFoo> {

        public InnerFoo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();
            InnerFoo foo = new InnerFoo();
            foo.setNome(jsonObject.get("nome").getAsString());

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
        JsonParser parse = new JsonParser();
        assert content != null;
        JsonObject obj = (JsonObject) parse.parse(content);
        JsonElement el = obj.get("modelos").getAsJsonArray();

        Type BrandList = new TypeToken<List<InnerFoo>>(){}.getType();//new listParameterizedType(InnerFoo.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(InnerFoo.class, new JsonDeserializerBrand()).serializeNulls().create();
        List<InnerFoo> brand = gson.fromJson(el, BrandList);
        assert brand != null;

        for (InnerFoo innerFoo : brand) {
            System.out.println(innerFoo.nome);
        }
    }
}


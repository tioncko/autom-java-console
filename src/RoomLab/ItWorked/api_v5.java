package RoomLab.ItWorked;

import Cadastro.Database.Metodos.Deserializers.jsonModelos;
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
import java.util.Arrays;
import java.util.List;

public class api_v5 {

    public static class InnerFoo {

        private String nome;

        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    public static class JsonDeserializerBrand implements JsonDeserializer<List<InnerFoo>> {

        public List<InnerFoo> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            List<InnerFoo> list = new ArrayList<>();

            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement childObject = jsonObject.get("modelos");
            JsonArray array = childObject.getAsJsonArray();

            for (int i = 0; i < array.size(); i++) {
                JsonObject element = array.get(i).getAsJsonObject();
                InnerFoo foo = new InnerFoo();
                foo.setNome(element.get("nome").getAsString());
                list.add(foo);
            }

            return list;
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

    public static void main(String[] args) throws IOException {

        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/56/modelos";

        String content = fetchContent(url);

        Type BrandList = new TypeToken<List<InnerFoo>>() {}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(BrandList, new JsonDeserializerBrand()).serializeNulls().create();
        List<InnerFoo> brand = gson.fromJson(content, BrandList);

        assert brand != null;
        for (InnerFoo innerFoo : brand) System.out.println(innerFoo.nome);
    }
}

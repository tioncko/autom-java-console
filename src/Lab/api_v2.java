package Lab;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.util.ArrayList;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class api_v2 {

    public static void main(String[] args) throws IOException {

        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas";

        String content = fetchContent(url);

        Gson gson = new GsonBuilder().registerTypeAdapter(Brand.class, new JsonDeserializerBrand()).serializeNulls().create();

        Type BrandList = new TypeToken<ArrayList<Brand>>(){}.getType();
        ArrayList<Brand> brand = gson.fromJson(content, BrandList);

        assert brand != null;
        for (Brand b : brand) {
            System.out.println(b);
        }
    }

    private static String fetchContent(String uri) throws IOException {

        final int OK = 200;
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();
        if(responseCode == OK){
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

    public static class JsonDeserializerBrand implements JsonDeserializer<Brand>{

        public Brand deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            JsonObject employeeJson = json.getAsJsonObject();

            int id = employeeJson.get("codigo").getAsInt();
            String firstName = employeeJson.get("nome").getAsString();

            Brand employee = new Brand();
            employee.setId(id);
            employee.setFirstName(firstName);

            return employee;
        }
        //https://mocki.io/fake-json-api
    }

    public static class Brand {
        private int id;
        private String firstName;

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @Override
        public String toString() {
            return "Brand{" + "id=" + id + ", firstName='" + firstName + '\'' + '}';
        }

    }
}

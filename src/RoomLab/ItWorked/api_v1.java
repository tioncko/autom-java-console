package RoomLab.ItWorked;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class api_v1 {

    public static void main(String[] args) throws IOException {

        String url = "https://mocki.io/v1/47f04afc-b0b3-4d28-9217-49143dcd9ebb";

        String content = fetchContent(url);

        Gson gson = new GsonBuilder().registerTypeAdapter(Employee.class, new JsonDeserializerEmployee()).serializeNulls().create();

        Employee employee = gson.fromJson(content, new TypeToken<Employee>(){}.getType());

        System.out.println(employee);
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

    public static class JsonDeserializerEmployee implements JsonDeserializer<Employee>{

        public Employee deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            JsonObject employeeJson = json.getAsJsonObject();
            int id = employeeJson.get("id").getAsInt();
            String firstName = employeeJson.get("first_name").getAsString();
            String lastName = employeeJson.get("last_name").getAsString();
            Date date = null;

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss a", Locale.ENGLISH);
                date = sdf.parse(employeeJson.get("date").getAsString() );
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String photoPath = employeeJson.get("photo").getAsString();
            boolean married =employeeJson.get("married").getAsBoolean();

            Employee employee = new Employee();
            employee.setId(id);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setDate(date);
            employee.setPhoto(photoPath);
            employee.setMarried(married);

            return employee;
        }
        //https://mocki.io/fake-json-api
    }

    public static class Employee {
        private int id;
        private String firstName;
        private String lastName;
        private Date date;
        private String photo;
        private boolean married;

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
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public Date getDate() {
            return date;
        }
        public void setDate(Date date) {
            this.date = date;
        }
        public String getPhoto() {
            return photo;
        }
        public void setPhoto(String photo) {
            this.photo = photo;
        }
        public boolean isMarried() {
            return married;
        }
        public void setMarried(boolean married) {
            this.married = married;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss a", Locale.ENGLISH);

            builder.append("Id: ").append(id).append(",\n")
                    .append("First Name: ").append(firstName).append(",\n")
                    .append("Last Name: ").append(lastName).append(",\n")
                    .append("Date: ").append(sdf.format(date)).append(",\n")
                    .append("Photo path: ").append(photo).append(",\n")
                    .append("Is Married: ").append(married);
            return builder.toString();
        }
    }
}

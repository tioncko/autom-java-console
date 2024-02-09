package RoomLab.ItWorked;

import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Raiz.Core.Config;
import Cadastro.NovosDados.Repositorio.Auxiliar.Marcas;
import Raiz.Utils.httpResponse;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.ParameterizedType;

public class DadosJSON extends httpResponse {

    private static final class ListParameterizedType implements ParameterizedType {

        private final Type type;

        private ListParameterizedType(Type type) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[] {type};
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

    private static class Deserialized {
        private Marcas getMarca(JsonObject object) {
            String nome = object.get("nome").getAsString();

            Marcas brand = new Marcas();
            brand.setMarca(nome);
            return brand;
        }
    }

    public static class JsonDeserializerObject implements JsonDeserializer<Marcas> {

        @Override
        public Marcas deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            Deserialized dz = new Deserialized();
            JsonObject getObj = json.getAsJsonObject();
            return dz.getMarca(getObj);
        }
    }

    public <T, R> List<T> requestJson(String uri, Class<T> objClass, R JsonDeserializerObject) throws Exception {

        //httpResponse resp = new httpResponse();
        String content = responseContent(uri);
        Gson gson = new GsonBuilder().registerTypeAdapter(objClass, JsonDeserializerObject).serializeNulls().create();
        Type type = new ListParameterizedType(objClass);
        List<T> list = gson.fromJson(content, type);

        assert list != null;
        return new ArrayList<>(list);
    }

    //VERIFICAR O QUE TÁ ROLANDO
    public static void main(String[] args) throws Exception {

        String fileParam = arquivoConfig.Marcas.getPropriedade();
        String fileName = Config.getProperties(fileParam);

        JsonDeserializerObject n = new JsonDeserializerObject();
        DadosJSON d = new DadosJSON();
        List<Marcas> pp = d.requestJson(fileName, Marcas.class, n);
        pp.forEach(p -> System.out.println(p.getMarca().toUpperCase()));
    }
}

package Raiz.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class jsonPerGson extends httpResponse {

    /**
     * Classe que parametriza o tipo do objeto recebido (caso receba uma lista, essa classe define que será uma lista no final do processo)
     */
    private static final class listParameterizedType implements ParameterizedType {
    //public de modo provisório, classe deve estar private
        private final Type type;

        private listParameterizedType(Type type) {
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

    /**
     * Método que faz a requisição do json via Gson, deserializa, e transforma em lista
     * https://zetcode.com/java/gson/ - tips
     */
    public static <T, R> List<T> requestListJson(String uri, Class<T> objClass, R JsonDeserializerObject)  {

        String content = responseContent(uri);
        Gson gson = new GsonBuilder().registerTypeAdapter(objClass, JsonDeserializerObject).serializeNulls().create();
        Type type = new listParameterizedType(objClass);
        List<T> list = gson.fromJson(content, type);

        assert list != null;
        return new ArrayList<>(list);
    }

    /**
     * Método que faz a requisição do json via Gson, deserializa, e transforma em um objeto único
     * https://zetcode.com/java/gson/ - tips
     */
    public static <T, R> T requestElemJson(String uri, Class<T> objClass, R JsonDeserializerObject)  {

        String content = responseContent(uri);
        Gson gson = new GsonBuilder().registerTypeAdapter(objClass, JsonDeserializerObject).serializeNulls().create();

        return gson.fromJson(content, objClass);
    }

    /**
     * Método que faz a requisição do json via Gson, deserializa a lista de objetos em um array, e transforma em um objeto único com atributo de lista
     * https://zetcode.com/java/gson/ - tips
     */
    public static <T, R> T requestNestedJson(String uri, Class<T> objClass, R JsonDeserializerObject) {

        String content = responseContent(uri);
        Type type = new listParameterizedType(objClass); //or new TypeToken<List<T>>(){}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, JsonDeserializerObject).serializeNulls().create();
        T list = gson.fromJson(content, type);

        assert list != null;
        return list;
    }
}

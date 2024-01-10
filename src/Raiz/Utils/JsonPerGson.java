package Raiz.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class JsonPerGson extends HTTPResponse {

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

    public static <T, R> List<T> requestJson(String uri, Class<T> objClass, R JsonDeserializerObject) throws Exception {

        String content = responseContent(uri);
        Gson gson = new GsonBuilder().registerTypeAdapter(objClass, JsonDeserializerObject).serializeNulls().create();
        Type type = new ListParameterizedType(objClass);
        List<T> list = gson.fromJson(content, type);

        assert list != null;
        return new ArrayList<>(list);
    }
}

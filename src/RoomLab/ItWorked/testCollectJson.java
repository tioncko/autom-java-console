package RoomLab.ItWorked;

import Raiz.Utils.jsonPerGson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class testCollectJson extends jsonPerGson {

    public static void main(String[] args) {
        testCollectJson m = new testCollectJson();
        m.whenDeserializingToNestedObjects_thenCorrect();
    }

    public class FooWithInner {
        public List<InnerFoo> innerFoo;

        public class InnerFoo {
            public String name;
        }
    }

    public void whenDeserializingToNestedObjects_thenCorrect() {
        String json = "{\"innerFoo\":[{\"name\":\"inner\"},{\"name\":\"outer\"}]}";

        FooWithInner targetObject = new Gson().fromJson(json, FooWithInner.class);

        //System.out.println(targetObject.innerFoo.get(1).name);

        for (int i = 0; i < targetObject.innerFoo.size(); i++) {
            System.out.println(targetObject.innerFoo.get(i).name);
        }
    }
}

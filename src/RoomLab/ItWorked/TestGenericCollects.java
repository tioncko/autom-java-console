package RoomLab.ItWorked;

import java.util.*;

public class TestGenericCollects {

    public static class MyClass {
        // note that this type of property declaration is called an "Automatic Property" and
        // it means the same thing as you had written (the private backing variable is used behind the scenes, but you don't see it)
        private Map<String, GenericListArray<String>> MySettings;

        public Map<String, GenericListArray<String>> getMySettings() {
            return MySettings;
        }

        public void setMySettings(Map<String, GenericListArray<String>> mySettings) {
            this.MySettings = mySettings;
        }
    }

    public static class GenericListArray<E> extends ArrayList<E> {

        protected List<E> list;
        public GenericListArray(List<E> l) {
            this.list = l;
        }

        @Override
        public String toString() {
            StringBuilder retrn = new StringBuilder();
            int k = 0;
            for (int i = 0; i < this.size(); i++) {
                retrn.append(this.get(i));

                while (k < i + 1 && k != this.size() - 1) {
                    retrn.append(", ");
                    k++;
                }
            }
            return retrn.toString();
        }
    }

    public static class GenericSetTree<E> extends TreeSet<E> {

        //protected Set<E> list;
        public GenericSetTree(Set<E> l) {
            // this.list = l;
            super(l);
        }

        @Override
        public String toString() {
            StringBuilder retrn = new StringBuilder();
            int k = 0;
            for (int i = 0; i < this.size(); i++) {
                retrn.append(this.stream().toList().get(i));

                while (k < i + 1 && k != this.size() - 1) {
                    retrn.append(", ");
                    k++;
                }
            }
            return retrn.toString();
        }
    }

    public static void main(String[] args) {

        //TestGenericCollects x = new TestGenericCollects();
       //System.out.println(x.ret("v1"));

    }

    public List<String> ret (String... update) {

        List<String> obj = new ArrayList<>();

        String obj1 = "", obj2 = "";
        if (update.length < 2) {
            obj1 = update[0];
            obj.add(obj1);
            System.out.println("Passo 1");
        }
        if (update.length == 2) {
            obj1 = update[0];
            obj.add(obj1);
            obj2 = update[1];
            obj.add(obj2);
            System.out.println("Passo 2");
        }
        else {
            obj.add(update[0]);
            System.out.println("Passo 3");
        }

        return obj;
    }
}

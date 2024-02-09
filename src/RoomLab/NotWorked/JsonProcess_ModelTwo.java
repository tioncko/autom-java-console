package RoomLab.NotWorked;

import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Cadastro.NovosDados.Repositorio.DTO.Servicos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import Cadastro.NovosDados.Repositorio.Abstratos.Gondola;

public class JsonProcess_ModelTwo {


    public static void main(String[] args) throws Exception {

        JsonProcess_ModelTwo.MapaJson y = new JsonProcess_ModelTwo.MapaJson();

        Function<Produtos, Propriedades.Categoria> propC = Gondola::getCategoria;
        y.getMapObject(Produtos.class, propC).forEach((K, V) -> System.out.println("Categoria{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        Function<Produtos, Propriedades.Grupos> propG = Gondola::getGrupo;
        y.getMapObject(Produtos.class, propG).forEach((K, V) -> System.out.println("Grupo{id=" + K + ", descricao='" + V + "'}"));
    }


    static class MapaJson{


        @SuppressWarnings("unchecked")
        public <T, R, U> Map<Integer, U> getMapObject (Class<T> classe, Function<T, R> prop) throws Exception {
            Map<Integer, U> v1 = new HashMap<>();
            ListaJson<T> item = new ListaJson<>();

            Integer i = 1;
            for(R p : item.getListProperties(item, classe, prop)) {
                v1.put(i, (U) Class.forName(p.toString()).getDeclaredConstructor().newInstance());
            }
            return v1;
        }


    }



    static class ListaJson<T> {
        protected T getClassNewInstance(Class<T> classe) throws Exception {
            return classe.getDeclaredConstructor().newInstance();
        }

        public <R> List<R> getListProperties(ListaJson<T> list, Class<T> classe, Function<T, R> property) throws Exception {
            List<R> str = new ArrayList<>();

            return str.stream().distinct().toList();
        }

        public List<T> getListItens(Class<T> classe) throws Exception {
            T instance = getClassNewInstance(classe);

            JsonProcess_ModelOne.Departments<T> x = new JsonProcess_ModelOne.Departments<>();
            List<T> itens = x.ReturnListJson(instance);

            String varContains = "Servicos Automotivos";

            if (instance instanceof Produtos) {
                Predicate<T> Products = c -> (!c.toString().contains(varContains));
                return itens.stream().filter(Products).toList();
            }
            if (instance instanceof Servicos) {
                Predicate<T> Services = c -> (c.toString().contains(varContains));
                return itens.stream().filter(Services).toList();
            }

            return new ArrayList<>();
        }
    }
}

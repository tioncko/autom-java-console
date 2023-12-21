package Lab;

import AcessoALoja.Piso.ItensLoja;
import NovosDados.Repositorio.Auxiliar.API.Referencias;
import NovosDados.Repositorio.Cadastro.Produtos;
import NovosDados.Repositorio.Cadastro.Servicos;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;


public class FunctionAPI {


    public static void main(String[] args) throws Exception {
        ListaLoja<Produtos> lp = new ListaLoja<>();
        for (Produtos p : lp.getListItens(Produtos.class)) {
            System.out.println(p);
        }

        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        for (Referencias.Categoria p : lp.getListProperty(lp, Produtos.class, (Produtos prod) -> prod.getCategoria())) {
            //for (Referencias.Categoria p : lp.getListProperty(lp, Servicos.class, Servicos::getCategoria)) {
            System.out.println(p);
        }
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        for (Referencias.Grupos p : lp.getListProperty(lp, Produtos.class, (Produtos prod) -> prod.getGrupo())) {
            System.out.println(p);
        }

    }

    @SuppressWarnings("unchecked")
    static class ListaLoja <T> {

        private T createInstance(Class<T> classe) throws Exception {
            return classe.getDeclaredConstructor().newInstance();
        }

        public List<T> getListItens(Class<T> classe) throws Exception {
            ItensLoja.Setores<T> x = new ItensLoja.Setores<>();
            List<T> lp = x.RetornoLoja(createInstance(classe));

            Predicate<T> Products = c -> (!c.toString().contains("Servicos Automotivos"));
            Predicate<T> Services = c -> (c.toString().contains("Servicos Automotivos"));

            T instance = createInstance(classe);
            if(instance instanceof Produtos){
                return lp.stream().filter(Products).toList();
            }
            if(instance instanceof Servicos) {
                return lp.stream().filter(Services).toList();
            }
            return new ArrayList<>();
        }

        //Estudar essa parte do código
        public <R> List<R> getListProperty (ListaLoja<T> prod, Class<T> classe, Function<T, R> propertyExtractor) throws Exception {
            List<R> str = new ArrayList<>();
            prod.getListItens(classe).forEach(p -> {
                str.add(propertyExtractor.apply(p));
            });
            return str.stream().distinct().toList();
        }
    }
}

package RoomLab.ItWorked;

import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Cadastro.Database.JSON.JsonTools.jsonResponse;
import Cadastro.NovosDados.Repositorio.Abstratos.Gondola;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Cadastro.NovosDados.Repositorio.DTO.Servicos;

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

        Function<Produtos, Propriedades.Categoria> ProdCat = (Produtos prod) -> prod.getCategoria();
        for (Propriedades.Categoria p : lp.getListProperty(lp, Produtos.class, ProdCat)) {
            //for (Propriedades.Categoria p : lp.getListProperty(lp, Servicos.class, Servicos::getCategoria)) {
            System.out.println(p);
        }
        Function<Produtos, Propriedades.Grupos> ProdGp = Gondola::getGrupo;
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        for (Propriedades.Grupos p : lp.getListProperty(lp, Produtos.class, ProdGp)) {
            System.out.println(p);
        }

    }

    @SuppressWarnings("unchecked")
    static class ListaLoja <T> {

        private T createInstance(Class<T> classe) throws Exception {
            return classe.getDeclaredConstructor().newInstance();
        }

        public List<T> getListItens(Class<T> classe) throws Exception {
            jsonResponse.jsonGenericObjects<T> x = new jsonResponse.jsonGenericObjects<>();
            String fileParam = arquivoConfig.Loja.getPropriedade();
            List<T> lp = x.jsonReturn(createInstance(classe), fileParam);

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


/*


        for (Propriedades.Categoria p : lp.getListCategoria(lp, Produtos.class)) {
            System.out.println(p);
        }
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        for (Propriedades.Grupos p : lp.getListGrupos(lp, Produtos.class)) {
            System.out.println(p);



        public List<Propriedades.Categoria> getListCategoria (ListaLoja<T> prod, Class<T> classe) throws Exception {
            List<Propriedades.Categoria> str = new ArrayList<>();
            prod.getListItens(classe).forEach(p ->
            {
                if(p instanceof Produtos q){
                    str.add(q.getCategoria());
                }
                if(p instanceof Servicos q){
                    str.add(q.getCategoria());
                }
            });
            return str.stream().distinct().toList();
        }

        public List<Propriedades.Grupos> getListGrupos (ListaLoja<T> prod, Class<T> classe) throws Exception {
            List<Propriedades.Grupos> str = new ArrayList<>();
            prod.getListItens(classe).forEach(p ->
            {
                if(p instanceof Produtos q){
                    str.add(q.getGrupo());
                }
                if(p instanceof Servicos q){
                    str.add(q.getGrupo());
                }
            });
            return str.stream().distinct().toList();
        }
* */
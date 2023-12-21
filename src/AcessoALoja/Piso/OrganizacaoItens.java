package AcessoALoja.Piso;

import NovosDados.Repositorio.Auxiliar.API.Referencias;
import NovosDados.Repositorio.Cadastro.Produtos;
import NovosDados.Repositorio.Cadastro.Servicos;

import java.util.*;
import java.util.function.Predicate;


public class OrganizacaoItens {


    public static void main(String[] args) throws Exception {
        ListaLoja<Produtos> lp = new ListaLoja<>();
        for (Produtos p : lp.getListItens(Produtos.class)) {
            System.out.println(p);
        }

        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        for (Referencias.Categoria p : lp.getListCategoria(lp, Produtos.class)) {
            System.out.println(p);
        }
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        for (Referencias.Grupos p : lp.getListGrupos(lp, Produtos.class)) {
            System.out.println(p);
        }

    }

    static class ListaLoja <T> {

        private T getClassNewInstance (Class<T> classe) throws Exception {
            return classe.getDeclaredConstructor().newInstance();
        }

        public List<T> getListItens(Class<T> classe) throws Exception {
            T instance = getClassNewInstance(classe);

            ItensLoja.Setores<T> x = new ItensLoja.Setores<>();
            List<T> lp = x.RetornoLoja(instance);

            Predicate<T> Products = c -> (c.toString().contains("Servicos Automotivos"));
            Predicate<T> Services = c -> (!c.toString().contains("Servicos Automotivos"));

            if(instance instanceof Produtos){
                return lp.stream().filter(Products).toList();
            }
            if(instance instanceof Servicos) {
                return lp.stream().filter(Services).toList();
            }
            return new ArrayList<>();
        }

        public List<Referencias.Categoria> getListCategoria (ListaLoja<T> prod, Class<T> classe) throws Exception {
            List<Referencias.Categoria> str = new ArrayList<>();
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

        public List<Referencias.Grupos> getListGrupos (ListaLoja<T> prod, Class<T> classe) throws Exception {
            List<Referencias.Grupos> str = new ArrayList<>();
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
    }
}

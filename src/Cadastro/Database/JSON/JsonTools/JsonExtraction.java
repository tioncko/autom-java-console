package Cadastro.Database.JSON.JsonTools;

import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Cadastro.NovosDados.Repositorio.Abstratos.Gondola;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;
import Cadastro.NovosDados.Repositorio.DTO.*;
import Raiz.Utils.LeitorDados;

import java.util.*;
import java.util.function.*;

public class JsonExtraction extends JsonResponse {

    public static class ColetaJsonDados extends LeitorDados {
        public <T> Categoria nomeCategoria(Integer id, Class<T> classe) throws Exception {

            Categoria cat;
            JsonToMap p = new JsonToMap();
            //String fileParam = Config.NameSettings.Loja.getProperty();
            String fileParam = arquivoConfig.Loja.getPropriedade();

            Set<Map.Entry<Integer, Categoria>> getCat;
            //ListaJsonResponse<T> lis = new ListaJsonResponse<>();
            T instance = getClassNewInstance(classe);

            /*
            if (instance instanceof Produtos) {
                Function<Produtos, Categoria> propC = Gondola::getCategoria;
                //getCat = p.getObjects(Produtos.class, propC, (a, b) -> new Categoria()).entrySet();
                getCat = p.getMapCategorias(Produtos.class, propC).entrySet();
            }

            if (instance instanceof Servicos) {
                Function<Servicos, Categoria> propC = Gondola::getCategoria;
                //getCat = p.getObjects(Servicos.class, propC, (a, b) -> new Categoria()).entrySet();
                getCat = p.getMapCategorias(Servicos.class, propC).entrySet();
            }
*/
            getCat = switch (instance.getClass().getSimpleName()) {
                case "Produtos" -> {
                    Function<Produtos, Categoria> propC = Gondola::getCategoria;
                    yield p.getMapObjects(Produtos.class, propC, (a, b) -> new Categoria(b), fileParam).entrySet();
                }
                case "Servicos" -> {
                    Function<Servicos, Categoria> propC = Gondola::getCategoria;
                    yield p.getMapObjects(Servicos.class, propC, (a, b) -> new Categoria(b), fileParam).entrySet();
                }
                default -> null;
            };

            /*

            Function<Produtos, Categoria> propP = Gondola::getCategoria;
            Function<Servicos, Categoria> propS = Gondola::getCategoria;


            Set<Map.Entry<Integer, Categoria>> getCat2 = instance instanceof Produtos
                    ? p.getObjects(Produtos.class, propP, (a, b) -> new Categoria(b)).entrySet()
                    : p.getObjects(Servicos.class, propS, (a, b) -> new Categoria(b)).entrySet();
*/
            assert getCat != null;
            cat = getCat.stream().filter(setid -> setid.getKey().equals(id)).findFirst().orElseThrow().getValue();
            return cat;
        }

        public <T> Grupos nomeGrupo(Integer id, Class<T> classe) throws Exception {

            Grupos gp;
            JsonToMap p = new JsonToMap();
            //String fileParam = Config.NameSettings.Loja.getProperty();
            String fileParam = arquivoConfig.Loja.getPropriedade();

            Set<Map.Entry<Integer, Grupos>> getGp;
            //ListaJsonResponse<T> lis = new ListaJsonResponse<>();
            T instance = getClassNewInstance(classe);

            /*
            if (instance instanceof Produtos) {
                Function<Produtos, Grupos> propG = Gondola::getGrupo;
                getGp = p.getMapGrupos(Produtos.class, propG).entrySet();
                //getGp = p.getObjects(Produtos.class, propG, (a, b) -> new Grupos()).entrySet();
            }

            if (instance instanceof Servicos) {
                Function<Servicos, Grupos> propG = Gondola::getGrupo;
                getGp = p.getMapGrupos(Servicos.class, propG).entrySet();
                //getGp = p.getObjects(Servicos.class, propG, (a, b) -> new Grupos()).entrySet();
            }
*/
            getGp = switch (instance.getClass().getSimpleName()) {
                case "Produtos" -> {
                    Function<Produtos, Grupos> propG = Gondola::getGrupo;
                    yield p.getMapObjects(Produtos.class, propG, (a, b) -> new Grupos(b), fileParam).entrySet();
                }
                case "Servicos" -> {
                    Function<Servicos, Grupos> propG = Gondola::getGrupo;
                    yield p.getMapObjects(Servicos.class, propG, (a, b) -> new Grupos(b), fileParam).entrySet();
                }
                default -> null;
            };

            assert getGp != null;
            gp = getGp.stream().filter(setid -> setid.getKey().equals(id)).findFirst().orElseThrow().getValue();

            return gp;
        }
        /*
        public MetodosFornecedor DTForn() {

            JsonToMap p = new JsonToMap();
            MetodosFornecedor novoFrn = new MetodosFornecedor();
            //String fileParam = Config.NameSettings.Fornecedores.getProperty();
            String fileParam = arquivoConfig.Fornecedores.getPropriedade();

            try {
                Map<Integer, Fornecedor> sup = p.getMapRecord(Fornecedor.class, fileParam);
                Set<Map.Entry<Integer, Fornecedor>> getForn = sup.entrySet();

                getForn.forEach(forn -> {
                    novoFrn.setRazaoSocial(forn.getValue().getRazaoSocial());
                    novoFrn.setNomeFantasia(forn.getValue().getNomeFantasia());
                    novoFrn.setCnpj(forn.getValue().getCnpj());
                    novoFrn.setEmail(forn.getValue().getEmail());
                    novoFrn.setInscEstadual(forn.getValue().getInscEstadual());
                    novoFrn.setTelefone(forn.getValue().getTelefone());
                    novoFrn.setInfoCEP(forn.getValue().getInfoCEP());
                    novoFrn.novoFornecedor(forn.getKey(), novoFrn);
                });
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return novoFrn;
        }
 */

        public Map<Integer, Fornecedor> MapForn() {
            try {
                JsonToMap p = new JsonToMap();
                //String fileParam = Config.NameSettings.Fornecedores.getProperty();
                String fileParam = arquivoConfig.Fornecedores.getPropriedade();
                Map<Integer, Fornecedor> sup = p.getMapRecord(Fornecedor.class, fileParam);

                // Set<Map.Entry<Integer, Fornecedor>> getForn ........ entrySet();
                // Predicate<T> filtroLoja = instance instanceof Produtos ? c -> (!c.toString().contains(varContains)) : c -> (c.toString().contains(varContains));
                //
                // criar Set<> para retornar apenas os produtos na lista (consequentemente, os serviços virão)
                //

                return sup;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return new HashMap<>();
        }
        /*
        public MetodosProduto DTProd() throws Exception {

            JsonToMap p = new JsonToMap();
            MetodosProduto novoProd = new MetodosProduto();
            //String fileParam = Config.NameSettings.Loja.getProperty();
            String fileParam = arquivoConfig.Loja.getPropriedade();

            Map<Integer, Produtos> sup = p.getMapRecord(Produtos.class, fileParam);
            Set<Map.Entry<Integer, Produtos>> getProd = sup.entrySet();

            String varContains = "Servicos Automotivos";
            getProd.stream().filter(c -> (!c.getValue().getGrupo().toString().contains(varContains))).forEach(prod -> {
                novoProd.setnomeProd(prod.getValue().getnomeProd());
                novoProd.setPreco(prod.getValue().getPreco());
                novoProd.setQtd(prod.getValue().getQtd());
                novoProd.setForn(prod.getValue().getForn());
                novoProd.setCategoria(prod.getValue().getCategoria());
                novoProd.setGrupo(prod.getValue().getGrupo());
                novoProd.novoProduto(prod.getKey(), novoProd);
            });

            return novoProd;
        }
 */

        public Map<Integer, Produtos> MapProd(){
            try {
                JsonToMap p = new JsonToMap();
                //String fileParam = Config.NameSettings.Loja.getProperty();
                String fileParam = arquivoConfig.Loja.getPropriedade();
                Map<Integer, Produtos> sup = p.getMapRecord(Produtos.class, fileParam);
                return sup;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return new HashMap<>();
        }

        /*
        public Fornecedor nomeForn (Integer id) throws Exception {

            Fornecedor forn;
            JsonToMap p = new JsonToMap();
            Set<Map.Entry<Integer, Fornecedor>> getForn = p.getMapRecord(Fornecedor.class).entrySet();

            forn = getForn.stream()
                    .filter(setid -> setid.getKey().equals(id))
                    .findFirst().orElseThrow().getValue();

            return forn;
        }
 */

        public Map<Integer, Servicos> MapServ(){
            try {
                JsonToMap p = new JsonToMap();
                //String fileParam = Config.NameSettings.Loja.getProperty();
                String fileParam = arquivoConfig.Loja.getPropriedade();
                Map<Integer, Servicos> sup = p.getMapRecord(Servicos.class, fileParam);
                return sup;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return new HashMap<>();
        }

    }

    protected static class JsonToMap {

        protected <T> Map<Integer, T> getMapRecord(Class<T> classe, String fileParam) throws Exception {
            Map<Integer, T> f1 = new HashMap<>();
            ListaJsonResponse<T> lr = new ListaJsonResponse<>();

            Integer i = 1;
            for (T p : lr.getListLegal(classe, fileParam)) {
                f1.put(i, p);
                i++;
            }
            return f1;
        }

        protected <T, R, U> Map<Integer, U> getMapObjects(Class<T> classe, Function<T, R> prop, BiFunction<Integer, String, U> obj, String fileParam) throws Exception {
            Map<Integer, U> m1 = new HashMap<>();
            ListaJsonResponse<T> li = new ListaJsonResponse<>();

            Integer i = 1;
            for (R p : li.getListProperties(classe, prop, fileParam)) {
                m1.put(i, obj.apply(i, p.toString()));
                i++;
            }
            return m1;
        }
        /*
        protected  <T, R> Map<Integer, Categoria> getMapCategorias(Class<T> classe, Function<T, R> propC) throws Exception {
            Map<Integer, Categoria> v1 = new HashMap<>();
            ListaJsonResponse<T> lp = new ListaJsonResponse<>();

            Integer i = 1;
            for (R p : lp.getListProperties(lp, classe, propC)) {
                v1.put(i, new Categoria(p.toString()));
                i++;
            }
            return v1;
        }

        protected  <T, R> Map<Integer, Grupos> getMapGrupos(Class<T> classe, Function<T, R> propG) throws Exception {
            Map<Integer, Grupos> v1 = new HashMap<>();
            ListaJsonResponse<T> lp = new ListaJsonResponse<>();

            Integer i = 1;
            for (R p : lp.getListProperties(lp, classe, propG)) {
                v1.put(i, new Grupos(p.toString()));
                i++;
            }
            return v1;
        }
*/
    }

    protected static class ListaJsonResponse<T> extends LeitorDados {
       /*
        protected T getClassNewInstance(Class<T> classe) throws Exception {
            return classe.getDeclaredConstructor().newInstance();
        }
        */

        protected <R> List<R> getListProperties(Class<T> classe, Function<T, R> property, String fileParam) throws Exception {
            List<R> str = new ArrayList<>();
            ListaJsonResponse<T> prod = new ListaJsonResponse<>();

            prod.getListObjects(classe, fileParam).forEach(p -> str.add(property.apply(p)));
            return str.stream().distinct().toList();
        }

        protected List<T> getListObjects(Class<T> classe, String fileParam) throws Exception {
            T instance = getClassNewInstance(classe);

            JsonGenericObjects<T> x = new JsonGenericObjects<>();
            //String fileParam = Config.arquivoConfig.Loja.getProperty();
            List<T> lp = x.JsonReturn(instance, fileParam);

            String varContains = "Servicos Automotivos";
            Predicate<T> filtroLoja = instance instanceof Produtos ? c -> (!c.toString().contains(varContains)) : c -> (c.toString().contains(varContains));

            /*
            if (instance instanceof Produtos) {
                Predicate<T> Products = c -> (!c.toString().contains(varContains));
                return lp.stream().filter(Products).toList();
            }
            if (instance instanceof Servicos) {
                Predicate<T> Services = c -> (c.toString().contains(varContains));
                return lp.stream().filter(Services).toList();
            }
            return new ArrayList<>();
            */
            return lp.stream().filter(filtroLoja).toList();
        }

        protected List<T> getListLegal(Class<T> classe, String fileParam) throws Exception {
            T instance = getClassNewInstance(classe);

            JsonGenericObjects<T> x = new JsonGenericObjects<>();
            //String fileParam = Config.arquivoConfig.Fornecedores.getProperty();
            List<T> lp = x.JsonReturn(instance, fileParam);

            return lp.stream().toList();
        }
    }

    //#region static test main
    /*
    public static void main(String[] args) throws Exception {
        ListaJsonResponse<Produtos> lp = new ListaJsonResponse<>();
        for (Produtos p : lp.getListItens(Produtos.class)) {
            System.out.println(p);
        }

        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        Function<Produtos, Propriedades.Categoria> propC = Gondola::getCategoria;
        for (Propriedades.Categoria p : lp.getListProperties(lp, Produtos.class, propC)) {
            System.out.println(p);
        }
        System.out.print("§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§\n");
        Function<Produtos, Propriedades.Grupos> propG = (Produtos p) -> p.getGrupo();
        for (Propriedades.Grupos p : lp.getListProperties(lp, Produtos.class, propG)) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) throws Exception {

        JsonExtraction.JsonToMap y = new JsonExtraction.JsonToMap();

        Function<Produtos, Propriedades.Categoria> propC = Gondola::getCategoria;
        y.getMapCategorias(Produtos.class, propC).forEach((K, V) -> System.out.println("Categoria{id=" + K + ", Categoria='" + V + "'}"));
                System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        Function<Produtos, Propriedades.Grupos> propG = Gondola::getGrupo;
        y.getMapGrupos(Produtos.class, propG).forEach((K, V) -> System.out.println("Grupo{id=" + K + ", Grupo='" + V + "'}"));
    }


    public static void main(String[] args) throws Exception {

        JsonExtraction.JsonToMap y = new JsonExtraction.JsonToMap();

        Function<Produtos, Propriedades.Categoria> propC = Gondola::getCategoria;
        y.getMapCategorias(Produtos.class, propC).forEach((K, V) -> System.out.println("Categoria{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        Function<Produtos, Propriedades.Grupos> propG = Gondola::getGrupo;
        y.getMapGrupos(Produtos.class, propG).forEach((K, V) -> System.out.println("Grupo{id=" + K + ", descricao='" + V + "'}"));
    }

    public static void main(String[] args) throws Exception {

        JsonToMap y = new JsonToMap();

        Function<Produtos, Categoria> propC = Gondola::getCategoria;
        y.getMapCategorias(Produtos.class, propC).forEach((K, V) -> System.out.println("Categoria{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        Function<Produtos, Grupos> propG = Gondola::getGrupo;
        y.getMapGrupos(Produtos.class, propG).forEach((K, V) -> System.out.println("Grupo{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        Function<Produtos, Categoria> propx = Gondola::getCategoria;
        y.getMapObjects(Produtos.class, propx, (a, b) -> new Categoria(b)).forEach((K, V) -> System.out.println("Categoria{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");

        Function<Produtos, Grupos> propy = Gondola::getGrupo;
        y.getMapObjects(Produtos.class, propy, (a, b) -> new Grupos(b)).forEach((K, V) -> System.out.println("Grupo{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");

        y.getMapRecord(Fornecedor.class).forEach((K, V) -> System.out.println("Forn{id=" + K + ", Valor='" + V + "'}"));

        ColetaJsonDados<Produtos> o = new ColetaJsonDados<>();
        System.out.println(o.nomeCategoria(2, Produtos.class));
        System.out.println(o.nomeGrupo(5, Produtos.class));

        //ColetaJsonDados<Fornecedor> u = new ColetaJsonDados<>();
        //System.out.println(u.nomeForn(5).getRazaoSocial());
    }
*/
    //#endregion
}

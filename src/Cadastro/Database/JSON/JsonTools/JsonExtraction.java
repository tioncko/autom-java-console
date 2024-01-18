package Cadastro.Database.JSON.JsonTools;

import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Cadastro.NovosDados.Repositorio.Abstratos.Gondola;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;
import Cadastro.NovosDados.Repositorio.DTO.*;
import Raiz.Core.impressaoLog;
import Raiz.Utils.leitorDados;

import java.util.*;
import java.util.function.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class jsonExtraction extends jsonResponse {

    public static class coletaJsonDados extends leitorDados {
        impressaoLog.logGenerico<coletaJsonDados> printLog = new impressaoLog.logGenerico<>();
        @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<coletaJsonDados>) (Object) (coletaJsonDados.class));

        /**
         * Método que recebe um id e um objeto que representa os dados da classe Produtos ou Serviços, e retorna um objeto Categoria
         */
        public <T> Categoria nomeCategoria(Integer id, Class<T> classe) {
            try {
                Categoria cat;
                jsonToMap json = new jsonToMap();
                String fileParam = arquivoConfig.Loja.getPropriedade();

                Set<Map.Entry<Integer, Categoria>> getCat;
                T instance = getClassNewInstance(classe);

                //#region rascunho
/*
                //String fileParam = Config.NameSettings.Loja.getProperty();
                //listaJsonResponse<T> lis = new listaJsonResponse<>();

            if (instance instanceof Produtos) {
                Function<Produtos, Categoria> propC = Gondola::getCategoria;
                //getCat = json.getObjects(Produtos.class, propC, (a, b) -> new Categoria()).entrySet();
                getCat = json.getMapCategorias(Produtos.class, propC).entrySet();
            }

            if (instance instanceof Servicos) {
                Function<Servicos, Categoria> propC = Gondola::getCategoria;
                //getCat = json.getObjects(Servicos.class, propC, (a, b) -> new Categoria()).entrySet();
                getCat = json.getMapCategorias(Servicos.class, propC).entrySet();
            }
*/
                //#endregion

                getCat = switch (instance.getClass().getSimpleName()) {
                    case "Produtos" -> {
                        Function<Produtos, Categoria> propC = Gondola::getCategoria;
                        yield json.getMapObjects(Produtos.class, propC, (a, b) -> new Categoria(b), fileParam).entrySet();
                    }
                    case "Servicos" -> {
                        Function<Servicos, Categoria> propC = Gondola::getCategoria;
                        yield json.getMapObjects(Servicos.class, propC, (a, b) -> new Categoria(b), fileParam).entrySet();
                    }
                    default -> null;
                };

                //#region rascunho
            /*

            Function<Produtos, Categoria> propP = Gondola::getCategoria;
            Function<Servicos, Categoria> propS = Gondola::getCategoria;


            Set<Map.Entry<Integer, Categoria>> getCat2 = instance instanceof Produtos
                    ? json.getObjects(Produtos.class, propP, (a, b) -> new Categoria(b)).entrySet()
                    : json.getObjects(Servicos.class, propS, (a, b) -> new Categoria(b)).entrySet();
*/
                //#endregion

                assert getCat != null;
                cat = getCat.stream().filter(setid -> setid.getKey().equals(id)).findFirst().orElseThrow().getValue();
                return cat;

            } catch (Exception e) {
                log.severe("[" + coletaJsonDados.class.getSimpleName() + "] " + e.getMessage());
            }
            return new Categoria();
        }

        /**
         * Método que recebe um id e um objeto que representa os dados da classe Produtos ou Serviços, e retorna um objeto Grupo
         */
        public <T> Grupos nomeGrupo(Integer id, Class<T> classe) {
            try {
                Grupos gpo;
                jsonToMap json = new jsonToMap();
                String fileParam = arquivoConfig.Loja.getPropriedade();

                Set<Map.Entry<Integer, Grupos>> getGp;
                T instance = getClassNewInstance(classe);

                //#region rascunho
            /*
                //String fileParam = Config.NameSettings.Loja.getProperty();
                //listaJsonResponse<T> lis = new listaJsonResponse<>();

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
                //#endregion

                getGp = switch (instance.getClass().getSimpleName()) {
                    case "Produtos" -> {
                        Function<Produtos, Grupos> propG = Gondola::getGrupo;
                        yield json.getMapObjects(Produtos.class, propG, (a, b) -> new Grupos(b), fileParam).entrySet();
                    }
                    case "Servicos" -> {
                        Function<Servicos, Grupos> propG = Gondola::getGrupo;
                        yield json.getMapObjects(Servicos.class, propG, (a, b) -> new Grupos(b), fileParam).entrySet();
                    }
                    default -> null;
                };

                assert getGp != null;
                gpo = getGp.stream().filter(setid -> setid.getKey().equals(id)).findFirst().orElseThrow().getValue();
                return gpo;

            } catch (Exception e) {
                log.severe("[" + coletaJsonDados.class.getSimpleName() + "] " + e.getMessage());
            }
            return new Grupos();
        }

        //#region rascunho
        /*
        public metodosFornecedor DTForn() {

            jsonToMap p = new jsonToMap();
            metodosFornecedor novoFrn = new metodosFornecedor();
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
        //#endregion

        /**
         * Método que retorna uma lista de Fornecedores
         */
        public Map<Integer, Fornecedor> mapForn() {
            try {
                jsonToMap json = new jsonToMap();
                String fileParam = arquivoConfig.Fornecedores.getPropriedade();
                Map<Integer, Fornecedor> getListForn = json.getMapRecord(Fornecedor.class, fileParam);

                return getListForn;

            } catch (Exception e) {
                log.severe("[" + coletaJsonDados.class.getSimpleName() + "] " + e.getMessage());
            }
            return new HashMap<>();
        }

        //#region rascunho
        /*
        public metodosProduto DTProd() throws Exception {

            jsonToMap p = new jsonToMap();
            metodosProduto novoProd = new metodosProduto();
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
//#endregion

        /**
         * Método que retorna uma lista de Produtos
         */
        public Map<Integer, Produtos> mapProd(){
            try {
                jsonToMap json = new jsonToMap();
                String fileParam = arquivoConfig.Loja.getPropriedade();
                String varContains = "Servicos Automotivos";
                Stream <Map.Entry <Integer, Produtos>>
                        getListProd = json.getMapRecord(Produtos.class, fileParam).entrySet().stream()
                        .filter(c -> (!c.getValue().toString().contains(varContains)));

                List<Produtos> prod = new ArrayList<>();
                getListProd.forEach(x -> prod.add(x.getValue()));

                Map<Integer, Produtos> retProd = new HashMap<>();
                for(int i = 1; i < prod.size(); i++){
                    retProd.put(i, prod.get(i));
                }
                return retProd;
            } catch (Exception e) {
                log.severe("[" + coletaJsonDados.class.getSimpleName() + "] " + e.getMessage());
            }
            return new HashMap<>();
        }

        //#region rascunho
        /*
        public Fornecedor nomeForn (Integer id) throws Exception {

            Fornecedor forn;
            jsonToMap p = new jsonToMap();
            Set<Map.Entry<Integer, Fornecedor>> getForn = p.getMapRecord(Fornecedor.class).entrySet();

            forn = getForn.stream()
                    .filter(setid -> setid.getKey().equals(id))
                    .findFirst().orElseThrow().getValue();

            return forn;
        }
 */
//#endregion

        /**
         * Método que retorna uma lista de Serviços
         */
        public Map<Integer, Servicos> mapServ(){
            try {
                jsonToMap json = new jsonToMap();
                String fileParam = arquivoConfig.Loja.getPropriedade();
                String varContains = "Servicos Automotivos";
                Stream <Map.Entry <Integer, Servicos>>
                        getListServ = json.getMapRecord(Servicos.class, fileParam).entrySet().stream()
                        .filter(c -> (c.getValue().toString().contains(varContains)));

                List<Servicos> serv = new ArrayList<>();
                getListServ.forEach(x -> serv.add(x.getValue()));

                Map<Integer, Servicos> retServ = new HashMap<>();
                for(int i = 1; i < serv.size(); i++){
                    retServ.put(i, serv.get(i));
                }
                return retServ;
            } catch (Exception e) {
                log.severe("[" + coletaJsonDados.class.getSimpleName() + "] " + e.getMessage());
            }
            return new HashMap<>();
        }
    }

    protected static class jsonToMap {

        /**
         * Método responsável por retornar uma lista genérica com índice de Fornecedores
         */
        protected <T> Map<Integer, T> getMapRecord(Class<T> classe, String fileParam) {
            Map<Integer, T> genMap = new HashMap<>();
            listaJsonResponse<T> genList = new listaJsonResponse<>();

            Integer i = 1;
            for (T gen : genList.getListLegal(classe, fileParam)) {
                genMap.put(i, gen);
                i++;
            }
            return genMap;
        }

        /**
         * Método responsável por retornar uma lista genérica com índice de Produtos e Serviços
         */
        protected <T, R, U> Map<Integer, U> getMapObjects(Class<T> classe, Function<T, R> prop, BiFunction<Integer, String, U> obj, String fileParam) {
            Map<Integer, U> genMap = new HashMap<>();
            listaJsonResponse<T> genList = new listaJsonResponse<>();

            Integer i = 1;
            for (R gen : genList.getListProperties(classe, prop, fileParam)) {
                genMap.put(i, obj.apply(i, gen.toString()));
                i++;
            }
            return genMap;
        }

        //#region rascunho
        /*
        protected  <T, R> Map<Integer, Categoria> getMapCategorias(Class<T> classe, Function<T, R> propC) throws Exception {
            Map<Integer, Categoria> v1 = new HashMap<>();
            listaJsonResponse<T> lp = new listaJsonResponse<>();

            Integer i = 1;
            for (R p : lp.getListProperties(lp, classe, propC)) {
                v1.put(i, new Categoria(p.toString()));
                i++;
            }
            return v1;
        }

        protected  <T, R> Map<Integer, Grupos> getMapGrupos(Class<T> classe, Function<T, R> propG) throws Exception {
            Map<Integer, Grupos> v1 = new HashMap<>();
            listaJsonResponse<T> lp = new listaJsonResponse<>();

            Integer i = 1;
            for (R p : lp.getListProperties(lp, classe, propG)) {
                v1.put(i, new Grupos(p.toString()));
                i++;
            }
            return v1;
        }
*/
        //#endregion
    }

    protected static class listaJsonResponse<T> extends leitorDados {
        impressaoLog.logGenerico<listaJsonResponse<T>> printLog = new impressaoLog.logGenerico<>();
        @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<listaJsonResponse<T>>) (Object) (listaJsonResponse.class));

        //#region rascunho
       /*
        protected T getClassNewInstance(Class<T> classe) throws Exception {
            return classe.getDeclaredConstructor().newInstance();
        }
        */
        //#endregion

        /**
         * Método responsável por retornar um distinct na lista gerada pelo método getListObjects
         */
        protected <R> List<R> getListProperties(Class<T> classe, Function<T, R> property, String fileParam) {
            try {
                List<R> str = new ArrayList<>();
                listaJsonResponse<T> prod = new listaJsonResponse<>();

                prod.getListObjects(classe, fileParam).forEach(p -> str.add(property.apply(p)));
                return str.stream().distinct().toList();
            } catch (Exception e) {
                log.severe("[" + listaJsonResponse.class.getSimpleName() + "] " + e.getMessage());
            }
            return new ArrayList<>();
        }

        /**
         * Método responsável por retornar a lista deserializada na classe jsonGenericObjects após efetuar a filtragem referente a
         * Categoria dos Produtos e Serviços
         */
        protected List<T> getListObjects(Class<T> classe, String fileParam) {
            try {
                T instance = getClassNewInstance(classe);

                jsonGenericObjects<T> jsonGen = new jsonGenericObjects<>();
                List<T> jsonList = jsonGen.jsonReturn(instance, fileParam);

                String varContains = "Servicos Automotivos";
                Predicate<T> filtroLoja = instance instanceof Produtos
                        ? c -> (!c.toString().contains(varContains))
                        : c -> (c.toString().contains(varContains));

                //#region rascunho
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
                //#endregion

                return jsonList.stream().filter(filtroLoja).toList();
            } catch (Exception e) {
                log.severe("[" + listaJsonResponse.class.getSimpleName() + "] " + e.getMessage());
            }
            return new ArrayList<>();
        }

        /**
         * Método responsável por retornar a lista deserializada na classe jsonGenericObjects
         */
        protected List<T> getListLegal(Class<T> classe, String fileParam) {
            try {
                T instance = getClassNewInstance(classe);

                jsonGenericObjects<T> jsonGen = new jsonGenericObjects<>();
                List<T> jsonList = jsonGen.jsonReturn(instance, fileParam);

                return jsonList.stream().toList();
            } catch (Exception e) {
                log.severe("[" + listaJsonResponse.class.getSimpleName() + "] " + e.getMessage());
            }
            return new ArrayList<>();
        }
    }

    //#region static test main
    /*
    public static void main(String[] args) throws Exception {
        listaJsonResponse<Produtos> lp = new listaJsonResponse<>();
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

        jsonExtraction.jsonToMap y = new jsonExtraction.jsonToMap();

        Function<Produtos, Propriedades.Categoria> propC = Gondola::getCategoria;
        y.getMapCategorias(Produtos.class, propC).forEach((K, V) -> System.out.println("Categoria{id=" + K + ", Categoria='" + V + "'}"));
                System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        Function<Produtos, Propriedades.Grupos> propG = Gondola::getGrupo;
        y.getMapGrupos(Produtos.class, propG).forEach((K, V) -> System.out.println("Grupo{id=" + K + ", Grupo='" + V + "'}"));
    }


    public static void main(String[] args) throws Exception {

        jsonExtraction.jsonToMap y = new jsonExtraction.jsonToMap();

        Function<Produtos, Propriedades.Categoria> propC = Gondola::getCategoria;
        y.getMapCategorias(Produtos.class, propC).forEach((K, V) -> System.out.println("Categoria{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        Function<Produtos, Propriedades.Grupos> propG = Gondola::getGrupo;
        y.getMapGrupos(Produtos.class, propG).forEach((K, V) -> System.out.println("Grupo{id=" + K + ", descricao='" + V + "'}"));
    }

    public static void main(String[] args) throws Exception {

        jsonToMap y = new jsonToMap();

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

        coletaJsonDados<Produtos> o = new coletaJsonDados<>();
        System.out.println(o.nomeCategoria(2, Produtos.class));
        System.out.println(o.nomeGrupo(5, Produtos.class));

        //coletaJsonDados<Fornecedor> u = new coletaJsonDados<>();
        //System.out.println(u.nomeForn(5).getRazaoSocial());
    }
*/

    //#endregion
}

package Cadastro.Database.JSON.JsonTools;

import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Cadastro.NovosDados.Repositorio.Abstratos.Gondola;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;
import Cadastro.NovosDados.Repositorio.DTO.*;
import Raiz.Core.impressaoLog;
import Raiz.Utils.leitorDados;
import Raiz.Utils.smartTools.genericCollects.*;

import java.util.*;
import java.util.function.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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

        /**
         * Método que retorna o nome de um fornecedor
         */
        public Fornecedores nomeForn (Integer id) {

            Fornecedores forn;
            coletaJsonDados p = new coletaJsonDados();
            Set<Map.Entry<Integer, Fornecedores>> getForn = p.mapForn().entrySet();;

            forn = getForn.stream()
                    .filter(setid -> setid.getKey().equals(id))
                    .findFirst().orElseThrow().getValue();
            return forn;
        }

        //#region rascunho
        /*
        public metodosFornecedores DTForn() {

            jsonToMap p = new jsonToMap();
            metodosFornecedores novoFrn = new metodosFornecedores();
            //String fileParam = Config.NameSettings.Fornecedores.getProperty();
            String fileParam = arquivoConfig.Fornecedores.getPropriedade();

            try {
                Map<Integer, Fornecedores> sup = p.getMapRecord(Fornecedores.class, fileParam);
                Set<Map.Entry<Integer, Fornecedores>> getForn = sup.entrySet();

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
        public Map<Integer, Fornecedores> mapForn() {
            try {
                jsonToMap jsonmap = new jsonToMap();
                String fileParam = arquivoConfig.Fornecedores.getPropriedade();
                Map<Integer, Fornecedores> getListForn = jsonmap.getMapRecord(Fornecedores.class, fileParam);

                for (int i = 1; i <= getListForn.size(); i++) {
                    int id = i;
                    Complementos jsonCp = new Complementos();
                    genericSet<Grupos> listAtividades = jsonCp.getListAllAtividades().entrySet().stream().filter(key -> key.getKey().equals(id)).findFirst().orElseThrow().getValue();

                    getListForn.entrySet().stream().filter(x -> x.getKey().equals(id)).forEach(forn -> {

                        getListForn.put(id, new Fornecedores(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getDocumento(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP(), listAtividades));
                    });
                }

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



        public Map<Integer, Produtos> base(){

            Map<Integer, Produtos> retProd = new HashMap<>();

            try {
                jsonToMap json = new jsonToMap();
                String fileParam = arquivoConfig.Loja.getPropriedade();
                String varContains = "Servicos Automotivos";
                Stream <Map.Entry <Integer, Produtos>>
                        getListProd = json.getMapRecord(Produtos.class, fileParam).entrySet().stream()
                        .filter(c -> (!c.getValue().toString().contains(varContains)));

                List<Produtos> prod = new ArrayList<>();
                getListProd.forEach(x -> prod.add(x.getValue()));


                for(int i = 0; i < prod.size(); i++){
                    retProd.put(i, prod.get(i));
                }
                /*
                Complementos jsonCp = new Complementos();
                for (int id = 1; id <= retProd.size(); id++) {
                    int i = id;

                    String nomeForn = jsonCp.getNomeForn().entrySet().stream().filter(key -> key.getKey().equals(i)).findFirst().orElseThrow().getValue();

                    retProd.entrySet().stream().filter(x -> x.getKey().equals(i)).forEach(insert -> {

                        retProd.put(i, new Produtos(insert.getValue().getnomeProd(), insert.getValue().getPreco(), insert.getValue().getQtd(), nomeForn, insert.getValue().getCategoria(), insert.getValue().getGrupo()));
                    });
                }

                //return retProd;

                return retProd;
            } catch (Exception e) {
                log.severe("[" + coletaJsonDados.class.getSimpleName() + "] " + e.getMessage());
            }
            return new HashMap<>();
        }
 */
//#endregion

        /**
         * Método que retorna uma lista de Produtos
         */
        //public Map<Integer, Produtos> mapProd () {
        public Map<Integer, Produtos> base () { // Insere os nomes dos fornecedores na tabela de produtos (trocar o nome base por mapProd)
            Complementos jsonCp = new Complementos();
            Map<Integer, Produtos> retProd = jsonCp.mapJsonProd();
            Set<Map.Entry<Integer, String>> sup = jsonCp.getNomeForn().entrySet(); ////

            for (int i = 1; i <= retProd.size(); i++) {
                int id = i;
                String nomeForn = sup.stream().filter(k -> k.getKey().equals(id)).findFirst().orElseThrow().getValue();

                retProd.entrySet().stream().filter(x -> x.getKey().equals(id)).forEach(insert -> {
                    retProd.put(id, new Produtos(
                            insert.getValue().getnomeProd(),
                            insert.getValue().getPreco(),
                            insert.getValue().getQtd(),
                            nomeForn,
                            insert.getValue().getCategoria(),
                            insert.getValue().getGrupo()));
                });
            }
            return retProd;
        }

        //public Map<Integer, Produtos> base(){
        public Map<Integer, Produtos> mapProd () {
            Map<Integer, Produtos> retProd = new HashMap<>();

            try {
                jsonToMap json = new jsonToMap();
                String fileParam = arquivoConfig.Loja.getPropriedade();
                String varContains = "Servicos Automotivos";
                Stream <Map.Entry <Integer, Produtos>>
                        getListProd = json.getMapRecord(Produtos.class, fileParam).entrySet().stream()
                        .filter(c -> (!c.getValue().toString().contains(varContains)));

                List<Produtos> prod = new ArrayList<>();
                getListProd.forEach(x -> prod.add(x.getValue()));

                for(int i = 0; i < prod.size(); i++){
                    retProd.put(i + 1, prod.get(i));
                }

                return retProd;
            } catch (Exception e) {
                log.severe("[" + coletaJsonDados.class.getSimpleName() + "] " + e.getMessage());
            }
            return new HashMap<>();
        }

        //#region rascunho
        /*
        public Fornecedores nomeForn (Integer id) throws Exception {

            Fornecedores forn;
            jsonToMap p = new jsonToMap();
            Set<Map.Entry<Integer, Fornecedores>> getForn = p.getMapRecord(Fornecedores.class).entrySet();

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
        y.getMapRecord(Produtos.class, propC).forEach((K, V) -> System.out.println("Categoria{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        Function<Produtos, Grupos> propG = Gondola::getGrupo;
        y.getMapRecord(Produtos.class, propG).forEach((K, V) -> System.out.println("Grupo{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        Function<Produtos, Categoria> propx = Gondola::getCategoria;
        y.getMapRecord(Produtos.class, propx, (a, b) -> new Categoria(b)).forEach((K, V) -> System.out.println("Categoria{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");

        Function<Produtos, Grupos> propy = Gondola::getGrupo;
        y.getMapRecord(Produtos.class, propy, (a, b) -> new Grupos(b)).forEach((K, V) -> System.out.println("Grupo{id=" + K + ", descricao='" + V + "'}"));
        System.out.print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");

        //y.getMapRecord(Fornecedores.class).forEach((K, V) -> System.out.println("Forn{id=" + K + ", Valor='" + V + "'}"));

        coletaJsonDados o = new coletaJsonDados();
        System.out.println(o.nomeCategoria(2, Produtos.class));
        System.out.println(o.nomeGrupo(5, Produtos.class));

        //coletaJsonDados<Fornecedores> u = new coletaJsonDados<>();
        //System.out.println(u.nomeForn(5).getRazaoSocial());
    }
*/
    //#endregion

    protected static class Complementos {

        /**
         * Método auxiliar (método MapForn) que retorna a lista de atividades que cada fornecedor exerce (modo randômico pois para aqueles fornecedores que
         * fazem parte do Suppliers.json).
         */
        public Map<Integer, genericSet<Grupos>> getListAllAtividades() {
            jsonToMap json = new jsonToMap();
            String fileParam = arquivoConfig.Loja.getPropriedade();

            Set<Map.Entry<Integer, Grupos>> getGp;
            Function<Produtos, Grupos> propG = Gondola::getGrupo;
            getGp = json.getMapObjects(Produtos.class, propG, (a, b) -> new Grupos(b), fileParam).entrySet();

            Random limit = new Random();
            Random grupo = new Random();

            Map<Integer, genericSet<Grupos>> mapGp = new HashMap<>();

            for (int i = 0; i < 20; i++) {
                Set<Integer> setGpId = new HashSet<>();
                genericSet<Grupos> listGp = new genericSet<>();

                for (int j = 0; j < (2 + limit.nextInt(6)); j++) {
                    setGpId.add(1 + grupo.nextInt(9));
                }

                Iterator<Integer> itrGp = setGpId.iterator();
                for (int k = 0; k < setGpId.toArray().length; k++) {
                    while (itrGp.hasNext()) {
                        int index = itrGp.next();
                        getGp.stream().filter(set -> set.getKey().equals(index)).forEach(o -> listGp.add(o.getValue()));
                    }
                }
                mapGp.put(i + 1, listGp);
            }

            return mapGp;
        }

        /**
         * Cria a lista minerada que correlaciona o grupo do produto com o nome do fornecedor
         */
        public Map<Integer, String> getNomeForn() {

            jsonToMap jsonMap = new jsonToMap();
            String fileParam = arquivoConfig.Loja.getPropriedade();
            Function<Produtos, Grupos> propG = Gondola::getGrupo;

            coletaJsonDados cjm = new coletaJsonDados();
            Set<Map.Entry<Integer, Produtos>> mapProd =
                    mapJsonProd().entrySet();
            Set<Map.Entry<Integer, List<String>>> getListForn = getListForn().entrySet();

            Map<Integer, String> listFornId = new HashMap<>();
            int size = jsonMap.getMapObjects(Produtos.class, propG, (a, b) -> new Grupos(b), fileParam).size();

            for (int j = 1; j <= size; j++) {
                String gpo = String.valueOf(cjm.nomeGrupo(j, Produtos.class));

                List<Integer> listProd = mapProd.stream().filter(e -> e.getValue().getGrupo().toString().contains(gpo)).map(Map.Entry::getKey).toList();
                List<String> listForn = getListForn.stream().filter(e -> e.getValue().contains(gpo)).map(Map.Entry::getValue).map(y -> y.get(0)).toList();

                try {
                    if (listProd.size() == listForn.size()) {
                        for (int i = 0; i < listProd.size(); i++) {
                            listFornId.put(listProd.get(i), listForn.get(i));
                        }
                    }
                } catch (Exception e) {
                    System.out.println("[" + e.getMessage() + "] Lista de fornecedores e/ou lista de produtos incompatível");
                }
            }
            return listFornId;
        }

        /**
         * Cria a sublista minerada facilita a correlação do grupo gerado randomicamente com o nome do fornecedor
         */
        public Map<Integer, List<String>> getListForn() {

            jsonToMap jsonMap = new jsonToMap();
            String fileParam = arquivoConfig.Loja.getPropriedade();
            Function<Produtos, Grupos> propG = Gondola::getGrupo;

            Set<Map.Entry<Integer, Produtos>> mapProd =
                    mapJsonProd().entrySet();

            Map<Integer, List<String>> listForn = new HashMap<>();
            List<String> forns;
            int cont = 0;
            for (int j = 1; j <= jsonMap.getMapObjects(Produtos.class, propG, (a, b) -> new Grupos(b), fileParam).size(); j++) {
                int code = j;
                coletaJsonDados cjm = new coletaJsonDados();
                Supplier<Stream<Map.Entry<Integer, Produtos>>> supProd = mapProd::stream;

                int listLen = (int) supProd.get().filter(p -> p.getValue().getGrupo().toString().contains(String.valueOf(cjm.nomeGrupo(code, Produtos.class)))).count();

                Random rdm = new Random();
                Map<String, List<String>> listStr = getListFornNomes();

                List<String> values = listStr.entrySet().stream()
                        .filter(e -> e.getKey().contains(String.valueOf(cjm.nomeGrupo(code, Produtos.class)))) //
                        .map(Map.Entry::getValue).flatMap(Collection::stream).toList();
                //.map(p -> p.getValue()).flatMap(x -> x.stream()).collect(Collectors.toList());

                for (int i = 0; i < listLen; i++) {
                    cont++;
                    forns = new ArrayList<>();
                    int rdmInt = rdm.nextInt(values.size());

                    forns.add(String.valueOf(values.get(rdmInt)));
                    forns.add(String.valueOf(cjm.nomeGrupo(code, Produtos.class)));

                    listForn.put(cont, forns);
                }
            }
            return listForn;
        }

        /**
         * Retorna uma lista com os nomes dos fornecedores que pertencem a um certo grupo
         */
        public Map<String, List<String>> getListFornNomes() {

            jsonToMap jsonMap = new jsonToMap();
            String fileParam = arquivoConfig.Loja.getPropriedade();
            Function<Produtos, Grupos> propG = Gondola::getGrupo;

            List<Integer> listInt;
            Map<String, List<Integer>> mapInt = new HashMap<>();
            coletaJsonDados cjm = new coletaJsonDados();

            for (int i = 1; i <= jsonMap.getMapObjects(Produtos.class, propG, (a, b) -> new Grupos(b), fileParam).size(); i++) {
                listInt = getFornIds(String.valueOf(cjm.nomeGrupo(i, Produtos.class)));
                mapInt.put(String.valueOf(cjm.nomeGrupo(i, Produtos.class)), listInt);
            }
            //mapInt.forEach((key, value) -> System.out.println(key + " <-> " + value));

            return mapInt.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream()
                            .map(i -> cjm.nomeForn(i).getNomeFantasia())
                            .collect(Collectors.toList())));
        }

        /**
         * Gera a lista de ids dos fornecedores que pertencem a um certo grupo
         **/
        public List<Integer> getFornIds(String param) {
            coletaJsonDados cjd = new coletaJsonDados();
            Set<Map.Entry<Integer, Fornecedores>> setForn = cjd.mapForn().entrySet();

            List<Integer> getIds = new ArrayList<>();
            setForn.forEach(x -> {
                if (x.getValue().getAtividades().contains(new Grupos(param))) {
                    getIds.add(x.getKey());
                }
            });
            return getIds;
        }

        /**
         * Responsável por coletar a lista de produtos deserializada
         */
        public Map<Integer, Produtos> mapJsonProd() {
            Map<Integer, Produtos> retProd = new HashMap<>();

            jsonToMap json = new jsonToMap();
            String fileParam = arquivoConfig.Loja.getPropriedade();
            String varContains = "Servicos Automotivos";
            Stream<Map.Entry<Integer, Produtos>> getListProd = json.getMapRecord(Produtos.class, fileParam).entrySet().stream()
                    .filter(c -> (!c.getValue().toString().contains(varContains)));

            List<Produtos> prod = new ArrayList<>();
            getListProd.forEach(x -> prod.add(x.getValue()));

            for (int i = 0; i < prod.size(); i++) {
                retProd.put(i + 1, prod.get(i));
            }
            return retProd;
        }
    }

    //#region rascunho
        /*
    public static void main(String[] args) {

        Complementos x = new Complementos();

        genericSet<Grupos> gp = x.getListAllAtividades().entrySet().stream().filter(xx -> xx.getKey().equals(1)).findFirst().orElseThrow().getValue();
        System.out.println(gp);

        coletaJsonDados z = new coletaJsonDados();
        System.out.println(z.mapForn());
//                .forEach(xxx -> System.out.println("Map{id=" + xxx.getKey() + ", Integer='" + xxx.getValue() + "'}"));



        jsonToMap json = new jsonToMap();
        String fileParam = arquivoConfig.Loja.getPropriedade();

        Set<Map.Entry<Integer, Categoria>> getCat;
        Function<Produtos, Categoria> propC = Gondola::getCategoria;
        getCat = json.getMapObjects(Produtos.class, propC, (a, b) -> new Categoria(b), fileParam).entrySet();

        //getCat.forEach(x -> System.out.println("Categoria{id=" + x.getKey() + ", Categoria='" + x.getValue() + "'}"));


        Random limit = new Random();
        Random categoria = new Random();

        //Map<Integer, Set<Integer>> u = new HashMap<>();
        Map<Integer, List<Categoria>> mapCat = new HashMap<>();

        for (int i = 0; i < 20; i++) {
            Set<Integer> setCatId = new HashSet<>();
            List<Categoria> listCat = new ArrayList<>();

            for (int j = 0; j < limit.nextInt(1,6); j++) {
                setCatId.add(categoria.nextInt(1,34));
            }

            Iterator<Integer> itrCat = setCatId.iterator();
            for (int k = 0; k < setCatId.toArray().length; k++) {
                while (itrCat.hasNext()) {
                    int index = itrCat.next();
                    getCat.stream().filter(set -> set.getKey().equals(index)).forEach(o -> listCat.add(o.getValue()));
                }
            }
            //u.put(i + 1, setCatId);
            mapCat.put(i + 1, listCat);
        };

        //u.forEach((key, value) -> System.out.println("Map{id=" + key + ", Integer='" + value + "'}"));
        mapCat.forEach((key, value) -> System.out.println("FornGenerico{id=" + key + ", Nome='" + value + "'}"));

    }


    public static void getList() {

        Complementos k = new Complementos();

        Set<Map.Entry<Integer, genericSet<Grupos>>> pe = k.getListAllAtividades().entrySet();
        int i = 1;
        for (Map.Entry<Integer, genericSet<Grupos>> o : pe){
            if (o.getKey().equals(i)) {
                System.out.println(o.getValue());
                i++;
            }
        }
    }


    public static void main(String args) throws Exception {
        jsonToMap y = new jsonToMap();
        String fileParam = arquivoConfig.Loja.getPropriedade();

        //Function<Produtos, Categoria> propC = Gondola::getCategoria;
        //y.getMapObjects(Produtos.class, propC, (a, b) -> new Categoria(b), fileParam).forEach((K, V) -> System.out.println("Categoria{id=" + K + ", Categoria='" + V + "'}"));

        Function<Produtos, Grupos> propG = Gondola::getGrupo;
        //y.getMapObjects(Produtos.class, propG,(a, b) -> new Grupos(b), fileParam).forEach((K, V) -> System.out.println("Grupo{id=" + K + ", Grupo='" + V + "'}"));

        System.out.println();
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

        jsonExtraction x = new jsonExtraction();
        coletaJsonDados qwe = new coletaJsonDados();

        List<Integer> lista;
        Map<String, List<Integer>> lst =  new HashMap<>();

        for (int i = 1; i <= y.getMapObjects(Produtos.class, propG,(a, b) -> new Grupos(b), fileParam).size(); i++) {

            lista = x.getFornIds(String.valueOf(qwe.nomeGrupo(i, Produtos.class)));
            lst.put(String.valueOf(qwe.nomeGrupo(i, Produtos.class)), lista);
        }
        lst.forEach((key, value) -> System.out.println(key + " <-> " + value));

        System.out.println();
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

        Map<String, List<String>> lst2 =  new HashMap<>();

        Set<Map.Entry<String, List<Integer>>> get = lst.entrySet();
        for (Map.Entry<String, List<Integer>> set : get) {
            List<String> lista2 = new ArrayList<>();
            for(Integer i : set.getValue()){
                lista2.add(qwe.nomeForn(i).getNomeFantasia());
            }
            lst2.put(set.getKey(), lista2);
        }
        lst2.forEach((key, value) -> System.out.println(key + " <-> " + value));

        System.out.println();
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

        //for (Integer qw : lista) {
        //    Map<Integer, Fornecedores> pe = qwe.mapForn();
        //
        //            pe.entrySet().stream().filter(d -> d.getKey().equals(qw))
        //                    .forEach(o -> System.out.println("{id=" + o.getKey() + ", Forn='" + o.getValue().getNomeFantasia() + "'}"));
        //        }

    }








        Map<String, List<String>> mapStr =  new HashMap<>();

        Set<Map.Entry<String, List<Integer>>> getMap = mapInt.entrySet();
        for (Map.Entry<String, List<Integer>> setMap : getMap) {
            List<String> listStr = new ArrayList<>();
            for(Integer i : setMap.getValue()){
                listStr.add(cjm.nomeForn(i).getNomeFantasia());
            }
            mapStr.put(setMap.getKey(), listStr);
            System.out.print("Carregando....\n");
        }
        mapStr.forEach((key, value) -> System.out.println(key + " <-> " + value));
        System.out.println();


        /*


            //for (int i = 0; i < values.size(); i++) {
            //    System.out.print(values.get(i) + " ");
           // }
            //System.out.println();
            //System.out.print(cjm.nomeGrupo(J, Produtos.class));
            //System.out.print(" -> ");
            //System.out.print(values.get(g) + "\n");

        for (int j = 1; j <= jsonMap.getMapObjects(Produtos.class, propG,(a, b) -> new Grupos(b), fileParam).size(); j++) {
            int J = j;

            Supplier<Stream<Map.Entry<String, List<String>>>> ddd = () -> op.entrySet().stream();
            int len = (int) ddd.get().filter(p -> p.getKey().contains(String.valueOf(cjm.nomeGrupo(J, Produtos.class)))).count();

            Random d = new Random();
            ddd.get().forEach(u -> {
                for (int i = 1; i <= len; i++) {
                    int g = 1 + d.nextInt(8);
                    System.out.print(1 + g);
                    System.out.print(" ");
                    System.out.print(u.getValue());
                    //System.out.print(cjm.nomeForn(g).getNomeFantasia());
                    System.out.println();
                }
            });
            System.out.println();
        }

    public static void main(String[] args) {

        jsonToMap jsonMap = new jsonToMap();
        String fileParam = arquivoConfig.Loja.getPropriedade();
        Function<Produtos, Grupos> propG = Gondola::getGrupo;

        coletaJsonDados cjm = new coletaJsonDados();
        Set<Map.Entry<Integer, Produtos>> k = cjm.mapProd().entrySet();

        //k.forEach(K -> System.out.println(K.getKey() + " === " + K.getValue().getGrupo()));

        Map<Integer, List<String>> fill = new HashMap<>();
        List<String> full;
        int tt = 0;
        for (int j = 1; j <= jsonMap.getMapObjects(Produtos.class, propG,(a, b) -> new Grupos(b), fileParam).size(); j++) {
            int J = j;

            Supplier<Stream<Map.Entry<Integer, Produtos>>> er = k::stream;

            int len = (int) er.get().filter(p -> p.getValue().getGrupo().toString().contains(String.valueOf(cjm.nomeGrupo(J, Produtos.class)))).count();

            Random d = new Random();
            jsonExtraction s = new jsonExtraction();
            Map<String, List<String>> op = s.getFornNomes();
            //op.forEach((key, value) -> System.out.println(key + " <-> " + value));

            List<String> values = op.entrySet().stream() //
                    .filter(e -> e.getKey().contains(String.valueOf(cjm.nomeGrupo(J, Produtos.class)))) //
                    .map(Map.Entry::getValue).flatMap(Collection::stream).toList();
            //.map(p -> p.getValue()).flatMap(x -> x.stream()).collect(Collectors.toList());

            for (int i = 0; i < len; i++) {
                full = new ArrayList<>();
                tt++;
                int g = d.nextInt(values.size());
                full.add(String.valueOf(values.get(g)));
                full.add(String.valueOf(cjm.nomeGrupo(J, Produtos.class)));
                fill.put(tt, full);
            }

            //fill.forEach((K, V) -> System.out.println(K + " <-> " + cjm.nomeGrupo(J, Produtos.class) + "=" + V));
        }
        fill.forEach((K, V) -> System.out.println(K + " <-> " + "value=" + V));
    }

            List<String> nome = new ArrayList<>();
        List<String> listForn = getListForn.stream()
                .filter(e -> e.getValue().contains("Freio"))
                .map(x -> x.getValue())
                .map(y -> y.get(0).toString())
                .toList();
                // certo .map(x -> x.getValue().toString())
                //.flatMap(x -> x.stream())
//                .flatMap(f -> {
//                    List<String> nomeForn = f.stream().toList();
//                    for (int i = 0; i < nomeForn.size(); i++) {
//                        nome.add(nomeForn.get(1));
//                    }
//                return f.stream();
//                })
                //.toList();
                // certo .collect(Collectors.toList());




    public static void main(String[] args) {

        jsonExtraction.Complementos x = new jsonExtraction.Complementos();

        ////Set<Map.Entry<Integer, String>> sup = x.getNomeForn().entrySet();
        //sup.get().forEach(k -> System.out.println(k.getKey() + " <==> " + k.getValue()));

        ////String p = sup.stream().filter(k -> k.getKey().equals(1)).findFirst().get().getValue();
        ////System.out.println(p);

        ////String qp = sup.stream().filter(k -> k.getKey().equals(3)).findFirst().orElseThrow().getValue();
        ////System.out.println(qp);
        jsonExtraction.coletaJsonDados jsonCp =  new coletaJsonDados();
        //jsonExtraction.Complementos x = new Complementos();

        //String d = x.getNomeForn().entrySet().stream().filter(key -> key.getKey().equals(1)).findFirst().orElseThrow().getValue();
        //System.out.println("info=" + d);
        jsonCp
                .mapProd()//.entrySet();
        //        //.mapProd()
                .forEach((K, V) -> System.out.println(K + " <-> " + V));

    }

*/

    //#endregion
}


package Cadastro.Database.Metodos;

import Cadastro.Database.dataSet;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;
import Cadastro.NovosDados.Repositorio.DTO.Fornecedor;
import Cadastro.NovosDados.Repositorio.Enums.camposForn;
import Raiz.Utils.smartTools.*;
import Raiz.Utils.smartTools.genericCollects.*;

import java.util.*;
import java.util.stream.Stream;

public class metodosFornecedor extends Fornecedor {

    private final dataSet<Fornecedor> DS;
    public static String message;

    /**
     * Construtor
     */
    @SuppressWarnings("unchecked")
    public metodosFornecedor(dataSet<?> banco) {
        this.DS = (dataSet<Fornecedor>) banco;
    }

    /**
     * Inserir novo fornecedor
     */
    public void novoFornecedor(Integer id, Fornecedor forn) {
        if (forn.getCnpj() != null){
            DS.insert(forn.setId(id), new Fornecedor(forn.getRazaoSocial(), forn.getNomeFantasia(), forn.getCnpj(), forn.getEmail(), forn.getInscEstadual(), forn.getTelefone(), forn.getInfoCEP(), forn.getAtividades()), Fornecedor.class);
        } else message = "\nNão é possível realizar o cadastro do cliente " + forn.getNomeFantasia() + ". Necessário informar o seu CPF.";
    }

    /**
     * Alterar um fornecedor
     */
    public void alterFornecedor(Integer id, String Campo, String... update) {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            if (DS.select(Fornecedor.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                camposForn getCampo = camposForn.valueOf(Campo.toUpperCase());

                Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();
                getForn.stream().filter(x -> x.getKey().equals(id)).forEach(forn -> {

                    String obj1 = "", obj2 = "";
                    boolean nullable = (forn.getValue().getAtividades() == null);
                    List<String> obj = new ArrayList<>();

                    if (update.length < 2 || nullable)
                        obj1 = update[0];
                    if (update.length == 2) {
                        obj1 = update[0];
                        obj2 = update[1];
                    }
                    else obj.add(update[0]);

                    switch (getCampo) {
                        case RAZAOSOCIAL ->
                            DS.insert(id, new Fornecedor(obj1, forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP(), forn.getValue().getAtividades()), Fornecedor.class);
                        case NOMEFANTASIA ->
                            DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), obj1, forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP(), forn.getValue().getAtividades()), Fornecedor.class);
                        case CNPJ ->
                            DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), obj1, forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP(), forn.getValue().getAtividades()), Fornecedor.class);
                        case EMAIL ->
                            DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), obj1, forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP(), forn.getValue().getAtividades()), Fornecedor.class);
                        case INSC_ESTADUAL ->
                            DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), obj1, forn.getValue().getTelefone(), forn.getValue().getInfoCEP(), forn.getValue().getAtividades()), Fornecedor.class);
                        case TELEFONE ->
                            DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), obj1, forn.getValue().getInfoCEP(), forn.getValue().getAtividades()), Fornecedor.class);
                        case CEP ->
                            DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), CEP.responseCEP(obj1, forn.getValue().getInfoCEP().getNum()), forn.getValue().getAtividades()), Fornecedor.class);
                        case NUMFORN ->
                            DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), CEP.responseCEP(forn.getValue().getInfoCEP().getCEP().replace("-", ""), Integer.parseInt(obj1)), forn.getValue().getAtividades()), Fornecedor.class);
                        case ATIVIDADES -> {

                            genericSet<Grupos> list = forn.getValue().getAtividades();
                            genericSet<Grupos> listAtividades = !(obj2.isEmpty())
                                ? updtAtividades(obj1, obj2, list)
                                : fornAtividades(String.valueOf(obj));//isrtAtividades(obj1);

                            DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP(), listAtividades), Fornecedor.class);
                        }
                        default -> {
                        }
                    }
                });
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de fornecedores está vazia.";
    }

    /**
     * Remover um fornecedor
     */
    public void remoFornecedor(Integer id) {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            if (DS.select(Fornecedor.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {

                DS.select(Fornecedor.class).remove(id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de fornecedores está vazia.";
    }

    /**
     * Localizar um fornecedor
     */
    public void findFornecedor(Integer id) {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            if (DS.select(Fornecedor.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();

                getForn.stream().filter(setid -> setid.getKey().equals(id)).forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de fornecedores está vazia.";
    }

    /**
     * Retorna um objeto Fornecedor pelo id
     */
    public Fornecedor fornecedorProd(Integer id) {
        Fornecedor forn = null;
        if (!DS.select(Fornecedor.class).isEmpty()) {
            if (DS.select(Fornecedor.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();

                forn = getForn.stream().filter(setid -> setid.getKey().equals(id)).findFirst().orElseThrow().getValue();
            } else System.out.println("\nRegistro inexistente na tabela.");
        } else System.out.println("\nA tabela de fornecedores está vazia.");

        return forn;
    }

    /**
     * Listar fornecedores por ids
     */
    public void listbyIdFornecedor(Integer ini_id, Integer fim_id) {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            if (DS.select(Fornecedor.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();

                getForn.stream().filter(id -> id.getKey() >= ini_id && id.getKey() <= fim_id).forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de fornecedores está vazia.";
    }

    /**
     * Remover fornecedores por ids
     */
    public void remobyIdFornecedor(Integer ini_id, Integer fim_id) {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            if (DS.select(Fornecedor.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();

                getForn.removeIf(id -> id.getKey() >= ini_id && id.getKey() <= fim_id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de fornecedores está vazia.";
    }

    /**
     * Imprimir fornecedores que estão na lista no momento
     */
    public boolean printMapWithSet() {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();

            getForn.forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            return true;
        } else {
            System.out.println("\nA tabela de fornecedores está vazia.");
            return false;
        }
    }

    /**
     * Retorna um novo id unico
     */
    public Integer nextId() {
        int maxnum;
        if (!DS.select(Fornecedor.class).isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();
            maxnum = getForn.stream().mapToInt(Map.Entry::getKey).max().getAsInt();
        } else maxnum = 0;

        return maxnum + 1;
    }

    /**
     * Verifica se o atributo atividade está ou não nulo
     */
    public boolean returnAtividades(Integer id) {
        Stream<Map.Entry<Integer, Fornecedor>>
                getForn = DS.select(Fornecedor.class).entrySet().stream().filter(x -> x.getKey().equals(id));
        return getForn.anyMatch(y -> y.getValue().getAtividades() == null);
    }

    /**
     * Método de apoio para atualizar as atividades do cadastro de fornecedores
     */
    public genericSet<Grupos> updtAtividades(String remove, String add, genericSet<Grupos> typeforn) {
        Grupos gpo = new Grupos();
        for (Grupos g : typeforn) {
            if (g.getGrupo().equals(remove))
                gpo = g;
        } typeforn.remove(gpo);

        gpo.setGrupo(add);
        typeforn.add(gpo);

        return typeforn;
    }

    /**
     * Método de apoio para inserir as atividades do cadastro de fornecedores
     */
    public genericSet<Grupos> fornAtividades(String... atividade) {
        genericSet<Grupos> lista = new genericSet<>();
        String[] list = null;
        for (String item : atividade) {
            list = item.trim().strip().split(", ");
        }

        assert list != null;
        for (String item : list) {
            lista.add(new Grupos(item.replaceAll("\\[", "").replaceAll("]", "")));
        }

        return lista;
    }

    /**
     * Valida se o id existe
     */
    public boolean userValid (Integer id) {
        boolean valid = false;
        if (!DS.select(Fornecedor.class).isEmpty()) {
            if (DS.select(Fornecedor.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {

                valid = true;
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de fornecedores está vazia.";

        return valid;
    }

    //#region rascunho
    /*
    //private Map<Integer, Fornecedor> tabFornecedor;

        //tabFornecedor.put(forn.setId(id), new Fornecedor(forn.getRazaoSocial(), forn.getNomeFantasia(), forn.getCnpj(), forn.getEmail(), forn.getInscEstadual(), forn.getTelefone(), forn.getInfoCEP(), forn.getAtividades()));

    public metodosFornecedor() {
        this.tabFornecedor = new HashMap<>();
    }

    public metodosFornecedor(metodosFornecedor forn) {
        this.tabFornecedor = forn.tabFornecedor;
    }

        private enum fieldForn {
        RAZAOSOCIAL, NOMEFANTASIA, CNPJ, EMAIL, INSC_ESTADUAL, TELEFONE, CEP, NUMFORN, ATIVIDADES;
    }



    public static void main(String[] args) {

        //Cliente cli = new Cliente();
        metodosFornecedor mcli = new metodosFornecedor();
        //smartTools.CEP CEP = new smartTools();

        System.out.println("# Lista de clientes #");
        mcli.setRazaoSocial("Jorge");
        mcli.setNomeFantasia("0000000000000");


        genericSet<Grupos> txt = new genericSet<>();
        txt.add(new Grupos("teste"));
        txt.add(new Grupos("teste2"));
        txt.add(new Grupos("teste3"));
        txt.add(new Grupos("teste4"));
        txt.add(new Grupos("teste5"));

        mcli.setAtividades(txt);
        mcli.setTelefone("00000000000");
        mcli.setInfoCEP(CEP.ResponseCEP("04472205", 47));
        mcli.novoFornecedor(1, mcli);

        mcli.PrintMapWithSet();
        System.out.println();

        System.out.println("# Alteração na lista de clientes #");
        mcli.alterFornecedor(1, "cnpj", "81251151");
        mcli.PrintMapWithSet();

        mcli.alterFornecedor(1, "atividades", "teste", "dardo");
        mcli.PrintMapWithSet();
        System.out.println();
    }
 */
    //#endregion
}

package Cadastro.Database.Metodos;

import Cadastro.Database.DataSet;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;
import Cadastro.NovosDados.Repositorio.DTO.Cliente;
import Cadastro.NovosDados.Repositorio.DTO.Fornecedor;
import Cadastro.NovosDados.Repositorio.Enums.camposForn;
import Raiz.Utils.SmartTools.*;
import Raiz.Utils.SmartTools.GenericCollects.*;

import java.util.*;

public class MetodosFornecedor extends Fornecedor {

    //private Map<Integer, Fornecedor> tabFornecedor;
    private final DataSet<Fornecedor> DS;

    /**
     * Construtor
     */
    /*
    public MetodosFornecedor() {
        this.tabFornecedor = new HashMap<>();
    }

    public MetodosFornecedor(MetodosFornecedor forn) {
        this.tabFornecedor = forn.tabFornecedor;
    }
     */

    @SuppressWarnings("unchecked")
    public MetodosFornecedor(DataSet<?> banco) {
        this.DS = (DataSet<Fornecedor>) banco;
    }

    /*
    private enum fieldForn {
        RAZAOSOCIAL, NOMEFANTASIA, CNPJ, EMAIL, INSC_ESTADUAL, TELEFONE, CEP, NUMFORN, ATIVIDADES;
    }
     */

    /**
     * Inserir novo fornecedor
     */
    public void novoFornecedor(Integer id, Fornecedor forn) {
        //tabFornecedor.put(forn.setId(id), new Fornecedor(forn.getRazaoSocial(), forn.getNomeFantasia(), forn.getCnpj(), forn.getEmail(), forn.getInscEstadual(), forn.getTelefone(), forn.getInfoCEP(), forn.getAtividades()));
        DS.insert(forn.setId(id), new Fornecedor(forn.getRazaoSocial(), forn.getNomeFantasia(), forn.getCnpj(), forn.getEmail(), forn.getInscEstadual(), forn.getTelefone(), forn.getInfoCEP(), forn.getAtividades()), Fornecedor.class);
    }

    /**
     * Alterar um fornecedor
     */
    public void alterFornecedor(Integer id, String Campo, String... update) {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            //fieldForn getCampo = fieldForn.valueOf(Campo.toUpperCase());
            camposForn getCampo = camposForn.valueOf(Campo.toUpperCase());

            Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();
            getForn.stream().filter(x -> x.getKey().equals(id)).forEach(forn -> {

                String obj1, obj2 = "";
                if (update.length < 2)
                    obj1 = update[0];
                else {
                    obj1 = update[0];
                    obj2 = update[1];
                }

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
                            DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), CEP.ResponseCEP(obj1, forn.getValue().getInfoCEP().getNum()), forn.getValue().getAtividades()), Fornecedor.class);
                    case NUMFORN ->
                            DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), CEP.ResponseCEP(forn.getValue().getInfoCEP().getCEP().replace("-", ""), Integer.parseInt(obj1)), forn.getValue().getAtividades()), Fornecedor.class);
                    case ATIVIDADES -> {

                        GenericSet<Grupos> list = forn.getValue().getAtividades();
                        GenericSet<Grupos> listAtividades = updtAtividades(obj1, obj2, list);

                        DS.insert(id, new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP(), listAtividades), Fornecedor.class);
                    }
                    default -> {
                    }
                }
            });
        } else
            System.out.println("A tabela de fornecedores está vazia.");
    }

    //#region processos

    /**
     * Remover um fornecedor
     */
    public void remoFornecedor(Integer id) {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            DS.select(Fornecedor.class).remove(id);
        } else
            System.out.println("A tabela de fornecedores está vazia.");
    }

    /**
     * Localizar um fornecedor
     */
    public void findFornecedor(Integer id) {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();
            getForn.stream().filter(setid -> setid.getKey().equals(id)).forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));

        } else
            System.out.println("A tabela de fornecedores está vazia.");
    }

    public Fornecedor fornecedorProd(Integer id) {
        Fornecedor forn = null;
        if (!DS.select(Fornecedor.class).isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();
            forn = getForn.stream().filter(setid -> setid.getKey().equals(id)).findFirst().orElseThrow().getValue();

        } else
            System.out.println("A tabela de fornecedores está vazia.");

        return forn;
    }

    /*
    if(!tabFornecedor.isEmpty()) {

    } else System.out.println("A tabela de fornecedores está vazia.");
*/

    /**
     * Listar fornecedores por ids
     */
    public void listbyIdFornecedor(Integer ini_id, Integer fim_id) {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();
            getForn.stream().filter(id -> id.getKey() >= ini_id && id.getKey() <= fim_id).forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
        } else
            System.out.println("A tabela de fornecedores está vazia.");
    }

    /**
     * Remover fornecedores por ids
     */
    public void remobyIdFornecedor(Integer ini_id, Integer fim_id) {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();
            getForn.removeIf(id -> id.getKey() >= ini_id && id.getKey() <= fim_id);
        } else
            System.out.println("A tabela de fornecedores está vazia.");
    }

    /**
     * Imprimir fornecedores que estão na lista no momento
     */
    public boolean PrintMapWithSet() {
        if (!DS.select(Fornecedor.class).isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = DS.select(Fornecedor.class).entrySet();
            getForn.forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            return true;
        } else {
            System.out.println("A tabela de fornecedores está vazia.");
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
        } else
            maxnum = 0;
        return maxnum + 1;
    }

    //#endregion

    public GenericSet<Grupos> updtAtividades(String remove, String add, GenericSet<Grupos> typeforn) {

        Grupos gp = null;
        for (Grupos g : typeforn) {
            if (g.getGrupo().equals(remove))
                gp = g;
        }
        typeforn.remove(gp);

        assert gp != null;
        gp.setGrupo(add);
        typeforn.add(gp);

        return typeforn;
    }

    public GenericSet<Grupos> fornAtividades(String... atividade) {

        GenericSet<Grupos> lista = new GenericSet<>();
        String[] lis = null;
        for (String item : atividade) {
            lis = item.trim().strip().split(", ");
        }

        assert lis != null;
        for (String x : lis) {
            lista.add(new Grupos(x));
        }
        return lista;
    }

/*
    public static void main(String[] args) {

        //Cliente cli = new Cliente();
        MetodosFornecedor mcli = new MetodosFornecedor();
        //SmartTools.CEP CEP = new SmartTools();

        System.out.println("# Lista de clientes #");
        mcli.setRazaoSocial("Jorge");
        mcli.setNomeFantasia("0000000000000");


        GenericSet<Grupos> txt = new GenericSet<>();
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
}

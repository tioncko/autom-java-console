package Database.Metodos;

import NovosDados.Repositorio.Cadastro.Fornecedor;
import Utils.MetodosUtils;

import java.util.*;

public class MetodosFornecedor extends Fornecedor {

    private final Map<Integer, Fornecedor> tabFornecedor;

    /**
     * Construtor
     */
    public MetodosFornecedor() {
        this.tabFornecedor = new HashMap<>();
    }

    private enum fieldForn {
        RAZAOSOCIAL, NOMEFANTASIA, CNPJ, EMAIL, INSC_ESTADUAL, INSC_MUNICIPAL,TELEFONE, CEP, NUMFORN;
    }

    /**
     * Inserir novo fornecedor
     */
    public void novoFornecedor(Integer id, Fornecedor forn){
        tabFornecedor.put(forn.setId(id), new Fornecedor(forn.getRazaoSocial(), forn.getNomeFantasia(), forn.getCnpj(), forn.getEmail(), forn.getInscEstadual(), forn.getInscMunicipal(), forn.getTelefone(), forn.getInfoCEP()));
    }

    /**
     * Alterar um fornecedor
     */
    public void alterFornecedor(Integer id, String Campo, String update){
        if(!tabFornecedor.isEmpty()){
            fieldForn getCampo = fieldForn.valueOf(Campo.toUpperCase());
            Set<Map.Entry<Integer, Fornecedor>> getForn = tabFornecedor.entrySet();
            getForn.stream()
                    .filter(x -> x.getKey().equals(id))
                    .forEach(forn -> {
                        switch (getCampo) {
                            case RAZAOSOCIAL -> tabFornecedor.put(id,
                                    new Fornecedor(update, forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getInscMunicipal(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP()));
                            case NOMEFANTASIA -> tabFornecedor.put(id,
                                    new Fornecedor(forn.getValue().getRazaoSocial(), update, forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getInscMunicipal(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP()));
                            case CNPJ -> tabFornecedor.put(id,
                                    new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), update, forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getInscMunicipal(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP()));
                            case EMAIL -> tabFornecedor.put(id,
                                    new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), update, forn.getValue().getInscEstadual(), forn.getValue().getInscMunicipal(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP()));
                            case INSC_ESTADUAL -> tabFornecedor.put(id,
                                    new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), update, forn.getValue().getInscMunicipal(), forn.getValue().getTelefone(), forn.getValue().getInfoCEP()));
                            case INSC_MUNICIPAL -> tabFornecedor.put(id,
                                    new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), update, forn.getValue().getTelefone(), forn.getValue().getInfoCEP()));
                            case TELEFONE -> tabFornecedor.put(id,
                                    new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getInscMunicipal(), update, forn.getValue().getInfoCEP()));
                            case CEP -> tabFornecedor.put(id,
                                    new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getInscMunicipal(), forn.getValue().getTelefone(), MetodosUtils.CEP.ResponseCEP(update, forn.getValue().getInfoCEP().getNum())));
                            case NUMFORN -> tabFornecedor.put(id,
                                    new Fornecedor(forn.getValue().getRazaoSocial(), forn.getValue().getNomeFantasia(), forn.getValue().getCnpj(), forn.getValue().getEmail(), forn.getValue().getInscEstadual(), forn.getValue().getInscMunicipal(), forn.getValue().getTelefone(), MetodosUtils.CEP.ResponseCEP(forn.getValue().getInfoCEP().getCEP().replace("-",""), Integer.parseInt(update))));
                            default -> {}
                        }
                    });
        } else System.out.println("A tabela de fornecedores está vazia.");
    }

    /**
     * Remover um fornecedor
     */
    public void remoFornecedor(Integer id){
        if(!tabFornecedor.isEmpty()) {
            tabFornecedor.remove(id);
        } else System.out.println("A tabela de fornecedores está vazia.");
    }

    /**
     * Localizar um fornecedor
     */
    public void findFornecedor(Integer id){
        if(!tabFornecedor.isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = tabFornecedor.entrySet();
            getForn.stream()
                    .filter(setid -> setid.getKey().equals(id))
                    .forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));

        } else System.out.println("A tabela de fornecedores está vazia.");
    }

    public Fornecedor fornecedorProd(Integer id){
        Fornecedor forn = null;
        if(!tabFornecedor.isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = tabFornecedor.entrySet();
            forn = getForn.stream()
                .filter(setid -> setid.getKey().equals(id))
                .findFirst().orElseThrow().getValue();

        } else System.out.println("A tabela de fornecedores está vazia.");

        return forn;
    }
/*
    if(!tabFornecedor.isEmpty()) {

    } else System.out.println("A tabela de fornecedores está vazia.");
*/
    /**
     * Listar fornecedores por ids
     */
    public void listbyIdFornecedor(Integer ini_id, Integer fim_id){
        if(!tabFornecedor.isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = tabFornecedor.entrySet();
            getForn.stream()
                    .filter(id -> id.getKey() >= ini_id && id.getKey() <= fim_id)
                    .forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
        } else System.out.println("A tabela de fornecedores está vazia.");
    }

    /**
     * Remover fornecedores por ids
     */
    public void remobyIdFornecedor(Integer ini_id, Integer fim_id){
        if(!tabFornecedor.isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = tabFornecedor.entrySet();
            getForn.removeIf(id -> id.getKey() >= ini_id && id.getKey() <= fim_id);
        } else System.out.println("A tabela de fornecedores está vazia.");
    }

    /**
     * Imprimir fornecedores que estão na lista no momento
     */
    public boolean PrintMapWithSet(){
        if(!tabFornecedor.isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = tabFornecedor.entrySet();
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
    public Integer nextId(){
        int maxnum;
        if(!tabFornecedor.isEmpty()) {
            Set<Map.Entry<Integer, Fornecedor>> getForn = tabFornecedor.entrySet();
            maxnum = getForn.stream().mapToInt(Map.Entry::getKey).max().getAsInt();
        } else maxnum = 0;
        return maxnum + 1;
    }
}

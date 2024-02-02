package Cadastro.Database.Metodos;

import Cadastro.Database.Metodos.Deserializers.jsonCEP;
import Cadastro.Database.dataSet;
import Cadastro.NovosDados.Repositorio.DTO.Funcionarios;
import Cadastro.NovosDados.Repositorio.Enums.Fields.camposFunc;

import java.util.Map;
import java.util.Set;

public class metodosFuncionarios extends Funcionarios {

    private final dataSet<Funcionarios> DS;
    public static String message;

    /**
     * Construtor
     */
    @SuppressWarnings("unchecked")
    public metodosFuncionarios(dataSet<?> banco) { this.DS = (dataSet<Funcionarios>) banco; }

    /**
     * Inserir novo funcionário
     */
    public void novoFuncionario(Integer id, Funcionarios func) {
        if (func.getDocumento() != null) {
            DS.insert(func.setId(id), new Funcionarios(func.getNome(), func.getIdade(), func.getGenero(), func.getDocumento(), func.getEmail(), func.getTelefone(), func.getInfoCEP(), func.getArea(), func.getDepartamento()), Funcionarios.class);
        } else message = "\nNão é possível realizar o cadastro do funcionário " + func.getNome() + ". Necessário informar o seu CPF.";
    }

    /**
     * Alterar um funcionário
     */
    public void alterFuncionario(Integer id, String Campo, String update) {
        if(!DS.select(Funcionarios.class).isEmpty()) {
            if(DS.select(Funcionarios.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {

                camposFunc getCampo = camposFunc.valueOf(Campo.toUpperCase());
                Set<Map.Entry<Integer, Funcionarios>> getFunc = DS.select(Funcionarios.class).entrySet();
                getFunc.stream().filter(x -> x.getKey().equals(id)).forEach(func -> {

                    switch (getCampo) {
                        case NOME ->
                                DS.insert(id, new Funcionarios(update, func.getValue().getIdade(), func.getValue().getGenero(), func.getValue().getDocumento(), func.getValue().getEmail(), func.getValue().getTelefone(), func.getValue().getInfoCEP(), func.getValue().getArea(), func.getValue().getDepartamento()), Funcionarios.class);
                        case IDADE ->
                                DS.insert(id, new Funcionarios(func.getValue().getNome(), Integer.parseInt(update), func.getValue().getGenero(), func.getValue().getDocumento(), func.getValue().getEmail(), func.getValue().getTelefone(), func.getValue().getInfoCEP(), func.getValue().getArea(), func.getValue().getDepartamento()), Funcionarios.class);
                        case GENERO ->
                                DS.insert(id, new Funcionarios(func.getValue().getNome(), func.getValue().getIdade(), update, func.getValue().getDocumento(), func.getValue().getEmail(), func.getValue().getTelefone(), func.getValue().getInfoCEP(), func.getValue().getArea(), func.getValue().getDepartamento()), Funcionarios.class);
                        case CPF ->
                                DS.insert(id, new Funcionarios(func.getValue().getNome(), func.getValue().getIdade(), func.getValue().getGenero(), update, func.getValue().getEmail(), func.getValue().getTelefone(), func.getValue().getInfoCEP(), func.getValue().getArea(), func.getValue().getDepartamento()), Funcionarios.class);
                        case EMAIL ->
                                DS.insert(id, new Funcionarios(func.getValue().getNome(), func.getValue().getIdade(), func.getValue().getGenero(), func.getValue().getDocumento(), update, func.getValue().getTelefone(), func.getValue().getInfoCEP(), func.getValue().getArea(), func.getValue().getDepartamento()), Funcionarios.class);
                        case TELEFONE ->
                                DS.insert(id, new Funcionarios(func.getValue().getNome(), func.getValue().getIdade(), func.getValue().getGenero(), func.getValue().getDocumento(), func.getValue().getEmail(), update, func.getValue().getInfoCEP(), func.getValue().getArea(), func.getValue().getDepartamento()), Funcionarios.class);
                        case CEP ->
                                DS.insert(id, new Funcionarios(func.getValue().getNome(), func.getValue().getIdade(), func.getValue().getGenero(), func.getValue().getDocumento(), func.getValue().getEmail(), func.getValue().getTelefone(), jsonCEP.responseCEP(update, func.getValue().getInfoCEP().getNum()), func.getValue().getArea(), func.getValue().getDepartamento()), Funcionarios.class);
                        case NUMFUNC ->
                                DS.insert(id, new Funcionarios(func.getValue().getNome(), func.getValue().getIdade(), func.getValue().getGenero(), func.getValue().getDocumento(), func.getValue().getEmail(), func.getValue().getTelefone(), jsonCEP.responseCEP(func.getValue().getInfoCEP().getCEP().replace("-", ""), Integer.parseInt(update)), func.getValue().getArea(), func.getValue().getDepartamento()), Funcionarios.class);
                        case AREA ->
                                DS.insert(id, new Funcionarios(func.getValue().getNome(), func.getValue().getIdade(), func.getValue().getGenero(), func.getValue().getDocumento(), func.getValue().getEmail(), func.getValue().getTelefone(), func.getValue().getInfoCEP(), update, func.getValue().getDepartamento()), Funcionarios.class);
                        case DEPTO ->
                                DS.insert(id, new Funcionarios(func.getValue().getNome(), func.getValue().getIdade(), func.getValue().getGenero(), func.getValue().getDocumento(), func.getValue().getEmail(), func.getValue().getTelefone(), func.getValue().getInfoCEP(), func.getValue().getArea(), update), Funcionarios.class);
                        default -> {}
                    }
                });
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de funcionários está vazia.";
    }

    /**
     * Remover um funcionário
     */
    public void remoFuncionario(Integer id) {
        if (!DS.select(Funcionarios.class).isEmpty()) {
            if (DS.select(Funcionarios.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {

                DS.select(Funcionarios.class).remove(id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de funcionários está vazia.";
    }

    /**
     * Localizar um funcionário
     */
    public void findFuncionario(Integer id) {
        if (!DS.select(Funcionarios.class).isEmpty()) {
            if (DS.select(Funcionarios.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                Set<Map.Entry<Integer, Funcionarios>> getFunc = DS.select(Funcionarios.class).entrySet();

                getFunc.stream().filter(setid -> setid.getKey().equals(id)).forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de funcionários está vazia.";
    }

    /**
     * Listar funcionários por ids
     */
    public void listbyIdFuncionario(Integer ini_id, Integer fim_id) {
        if (!DS.select(Funcionarios.class).isEmpty()) {
            if (DS.select(Funcionarios.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Set<Map.Entry<Integer, Funcionarios>> getFunc = DS.select(Funcionarios.class).entrySet();

                getFunc.stream().filter(id -> id.getKey() >= ini_id && id.getKey() <= fim_id).forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de funcionários está vazia.";
    }

    /**
     * Remover funcionários por ids
     */
    public void remobyIdFuncionario(Integer ini_id, Integer fim_id) {
        if (!DS.select(Funcionarios.class).isEmpty()) {
            if (DS.select(Funcionarios.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Set<Map.Entry<Integer, Funcionarios>> getFunc = DS.select(Funcionarios.class).entrySet();

                getFunc.removeIf(id -> id.getKey() >= ini_id && id.getKey() <= fim_id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de funcionários está vazia.";
    }

    /**
     * Imprimir funcionários que estão na lista no momento
     */
    public boolean printMapWithSet() {
        if (!DS.select(Funcionarios.class).isEmpty()) {
            Set<Map.Entry<Integer, Funcionarios>> getFunc = DS.select(Funcionarios.class).entrySet();

            getFunc.forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            return true;
        } else {
            System.out.println("A tabela de funcionários está vazia.");
            return false;
        }
    }

    /**
     * Retorna um novo id unico
     */
    public Integer nextId() {
        int maxnum;
        if (!DS.select(Funcionarios.class).isEmpty()) {
            Set<Map.Entry<Integer, Funcionarios>> getFunc = DS.select(Funcionarios.class).entrySet();
            maxnum = getFunc.stream().mapToInt(Map.Entry::getKey).max().getAsInt();
        } else maxnum = 0;

        return maxnum + 1;
    }

    /**
     * Valida se o id existe
     */
    public boolean userValid (Integer id) {
        boolean valid = false;
        if (!DS.select(Funcionarios.class).isEmpty()) {
            if (DS.select(Funcionarios.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {

                valid = true;
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de funcionários está vazia.";

        return valid;
    }
}

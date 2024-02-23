package Cadastro.Database.Metodos;

import Cadastro.Database.dataSet;
import Cadastro.NovosDados.Repositorio.Auxiliar.Marcas;
import Cadastro.NovosDados.Repositorio.DTO.Carros;
import Cadastro.NovosDados.Repositorio.DTO.Clientes;
import Cadastro.NovosDados.Repositorio.Enums.Fields.camposCarro;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class metodosCarros extends Carros {

    private final dataSet<Carros> DS;
    public static String message;

    /**
     * Construtor
     */
    @SuppressWarnings("unchecked")
    public metodosCarros(dataSet<?> banco) { this.DS = (dataSet<Carros>) banco; }

    /**
     * Inserir novo carro
     */
    public void novoCarro(Integer id, Carros car) {
        DS.insert(car.setId(id), new Carros(car.getNome(), car.getPlaca(), car.getOrigem(), car.getCliente(), car.getMarca()), Carros.class);
    }

    /**
     * Alterar um cliente
     */
    public void alterCarro(Integer id, String Campo, String update) {
        if(!DS.select(Carros.class).isEmpty()){
            if (DS.select(Carros.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                camposCarro getCampo = camposCarro.valueOf(Campo.toUpperCase());

                Stream<Map.Entry<Integer, Carros>> getCar = DS.select(Carros.class).entrySet().stream();
                getCar.filter(x -> x.getKey().equals(id)).forEach(car -> {
                    switch (getCampo) {
                        case MODELO -> DS.insert(id, new Carros(update, car.getValue().getPlaca(), car.getValue().getOrigem(), car.getValue().getCliente(), car.getValue().getMarca()), Carros.class);
                        case PLACA -> DS.insert(id, new Carros(car.getValue().getNome(), update, car.getValue().getOrigem(), car.getValue().getCliente(),car.getValue().getMarca()), Carros.class);
                        case ORIGEM -> DS.insert(id, new Carros(car.getValue().getNome(), car.getValue().getPlaca(), update, car.getValue().getCliente(),car.getValue().getMarca()), Carros.class);
                        case CLIENTE -> DS.insert(id, new Carros(car.getValue().getNome(), car.getValue().getPlaca(), car.getValue().getOrigem(), new Clientes(update), car.getValue().getMarca()), Carros.class);
                        case MARCA -> DS.insert(id, new Carros(car.getValue().getNome(), car.getValue().getPlaca(), car.getValue().getOrigem(), car.getValue().getCliente(),new Marcas(update)), Carros.class);
                        default -> {}
                    }
                });
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de carros está vazia.";
    }

    /**
     * Remover um cliente
     */
    public void remoCarro(Integer id) {
        if(!DS.select(Carros.class).isEmpty()) {
            if (DS.select(Carros.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                DS.select(Carros.class).remove(id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de carros está vazia.";
    }

    /**
     * Localizar um cliente
     */
    public void findCarro(Integer id) {
        if(!DS.select(Carros.class).isEmpty()) {
            if (DS.select(Carros.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                Set<Map.Entry<Integer, Carros>>
                        getCar = DS.select(Carros.class).entrySet();

                getCar.stream()
                        .filter(setid -> setid.getKey().equals(id))
                        .forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de carros está vazia.";
    }

    /**
     * Listar clientes por ids
     */
    public void listbyIdCarro(Integer ini_id, Integer fim_id) {
        if(!DS.select(Carros.class).isEmpty()) {
            if (DS.select(Carros.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Set<Map.Entry<Integer, Carros>>
                        getCar = DS.select(Carros.class).entrySet();

                getCar.stream()
                        .filter(id -> id.getKey() >= ini_id && id.getKey() <= fim_id)
                        .forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de carros está vazia.";
    }

    /**
     * Remover clientes por ids
     */
    public void remobyIdCarro(Integer ini_id, Integer fim_id) {
        if(!DS.select(Carros.class).isEmpty()) {
            if (DS.select(Carros.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Set<Map.Entry<Integer, Carros>>
                        getCar = DS.select(Carros.class).entrySet();

                getCar.removeIf(id -> id.getKey() >= ini_id && id.getKey() <= fim_id);
                //getCli.stream().forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de carros está vazia.";
    }


    /**
     * Imprimir clientes que estão na lista no momento
     */
    public boolean printMapWithSet() {
        if(!DS.select(Carros.class).isEmpty()) {
            Set<Map.Entry<Integer, Carros>> getCar = DS.select(Carros.class).entrySet();
            getCar.forEach(list -> System.out.println("id{" + list.getKey() + "}, " + list.getValue()));
            return true;
            }
        else {
            System.out.println("A tabela de carros está vazia.");
            return false;
        }
    }

    /**
     * Retorna um novo id unico
     */
    public Integer nextId() {
            Integer maxnum = null;
            if (!DS.select(Carros.class).isEmpty()) {
                Set<Map.Entry<Integer, Carros>> getCli = DS.select(Carros.class).entrySet();

                maxnum = getCli.stream().mapToInt(Map.Entry::getKey).max().getAsInt();
                //ou maxnum = getCli.stream().mapToInt(x -> x.getKey()).max().getAsInt();
            } else {
                maxnum = 0;
            }
            return maxnum + 1;
        }

    /**
     * Valida se o id existe
     */
    public boolean userValid (Integer id) {
        boolean valid = false;
        if (!DS.select(Carros.class).isEmpty()) {
            if (DS.select(Carros.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                valid = true;
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de carros está vazia.";

        return valid;
    }
}


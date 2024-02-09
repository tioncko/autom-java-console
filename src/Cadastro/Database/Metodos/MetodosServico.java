package Cadastro.Database.Metodos;

import Cadastro.Database.dataSet;
import Cadastro.Database.JSON.JsonTools.jsonExtraction.*;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Cadastro.NovosDados.Repositorio.DTO.Servicos;
import Cadastro.NovosDados.Repositorio.Enums.Fields.camposItens;
import Raiz.Core.impressaoLog;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class metodosServico extends Servicos {

    private final coletaJsonDados loja = new coletaJsonDados();
    private final dataSet<Servicos> DS;
    public static String message;

    impressaoLog.logGenerico<metodosServico> printLog = new impressaoLog.logGenerico<>();
    @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<metodosServico>) (Object) (metodosServico.class));

    /**
     * Construtor
     */
    @SuppressWarnings("unchecked")
    public metodosServico(dataSet<?> banco) {
        this.DS = (dataSet<Servicos>) banco;
    }

    /**
     * Inserir novo servico
     */
    public void novoServico(Integer id, Servicos serv) {
        DS.insert(serv.setId(id), new Servicos(serv.getNome(), serv.getPreco(), serv.getCategoria(), serv.getGrupo()), Servicos.class);
    }

    /**
     * Alterar um servico
     */
    public void alterServico(Integer id, String Campo, String update) {
        if(!DS.select(Servicos.class).isEmpty()){
            if (DS.select(Servicos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                camposItens getCampo = camposItens.valueOf(Campo.toUpperCase());
                Stream<Map.Entry<Integer, Servicos>>
                        getServ = DS.select(Servicos.class).entrySet().stream().filter(x -> x.getKey().equals(id));

                getServ.forEach(serv -> {
                        switch (getCampo) {
                            case DESCRICAO  -> DS.insert(id, new Servicos(update, serv.getValue().getPreco(), serv.getValue().getCategoria(), serv.getValue().getGrupo()), Servicos.class);
                            case PRECO      -> DS.insert(id, new Servicos(serv.getValue().getNome(), Double.parseDouble(update), serv.getValue().getCategoria(), serv.getValue().getGrupo()), Servicos.class);
                            case CATEGORIA  -> {
                                try {
                                    DS.insert(id, new Servicos(serv.getValue().getNome(), serv.getValue().getPreco(), loja.nomeCategoria(Integer.parseInt(update), Produtos.class), serv.getValue().getGrupo()), Servicos.class);
                                } catch (Exception e) {
                                    //System.out.println(e.getMessage());
                                    log.warning("[" + metodosServico.class.getSimpleName() + "] " + e.getMessage());
                                }
                            }
                            case GRUPO      -> {
                                try {
                                    DS.insert(id, new Servicos(serv.getValue().getNome(), serv.getValue().getPreco(), serv.getValue().getCategoria(), loja.nomeGrupo(Integer.parseInt(update), Produtos.class)), Servicos.class);
                                } catch (Exception e) {
                                    //System.out.println(e.getMessage());
                                    log.warning("[" + metodosServico.class.getSimpleName() + "] " + e.getMessage());
                                }
                            }
                        }
                    });
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de serviços está vazia.";
    }

    /**
     * Remover um servico
     */
    public void remoServico(Integer id) {
        if (!DS.select(Servicos.class).isEmpty()) {
            if (DS.select(Servicos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                DS.select(Servicos.class).remove(id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de serviços está vazia.";
    }

    /**
     * Localizar um servico
     */
    public void findServico(Integer id) {
        if (!DS.select(Servicos.class).isEmpty()) {
            if (DS.select(Servicos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                Stream<Map.Entry<Integer, Servicos>>
                        getServ = DS.select(Servicos.class).entrySet().stream().filter(setid -> setid.getKey().equals(id));

                getServ.forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de serviços está vazia.";
    }

    /**
     * Listar servicos por ids
     */
    public void listbyIdServico(Integer ini_id, Integer fim_id) {
        if (!DS.select(Servicos.class).isEmpty()) {
            if (DS.select(Servicos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Supplier<Stream<Map.Entry<Integer, Servicos>>>
                        getServ = () -> DS.select(Servicos.class).entrySet().stream().filter(id -> id.getKey() >= ini_id && id.getKey() <= fim_id);

            //int last = getCli.get().reduce((start, end) -> end).get().getKey();
            //if(last >= ini_id || last <= fim_id) {
                getServ.get().forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de serviços está vazia.";
    }

    /**
     * Remover servicos por ids
     */
    public void remobyIdServico(Integer ini_id, Integer fim_id) {
        if (!DS.select(Servicos.class).isEmpty()) {
            if (DS.select(Servicos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(ini_id))) {
                Set<Map.Entry<Integer, Servicos>>
                    getServ = DS.select(Servicos.class).entrySet();

                getServ.removeIf(id -> id.getKey() >= ini_id && id.getKey() <= fim_id);
            //getCli.stream().forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de serviços está vazia.";
    }

    /**
     * Retorna um novo id unico
     */
    public Integer nextId() {
        Integer maxnum = null;
        if (!DS.select(Servicos.class).isEmpty()) {
            Stream<Map.Entry<Integer, Servicos>> getServ = DS.select(Servicos.class).entrySet().stream();
            maxnum = getServ.mapToInt(Map.Entry::getKey).max().getAsInt();
        } else maxnum = 0;
        return maxnum + 1;
    }

    /**
     * Imprimir servicos que estão na lista no momento
     */
    public boolean printMapWithSet() {
        if (!DS.select(Servicos.class).isEmpty()) {
            Stream<Map.Entry<Integer, Servicos>> getServ = DS.select(Servicos.class).entrySet().stream();
            getServ.forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            return true;
        } else {
            System.out.println("A tabela de serviços está vazia.");
            return false;
        }
    }

    /**
     * Valida se o id existe
     */
    public boolean userValid (Integer id) {
        boolean valid = false;

        if (!DS.select(Servicos.class).isEmpty()) {
            if (DS.select(Servicos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                valid = true;
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de serviços está vazia.";

        return valid;
    }
}

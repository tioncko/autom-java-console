package Cadastro.Database.Metodos;

import Cadastro.Database.DataSet;
import Cadastro.Database.JSON.JsonTools.JsonExtraction.*;
import Cadastro.Database.JSON.JsonTools.JsonResponse;
import Cadastro.NovosDados.Repositorio.DTO.Fornecedor;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Cadastro.NovosDados.Repositorio.DTO.Servicos;
import Cadastro.NovosDados.Repositorio.Enums.camposItens;
import Raiz.Core.ImpressaoLog;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class MetodosServico extends Servicos{

    private final ColetaJsonDados loja = new ColetaJsonDados();
    private final DataSet<Servicos> DS;
    public static String message;

    ImpressaoLog.LogGenerico<MetodosServico> printLog = new ImpressaoLog.LogGenerico<>();
    @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<MetodosServico>) (Object) (MetodosServico.class));

    @SuppressWarnings("unchecked")
    public MetodosServico(DataSet<?> banco) {
        this.DS = (DataSet<Servicos>) banco;
    }

    public void novoServico(Integer id, Servicos serv) {
        DS.insert(serv.setId(id), new Servicos(serv.getNomeServ(), serv.getPreco(), serv.getCategoria(), serv.getGrupo()), Servicos.class);
    }

    public void alterServico(Integer id, String Campo, String update) {
        if(!DS.select(Servicos.class).isEmpty()){
            if (DS.select(Servicos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                camposItens getCampo = camposItens.valueOf(Campo.toUpperCase());
                Stream<Map.Entry<Integer, Servicos>>
                        getServ = DS.select(Servicos.class).entrySet().stream().filter(x -> x.getKey().equals(id));

                getServ.forEach(serv -> {
                        switch (getCampo) {
                            case DESCRICAO  -> DS.insert(id, new Servicos(update, serv.getValue().getPreco(), serv.getValue().getCategoria(), serv.getValue().getGrupo()), Servicos.class);
                            case PRECO      -> DS.insert(id, new Servicos(serv.getValue().getNomeServ(), Double.parseDouble(update), serv.getValue().getCategoria(), serv.getValue().getGrupo()), Servicos.class);
                            case CATEGORIA  -> {
                                try {
                                    DS.insert(id, new Servicos(serv.getValue().getNomeServ(), serv.getValue().getPreco(), loja.nomeCategoria(Integer.parseInt(update), Produtos.class), serv.getValue().getGrupo()), Servicos.class);
                                } catch (Exception e) {
                                    //System.out.println(e.getMessage());
                                    log.warning("[" + MetodosServico.class.getSimpleName() + "] " + e.getMessage());
                                }
                            }
                            case GRUPO      -> {
                                try {
                                    DS.insert(id, new Servicos(serv.getValue().getNomeServ(), serv.getValue().getPreco(), serv.getValue().getCategoria(), loja.nomeGrupo(Integer.parseInt(update), Produtos.class)), Servicos.class);
                                } catch (Exception e) {
                                    //System.out.println(e.getMessage());
                                    log.warning("[" + MetodosServico.class.getSimpleName() + "] " + e.getMessage());
                                }
                            }
                        }
                    });
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de serviços está vazia.";
    }

    public void remoServico(Integer id) {
        if (!DS.select(Servicos.class).isEmpty()) {
            if (DS.select(Servicos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                DS.select(Servicos.class).remove(id);
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de serviços está vazia.";
    }

    public void findServico(Integer id) {
        if (!DS.select(Servicos.class).isEmpty()) {
            if (DS.select(Servicos.class).entrySet().stream().anyMatch(x -> x.getKey().equals(id))) {
                Stream<Map.Entry<Integer, Servicos>>
                        getServ = DS.select(Servicos.class).entrySet().stream().filter(setid -> setid.getKey().equals(id));

                getServ.forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            } else message = "\nRegistro inexistente na tabela.";
        } else message = "\nA tabela de serviços está vazia.";
    }

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

    public Integer nextId() {
        Integer maxnum = null;
        if (!DS.select(Servicos.class).isEmpty()) {
            Stream<Map.Entry<Integer, Servicos>> getServ = DS.select(Servicos.class).entrySet().stream();
            maxnum = getServ.mapToInt(Map.Entry::getKey).max().getAsInt();
        } else maxnum = 0;
        return maxnum + 1;
    }

    public boolean PrintMapWithSet() {
        if (!DS.select(Servicos.class).isEmpty()) {
            Stream<Map.Entry<Integer, Servicos>> getServ = DS.select(Servicos.class).entrySet().stream();
            getServ.forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
            return true;
        } else {
            System.out.println("\nA tabela de serviços está vazia.");
            return false;
        }
    }

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

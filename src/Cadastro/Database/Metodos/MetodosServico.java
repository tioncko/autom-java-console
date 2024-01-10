package Cadastro.Database.Metodos;

import Cadastro.Database.DataSet;
import Cadastro.Database.JSON.JsonTools.JsonExtraction.*;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Cadastro.NovosDados.Repositorio.DTO.Servicos;

import java.util.Map;
import java.util.Set;

public class MetodosServico extends Servicos{

    private final ColetaJsonDados loja = new ColetaJsonDados();
    private final DataSet<Servicos> DS;

    @SuppressWarnings("unchecked")
    public MetodosServico(DataSet<?> banco) {
        this.DS = (DataSet<Servicos>) banco;
    }

    public void novoServico(Integer id, Servicos serv) {
        DS.insert(serv.setId(id), new Servicos(serv.getNomeServ(), serv.getPreco(), serv.getCategoria(), serv.getGrupo()), Servicos.class);
    }

    public Integer nextId() {

        Integer maxnum = null;
        if (!DS.select(Servicos.class).isEmpty()) {
            Set<Map.Entry<Integer, Servicos>>
                    getCli = DS.select(Servicos.class).entrySet();

            maxnum = getCli.stream().mapToInt(Map.Entry::getKey).max().getAsInt();
            //ou maxnum = getCli.stream().mapToInt(x -> x.getKey()).max().getAsInt();
        } else {
            maxnum = 0;
        }
        return maxnum + 1;
    }

    public boolean PrintMapWithSet() {
        if (!DS.select(Servicos.class).isEmpty()) {
            Set<Map.Entry<Integer, Servicos>>
                    entries = DS.select(Servicos.class).entrySet();

            for (Map.Entry<Integer, Servicos> entry : entries) {
                Integer key = entry.getKey();
                Servicos cli = entry.getValue();
                System.out.println("id{" + key + "}, " + cli);
            }
            return true;
        } else {
            System.out.println("A tabela de cliente está vazia.");
            return false;
        }
    }
}

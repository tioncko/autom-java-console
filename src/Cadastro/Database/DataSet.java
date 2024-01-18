package Cadastro.Database;

import Cadastro.Database.JSON.JsonTools.jsonExtraction;
import Cadastro.NovosDados.Repositorio.DTO.*;
import Raiz.Core.impressaoLog;
import Raiz.Utils.leitorDados;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class dataSet<T> extends leitorDados {

    impressaoLog.logGenerico<dataSet<T>> printLog = new impressaoLog.logGenerico<>();
    @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<dataSet<T>>) (Object) (dataSet.class));

    //#region rascunho
    /*
    private final Map<Integer, T> tabValue;
    public dataSet() { this.tabValue = new HashMap<>();
    }

    public void addNewItem(Integer id, T value) {
        tabValue.put(id, value);
    }

    public Map<Integer, T> getTabValue() {
        return tabValue;
    }
     */
    //#endregion

    private final Map<Integer, Cliente> tabCliente;
    private final Map<Integer, Fornecedor> tabForn;
    private final Map<Integer, Usuario> tabUsuario;
    private final Map<Integer, Produtos> tabProduto;
    private final Map<Integer, Servicos> tabServico;

    public dataSet() {
        this.tabCliente = new HashMap<>();

        rootAccess ac = new rootAccess();
        this.tabUsuario = new HashMap<>(ac.givePermission());//new HashMap<>();

        jsonExtraction.coletaJsonDados json = new jsonExtraction.coletaJsonDados();
        this.tabForn = new HashMap<>(json.mapForn());
        this.tabProduto = new HashMap<>(json.mapProd());//new HashMap<>();
        this.tabServico = new HashMap<>(json.mapServ());
    }

    /**
     * Insere os dados no banco de dados
     */
    public void insert(Integer id, T value, Class<T> classe) {
        try {
            T instance = getClassNewInstance(classe);
            switch (instance.getClass().getSimpleName()) {
                case "Cliente":
                    tabCliente.put(id, (Cliente) value);
                    break;
                case "Fornecedor":
                    tabForn.put(id, (Fornecedor) value);
                    break;
                case "Usuario":
                    tabUsuario.put(id, (Usuario) value);
                    break;
                case "Produtos":
                    tabProduto.put(id, (Produtos) value);
                    break;
                case "Servicos":
                    tabServico.put(id, (Servicos) value);
                    break;
            }
        } catch (Exception e) {
            log.warning("[" + dataSet.class.getSimpleName() + "] " + e.getMessage());
        }
    }

    /**
     * Retorna os dados para manipulação das classes deste projeto
     */
    @SuppressWarnings("unchecked")
    public Map<Integer, T> select(Class<T> classe) {
        try {
            T instance = getClassNewInstance(classe);
            return switch (instance.getClass().getSimpleName()) {
                case "Cliente" -> (Map<Integer, T>) tabCliente;
                case "Fornecedor" -> (Map<Integer, T>) tabForn;
                case "Usuario" -> (Map<Integer, T>) tabUsuario;
                case "Produtos" -> (Map<Integer, T>) tabProduto;
                case "Servicos" -> (Map<Integer, T>) tabServico;
                default -> null;
            };
        } catch (Exception e) {
            log.warning("[" + dataSet.class.getSimpleName() + "] " + e.getMessage());
        }
        return new HashMap<>();
    }
}

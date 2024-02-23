package Cadastro.Database;

import Cadastro.Database.JSON.JsonTools.jsonExtraction.*;
import Cadastro.NovosDados.Repositorio.DTO.*;
import Raiz.Core.impressaoLog;
import Raiz.Utils.leitorDados;

import java.time.LocalTime;
import java.util.Date;
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

    private final Map<Integer, Clientes> tabCliente;
    private final Map<Integer, Fornecedores> tabForn;
    private final Map<Integer, Usuarios> tabUsuario;
    private final Map<Integer, Produtos> tabProduto;
    private final Map<Integer, Servicos> tabServico;
    private final Map<Integer, Funcionarios> tabFunc;
    private final Map<Integer, Carros> tabCarro;

    public dataSet() {
        //rootAccess ac = new rootAccess();
        coletaJsonDados json = new coletaJsonDados();

        System.out.println("Carregando sistema....");
        this.tabCliente = new HashMap<>(json.mapCli());
        this.tabFunc = new HashMap<>(json.mapFunc());
        this.tabUsuario = new HashMap<>(json.mapUsr()); //(ac.givePermission());//new HashMap<>();
        this.tabProduto = new HashMap<>(json.mapProd());//new HashMap<>();

        System.out.println("Finalizando carregamento do sistema....");
        this.tabForn = new HashMap<>(json.mapForn());
        this.tabServico = new HashMap<>(json.mapServ());
        this.tabCarro = new HashMap<>();

        System.out.println("Iniciando sistema....");
        System.out.print("\033[2J\033[1;1H");
    }
//camposForn getCampo = camposForn.valueOf(Campo.toUpperCase());
    /**
     * Insere os dados no banco de dados
     */
    public void insert(Integer id, T value, Class<T> classe) {
        try {
            T instance = getClassNewInstance(classe);
            switch (instance.getClass().getSimpleName()) {
                case "Clientes":
                    tabCliente.put(id, (Clientes) value);
                    break;
                case "Fornecedores":
                    tabForn.put(id, (Fornecedores) value);
                    break;
                case "Usuarios":
                    tabUsuario.put(id, (Usuarios) value);
                    break;
                case "Produtos":
                    tabProduto.put(id, (Produtos) value);
                    break;
                case "Servicos":
                    tabServico.put(id, (Servicos) value);
                    break;
                case "Funcionarios":
                    tabFunc.put(id, (Funcionarios) value);
                    break;
                case "Carros":
                    tabCarro.put(id, (Carros) value);
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
                case "Clientes" -> (Map<Integer, T>) tabCliente;
                case "Fornecedores" -> (Map<Integer, T>) tabForn;
                case "Usuarios" -> (Map<Integer, T>) tabUsuario;
                case "Produtos" -> (Map<Integer, T>) tabProduto;
                case "Servicos" -> (Map<Integer, T>) tabServico;
                case "Funcionarios" -> (Map<Integer, T>) tabFunc;
                case "Carros" -> (Map<Integer, T>) tabCarro;
                default -> null;
            };
        } catch (Exception e) {
            log.warning("[" + dataSet.class.getSimpleName() + "] " + e.getMessage());
        }
        return new HashMap<>();
    }
}

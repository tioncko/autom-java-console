package Cadastro.Database;

import Cadastro.Database.JSON.JsonTools.JsonExtraction;
import Cadastro.NovosDados.Repositorio.DTO.*;
import Raiz.Utils.LeitorDados;

import java.util.HashMap;
import java.util.Map;

public class DataSet<T> extends LeitorDados {
    /*
    private final Map<Integer, T> tabValue;
    public DataSet() { this.tabValue = new HashMap<>();
    }

    public void addNewItem(Integer id, T value) {
        tabValue.put(id, value);
    }

    public Map<Integer, T> getTabValue() {
        return tabValue;
    }
     */

    private final Map<Integer, Cliente> tabCliente;
    private final Map<Integer, Fornecedor> tabForn;
    private final Map<Integer, Usuario> tabUsuario;
    private final Map<Integer, Produtos> tabProduto;
    private final Map<Integer, Servicos> tabServico;

    public DataSet() {
        this.tabCliente = new HashMap<>();

        AdminAccess ac = new AdminAccess();
        this.tabUsuario = new HashMap<>(ac.givePermission());//new HashMap<>();

        JsonExtraction.ColetaJsonDados json = new JsonExtraction.ColetaJsonDados();
        this.tabForn = new HashMap<>(json.MapForn());
        this.tabProduto = new HashMap<>(json.MapProd());//new HashMap<>();
        this.tabServico = new HashMap<>(json.MapServ());
    }

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
            System.out.println(e.getMessage());
        }
    }

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
            System.out.println(e.getMessage());
        }
        return new HashMap<>();
    }
}

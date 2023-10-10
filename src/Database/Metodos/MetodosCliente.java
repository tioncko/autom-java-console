package Database.Metodos;

import NovosDados.Repositorio.Cliente;

import java.util.*;

public class MetodosCliente extends Cliente {

    private final Map<Integer, Cliente> tabCliente;

    /**
     * Construtor
     */
    public MetodosCliente() {
        this.tabCliente = new HashMap<Integer, Cliente>();
    }

    /**
     * Inserir novo cliente
     */
    public void novoCliente(Integer id, Cliente cli) {
        tabCliente.put(cli.setId(id), new Cliente(cli.getNome(), cli.getIdade(), cli.getCpf(), cli.getEmail(), cli.getTelefone(), cli.getInfoCEP()));
    }

    /**
     * Alterar um cliente
     */
    // PENDENTE ALTERAÇÃO DE CEP OU NUMERO DA CASA
    public void alterCliente(Integer id, String Campo, String update) {
        if (!tabCliente.isEmpty()) {
            Set<Map.Entry<Integer, Cliente>>
                    getCli = tabCliente.entrySet();

            for (Map.Entry<Integer, Cliente> setCli : getCli) {
                Cliente cli = setCli.getValue();

                if (setCli.getKey().equals(id)) {
                    switch (Campo) {
                        case "Nome":
                            tabCliente.put(id, new Cliente(update, cli.getIdade(), cli.getCpf(), cli.getEmail(), cli.getTelefone(), cli.getInfoCEP()));
                            break;
                        case "Idade":
                            tabCliente.put(id, new Cliente(cli.getNome(), Integer.parseInt(update), cli.getCpf(), cli.getEmail(), cli.getTelefone(), cli.getInfoCEP()));
                            break;
                        case "CPF":
                            tabCliente.put(id, new Cliente(cli.getNome(), cli.getIdade(), update, cli.getEmail(), cli.getTelefone(), cli.getInfoCEP()));
                            break;
                        case "E-mail":
                            tabCliente.put(id, new Cliente(cli.getNome(), cli.getIdade(), cli.getCpf(), update, cli.getTelefone(), cli.getInfoCEP()));
                            break;
                        case "Telefone":
                            tabCliente.put(id, new Cliente(cli.getNome(), cli.getIdade(), cli.getCpf(), cli.getEmail(), update, cli.getInfoCEP()));
                            break;
                        default:
                            break;
                    }
                }
            }
        } else {
            System.out.println("A tabela de cliente está vazia.");
        }
    }

    /**
     * Remover um cliente
     */
    public void remoCliente(Integer id) {
        if (!tabCliente.isEmpty()) {
            tabCliente.remove(id);
        } else {
            System.out.println("A tabela de cliente está vazia.");
        }
    }

    /**
     * Localizar um cliente
     */
    public void findCliente(Integer id) {
        if (!tabCliente.isEmpty()) {
            Set<Map.Entry<Integer, Cliente>>
                    getCli = tabCliente.entrySet();

            getCli.stream()
                    .filter(setid -> setid.getKey().equals(id))
                    .forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
        } else {
            System.out.println("A tabela de cliente está vazia.");
        }
    }

    /**
     * Listar clientes por ids
     */
    public void listbyIdCliente(Integer ini_id, Integer fim_id) {
        if (!tabCliente.isEmpty()) {
            Set<Map.Entry<Integer, Cliente>>
                    getCli = tabCliente.entrySet();

            getCli.stream()
                    .filter(id -> id.getKey() >= ini_id && id.getKey() <= fim_id)
                    .forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));

        } else {
            System.out.println("A tabela de cliente está vazia.");
        }
    }

    /**
     * Remover clientes por ids
     */
    public void remobyIdCliente(Integer ini_id, Integer fim_id) {
        if (!tabCliente.isEmpty()) {
            Set<Map.Entry<Integer, Cliente>>
                    getCli = tabCliente.entrySet();

            getCli.removeIf(id -> id.getKey() >= ini_id && id.getKey() <= fim_id);
            //getCli.stream().forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));
        } else {
            System.out.println("A tabela de cliente está vazia.");
        }
    }

    /**
     * Imprimir clientes que estão na lista no momento
     */
    public void PrintMapWithSet() {
        if (!tabCliente.isEmpty()) {
            Set<Map.Entry<Integer, Cliente>>
                    entries = tabCliente.entrySet();

            for (Map.Entry<Integer, Cliente> entry : entries) {
                Integer key = entry.getKey();
                Cliente cli = entry.getValue();
                System.out.println("id{" + key + "}, " + cli);
            }
        } else {
            System.out.println("A tabela de cliente está vazia.");
        }
    }

    /**
     * Retorna um novo id unico
     */
    public Integer nextId() {

        Integer maxnum = null;
        if (!tabCliente.isEmpty()) {
            Set<Map.Entry<Integer, Cliente>>
                    getCli = tabCliente.entrySet();

            maxnum = getCli.stream().mapToInt(Map.Entry::getKey).max().getAsInt();
            //ou maxnum = getCli.stream().mapToInt(x -> x.getKey()).max().getAsInt();
        } else {
            maxnum = 0;
        }
        return maxnum + 1;
    }

//#region notes
/*
    public static void main(String[] args) {

        //Cliente cli = new Cliente();
        MetodosCliente mcli = new MetodosCliente();
        MetodosUtils Utils = new MetodosUtils();
        MetodosUtils.CEP CEP = Utils.new CEP();

        System.out.println("# Lista de clientes #");
        mcli.setNome("Jorge");
        mcli.setCpf("0000000000000");
        mcli.setIdade(00);
        mcli.setEmail("teste@gmail.com");
        mcli.setTelefone("00000000000");
        mcli.setInfoCEP(CEP.ResponseCEP("04472205", 47));
        mcli.novoCliente(1, mcli);
        //System.out.println(MetodosUtils.ResponseCEP("04472205", 47).toString());

        mcli.setNome("Pedro");
        mcli.setCpf("0000000000000");
        mcli.setIdade(00);
        mcli.setEmail("teste@gmail.com");
        mcli.setTelefone("00000000000");
        mcli.setInfoCEP(CEP.ResponseCEP("04915020", 47));
        mcli.novoCliente(2, mcli);
        //System.out.println(MetodosUtils.ResponseCEP("04915020", 47).toString());

        mcli.setNome("Deise");
        mcli.setCpf("0000000000000");
        mcli.setIdade(00);
        mcli.setEmail("teste@gmail.com");
        mcli.setTelefone("00000000000");
        mcli.setInfoCEP(CEP.ResponseCEP("09910060", 47));
        mcli.novoCliente(3, mcli);
        //System.out.println(MetodosUtils.ResponseCEP("09910060", 47).toString());

        mcli.setNome("Carla");
        mcli.setCpf("0000000000000");
        mcli.setIdade(00);
        mcli.setEmail("teste@gmail.com");
        mcli.setTelefone("00000000000");
        mcli.setInfoCEP(CEP.ResponseCEP("04472205", 47));
        mcli.novoCliente(4, mcli);
        //System.out.println(MetodosUtils.ResponseCEP("09910060", 47).toString());

        mcli.setNome("Keyla");
        mcli.setCpf("0000000000000");
        mcli.setIdade(00);
        mcli.setEmail("teste@gmail.com");
        mcli.setTelefone("00000000000");
        mcli.setInfoCEP(CEP.ResponseCEP("09910250", 47));
        mcli.novoCliente(5, mcli);
        //System.out.println(MetodosUtils.ResponseCEP("09910060", 47).toString());

        mcli.PrintMapWithSet();
        System.out.println();

        System.out.println("# Alteração na lista de clientes #");
        mcli.alterCliente(4, "Nome", "Nayara");
        mcli.alterCliente(2, "E-mail", "trycatch@gmail.com");
        mcli.alterCliente(5, "CPF", "81628784598");
        mcli.alterCliente(3, "Telefone", "15945482154");
        mcli.alterCliente(1, "Idade", "57");
        mcli.PrintMapWithSet();
        System.out.println();

        System.out.println("# Remover na lista de clientes #");
        mcli.remoCliente(1);
        mcli.PrintMapWithSet();
        System.out.println();

        System.out.println("# Localizar na lista de clientes por id #");
        mcli.findCliente(4);
        System.out.println();

        System.out.println("# Listar por ids na lista de clientes #");
        mcli.listbyIdCliente(2, 4);
        System.out.println();

        System.out.println("# Remover por ids na lista de clientes #");
        mcli.remobyIdCliente(1, 3);
        mcli.PrintMapWithSet();

        System.out.println(mcli.nextId());
    }

*/
    //#endregion

}

//#region notes
/*

    public void PrintMapInLine(){
        System.out.println(tabCliente);
    }

    public static void main(String[] args) {

        Cliente cli = new Cliente();
        MetodosCliente mcli = new MetodosCliente();

        cli.setId(1);
        cli.setNome("Jorge");
        cli.setCpf("12345678901");
        cli.setIdade(24);
        cli.setEmail("teste@gmail.com");
        cli.setTelefone("11959595959");
        mcli.novoCliente(cli);

        System.out.println(cli.ResponseCEP("04472205", 47).toString());
        System.out.println(cli.toString());;
    }


    public void PrintWithMap()
    {
        for(Map.Entry<Integer, Cliente> printPairs: tabCliente.entrySet()) {
            System.out.print(printPairs.getKey() + " " + printPairs.getValue() + "\n");
        }
    }

        public void findCliente(Integer id) {
        Set<Map.Entry<Integer, Cliente>>
                getCli = tabCliente.entrySet();

        getCli.stream()
                .filter(setid -> setid.getKey().equals(id))
                .forEach(x -> System.out.println("id{" + x.getKey() + "}, " + x.getValue()));

        for (Map.Entry<Integer, Cliente> setCli : getCli) {
            if (setCli.getKey().equals(id)) {
                System.out.println("id{" + id + "}, " + tabCliente.get(id));
            }
        }
    }
*/
//#endregion


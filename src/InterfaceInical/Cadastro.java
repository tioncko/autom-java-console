package InterfaceInical;

import Database.Metodos.*;
import Utils.MetodosUtils;

public class Cadastro{

    MetodosCliente novoCli;

    public Cadastro() {
        this.novoCli = new MetodosCliente();
    }

    /**
     * Classe interna para as Ações do cliente
     */
    public class AcoesCliente {
        public void cadastrarCliente(String nome, int idade, String cpf, String email, String telefone, String CEP, int numCasa) {
            novoCli.setNome(nome);
            novoCli.setCpf(cpf);
            novoCli.setIdade(idade);
            novoCli.setEmail(email);
            novoCli.setTelefone(telefone);
            novoCli.setInfoCEP(MetodosUtils.CEP.ResponseCEP(CEP, numCasa));

            novoCli.novoCliente(novoCli.nextId(), novoCli);
        }

        public void alterarCliente(Integer id, String campo, String update) {
            novoCli.alterCliente(id, campo, update);
        }

        public void excluirCliente(Integer id) {
            novoCli.remoCliente(id);
        }

        public void localizarCliente(Integer id) {
            novoCli.findCliente(id);
        }

        public void localizarMaisClientes(Integer ini, Integer fim) {
            novoCli.listbyIdCliente(ini, fim);
        }

        public void removerMaisClientes(Integer ini, Integer fim) {
            novoCli.remobyIdCliente(ini, fim);
        }

        public void listarClientes() {
            novoCli.PrintMapWithSet();
        }
    }
}

package InterfaceInical;

import Database.DataTables;
import Database.Metodos.*;
import Database.InterfaceCRUD;
import Utils.MetodosUtils;

public class Cadastro implements InterfaceCRUD {

    MetodosCliente novoCli;
    MetodosUsuario novoUsr;
    DataTables DT = new DataTables();

    public Cadastro() throws Exception {
        this.novoCli = new MetodosCliente();
        this.novoUsr = new MetodosUsuario(DT.DTUsers());
    }

    /**
     * Classe interna para as Ações do cliente
     */
    public class AcoesCliente implements ICliente {
        @Override
        public void cadastrarCliente(String nome, int idade, String cpf, String email, String telefone, String CEP, int numCasa) {
            novoCli.setNome(nome);
            novoCli.setCpf(cpf);
            novoCli.setIdade(idade);
            novoCli.setEmail(email);
            novoCli.setTelefone(telefone);
            novoCli.setInfoCEP(MetodosUtils.CEP.ResponseCEP(CEP, numCasa));

            novoCli.novoCliente(novoCli.nextId(), novoCli);
        }

        @Override
        public void alterarCliente(Integer id, String campo, String update) {
            novoCli.alterCliente(id, campo, update);
        }

        @Override
        public void excluirCliente(Integer id) {
            novoCli.remoCliente(id);
        }

        @Override
        public void localizarCliente(Integer id) {
            novoCli.findCliente(id);
        }

        @Override
        public void localizarMaisClientes(Integer ini, Integer fim) {
            novoCli.listbyIdCliente(ini, fim);
        }

        @Override
        public void removerMaisClientes(Integer ini, Integer fim) {
            novoCli.remobyIdCliente(ini, fim);
        }

        @Override
        public void listarClientes() {
            novoCli.PrintMapWithSet();
        }

    }

    /**
     * Classe interna para as Ações do usuário
     */
    public class AcoesUsuario implements IUsers{
        @Override
        public void cadastrarUsuario(String login, String pass, String nome, String depto) throws Exception{
            novoUsr.setLogin(login);
            novoUsr.setPassword(MetodosUtils.Senha.Encrypt(pass));
            novoUsr.setNome(nome);
            novoUsr.setDepto(depto);

            novoUsr.novoUsuario(novoUsr.nextId(), novoUsr);
        }

        @Override
        public void alterarUsuario(Integer id, String campo, String update) throws Exception {
            novoUsr.alterUsuario(id, campo, update);
        }

        @Override
        public void excluirUsuario(Integer id) {
            novoUsr.remoUsuario(id);
        }

        @Override
        public void localizarUsuario(Integer id) {
            novoUsr.findUsuario(id);
        }

        @Override
        public void localizarMaisUsuarios(Integer ini, Integer fim) {
            novoUsr.listbyIdUsuario(ini, fim);
        }

        @Override
        public void removerMaisUsuarios(Integer ini, Integer fim) {
            novoUsr.remobyIdUsuario(ini, fim);
        }

        @Override
        public void listarUsuario() {
            novoUsr.PrintMapWithSet();
        }
    }
}

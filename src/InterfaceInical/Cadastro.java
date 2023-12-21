package InterfaceInical;

import Database.DTO;
import Database.Metodos.*;
import Database.INovosDados;
import Utils.MetodosUtils;

public class Cadastro implements INovosDados {

    MetodosCliente novoCli;
    MetodosUsuario novoUsr;
    MetodosFornecedor novoFrn;
    DTO DT = new DTO();

    public Cadastro(){
        this.novoCli = new MetodosCliente();
        this.novoUsr = new MetodosUsuario(DT.DTUsers());
        this.novoFrn = new MetodosFornecedor();
    }

    /**
     * Classe interna para as Ações do cliente
     */
    public class AcoesCliente implements ICustomer {
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
        public boolean listarClientes() {
            return novoCli.PrintMapWithSet();
        }
    }

    /**
     * Classe interna para as Ações do usuário
     */
    public class AcoesUsuario implements IUsers, IPermissions{
        @Override
        public void cadastrarUsuario(String login, String pass, String nome, String depto){
            novoUsr.setLogin(login);
            novoUsr.setPassword(MetodosUtils.Senha.Encrypt(pass));
            novoUsr.setNome(nome);
            novoUsr.setDepto(depto);

            novoUsr.novoUsuario(novoUsr.nextId(), novoUsr);
        }

        @Override
        public void alterarUsuario(Integer id, String campo, String update){
            novoUsr.alterUsuario(id, campo, update);
            System.out.println(MetodosUsuario.cod == 1 ? "\nUsuário alterado" : "\nNão foi possível alterar este usuário");
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
        public boolean listarUsuario() {
            return novoUsr.PrintMapWithSet();
        }


        @Override
        public Integer returnNextId() {
            return novoUsr.nextId();
        }


        @Override
        public void asociarPermissao(Integer id, String access) {
            novoUsr.darPermissao(id, access);
            System.out.print(MetodosUsuario.cod == 1 ? "\nUsuário inserido" : "\nUsuario existente");
        }

        @Override
        public void alterarPermissao(Integer idAdm, Integer id, String access) {
            novoUsr.altPermissao(idAdm,id, access);
            System.out.println(MetodosUsuario.cod == 1 ? "\nUsuário alterado" : "\nNão foi possível alterar este usuário");
        }

        @Override
        public void removerPermissao(Integer id) {
            novoUsr.remPermissao(id);
        }
    }

    public class AcoesFornecedor implements ISupplier {
        @Override
        public void cadastrarFornecedor(String razaoSocial, String nomeFantasia, String cnpj, String email, String inscEstadual, String inscMunicipal, String telefone, String CEP, int numCasa) {
            novoFrn.setRazaoSocial(razaoSocial);
            novoFrn.setNomeFantasia(nomeFantasia);
            novoFrn.setCnpj(cnpj);
            novoFrn.setEmail(email);
            novoFrn.setInscEstadual(inscEstadual);
            novoFrn.setInscMunicipal(inscMunicipal);
            novoFrn.setTelefone(telefone);
            novoFrn.setInfoCEP(MetodosUtils.CEP.ResponseCEP(CEP, numCasa));

            novoFrn.novoFornecedor(novoFrn.nextId(), novoFrn);
        }

        @Override
        public void alterarFornecedor(Integer id, String campo, String update) {
            novoFrn.alterFornecedor(id, campo, update);
        }

        @Override
        public void excluirFornecedor(Integer id) {
            novoFrn.remoFornecedor(id);
        }

        @Override
        public void localizarFornecedor(Integer id) {
            novoFrn.findFornecedor(id);
        }

        @Override
        public void localizarMaisFornecedores(Integer ini, Integer fim) {
            novoFrn.listbyIdFornecedor(ini, fim);
        }

        @Override
        public void removerMaisFornecedores(Integer ini, Integer fim) {
            novoFrn.remobyIdFornecedor(ini, fim);
        }

        @Override
        public boolean listarFornecedores() {
            return novoFrn.PrintMapWithSet();
        }
    }
}

package Raiz.Inicio;

import Cadastro.Database.Metodos.*;
import Cadastro.Database.Metodos.Interfaces.INovosDados;
import Cadastro.Database.DataSet;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades;
import Raiz.Utils.SmartTools;

public class Cadastro implements INovosDados {

    MetodosCliente novoCli;
    MetodosUsuario novoUsr;
    MetodosFornecedor novoFrn;
    MetodosProduto novoPrd;
    MetodosServico novoSrv;

    public Cadastro(DataSet<?> DS) throws Exception {
        this.novoCli = new MetodosCliente(DS);
        this.novoUsr = new MetodosUsuario(DS);
        this.novoFrn = new MetodosFornecedor(DS);
        this.novoPrd = new MetodosProduto(DS);
        this.novoSrv = new MetodosServico(DS);
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
            novoCli.setInfoCEP(SmartTools.CEP.ResponseCEP(CEP, numCasa));

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
            novoUsr.setPassword(SmartTools.Senha.Encrypt(pass));
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
        public void associarPermissao(Integer id, String access) {
            novoUsr.darPermissao(id, access);
            //System.out.print(MetodosUsuario.cod == 1 ? "\nUsuário inserido" : "\nUsuario existente");
            System.out.println("\nUsuário inserido");
        }

        @Override
        public void alterarPermissao(Integer idAdm, Integer id, String access) {
            novoUsr.altPermissao(idAdm, id, access);
            //System.out.println(MetodosUsuario.cod == 1 ? "\nUsuário alterado" : "\nNão foi possível alterar este usuário");
            System.out.println("\nUsuário alterado");
        }

        @Override
        public void removerPermissao(Integer id) {
            novoUsr.remPermissao(id);
        }
    }

    /**
     * Classe interna para as Ações do Fornecedor
     */
    public class AcoesFornecedor implements ISupplier {
        @Override
        public void cadastrarFornecedor(String razaoSocial, String nomeFantasia, String cnpj, String email, String inscEstadual, String telefone, String CEP, int numCasa, String... atividade) {
            novoFrn.setRazaoSocial(razaoSocial);
            novoFrn.setNomeFantasia(nomeFantasia);
            novoFrn.setCnpj(cnpj);
            novoFrn.setEmail(email);
            novoFrn.setInscEstadual(inscEstadual);
            novoFrn.setTelefone(telefone);
            novoFrn.setInfoCEP(SmartTools.CEP.ResponseCEP(CEP, numCasa));
            novoFrn.setAtividades(novoFrn.fornAtividades(atividade));

            novoFrn.novoFornecedor(novoFrn.nextId(), novoFrn);
        }

        @Override
        public void alterarFornecedor(Integer id, String campo, String... update) {
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

    /**
     * Classe interna para as Ações dos Produtos
     */
    public class AcoesProdutos implements IProducts {

        @Override
        public void cadastrarProduto(String nomeProd, double preco, int qtd, String forn, Propriedades.Categoria categoria, Propriedades.Grupos grupo) {
            novoPrd.setnomeProd(nomeProd);
            novoPrd.setPreco(preco);
            novoPrd.setQtd(qtd);
            novoPrd.setForn(forn);
            novoPrd.setCategoria(categoria);
            novoPrd.setGrupo(grupo);

            novoPrd.novoProduto(novoPrd.nextId(), novoPrd);
        }

        @Override
        public void alterarProduto(Integer id, String campo, String update) {
            novoPrd.alterProduto(id, campo, update);
        }

        @Override
        public void excluirProduto(Integer id) {
            novoPrd.remoProduto(id);
        }

        @Override
        public void localizarProduto(Integer id) {
            novoPrd.findProduto(id);
        }

        @Override
        public void localizarMaisProdutos(Integer ini, Integer fim) {
            novoPrd.listbyIdProduto(ini, fim);
        }

        @Override
        public void removerMaisProdutos(Integer ini, Integer fim) {
            novoPrd.remobyIdProduto(ini, fim);
        }

        @Override
        public boolean listarProdutos() {
            return novoPrd.PrintMapWithSet();
        }
    }

    /**
     * Classe interna para as Ações dos Servicos
     */
    public class AcoesServicos implements IServices {

        @Override
        public void cadastrarServico(String nomeProd, double preco, Propriedades.Categoria categoria, Propriedades.Grupos grupo) {
            novoSrv.setNomeServ(nomeProd);
            novoSrv.setPreco(preco);
            novoSrv.setCategoria(categoria);
            novoSrv.setGrupo(grupo);

            novoSrv.novoServico(novoSrv.nextId(), novoSrv);
        }

        @Override
        public void alterarServico(Integer id, String campo, String update) {
            novoSrv.alterServico(id, campo, update);
        }

        @Override
        public void excluirServico(Integer id) {
            novoSrv.remoServico(id);
        }

        @Override
        public void localizarServico(Integer id) {
            novoSrv.findServico(id);
        }

        @Override
        public void localizarMaisServicos(Integer ini, Integer fim) {
            novoSrv.listbyIdServico(ini, fim);
        }

        @Override
        public void removerMaisServicos(Integer ini, Integer fim) {
            novoSrv.remobyIdServico(ini, fim);
        }

        @Override
        public boolean listarServicos() {
            return novoSrv.PrintMapWithSet();
        }
    }
}
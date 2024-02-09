package Raiz.Inicio;

import Cadastro.Database.Metodos.*;
import Cadastro.Database.Metodos.Deserializers.jsonCEP;
import Cadastro.Database.Metodos.Interfaces.INovosDados;
import Cadastro.Database.dataSet;
import Cadastro.NovosDados.Repositorio.Auxiliar.Marcas;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;
import Raiz.Utils.smartTools;

public class Cadastro implements INovosDados {

    metodosCliente novoCli;
    metodosUsuarios novoUsr;
    metodosFornecedores novoFrn;
    metodosProduto novoPrd;
    metodosServico novoSrv;
    metodosFuncionarios novoFnc;
    metodosCarros novoCar;

    public Cadastro(dataSet<?> DS) {
        this.novoCli = new metodosCliente(DS);
        this.novoUsr = new metodosUsuarios(DS);
        this.novoFrn = new metodosFornecedores(DS);
        this.novoPrd = new metodosProduto(DS);
        this.novoSrv = new metodosServico(DS);
        this.novoFnc = new metodosFuncionarios(DS);
        this.novoCar = new metodosCarros(DS);
    }

    /**
     * Classe interna para as Ações do cliente
     */
    public class AcoesClientes implements ICustomer, IGeneral {
        @Override
        public void cadastrar(String nome, int idade, String cpf, String email, String telefone, String CEP, int numCasa) {
            novoCli.setNome(nome);
            novoCli.setDocumento(cpf);
            novoCli.setIdade(idade);
            novoCli.setEmail(email);
            novoCli.setTelefone(telefone);
            novoCli.setInfoCEP(jsonCEP.responseCEP(CEP, numCasa));

            novoCli.novoCliente(novoCli.nextId(), novoCli);
        }

        @Override
        public void alterar(Integer id, String campo, String... update) {
            novoCli.alterCliente(id, campo, update[0]);
        }

        @Override
        public void excluir(Integer id) {
            novoCli.remoCliente(id);
        }

        @Override
        public void localizar(Integer id) {
            novoCli.findCliente(id);
        }

        @Override
        public void localizarMais(Integer ini, Integer fim) {
            novoCli.listbyIdCliente(ini, fim);
        }

        @Override
        public void removerMais(Integer ini, Integer fim) {
            novoCli.remobyIdCliente(ini, fim);
        }

        @Override
        public boolean listar() {
            return novoCli.printMapWithSet();
        }

        public boolean validarId(Integer id) { return novoCli.userValid(id); }
    }

    /**
     * Classe interna para as Ações do usuário
     */
    public class AcoesUsuarios implements IUsers, IPermissions, IGeneral {
        @Override
        public void cadastrar(String login, String pass, String nome, String depto){
            novoUsr.setLogin(login);
            novoUsr.setPassword(smartTools.Senha.Encrypt(pass));
            novoUsr.setNome(nome);
            novoUsr.setDepto(depto);

            novoUsr.novoUsuario(novoUsr.nextId(), novoUsr);
        }

        @Override
        public void alterar(Integer id, String campo, String... update){
            novoUsr.alterUsuario(id, campo, update[0]);
            System.out.println(metodosUsuarios.cod == 1 ? "\nUsuário alterado" : "\nNão foi possível alterar este usuário");
        }

        @Override
        public void excluir(Integer id) {
            novoUsr.remoUsuario(id);
        }

        @Override
        public void localizar(Integer id) {
            novoUsr.findUsuario(id);
        }

        @Override
        public void localizarMais(Integer ini, Integer fim) {
            novoUsr.listbyIdUsuario(ini, fim);
        }

        @Override
        public void removerMais(Integer ini, Integer fim) {
            novoUsr.remobyIdUsuario(ini, fim);
        }

        @Override
        public boolean listar() {
            return novoUsr.printMapWithSet();
        }

        @Override
        public Integer returnNextId() {
            return novoUsr.nextId();
        }

        @Override
        public void associarPermissao(Integer id, String access) {
            novoUsr.darPermissao(id, access);
            //System.out.print(metodosUsuarios.cod == 1 ? "\nUsuário inserido" : "\nUsuario existente");
            System.out.println("\nUsuário inserido");
        }

        @Override
        public void alterarPermissao(Integer idAdm, Integer id, String access) {
            novoUsr.altPermissao(idAdm, id, access);
            //System.out.println(metodosUsuarios.cod == 1 ? "\nUsuário alterado" : "\nNão foi possível alterar este usuário");
            System.out.println("\nUsuário alterado");
        }

        @Override
        public void removerPermissao(Integer id) {
            novoUsr.remPermissao(id);
        }

        public boolean validarId(Integer id) { return novoUsr.userValid(id); }

    }

    /**
     * Classe interna para as Ações do Fornecedores
     */
    public class AcoesFornecedores implements ISupplier, IGeneral {
        @Override
        public void cadastrar(String razaoSocial, String nomeFantasia, String cnpj, String email, String inscEstadual, String telefone, String CEP, int numCasa, String... atividade) {
            novoFrn.setRazaoSocial(razaoSocial);
            novoFrn.setNomeFantasia(nomeFantasia);
            novoFrn.setDocumento(cnpj);
            novoFrn.setEmail(email);
            novoFrn.setInscEstadual(inscEstadual);
            novoFrn.setTelefone(telefone);
            novoFrn.setInfoCEP(jsonCEP.responseCEP(CEP, numCasa));
            if (!atividade[0].isEmpty()) novoFrn.setAtividades(novoFrn.fornAtividades(atividade));
            novoFrn.novoFornecedor(novoFrn.nextId(), novoFrn);
        }

        @Override
        public void alterar(Integer id, String campo, String... update) {
            novoFrn.alterFornecedor(id, campo, update);
        }

        @Override
        public void excluir(Integer id) {
            novoFrn.remoFornecedor(id);
        }

        @Override
        public void localizar(Integer id) {
            novoFrn.findFornecedor(id);
        }

        @Override
        public void localizarMais(Integer ini, Integer fim) {
            novoFrn.listbyIdFornecedor(ini, fim);
        }

        @Override
        public void removerMais(Integer ini, Integer fim) {
            novoFrn.remobyIdFornecedor(ini, fim);
        }

        @Override
        public boolean listar() {
            return novoFrn.printMapWithSet();
        }

        public boolean validarId(Integer id) { return novoFrn.userValid(id); }

        public boolean retorno(Integer id) { return novoFrn.returnAtividades(id); }
    }

    /**
     * Classe interna para as Ações dos Produtos
     */
    public class AcoesProdutos implements IProducts, IGeneral {

        @Override
        public void cadastrar(String nomeProd, double preco, int qtd, String forn, Categoria categoria, Grupos grupo) {
            novoPrd.setnomeProd(nomeProd);
            novoPrd.setPreco(preco);
            novoPrd.setQtd(qtd);
            novoPrd.setForn(forn);
            novoPrd.setCategoria(categoria);
            novoPrd.setGrupo(grupo);

            novoPrd.novoProduto(novoPrd.nextId(), novoPrd);
        }

        @Override
        public void alterar(Integer id, String campo, String... update) {
            novoPrd.alterProduto(id, campo, update[0]);
        }

        @Override
        public void excluir(Integer id) {
            novoPrd.remoProduto(id);
        }

        @Override
        public void localizar(Integer id) {
            novoPrd.findProduto(id);
        }

        @Override
        public void localizarMais(Integer ini, Integer fim) {
            novoPrd.listbyIdProduto(ini, fim);
        }

        @Override
        public void removerMais(Integer ini, Integer fim) {
            novoPrd.remobyIdProduto(ini, fim);
        }

        @Override
        public boolean listar() {
            return novoPrd.printMapWithSet();
        }

        public boolean validarId(Integer id) { return novoPrd.userValid(id); }
    }

    /**
     * Classe interna para as Ações dos Servicos
     */
    public class AcoesServicos implements IServices, IGeneral {

        @Override
        public void cadastrar(String nomeProd, double preco, Categoria categoria, Grupos grupo) {
            novoSrv.setNome(nomeProd);
            novoSrv.setPreco(preco);
            novoSrv.setCategoria(categoria);
            novoSrv.setGrupo(grupo);

            novoSrv.novoServico(novoSrv.nextId(), novoSrv);
        }

        @Override
        public void alterar(Integer id, String campo, String... update) {
            novoSrv.alterServico(id, campo, update[0]);
        }

        @Override
        public void excluir(Integer id) {
            novoSrv.remoServico(id);
        }

        @Override
        public void localizar(Integer id) {
            novoSrv.findServico(id);
        }

        @Override
        public void localizarMais(Integer ini, Integer fim) {
            novoSrv.listbyIdServico(ini, fim);
        }

        @Override
        public void removerMais(Integer ini, Integer fim) {
            novoSrv.remobyIdServico(ini, fim);
        }

        @Override
        public boolean listar() {
            return novoSrv.printMapWithSet();
        }

        public boolean validarId(Integer id) { return novoSrv.userValid(id); }
    }

    /**
     * Classe interna para as Ações dos Servicos
     */
    public class AcoesFuncionarios implements IEmployee, IGeneral {

        @Override
        public void cadastrar(String nome, int idade, String genero, String cpf, String email, String telefone, String CEP, int numCasa, String area, String departamento) {
            novoFnc.setNome(nome);
            novoFnc.setIdade(idade);
            novoFnc.setGenero(genero);
            novoFnc.setDocumento(cpf);
            novoFnc.setEmail(email);
            novoFnc.setTelefone(telefone);
            novoFnc.setInfoCEP(jsonCEP.responseCEP(CEP, numCasa));
            novoFnc.setArea(area);
            novoFnc.setDepartamento(departamento);

            novoFnc.novoFuncionario(novoFnc.nextId(), novoFnc);
        }

        @Override
        public void alterar(Integer id, String campo, String... update) { novoFnc.alterFuncionario(id, campo, update[0]); }

        @Override
        public void excluir(Integer id) { novoFnc.remoFuncionario(id); }

        @Override
        public void localizar(Integer id) { novoFnc.findFuncionario(id); }

        @Override
        public void localizarMais(Integer ini, Integer fim) { novoFnc.listbyIdFuncionario(ini, fim); }

        @Override
        public void removerMais(Integer ini, Integer fim) { novoFnc.remobyIdFuncionario(ini, fim); }

        @Override
        public boolean listar() { return novoFnc.printMapWithSet(); }

        @Override
        public boolean validarId(Integer id) { return novoFnc.userValid(id); }
    }

    /**
     * Classe interna para as Ações dos Carros
     */
    public class AcoesCarros implements ICars, IGeneral {

        @Override
        public void cadastrar(String nome, String placa, String origem, String marca) {
            novoCar.setNome(nome);
            novoCar.setPlaca(placa);
            novoCar.setOrigem(origem);
            novoCar.setMarca(new Marcas(marca));

            novoCar.novoCarro(novoCar.nextId(), novoCar);
        }

        @Override
        public void alterar(Integer id, String campo, String... update) {
            novoCar.alterCarro(id, campo, update[0]);
        }

        @Override
        public void excluir(Integer id) {
            novoCar.remoCarro(id);
        }

        @Override
        public void localizar(Integer id) {
            novoCar.findCarro(id);
        }

        @Override
        public void localizarMais(Integer ini, Integer fim) {
            novoCar.listbyIdCarro(ini, fim);
        }

        @Override
        public void removerMais(Integer ini, Integer fim) {
            novoCar.remobyIdCarro(ini, fim);
        }

        @Override
        public boolean listar() {
            return novoCar.printMapWithSet();
        }

        @Override
        public boolean validarId(Integer id) { return novoCar.userValid(id); }
    }
}

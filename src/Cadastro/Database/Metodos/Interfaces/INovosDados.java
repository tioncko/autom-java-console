package Cadastro.Database.Metodos.Interfaces;

import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;

import java.util.List;

public interface INovosDados {

    public interface IReader {
        public String readText(String str);
        public Integer readInt(String str);
        public String readSentence(String str);
        public Double readDbl(String str);
        public List<String> readStrList(String str);
        public String readMask(String str);
    }

    public interface IGeneral {
        public void alterar(Integer id, String campo, String... update);
        public void excluir(Integer id);
        public void localizar(Integer id);
        public void localizarMais(Integer ini, Integer fim);
        public void removerMais(Integer ini, Integer fim);
        public boolean listar();
        public boolean validarId(Integer id);
    }

    public interface IPermissions {
        public Integer returnNextId();
        public void associarPermissao(Integer id, String access);
        public void alterarPermissao(Integer idAdm, Integer id, String access);
        public void removerPermissao(Integer id);
    }

    public interface ICustomer {
        public int cadastrar(String nome, int idade, String cpf, String email, String telefone, String CEP, int numCasa);
        //public void alterar(Integer id, String campo, String update);
    }

    public interface IUsers {
        public void cadastrar(String login, String pass, String nome, String depto);
        //public void alterar(Integer id, String campo, String update);
    }

    public interface ISupplier {
        public void cadastrar(String razaoSocial, String nomeFantasia, String cnpj, String email, String inscEstadual, String telefone, String CEP, int numCasa, String... atividade);
        //public void alterar(Integer id, String campo, String... update);
    }

    public interface IProducts {
        public void cadastrar(String nomeProd, double preco, int qtd, String forn, Categoria categoria, Grupos grupo);
        //public void alterar(Integer id, String campo, String update);
        //public <T> void ver(Integer id, String campo, T... update);
    }

    public interface IServices {
        public void cadastrar(String nomeProd, double preco, Categoria categoria, Grupos grupo);
        //public void alterar(Integer id, String campo, String update);
    }

    public interface IEmployee {
        public int cadastrar(String nome, int idade, String genero, String cpf, String email, String telefone, String CEP, int numCasa, String area, String departamento);
        //public void alterar(Integer id, String campo, String update);
    }

    public interface ICars {
        public void cadastrar(String nome, String placa, String origem, String marca);
        //public void alterar(Integer id, String campo, String update);
    }
}
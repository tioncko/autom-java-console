package Cadastro.Database.Metodos.Interfaces;

import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades.*;

import java.util.List;

public interface INovosDados {

    public interface IReader {
        public String ReadText(String str);
        public Integer ReadInt(String str);
        public String ReadSentence(String str);
        public Double ReadDbl(String str);
        public List<String> ReadStrList(String str);
        public String ReadMask(String str);
    }

    public interface ICustomer {
        public void cadastrarCliente(String nome, int idade, String cpf, String email, String telefone, String CEP, int numCasa);
        public void alterarCliente(Integer id, String campo, String update);
        public void excluirCliente(Integer id);
        public void localizarCliente(Integer id);
        public void localizarMaisClientes(Integer ini, Integer fim);
        public void removerMaisClientes(Integer ini, Integer fim);
        public boolean listarClientes();
    }

    public interface IUsers {
        public void cadastrarUsuario(String login, String pass, String nome, String depto);
        public void alterarUsuario(Integer id, String campo, String update);
        public void excluirUsuario(Integer id);
        public void localizarUsuario(Integer id);
        public void localizarMaisUsuarios(Integer ini, Integer fim);
        public void removerMaisUsuarios(Integer ini, Integer fim);
        public boolean listarUsuario();
    }

    public interface IPermissions {
        public Integer returnNextId();
        public void associarPermissao(Integer id, String access);
        public void alterarPermissao(Integer idAdm, Integer id, String access);
        public void removerPermissao(Integer id);
    }

    public interface ISupplier {
        public void cadastrarFornecedor(String razaoSocial, String nomeFantasia, String cnpj, String email, String inscEstadual, String telefone, String CEP, int numCasa, String... atividade);
        public void alterarFornecedor(Integer id, String campo, String... update);
        public void excluirFornecedor(Integer id);
        public void localizarFornecedor(Integer id);
        public void localizarMaisFornecedores(Integer ini, Integer fim);
        public void removerMaisFornecedores(Integer ini, Integer fim);
        public boolean listarFornecedores();
    }

    public interface IProducts {
        public void cadastrarProduto(String nomeProd, double preco, int qtd, String forn, Categoria categoria, Grupos grupo);
        public void alterarProduto(Integer id, String campo, String update);
        public void excluirProduto(Integer id);
        public void localizarProduto(Integer id);
        public void localizarMaisProdutos(Integer ini, Integer fim);
        public void removerMaisProdutos(Integer ini, Integer fim);
        public boolean listarProdutos();
    }

    public interface IServices {
        public void cadastrarServico(String nomeProd, double preco, Categoria categoria, Grupos grupo);
        public void alterarServico(Integer id, String campo, String update);
        public void excluirServico(Integer id);
        public void localizarServico(Integer id);
        public void localizarMaisServicos(Integer ini, Integer fim);
        public void removerMaisServicos(Integer ini, Integer fim);
        public boolean listarServicos();

    }
}
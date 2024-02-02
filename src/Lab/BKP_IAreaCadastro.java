package Lab;

public interface BKP_IAreaCadastro {

    public interface IClientes {
        public void menuCadastroCliente(Integer userId);
        public void acoesCadastroCliente(String id, Integer userId);
    }

    public interface IFornecedores {
        public void menuCadastroFornecedor(Integer userId);
        public void acoesCadastroFornecedor(String id, Integer userId);
    }

    public interface IUsuarios {
        public void menuCadastroUsuario(Integer userId);
        public void acoesCadastroUsuario(String id, Integer userId);
    }

    public interface IProdutos {
        public void menuCadastroProduto(Integer userId);
        public void acoesCadastroProduto(String id, Integer userId);
    }

    public interface IServicos {
        public void menuCadastroServico(Integer userId);
        public void acoesCadastroServico(String id, Integer userId);
    }

    public interface IFuncionarios {
        public void menuCadastroFuncionario(Integer userId);
        public void acoesCadastroFuncionario(String id, Integer userId);
    }
}

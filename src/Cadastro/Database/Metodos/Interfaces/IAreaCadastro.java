package Cadastro.Database.Metodos.Interfaces;

public interface IAreaCadastro {

    public interface IClientes {
        public void menuCadastroCliente(Integer userId) throws Exception;
        public void AcoesCadastroCliente(String id, Integer userId) throws Exception;
    }

    public interface IFornecedores {
        public void menuCadastroFornecedor(Integer userId) throws Exception;
        public void AcoesCadastroFornecedor(String id, Integer userId) throws Exception;
    }

    public interface IUsuarios {
        public void menuCadastroUsuario(Integer userId) throws Exception;
        public void AcoesCadastroUsuario(String id, Integer userId) throws Exception;
    }

    public interface IProdutos {
        public void menuCadastroProduto(Integer userId) throws Exception;
        public void AcoesCadastroProduto(String id, Integer userId) throws Exception;
    }

    public interface IServicos {
        public void menuCadastroServico(Integer userId) throws Exception;
        public void AcoesCadastroServico(String id, Integer userId) throws Exception;
    }

}

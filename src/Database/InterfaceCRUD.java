package Database;

public interface InterfaceCRUD {

    public interface IReader{
        public String ReadText(String str);
        public Integer ReadInt(String str);
        public Double ReadDbl(String str);
    }

    public interface ICliente{
        public void cadastrarCliente(String nome, int idade, String cpf, String email, String telefone, String CEP, int numCasa);
        public void alterarCliente(Integer id, String campo, String update);
        public void excluirCliente(Integer id);
        public void localizarCliente(Integer id);
        public void localizarMaisClientes(Integer ini, Integer fim);
        public void removerMaisClientes(Integer ini, Integer fim);
        public void listarClientes();
    }

    public interface IUsers {
        public void cadastrarUsuario(String login, String pass, String nome, String depto) throws Exception;
        public void alterarUsuario(Integer id, String campo, String update) throws Exception;
        public void excluirUsuario(Integer id) throws Exception;
        public void localizarUsuario(Integer id) throws Exception;
        public void localizarMaisUsuarios(Integer ini, Integer fim) throws Exception;
        public void removerMaisUsuarios(Integer ini, Integer fim) throws Exception;
        public void listarUsuario() throws Exception;
    }
}
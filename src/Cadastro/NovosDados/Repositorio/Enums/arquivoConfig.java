package Cadastro.NovosDados.Repositorio.Enums;

public enum arquivoConfig {
    Loja("store_class"),
    Fornecedores("supply_class"),
    Clientes("custom_class"),
    Funcionarios("employ_class"),
    Marcas("endpointMarcas"),
    Modelos("endpointModelos"),
    CEP("endpointCEP");

    private final String propriedade;

    arquivoConfig(String param) {
        this.propriedade = param;
    }

    public String getPropriedade() {
        return propriedade;
    }
}

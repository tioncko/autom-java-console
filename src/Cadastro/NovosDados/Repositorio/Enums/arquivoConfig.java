package Cadastro.NovosDados.Repositorio.Enums;

public enum arquivoConfig {
    Loja("store_class"),
    Fornecedores("supply_class"),
    Marcas("endpointMarcas");

    private final String propriedade;

    arquivoConfig(String param) {
        this.propriedade = param;
    }

    public String getPropriedade() {
        return propriedade;
    }
}

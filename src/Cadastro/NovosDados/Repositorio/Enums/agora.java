package Cadastro.NovosDados.Repositorio.Enums;

public enum agora {
     morning("Bom dia")
    ,afternoon("Boa tarde")
    ,evening("Boa noite");

    private final String dia;

    agora(String value) {
        this.dia = value;
    }

    public String getDay() {
        return dia;
    }
}

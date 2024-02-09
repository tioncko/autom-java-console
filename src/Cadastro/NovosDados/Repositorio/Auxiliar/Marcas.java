package Cadastro.NovosDados.Repositorio.Auxiliar;

import java.util.Objects;

public class Marcas {

    private String Marca;
    private int apiCode;

    public Marcas() {
    }

    public Marcas(String marca) {
        this.Marca = marca;
    }

    public Marcas(String marca, int apiCode) {
        this.Marca = marca;
        this.apiCode = apiCode;
    }

    public String getMarca() {
        return Marca;
    }
    public void setMarca(String marca) {
        this.Marca = marca;
    }

    public int getApiCode() {
        return apiCode;
    }
    public void setApiCode(int apiCode) { this.apiCode = apiCode; }

    @Override
    //public String toString() { return "Marcas{" + Marca + '\'' + ", apiCode=" + apiCode + '}'; }
    public String toString() { return Marca; }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marcas marca = (Marcas) o;
        return Objects.equals(Marca, marca.Marca);
    }

    @Override
    public int hashCode() {return Objects.hash(Marca);}
}

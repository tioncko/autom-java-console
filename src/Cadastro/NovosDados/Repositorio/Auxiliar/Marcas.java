package Cadastro.NovosDados.Repositorio.Auxiliar;

import java.util.Objects;

public class Marcas {

    private String Marca;

    public Marcas() {
    }

    public Marcas(String marca) {
        Marca = marca;
    }

    public String getMarca() {
        return Marca;
    }
    public void setMarca(String marca) {
        this.Marca = marca;
    }

    @Override
    public String toString() {
        return Marca;
    }

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

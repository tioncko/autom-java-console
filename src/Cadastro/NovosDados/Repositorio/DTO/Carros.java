package Cadastro.NovosDados.Repositorio.DTO;

import Cadastro.NovosDados.Repositorio.Auxiliar.Marcas;

public class Carros {

    private int id;
    private String nome;
    private String placa;
    private String origem;
    private Cliente cliente;
    private Marcas marca;

    public Carros(String nome, String placa, String origem, Cliente cliente, Marcas marca) {
        this.nome = nome;
        this.placa = placa;
        this.origem = origem;
        this.cliente = cliente;
        this.marca = marca;
    }

    public Carros() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getOrigem() {
        return origem;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Marcas getMarca() {
        return marca;
    }
    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Carros{" +
                "nome='" + nome + '\'' +
                ", placa='" + placa + '\'' +
                ", origem='" + origem + '\'' +
                ", cliente=" + cliente +
                ", marca=" + marca +
                '}';
    }
}

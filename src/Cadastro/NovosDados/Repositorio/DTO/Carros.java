package Cadastro.NovosDados.Repositorio.DTO;

import Cadastro.NovosDados.Repositorio.Auxiliar.Marcas;

public class Carros {

    private int id;
    private String nome;
    private String placa;
    private String origem;
    private Clientes clientes;
    private Marcas marca;

    public Carros(String nome, String placa, String origem) {
        this.nome = nome;
        this.placa = placa;
        this.origem = origem;
    }

    public Carros(String nome, String placa, String origem, Marcas marca) {
        this.nome = nome;
        this.placa = placa;
        this.origem = origem;
        this.marca = marca;
    }

    public Carros(String nome, String placa, String origem, Clientes clientes, Marcas marca) {
        this.nome = nome;
        this.placa = placa;
        this.origem = origem;
        this.clientes = clientes;
        this.marca = marca;
    }

    public Carros() {
    }

    public int getId() {
        return id;
    }
    public Integer setId(int id) {
        return this.id = id;
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

    public Clientes getCliente() {
        return clientes;
    }
    public void setCliente(Clientes clientes) {
        this.clientes = clientes;
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
                ", clientes='" + clientes + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }
}

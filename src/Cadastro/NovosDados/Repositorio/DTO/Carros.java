package Cadastro.NovosDados.Repositorio.DTO;

import Cadastro.NovosDados.Repositorio.Auxiliar.Marcas;

import java.util.ArrayList;
import java.util.List;

public class Carros {

    private int id;
    private String modelo;
    private String placa;
    private String origem;
    private Clientes clientes;
    private Marcas marca;

    public Carros(String nome) {
        this.modelo = nome;
    }

    public Carros(String nome, String placa, String origem) {
        this.modelo = nome;
        this.placa = placa;
        this.origem = origem;
    }

    public Carros(String nome, String placa, String origem, Marcas marca) {
        this.modelo = nome;
        this.placa = placa;
        this.origem = origem;
        this.marca = marca;
    }

    public Carros(String nome, String placa, String origem, Clientes clientes, Marcas marca) {
        this.modelo = nome;
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
        return modelo;
    }
    public void setNome(String nome) {
        this.modelo = nome;
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
                "modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", origem='" + origem + '\'' +
                ", cliente='" + clientes.getNome() + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }

    public class Modelos {

        private List<Carros> model;

        public List<Carros> getModelo() { return model; }
        public void setModelo(List<Carros> modelo) { this.model = modelo; }
    }
}

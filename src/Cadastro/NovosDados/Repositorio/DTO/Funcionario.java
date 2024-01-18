package Cadastro.NovosDados.Repositorio.DTO;

import Cadastro.NovosDados.Repositorio.Abstratos.outrosDados;
import Cadastro.NovosDados.Repositorio.Auxiliar.validarCEP;

public class Funcionario extends outrosDados {

    private int id;
    private String nome;
    private int idade;
    private String genero;
    private String area;
    private String departamento;

    public Funcionario(String nome, int idade, String genero, String email, String telefone, validarCEP infoCEP, String area, String departamento) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.setEmail(email);
        this.setTelefone(telefone);
        this.setInfoCEP(infoCEP);
        this.area = area;
        this.departamento = departamento;
    }

    public Funcionario() {
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

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", " + getInfoCEP() + '\'' +
                ", area='" + area + '\'' +
                ", departamento='" + departamento + '\''
                + '}';
    }
}

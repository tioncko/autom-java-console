package Cadastro.NovosDados.Repositorio.DTO;

import Cadastro.NovosDados.Repositorio.Abstratos.outrosDados;
import Cadastro.NovosDados.Repositorio.Auxiliar.validarCEP;

public class Clientes extends outrosDados {

    private int id;
    private String nome;
    private int idade;
    //private String cpf;

    //#region rascunho
    /* private String email;
    //private String telefone;
    //private validarCEP infoCEP;
     */
    //#endregion

    public Clientes(String nome, int idade, String cpf, String email, String telefone, validarCEP infoCEP) {
        this.nome = nome;
        this.idade = idade;
        this.setDocumento(cpf);
        this.setEmail(email);
        this.setTelefone(telefone);
        this.setInfoCEP(infoCEP);
    }

    public Clientes(String nome) {
        this.nome = nome;
    }

    public Clientes() {

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

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    //#region rascunho
/*
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public validarCEP getInfoCEP() {
        return infoCEP;
    }

    public void setInfoCEP(validarCEP infoCEP) {
        this.infoCEP = infoCEP;
    }


    /*
    public String getCPF() {
        return cpf;
    }
    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Clientes cli)) return false;
        return Objects.equals(this.getCPF(), cli.getCPF());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCPF());
    }
*/
    //#endregion

    @Override
    public String toString() {
        return "Clientes{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", cpf='" + getDocumento() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", " + getInfoCEP() +
                '}';
    }
}
    //#region rascunho
    /*
    public static void main(String[] args) {

        System.out.println(Clientes.ResponseCEP("04472205", 47).getCidade());
        System.out.println(Clientes.ResponseCEP("04472205", 47));
    }


   É simples, crie uma classe com os membros que precisa usar, então faça o valor ser este objeto com todos os membros dentro. Algo assim (de forma muito simplificada):

import java.util.*;

class Main {
    public static void main (String[] args) {
        Map<String, Alimento> mapalimentos = new HashMap<>();
        mapalimentos.put("Maçã", new Alimento(50, 10));
        mapalimentos.put("Melancia", new Alimento(30, 20));
    }
}

class Alimento {
    public Alimento(int calorias, int proteinas) {
        Calorias = calorias;
        Proteinas = proteinas;
    }
    int Calorias;
    int Proteinas;
}
*/
//#endregion

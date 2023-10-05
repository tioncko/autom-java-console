package NovosDados.Repositorio;

import Utils.MetodosUtils;
import Utils.Objetos.ValidCEP;

public class Cliente extends MetodosUtils {

    private int id;
    private String nome;
    private int idade;
    private String cpf;
    private String email;
    private String telefone;
    private ValidCEP infoCEP;

    public Cliente(String nome, int idade, String cpf, String email, String telefone, ValidCEP infoCEP) {

        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.infoCEP = infoCEP;
    }

    public Cliente() {

    }

//#region Getters and Setters

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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

    public ValidCEP getInfoCEP() {
        return infoCEP;
    }

    public void setInfoCEP(ValidCEP infoCEP) {
        this.infoCEP = infoCEP;
    }

//#endregion

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", " + infoCEP +
                '}';
    }
}

//#region notes
    /*
    public static void main(String[] args) {

        System.out.println(Cliente.ResponseCEP("04472205", 47).getCidade());
        System.out.println(Cliente.ResponseCEP("04472205", 47));
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

package Cadastro.Database.JSON.JsonTools;

import Cadastro.Database.Metodos.Deserializers.jsonCEP;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades;
import Cadastro.NovosDados.Repositorio.DTO.*;
import com.google.gson.JsonObject;

class jsonDTO {

    /**
     * Método que recebe um objeto Json que retorna um objeto Produtos
     */
    protected static Produtos getProdutos(JsonObject item) {
        Produtos prod = new Produtos();
        prod.setnomeProd(item.get("Nome").getAsString().toUpperCase());
        prod.setQtd(item.get("Quantidade").getAsInt());
        prod.setPreco(item.get("Preco").getAsDouble());

        Propriedades.Categoria cat = new Propriedades.Categoria();
        cat.setCategoria(item.get("Categoria").getAsString());
        prod.setCategoria(cat);

        Propriedades.Grupos gp = new Propriedades.Grupos();
        gp.setGrupo(item.get("Grupo").getAsString());
        prod.setGrupo(gp);
        return prod;
    }

    /**
     * Método que recebe um objeto Json que retorna um objeto Servicos
     */
    protected static Servicos getServicos(JsonObject item) {
        Servicos serv = new Servicos();
        serv.setNome(item.get("Nome").getAsString().toUpperCase());
        serv.setPreco(item.get("Preco").getAsDouble());

        Propriedades.Categoria cat = new Propriedades.Categoria();
        cat.setCategoria(item.get("Categoria").getAsString());
        serv.setCategoria(cat);

        Propriedades.Grupos gp = new Propriedades.Grupos();
        gp.setGrupo(item.get("Grupo").getAsString());
        serv.setGrupo(gp);
        return serv;
    }

    /**
     * Método que recebe um objeto Json que retorna um objeto Fornecedores
     */
    protected static Fornecedores getFornecedor(JsonObject item) {
        Fornecedores forn = new Fornecedores();
        forn.setRazaoSocial(item.get("razaoSocial").getAsString());
        forn.setNomeFantasia(item.get("nomeFantasia").getAsString());
            String cnpj = item.get("cnpj").getAsString();
            while (cnpj.length() <= 13) {
                String ret = "0";
                cnpj = ret + cnpj;
            }
        forn.setDocumento(cnpj);
        forn.setEmail(item.get("email").getAsString());
        forn.setInscEstadual(item.get("inscEstadual").getAsString());
        forn.setTelefone(item.get("telefone").getAsString());
            String CEP = item.get("infoCEP").getAsString();
            while (CEP.length() <= 7) {
                String ret = "0";
                CEP = ret + CEP;
            }
            int numLocal = item.get("numLocal").getAsInt();
//        forn.setInfoCEP(smartTools.CEP.responseCEP(CEP, numLocal));
        forn.setInfoCEP(jsonCEP.responseCEP(CEP, numLocal));

        return forn;
    }

    /**
     * Método que recebe um objeto Json que retorna um objeto Clientes
     */
    protected static Clientes getCliente(JsonObject item) {
        Clientes cli = new Clientes();
        cli.setNome(item.get("nome").getAsString());
        cli.setIdade(item.get("idade").getAsInt());
            String cpf = item.get("cpf").getAsString();
            while (cpf.length() <= 10) {
                String ret = "0";
                cpf = ret + cpf;
            }
        cli.setDocumento(cpf);
        cli.setEmail(item.get("email").getAsString());
        cli.setTelefone(item.get("telefone").getAsString());
            String CEP = item.get("infoCEP").getAsString();
            while (CEP.length() <= 7) {
                String ret = "0";
                CEP = ret + CEP;
            }
        int numLocal = item.get("numLocal").getAsInt();
        cli.setInfoCEP(jsonCEP.responseCEP(CEP, numLocal));

        return cli;
    }

    protected static Funcionarios getFuncionario(JsonObject item) {
        Funcionarios func = new Funcionarios();
        func.setNome(item.get("nome").getAsString());
        func.setIdade(item.get("idade").getAsInt());
        func.setGenero(item.get("genero").getAsString());
            String cpf = item.get("cpf").getAsString();
            while (cpf.length() <= 10) {
                String ret = "0";
                cpf = ret + cpf;
            }
        func.setDocumento(cpf);
        func.setEmail(item.get("email").getAsString());
        func.setTelefone(item.get("telefone").getAsString());
            String CEP = item.get("infoCEP").getAsString();
            while (CEP.length() <= 7) {
                String ret = "0";
                CEP = ret + CEP;
            }
        int numLocal = item.get("numLocal").getAsInt();
        func.setInfoCEP(jsonCEP.responseCEP(CEP, numLocal));
        func.setArea(item.get("area").getAsString());
        func.setDepartamento(item.get("depto").getAsString());

        return func;
    }
}
/*
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.setDocumento(cpf);
        this.setEmail(email);
        this.setTelefone(telefone);
        this.setInfoCEP(infoCEP);
        this.area = area;
        this.departamento = departamento;
        */
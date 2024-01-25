package Cadastro.Database.JSON.JsonTools;

import Cadastro.Database.Metodos.Deserializers.jsonCEP;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades;
import Cadastro.NovosDados.Repositorio.DTO.Fornecedor;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Cadastro.NovosDados.Repositorio.DTO.Servicos;
import Raiz.Utils.smartTools;
import com.google.gson.JsonObject;

class jsonDTO {

    /**
     * Método que recebe um objeto Json que retorna um objeto Produtos
     */
    protected static Produtos getProdutos(JsonObject item) {
        Produtos prod = new Produtos();
        prod.setnomeProd(item.get("Nome").getAsString());
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
        serv.setNomeServ(item.get("Nome").getAsString());
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
     * Método que recebe um objeto Json que retorna um objeto Fornecedor
     */
    protected static Fornecedor getFornecedor(JsonObject item) {
        Fornecedor forn = new Fornecedor();
        forn.setRazaoSocial(item.get("razaoSocial").getAsString());
        forn.setNomeFantasia(item.get("nomeFantasia").getAsString());
            String cnpj = item.get("cnpj").getAsString();
            while (cnpj.length() <= 13) {
                String ret = "0";
                cnpj = ret + cnpj;
            }
        forn.setCnpj(cnpj);
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
}

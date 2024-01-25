package Lab.NotWorked;

import Cadastro.Database.Metodos.Deserializers.jsonCEP;
import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Raiz.Core.Config;
import Cadastro.NovosDados.Repositorio.DTO.Fornecedor;
import Raiz.Utils.jsonPerGson;
import Raiz.Utils.smartTools;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonFornecedores extends jsonPerGson {

    private static class DeserializeFornecedores {
        protected Fornecedor getJsonFornecedor(JsonObject object) {

            String razaoSocial = object.get("razaoSocial").getAsString();
            String nomeFantasia = object.get("nomeFantasia").getAsString();
            String cnpj = object.get("cnpj").getAsString();
            String email = object.get("email").getAsString();
            String inscEstadual = object.get("inscEstadual").getAsString();
            String telefone = object.get("telefone").getAsString();
            String infoCEP = object.get("infoCEP").getAsString();
            int numLocal = object.get("numLocal").getAsInt();

            Fornecedor forn = new Fornecedor();
            forn.setRazaoSocial(razaoSocial);
            forn.setNomeFantasia(nomeFantasia);
            forn.setCnpj(cnpj);
            forn.setEmail(email);
            forn.setInscEstadual(inscEstadual);
            forn.setTelefone(telefone);
            forn.setInfoCEP(jsonCEP.responseCEP(infoCEP, numLocal));
//            forn.setInfoCEP(smartTools.CEP.responseCEP(infoCEP, numLocal));
            return forn;
        }
    }

    private static class JsonDeserializerFornecedores implements JsonDeserializer<Fornecedor> {

        @Override
        public Fornecedor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            DeserializeFornecedores df = new DeserializeFornecedores();
            JsonObject getObj = json.getAsJsonObject();
            return df.getJsonFornecedor(getObj);
        }
    }

    //#region rascunho
    /*
    public Map<Integer, Fornecedor> ListaFornecedores() {

        Map<Integer, Fornecedor> lista = new HashMap<>();
        String configParam = arquivoConfig.Fornecedores.getPropriedade();
        String endpointJson = Config.getProperties(configParam);

        JsonDeserializerFornecedores json = new JsonDeserializerFornecedores();
        List<Fornecedor> forn = requestListJson(endpointJson, Fornecedor.class, json);

        Integer i = 1;
        for (Fornecedor item : forn) {
            lista.put(i, new Fornecedor(item.getRazaoSocial(), item.getNomeFantasia(), item.getCnpj(), item.getEmail(), item.getInscEstadual(), item.getTelefone(), item.getInfoCEP()));
            i++;
        }
        return lista;
    }
         */
//#endregion
}

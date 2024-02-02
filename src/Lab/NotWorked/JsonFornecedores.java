package Lab.NotWorked;

import Cadastro.Database.Metodos.Deserializers.jsonCEP;
import Cadastro.NovosDados.Repositorio.DTO.Fornecedores;
import Raiz.Utils.jsonPerGson;
import com.google.gson.*;
import java.lang.reflect.Type;

public class JsonFornecedores extends jsonPerGson {

    private static class DeserializeFornecedores {
        protected Fornecedores getJsonFornecedor(JsonObject object) {

            String razaoSocial = object.get("razaoSocial").getAsString();
            String nomeFantasia = object.get("nomeFantasia").getAsString();
            String cnpj = object.get("cnpj").getAsString();
            String email = object.get("email").getAsString();
            String inscEstadual = object.get("inscEstadual").getAsString();
            String telefone = object.get("telefone").getAsString();
            String infoCEP = object.get("infoCEP").getAsString();
            int numLocal = object.get("numLocal").getAsInt();

            Fornecedores forn = new Fornecedores();
            forn.setRazaoSocial(razaoSocial);
            forn.setNomeFantasia(nomeFantasia);
            forn.setDocumento(cnpj);
            forn.setEmail(email);
            forn.setInscEstadual(inscEstadual);
            forn.setTelefone(telefone);
            forn.setInfoCEP(jsonCEP.responseCEP(infoCEP, numLocal));
//            forn.setInfoCEP(smartTools.CEP.responseCEP(infoCEP, numLocal));
            return forn;
        }
    }

    private static class JsonDeserializerFornecedores implements JsonDeserializer<Fornecedores> {

        @Override
        public Fornecedores deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            DeserializeFornecedores df = new DeserializeFornecedores();
            JsonObject getObj = json.getAsJsonObject();
            return df.getJsonFornecedor(getObj);
        }
    }

    //#region rascunho
    /*
    public Map<Integer, Fornecedores> ListaFornecedores() {

        Map<Integer, Fornecedores> lista = new HashMap<>();
        String configParam = arquivoConfig.Fornecedores.getPropriedade();
        String endpointJson = Config.getProperties(configParam);

        JsonDeserializerFornecedores json = new JsonDeserializerFornecedores();
        List<Fornecedores> forn = requestListJson(endpointJson, Fornecedores.class, json);

        Integer i = 1;
        for (Fornecedores item : forn) {
            lista.put(i, new Fornecedores(item.getRazaoSocial(), item.getNomeFantasia(), item.getCnpj(), item.getEmail(), item.getInscEstadual(), item.getTelefone(), item.getInfoCEP()));
            i++;
        }
        return lista;
    }
         */
//#endregion
}

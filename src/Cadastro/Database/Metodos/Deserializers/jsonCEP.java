package Cadastro.Database.Metodos.Deserializers;

import Cadastro.NovosDados.Repositorio.Auxiliar.validarCEP;
import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Raiz.Core.Config;
import Raiz.Core.impressaoLog;
import Raiz.Utils.jsonPerGson;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.logging.Logger;

public class jsonCEP extends jsonPerGson {

    private static class deserializeCEP {

        /**
         * Método que recebe um objeto Json que retorna um objeto CEP deserializado
         */
        protected validarCEP getCEP(JsonObject item) {
            validarCEP novoCEP = new validarCEP();
            novoCEP.setCEP(item.get("cep").getAsString());

            novoCEP.setEndereco(item.get("complemento").getAsString().isEmpty()
                    ? item.get("logradouro").getAsString()
                    : item.get("logradouro").getAsString() + " (" + (item.get("complemento").getAsString().isEmpty() + ")"));

            novoCEP.setBairro(item.get("bairro").getAsString());
            novoCEP.setCidade(item.get("localidade").getAsString());
            novoCEP.setUF(item.get("uf").getAsString());
            return novoCEP;
        }
    }

    private static class jsonDeserializerCEP implements JsonDeserializer<validarCEP> {

        /**
         * Método que utiliza a biblioteca Gson para deserializar um objeto Json que esteja em algum endpoint
         */
        @Override
        public validarCEP deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            deserializeCEP dm = new deserializeCEP();
            JsonObject getObj = json.getAsJsonObject();
            return dm.getCEP(getObj);
        }
    }

    /**
     * Método que retorna o objeto CEP apos deserializar
     */
    public static validarCEP responseCEP (String param, int num)  {
        impressaoLog.logGenerico<jsonCEP> printLog = new impressaoLog.logGenerico<>();
        @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<jsonCEP>) (Object) (jsonCEP.class));

        try {
            String configParam = arquivoConfig.CEP.getPropriedade();
            String endpointJson = Config.getProperties(configParam) + param;

            jsonDeserializerCEP json = new jsonDeserializerCEP();
            validarCEP requestCEP = requestElemJson(endpointJson, validarCEP.class, json);

            validarCEP CEP = new validarCEP();
            CEP.setCEP(requestCEP.getCEP());
            CEP.setEndereco(requestCEP.getEndereco());
            CEP.setBairro(requestCEP.getBairro());
            CEP.setNum(num);
            CEP.setCidade(requestCEP.getCidade());
            CEP.setUF(requestCEP.getUF());

            return CEP;
        } catch (Exception e) {
            log.warning("[" + jsonCEP.class.getSimpleName() + "] " + e.getMessage());
        }
        return new validarCEP();
    }

    //#region rascunho
    /*
    public static void main(String[] args) throws Exception {

        //jsonCEP k = new jsonCEP();
        System.out.println(jsonCEP.responseCEP("04472205", 47));;
    }

    //verificar novas apis pois a atual não retorna (ou está offline)
    //https://apicep.com/api-de-consulta/
    //estudar oAuth2
    //https://www.gov.br/conecta/catalogo/apis/cep-codigo-de-enderecamento-postal/swagger-json/swagger_view#section/Authentication

    //https://opencep.com/v1/15050305 bkp

    //            if (item.get("complemento").getAsString().isEmpty()) novoCEP.setEndereco(item.get("logradouro").getAsString());
    //            else novoCEP.setEndereco(item.get("logradouro").getAsString() + " (" + (item.get("complemento").getAsString().isEmpty() + ")"));

     */
    //#endregion

}
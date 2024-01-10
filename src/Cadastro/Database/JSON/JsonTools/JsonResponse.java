package Cadastro.Database.JSON.JsonTools;

import Cadastro.NovosDados.Repositorio.DTO.Fornecedor;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Cadastro.NovosDados.Repositorio.DTO.Servicos;
import Raiz.Core.Config;
import Raiz.Core.ImpressaoLog;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JsonResponse extends JsonDTO {

    public static class JsonGenericObjects<T> {

        private final List<T> itens;

        public JsonGenericObjects() {
            this.itens = new ArrayList<>();
        }

        protected List<T> adicionarItemALista(T item) {
            itens.add(item);
            return itens;
        }

        @SuppressWarnings("unchecked")
        public List<T> JsonReturn(T classe, String fileParam) {

            ImpressaoLog.LogGenerico<JsonGenericObjects<T>> printLog = new ImpressaoLog.LogGenerico<>();
            @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<JsonGenericObjects<T>>) (Object) (JsonGenericObjects.class));

            String fileName = Config.getProperties(fileParam);
            Path path = Paths.get(fileName);

            JsonGenericObjects<T> itensGen = new JsonGenericObjects<>();
            List<T> listaGen = new ArrayList<>();

            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                JsonParser parser = new JsonParser();
                JsonElement tree = parser.parse(reader);
                JsonArray array = tree.getAsJsonArray();

                for (JsonElement element : array) {
                    if (element.isJsonObject()) {

                        JsonObject item = element.getAsJsonObject();

                        /*
                        if (loja instanceof Produtos) {
                            Produtos prod = JsonDTO.getProdutos(item);
                            listaLoja = itensLoja.adicionarItemALista((T) prod);
                        }
                        if (loja instanceof Servicos) {
                            Servicos serv = JsonDTO.getServicos(item);
                            listaLoja = itensLoja.adicionarItemALista((T) serv);
                        }
                        */
                        listaGen = switch (classe.getClass().getSimpleName()){
                            case "Produtos" -> {
                                Produtos prod = getProdutos(item);
                                //yield -> mantém a execução da thread atual
                                yield itensGen.adicionarItemALista((T) prod);
                            }
                            case "Servicos" -> {
                                Servicos serv = getServicos(item);
                                yield itensGen.adicionarItemALista((T) serv);
                            }
                            case "Fornecedor" -> {
                                Fornecedor forn = getFornecedor(item);
                                yield itensGen.adicionarItemALista((T) forn);
                            }
                            default -> listaGen;
                        };
                    }
                }
            } catch (Exception e) {
                log.warning("[" + JsonGenericObjects.class.getSimpleName() + "] " + e.getMessage());
            }
            return listaGen;
        }
    /*
        private static Produtos getProdutos(JsonObject item) {
            Produtos prod = new Produtos();
            prod.setnomeProd(item.get("Nome").getAsString());
            prod.setQtd(item.get("Quantidade").getAsInt());
            prod.setPreco(item.get("Preco").getAsDouble());

            Categoria cat = new Categoria();
            cat.setCategoria(item.get("Categoria").getAsString());
            prod.setCategoria(cat);

            Grupos gp = new Grupos();
            gp.setGrupo(item.get("Grupo").getAsString());
            prod.setGrupo(gp);
            return prod;
        }

        private static Servicos getServicos(JsonObject item) {
            Servicos serv = new Servicos();
            serv.setNome(item.get("Nome").getAsString());
            serv.setPreco(item.get("Preco").getAsDouble());

            Categoria cat = new Categoria();
            cat.setCategoria(item.get("Categoria").getAsString());
            serv.setCategoria(cat);

            Grupos gp = new Grupos();
            gp.setGrupo(item.get("Grupo").getAsString());
            serv.setGrupo(gp);
            return serv;
        }
 */
    }
}

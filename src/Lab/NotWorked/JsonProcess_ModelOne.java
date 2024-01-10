package Lab.NotWorked;

import Cadastro.NovosDados.Repositorio.Enums.arquivoConfig;
import Raiz.Core.Config;
import Cadastro.NovosDados.Repositorio.Auxiliar.Propriedades;
import Cadastro.NovosDados.Repositorio.DTO.Produtos;
import Cadastro.NovosDados.Repositorio.DTO.Servicos;
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

public class JsonProcess_ModelOne {

    public static class Departments<T> {

        private final List<T> itens;

        public Departments() {
            this.itens = new ArrayList<>();
        }

        public List<T> addItemToList(T item) {
            itens.add(item);
            return itens;
        }

        @SuppressWarnings("unchecked")
        public List<T> ReturnListJson(T loja) {

            String fileParam = arquivoConfig.Loja.getPropriedade();
            String fileName = Config.getProperties(fileParam);
            Path path = Paths.get(fileName);

            Departments<T> itensDept = new Departments<>();
            List<T> JsonList = new ArrayList<>();

            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                JsonParser parser = new JsonParser();
                JsonElement tree = parser.parse(reader);
                JsonArray array = tree.getAsJsonArray();

                for (JsonElement element : array) {
                    if (element.isJsonObject()) {
                        JsonObject item = element.getAsJsonObject();

                        Class<T> classe = (Class<T>) loja;
                        T list = getObject(item, classe);
                        JsonList = itensDept.addItemToList(list);
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return JsonList;
        }

        @SuppressWarnings("unchecked")
        private T getObject (JsonObject item, Class<T> classe) throws Exception {
            JsonProcess_ModelTwo.ListaJson<T> two = new JsonProcess_ModelTwo.ListaJson<>();
            T instance = two.getClassNewInstance(classe);
            T obj = null;

            if (instance instanceof Produtos) {
                Produtos prod = getProdutos(item);
                obj = (T) prod;
            }
            if (instance instanceof Servicos) {
                Servicos serv = getServicos(item);
                obj = (T) serv;
            }

            return obj;
        }

        private static Produtos getProdutos(JsonObject item) {
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

        private static Servicos getServicos(JsonObject item) {
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


    }
}

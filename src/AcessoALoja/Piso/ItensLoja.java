package AcessoALoja.Piso;

import NovosDados.Repositorio.Auxiliar.API.Referencias.*;
import NovosDados.Repositorio.Cadastro.Produtos;
import NovosDados.Repositorio.Cadastro.Servicos;
import Propriedades.Config;
import Propriedades.ImpressaoLog;
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

public class ItensLoja {

    public static class Setores<T> {

        private final List<T> itens;

        public Setores() {
            this.itens = new ArrayList<>();
        }


        public List<T> adicionarItemALista(T item) {
            itens.add(item);
            return itens;
        }

        @SuppressWarnings("unchecked")
        public List<T> RetornoLoja(T loja) {

            ImpressaoLog.LogGenerico<Setores<T>> printLog = new ImpressaoLog.LogGenerico<>();
            @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<Setores<T>>) (Object) (Setores.class));

            String fileParam = Config.NameSettings.Loja.getProperty();
            String fileName = Config.getProperties(fileParam);
            Path path = Paths.get(fileName);

            Setores<T> itensLoja = new Setores<>();
            List<T> listaLoja = new ArrayList<>();

            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                JsonParser parser = new JsonParser();
                JsonElement tree = parser.parse(reader);
                JsonArray array = tree.getAsJsonArray();

                for (JsonElement element : array) {
                    if (element.isJsonObject()) {

                        JsonObject item = element.getAsJsonObject();
                        if (loja instanceof Produtos) {
                            Produtos prod = getProdutos(item);
                            listaLoja = itensLoja.adicionarItemALista((T) prod);
                        } else if (loja instanceof Servicos) {
                            Servicos serv = getServicos(item);
                            listaLoja = itensLoja.adicionarItemALista((T) serv);
                        }
                    }
                }
            } catch (Exception e) {
                log.warning("[" + Setores.class.getSimpleName() + "] " + e.getMessage());
            }
            return listaLoja;
        }

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
    }
}

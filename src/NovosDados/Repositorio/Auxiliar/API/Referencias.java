package NovosDados.Repositorio.Auxiliar.API;

public class Referencias {

    public static class Categoria {

        private String Categoria;

        public String getCategoria() {
            return Categoria;
        }

        public void setCategoria(String categoria) {
            this.Categoria = categoria;
        }

        public Categoria() {
        }

        public String toString() {
            return Categoria;
        }
    }

    public static class Grupos {

        private String Grupo;

        public String getGrupo() {
            return Grupo;
        }

        public void setGrupo(String grupo) {
            this.Grupo = grupo;
        }

        public Grupos() {

        }

        public String toString() {
            return Grupo;
        }
    }
}

//https://www.fastautopecas.com.br/ --> criar relação via excel e depois

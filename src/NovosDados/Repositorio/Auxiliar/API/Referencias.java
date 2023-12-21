package NovosDados.Repositorio.Auxiliar.API;

import java.util.Objects;

public class Referencias {

    public static class Categoria {

        private String Categoria;

        public String getCategoria() {
            return Categoria;
        }

        public void setCategoria(String categoria) {
            this.Categoria = categoria;
        }

        @Override
        public String toString() {
            return Categoria;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Categoria categoria = (Categoria) o;
            return Objects.equals(Categoria, categoria.Categoria);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Categoria);
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

        @Override
        public String toString() {
            return Grupo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Grupos grupo = (Grupos) o;
            return Objects.equals(Grupo, grupo.Grupo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Grupo);
        }
    }
}

//https://www.fastautopecas.com.br/ --> criar relação via excel e depois

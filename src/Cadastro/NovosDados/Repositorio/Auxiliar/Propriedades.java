package Cadastro.NovosDados.Repositorio.Auxiliar;

import java.util.Objects;

public class Propriedades {

    public static class Categoria implements Comparable<Categoria> {

        private String Categoria;

        public Categoria() {
        }
        public Categoria(String categoria) {
            this.Categoria = categoria;
        }

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
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Categoria categoria = (Categoria) o;
            return Objects.equals(Categoria, categoria.Categoria);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Categoria);
        }

        @Override
        public int compareTo(Categoria cat) {
            return this.Categoria.compareTo(cat.getCategoria());
        }
    }

    public static class Grupos implements Comparable<Grupos> {

        private String Grupo;

        public Grupos() {
        }
        public Grupos(String grupo) {
            this.Grupo = grupo;
        }

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

        @Override
        public int compareTo(Grupos gp) {
            return this.Grupo.compareTo(gp.getGrupo());
        }
    }
}

//https://www.fastautopecas.com.br/ --> criar relação via excel e depois

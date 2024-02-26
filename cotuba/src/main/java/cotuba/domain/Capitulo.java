package cotuba.domain;

import java.util.Objects;

public class Capitulo {
    private String titulo;
    private String conteudoHTML;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudoHTML() {
        return conteudoHTML;
    }

    public void setConteudoHTML(String conteudoHTML) {
        this.conteudoHTML = conteudoHTML;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Capitulo capitulo)) return false;
        return Objects.equals(titulo, capitulo.titulo) && Objects.equals(conteudoHTML, capitulo.conteudoHTML);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, conteudoHTML);
    }
}

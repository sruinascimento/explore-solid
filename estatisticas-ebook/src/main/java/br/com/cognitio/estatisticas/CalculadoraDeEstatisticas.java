package br.com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;

public class CalculadoraDeEstatisticas implements Plugin {

    @Override
    public String aposRenderizacao(String html) {
        return html;
    }

    @Override
    public void aposGeracao(Ebook ebook) {

        for (Capitulo capitulo : ebook.getCapitulos()) {

            String textoSemPontuacao = capitulo.getConteudoHTML().replaceAll("\\p{Punct}", "\\s");
            String decomposta = Normalizer.normalize(textoSemPontuacao, Normalizer.Form.NFD);
            String textoSemAcentos = decomposta.replaceAll("[^\\p{ASCII}]", "");
            Document doc = Jsoup.parse(textoSemAcentos);

            String textoDoCapitulo = doc.body().text();

            String[] palavras = textoDoCapitulo.split("\\s+");

            for (String palavra : palavras) {
                System.out.println(palavra);
            }

        }

    }

}
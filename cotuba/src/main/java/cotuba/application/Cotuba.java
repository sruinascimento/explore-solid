package cotuba.application;

import cotuba.domain.*;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.plugin.AoFinalizarGeracao;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class Cotuba {
    public void executar(ParametrosCotuba parametrosCotuba) {
        FormatoEbook formato = parametrosCotuba.getFormato();
        Path diretorioDosMD = parametrosCotuba.getDiretorioDosMD();
        Path diretorioDeSaida = parametrosCotuba.getArquivoDeSaida();
        RenderizadorMDParaHTML renderizadorMDParaHTML = new RenderizadorMDParaHTML();
        List<Capitulo> capitulos = renderizadorMDParaHTML.renderizar(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(diretorioDeSaida);
        ebook.setCapitulos(capitulos);

        GeradorEbook geradorEbook = GeradorEbook.cria(formato);
        geradorEbook.gera(ebook);
        AoFinalizarGeracao.gerou(ebook);
    }

}

package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class Cotuba {
    private String formato;
    private Path diretorioDosMD;
    private Path diretorioDeSaida;
    private final GeradorEPUB geradorEPUB;
    private final GeradorPDF geradorPDF;
    private final RenderizadorMDParaHTML renderizadorMDParaHTML;

    public Cotuba(GeradorEPUB geradorEPUB, GeradorPDF geradorPDF, RenderizadorMDParaHTML renderizadorMDParaHTML) {
        this.geradorEPUB = geradorEPUB;
        this.geradorPDF = geradorPDF;
        this.renderizadorMDParaHTML = renderizadorMDParaHTML;
    }

    public void executar(ParametrosCotuba opcoesCLI) {
        this.formato = opcoesCLI.getFormato();
        this.diretorioDosMD = opcoesCLI.getDiretorioDosMD();
        this.diretorioDeSaida = opcoesCLI.getArquivoDeSaida();
        List<Capitulo> capitulos = renderizadorMDParaHTML.renderizar(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(diretorioDeSaida);
        ebook.setCapitulos(capitulos);

        if ("pdf".equals(formato)) {
            geradorPDF.gerar(ebook);
        } else if ("epub".equals(formato)) {
            geradorEPUB.gerar(ebook);
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }
    }

}

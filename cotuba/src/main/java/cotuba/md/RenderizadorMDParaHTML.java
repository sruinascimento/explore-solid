package cotuba.md;

import cotuba.domain.Capitulo;
import cotuba.plugin.AoRenderizarHTML;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class RenderizadorMDParaHTML {

    public List<Capitulo> renderizar(Path diretorioDosMD) {
        return obterArquivosMD(diretorioDosMD)
                .stream()
                .map(arquivoMD -> {
                    Capitulo capitulo = new Capitulo();
                    Node document = parserDoMD(arquivoMD, capitulo);
                    renderizarParaHTML(arquivoMD, capitulo, document);
                    return capitulo;
                }).toList();
    }

    private static List<Path> obterArquivosMD(Path diretorioDosMD) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
        List<Path> arquivosMD = new ArrayList<>();
        try (Stream<Path> arquivos = Files.list(diretorioDosMD)) {
            arquivos
                    .filter(matcher::matches)
                    .sorted()
                    .forEach(arquivosMD::add);
        } catch (IOException ex) {
            throw new IllegalStateException("Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
        }
        return arquivosMD;
    }

    private static Node parserDoMD(Path arquivoMD, Capitulo capitulo) {
        Parser parser = Parser.builder().build();
        Node document = null;
        try {
            document = parser.parseReader(Files.newBufferedReader(arquivoMD));
            document.accept(new AbstractVisitor() {
                @Override
                public void visit(Heading heading) {
                    if (heading.getLevel() == 1) {
                        String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
                        capitulo.setTitulo(tituloDoCapitulo);
                    } else if (heading.getLevel() == 2) {
                        // seção
                    } else if (heading.getLevel() == 3) {
                        // título
                    }
                }

            });
        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
        }
        return document;
    }

    private static void renderizarParaHTML(Path arquivoMD, Capitulo capitulo, Node document) {
        try {
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            String html = renderer.render(document);

            capitulo.setConteudoHTML(html);

            AoRenderizarHTML.renderizou(capitulo);

        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
        }
    }
}

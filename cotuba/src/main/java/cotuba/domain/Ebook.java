package cotuba.domain;

import cotuba.plugin.EbookSoParaLeitura;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ebook implements EbookSoParaLeitura {
    private FormatoEbook formato;
    private Path arquivoDeSaida;
    List<Capitulo> capitulos = new ArrayList<>();

    public FormatoEbook getFormato() {
        return formato;
    }

    public void setFormato(FormatoEbook formato) {
        this.formato = formato;
    }

    public Path getArquivoDeSaida() {
        return arquivoDeSaida;
    }

    public void setArquivoDeSaida(Path arquivoDeSaida) {
        this.arquivoDeSaida = arquivoDeSaida;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void addCapitulo(Capitulo capitulo) {
        capitulos.add(capitulo);
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    public boolean isUltimoCapitulo(Capitulo capitulo) {
        return capitulos.get(this.capitulos.size()- 1).equals(capitulo);
    }
}

package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.pdf.GeradorPDFComITextPDF;

public interface GeradorPDF {
    void gerar(Ebook ebook);
}

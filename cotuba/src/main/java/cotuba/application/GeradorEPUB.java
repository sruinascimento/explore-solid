package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.epub.GeradorEpubComEpublib;

public interface GeradorEPUB {
    void gerar(Ebook ebook);
}

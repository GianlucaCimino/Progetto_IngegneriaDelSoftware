package Operazioni.Filtra;

import Libro.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class FiltraPerValutazione implements Filtra {
    private final int valutazione;

    public FiltraPerValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    @Override
    public List<Libro> filtra(List<Libro> libri) {
        if (valutazione < 1 || valutazione > 5) throw new IllegalArgumentException("Valutazione inesistente!");
        return libri.stream()
                .filter(l -> l.getValutazione() == valutazione)
                .collect(Collectors.toList());
    }
}

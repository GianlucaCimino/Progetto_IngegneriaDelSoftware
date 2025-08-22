package Operazioni;

import Libro.Libro;

public class FiltraPerValutazione implements Filtra{
    private final int valutazione;

    public FiltraPerValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    @Override
    public boolean filtra(Libro libro) {
        if (valutazione < 1 || valutazione > 5) throw new IllegalArgumentException("Valutazione inesistente!");
        return libro.getValutazione() == valutazione;
    }
}

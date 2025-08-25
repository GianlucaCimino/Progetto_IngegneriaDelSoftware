package Operazioni.Ricerca;

import Libro.Libro;

import java.util.List;

public class ContestoRicerca {
    private Ricerca strategia;

    public void setStrategia(Ricerca strategia) {
        this.strategia = strategia;
    }

    public List<Libro> ricerca(List<Libro> libri) {
        if (strategia != null) {
            return strategia.ricerca(libri);
        }
        return libri;
    }
}

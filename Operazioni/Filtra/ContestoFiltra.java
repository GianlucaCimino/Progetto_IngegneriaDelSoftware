package Operazioni.Filtra;

import Libro.Libro;

import java.util.List;

public class ContestoFiltra {
    private Filtra strategia;

    public void setStrategia(Filtra strategia) {
        this.strategia = strategia;
    }

    public List<Libro> filtra(List<Libro> libri) {
        if (strategia != null) {
            return strategia.filtra(libri);
        }
        return libri;
    }
}

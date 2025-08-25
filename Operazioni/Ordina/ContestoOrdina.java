package Operazioni.Ordina;

import Libro.Libro;

import java.util.List;

public class ContestoOrdina {
    private Ordina strategia;

    public void setStrategia(Ordina strategia) {
        this.strategia = strategia;
    }

    public List<Libro> ordina(List<Libro> libri) {
        if (strategia != null) {
            return strategia.ordina(libri);
        }
        return libri;
    }
}

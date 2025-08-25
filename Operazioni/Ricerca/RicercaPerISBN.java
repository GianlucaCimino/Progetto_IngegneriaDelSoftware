package Operazioni.Ricerca;

import Libro.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class RicercaPerISBN implements Ricerca{
    private final String CodISBN;

    public RicercaPerISBN(String codISBN) {
        this.CodISBN = codISBN;
    }

    @Override
    public List<Libro> ricerca(List<Libro> libri) {
        return libri.stream()
                .filter(libro -> libro.getCodISBN().toLowerCase().contains(CodISBN))
                .collect(Collectors.toList());
    }
}

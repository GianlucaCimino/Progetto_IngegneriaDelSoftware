package Operazioni.Ricerca;

import Libro.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class RicercaPerAutore implements Ricerca{
    private final String Autore;

    public RicercaPerAutore(String autore) {
        this.Autore = autore;
    }

    @Override
    public List<Libro> ricerca(List<Libro> libri) {
            return libri.stream()
                    .filter(libro -> libro.getAutore().toLowerCase().contains(Autore))
                    .collect(Collectors.toList());
    }
}

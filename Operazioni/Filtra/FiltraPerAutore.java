package Operazioni.Filtra;

import Libro.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class FiltraPerAutore implements Filtra {
    private final String autore;

    public FiltraPerAutore(String autore) {
        this.autore = autore;
    }

    @Override
    public List<Libro> filtra(List<Libro> libri) {
        if (autore == null) throw new IllegalArgumentException("Autore inesistente!");
        return libri.stream()
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }
}

package Operazioni.Filtra;

import Libro.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class FiltraPerGenere implements Filtra {
    private final String genere;

    public FiltraPerGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public List<Libro> filtra(List<Libro> libri) {
        if (genere == null) throw new IllegalArgumentException("Genere inesistente!");
        return libri.stream()
                .filter(l -> l.getGenere().equalsIgnoreCase(genere))
                .collect(Collectors.toList());
    }
}

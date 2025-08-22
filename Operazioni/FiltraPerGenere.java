package Operazioni;

import Libro.Libro;

public class FiltraPerGenere implements Filtra{
    private final String genere;

    public FiltraPerGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public boolean filtra(Libro libro) {
        if (genere == null) throw new IllegalArgumentException("Genere inesistente!");
        return libro.getGenere() == genere;
    }
}

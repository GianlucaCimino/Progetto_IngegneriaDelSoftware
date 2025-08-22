package Operazioni;

import Libro.Libro;

public class FiltraPerAutore implements Filtra{
    private final String autore;

    public FiltraPerAutore(String autore) {
        this.autore = autore;
    }

    @Override
    public boolean filtra(Libro libro) {
        if (autore == null) throw new IllegalArgumentException("Autore inesistente!");
        return libro.getAutore() == autore;
    }
}

package Operazioni;

import Libro.Libro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Libreria {
    private final List<Libro> Libreria = new ArrayList<Libro>();

    public void aggiungiLibro(Libro libro) {
        if(libro == null) throw new IllegalArgumentException("Libro inesistente!");
        Libreria.add(libro);
    }

    public boolean rimuoviLibro(Libro libro) {
        return Libreria.remove(libro);
    }

    public List<Libro> getLibreria(){
        return Collections.unmodifiableList(Libreria);
    }
}

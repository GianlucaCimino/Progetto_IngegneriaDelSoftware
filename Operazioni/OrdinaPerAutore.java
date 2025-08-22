package Operazioni;

import Libro.Libro;

import java.util.Comparator;

public class OrdinaPerAutore implements Ordina{

    @Override
    public Comparator<Libro> ordina() {
        return Comparator.comparing(Libro::getAutore, String.CASE_INSENSITIVE_ORDER);
    }
}

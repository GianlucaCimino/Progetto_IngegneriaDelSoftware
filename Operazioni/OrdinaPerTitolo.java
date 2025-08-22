package Operazioni;

import Libro.Libro;

import java.util.Comparator;

public class OrdinaPerTitolo implements Ordina{

    @Override
    public Comparator<Libro> ordina() {
        return Comparator.comparing(Libro::getTitolo, String.CASE_INSENSITIVE_ORDER);
    }
}

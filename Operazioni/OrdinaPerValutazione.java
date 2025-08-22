package Operazioni;

import Libro.Libro;

import java.util.Comparator;

public class OrdinaPerValutazione implements Ordina{
    @Override
    public Comparator<Libro> ordina() {
        return Comparator.comparingInt(Libro::getValutazione);    }
}

package Operazioni.Ordina;

import Libro.Libro;

import java.util.Comparator;
import java.util.List;

public interface Ordina {
    List<Libro> ordina(List<Libro> libri);
}

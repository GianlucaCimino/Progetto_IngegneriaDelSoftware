package Operazioni.Ordina;

import Libro.Libro;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdinaPerValutazione implements Ordina {

    @Override
    public List<Libro> ordina(List<Libro> libri) {
        return libri.stream()
                .sorted(Comparator.comparing(Libro::getValutazione).reversed())
                .collect(Collectors.toList());
    }
}

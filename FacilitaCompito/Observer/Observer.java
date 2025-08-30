package FacilitaCompito.Observer;

import Libro.Libro;

import java.util.List;

public interface Observer {
    void update(List<Libro> libri);
}

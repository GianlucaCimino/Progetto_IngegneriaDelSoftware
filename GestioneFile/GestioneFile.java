package GestioneFile;

import Libro.Libro;

import java.util.List;

public interface GestioneFile {
    void salva(List<Libro> libri, String path);
    List<Libro> carica(String path);
}

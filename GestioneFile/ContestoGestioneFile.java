package GestioneFile;

import Libro.Libro;

import java.util.List;

public class ContestoGestioneFile {
    private GestioneFile strategy;

    public ContestoGestioneFile(GestioneFile strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(GestioneFile strategy) {
        this.strategy = strategy;
    }

    public void salva(List<Libro> libri, String path) {
        strategy.salva(libri, path);
    }

    public List<Libro> carica(String path) {
        return strategy.carica(path);
    }
}

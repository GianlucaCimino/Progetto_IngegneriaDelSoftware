package Operazioni;

import Libro.Libro;

public class RicercaPerAutore implements Ricerca{
    private final String Autore;

    public RicercaPerAutore(String autore) {
        this.Autore = autore;
    }

    @Override
    public String ricerca(Libro libro) {
        if(libro.getAutore().equals(Autore)){
            return libro.getTitolo()+libro.getCodISBN()+libro.getGenere()+libro.getValutazione()+libro.getSl();
        }
        return "ISBN non trovato!";
    }
}

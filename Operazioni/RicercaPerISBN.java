package Operazioni;

import Libro.Libro;

public class RicercaPerISBN implements Ricerca{
    private final String CodISBN;

    public RicercaPerISBN(String codISBN) {
        this.CodISBN = codISBN;
    }

    @Override
    public String ricerca(Libro libro) {
        if(libro.getCodISBN().equals(CodISBN)){
            return libro.getTitolo()+libro.getAutore()+libro.getGenere()+libro.getValutazione()+libro.getSl();
        }
        return "ISBN non trovato!";
    }
}

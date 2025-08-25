package Operazioni;

import Libro.Libro;
import Libro.StatoLettura;
import Operazioni.Filtra.ContestoFiltra;
import Operazioni.Filtra.Filtra;
import Operazioni.Ordina.ContestoOrdina;
import Operazioni.Ordina.Ordina;
import Operazioni.Ricerca.ContestoRicerca;
import Operazioni.Ricerca.Ricerca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Libreria {
    private final List<Libro> libri = new ArrayList<Libro>();
    private ContestoOrdina contestoOrdina = new ContestoOrdina();
    private ContestoFiltra contestoFiltra = new ContestoFiltra();
    private ContestoRicerca contestoRicerca = new ContestoRicerca();

    public void aggiungiLibro(Libro libro) {
        if(libro == null) throw new IllegalArgumentException("Libro inesistente!");
        libri.add(libro);
    }

    public void rimuoviLibro(String ISBN) {
        for(Libro libro: libri){
            if(libro.getCodISBN().equals(ISBN)){
                libri.remove(libro);
                break;
            }
        }
    }

    public List<Libro> getLibri(){
        return Collections.unmodifiableList(libri);
    }

    public void modificaPerGenere(String ISBN, String Genere){
        for(Libro libro : libri){
            if(libro.getCodISBN().equals(ISBN)){
                libro.setGenere(Genere);
                break;
            }
        }
    }

    public void modificaPerStatoLettura(String ISBN, StatoLettura SL){
        for(Libro libro : libri){
            if(libro.getCodISBN().equals(ISBN)){
                libro.setSL(SL);
                break;
            }
        }
    }

    public void modificaPerValutazione(String ISBN, int Valutazione){
        for(Libro libro : libri){
            if(libro.getCodISBN().equals(ISBN)){
                libro.setValutazione(Valutazione);
                break;
            }
        }
    }

    public List<Libro> filtra(ContestoFiltra contesto) {
        return contesto.filtra(libri);
    }

    public List<Libro> ordina(ContestoOrdina contesto) {
        return contesto.ordina(libri);
    }

    public List<Libro> ricerca(ContestoRicerca contesto) {
        return contesto.ricerca(libri);
    }
}

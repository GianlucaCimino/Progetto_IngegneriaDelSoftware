package FacilitaCompito.Facade;

import GestioneFile.ContestoGestioneFile;
import GestioneFile.GestioneFileJSON;
import Libro.Libro;
import Operazioni.Filtra.ContestoFiltra;
import Operazioni.Filtra.FiltraPerAutore;
import Operazioni.Filtra.FiltraPerGenere;
import Operazioni.Filtra.FiltraPerValutazione;
import Operazioni.Libreria;
import Operazioni.Ordina.ContestoOrdina;
import Operazioni.Ordina.OrdinaPerAutore;
import Operazioni.Ordina.OrdinaPerTitolo;
import Operazioni.Ordina.OrdinaPerValutazione;
import Operazioni.Ricerca.ContestoRicerca;
import Operazioni.Ricerca.RicercaPerAutore;
import Operazioni.Ricerca.RicercaPerISBN;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;

import java.util.List;

public class LibreriaFacade {
    ContestoRicerca contestoRic = new ContestoRicerca();
    ContestoOrdina contestoOrd = new ContestoOrdina();
    ContestoFiltra contestoFil = new ContestoFiltra();
    ContestoGestioneFile contestoGes = new ContestoGestioneFile(null);
    Libreria libri = new Libreria();

    public List<Libro> FiltraPerAutore(List<Libro> libreria, String Autore){
        contestoFil.setStrategia(new FiltraPerAutore(Autore));
        return contestoFil.filtra(libreria);
    }

    public List<Libro> FiltraPerGenere(List<Libro> libreria, String Genere){
        contestoFil.setStrategia(new FiltraPerGenere(Genere));
        return contestoFil.filtra(libreria);
    }

    public List<Libro> FiltraPerValutazione(List<Libro> libreria, int Valutazione){
        contestoFil.setStrategia(new FiltraPerValutazione(Valutazione));
        return contestoFil.filtra(libreria);
    }

    public List<Libro> RicercaPerAutore(List<Libro> libreria, String Autore){
        contestoRic.setStrategia(new RicercaPerAutore(Autore));
        return contestoRic.ricerca(libreria);
    }

    public List<Libro> RicercaPerISBN(List<Libro> libreria, String CodISBN){
        contestoRic.setStrategia(new RicercaPerISBN(CodISBN));
        return contestoRic.ricerca(libreria);
    }

    public List<Libro> OrdinaPerAutore(List<Libro> libreria){
        contestoOrd.setStrategia(new OrdinaPerAutore());
        return contestoOrd.ordina(libreria);
    }

    public List<Libro> OrdinaPerTitolo(List<Libro> libreria){
        contestoOrd.setStrategia(new OrdinaPerTitolo());
        return contestoOrd.ordina(libreria);
    }

    public List<Libro> OrdinaPerValutazione(List<Libro> libreria){
        contestoOrd.setStrategia(new OrdinaPerValutazione());
        return contestoOrd.ordina(libreria);
    }

    public void SalvaLibri(List<Libro> libreria, String NomeFile){
        contestoGes.setStrategy(new GestioneFileJSON());
        contestoGes.salva(libreria, NomeFile);
    }

    public List<Libro> CaricaLibri(String NomeFile){
        contestoGes.setStrategy(new GestioneFileJSON());
        return contestoGes.carica(NomeFile);
    }

    public List<Libro> getLibri(){
        return libri.getLibri();
    }

    public void aggiungiLibro(Libro libro){
        libri.aggiungiLibro(libro);
    }

    public void setLibri(List<Libro> nuoviLibri){
        libri.setLibri(nuoviLibri);
    }

    public void rimuoviLibro(String ISBN){
        libri.rimuoviLibro(ISBN);
    }
}

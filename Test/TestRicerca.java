package Test;

import Libro.Libro;
import Libro.StatoLettura;
import Operazioni.Libreria;
import Operazioni.Ricerca.ContestoRicerca;
import Operazioni.Ricerca.RicercaPerAutore;
import Operazioni.Ricerca.RicercaPerISBN;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestRicerca {
    @Test
    public void TestRicercaPerISBN(){
        Libreria lib = new Libreria();
        Libro l1 = new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build();
        Libro l2 = new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(3).build();
        Libro l3 = new Libro.Builder("Harry Potter e la pietra filosofale","J. K. Rowling","421522257").setSl(StatoLettura.DA_LEGGERE).setGenere("Fantasy").setValutazione(1).build();
        Libro l4 = new Libro.Builder("Harry Potter e il calice di fuoco","J. K. Rowling","875924553").setSl(StatoLettura.IN_LETTURA).setGenere("Fantasy").setValutazione(3).build();
        Libro l5 = new Libro.Builder("Baudolino","U. Eco","745762157").build();

        lib.aggiungiLibro(l1);
        lib.aggiungiLibro(l2);
        lib.aggiungiLibro(l3);
        lib.aggiungiLibro(l4);
        lib.aggiungiLibro(l5);

        ContestoRicerca contesto = new ContestoRicerca();
        contesto.setStrategia(new RicercaPerISBN("123123123"));

        List<Libro> risultato = lib.ricerca(contesto);
        assertEquals(1, risultato.size());
    }

    @Test
    public void TestRicercaPerAutore(){
        Libreria lib = new Libreria();
        Libro l1 = new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build();
        Libro l2 = new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(3).build();
        Libro l3 = new Libro.Builder("Harry Potter e la pietra filosofale","J. K. Rowling","421522257").setSl(StatoLettura.DA_LEGGERE).setGenere("Fantasy").setValutazione(1).build();
        Libro l4 = new Libro.Builder("Harry Potter e il calice di fuoco","J. K. Rowling","875924553").setSl(StatoLettura.IN_LETTURA).setGenere("Fantasy").setValutazione(3).build();
        Libro l5 = new Libro.Builder("Baudolino","U. Eco","745762157").build();

        lib.aggiungiLibro(l1);
        lib.aggiungiLibro(l2);
        lib.aggiungiLibro(l3);
        lib.aggiungiLibro(l4);
        lib.aggiungiLibro(l5);

        ContestoRicerca contesto = new ContestoRicerca();
        contesto.setStrategia(new RicercaPerAutore("U. Eco"));

        List<Libro> risultato = lib.ricerca(contesto);
        assertEquals(2, risultato.size());
    }
}

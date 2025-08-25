package Test;

import Libro.Libro;
import Libro.StatoLettura;
import Operazioni.Libreria;
import Operazioni.Ordina.ContestoOrdina;
import Operazioni.Ordina.OrdinaPerAutore;
import Operazioni.Ordina.OrdinaPerTitolo;
import Operazioni.Ordina.OrdinaPerValutazione;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrdinamento {
    @Test
    public void TestOrninaPerAutore(){
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

        ContestoOrdina contesto = new ContestoOrdina();
        contesto.setStrategia(new OrdinaPerAutore());

        List<Libro> risultato = lib.ordina(contesto);
        assertEquals("Antoine de Saint-Exupéry", risultato.get(0).getAutore());
        assertEquals("J. K. Rowling", risultato.get(2).getAutore());
        assertEquals("U. Eco", risultato.get(3).getAutore());
    }

    @Test
    public void TestOrninaPerTitolo(){
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

        ContestoOrdina contesto = new ContestoOrdina();
        contesto.setStrategia(new OrdinaPerTitolo());

        List<Libro> risultato = lib.ordina(contesto);
        assertEquals("Baudolino", risultato.get(0).getTitolo());
        assertEquals("Il nome della rosa", risultato.get(3).getTitolo());
        assertEquals("Piccolo Principe", risultato.get(4).getTitolo());
    }

    @Test
    public void TestOrninaPerValutazione(){
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

        ContestoOrdina contesto = new ContestoOrdina();
        contesto.setStrategia(new OrdinaPerValutazione());

        List<Libro> risultato = lib.ordina(contesto);
        assertEquals("Harry Potter e la pietra filosofale", risultato.get(2).getTitolo());
        assertEquals("Il nome della rosa", risultato.get(0).getTitolo());
    }
}

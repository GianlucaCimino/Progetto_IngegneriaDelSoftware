package Test;

import Libro.Libro;
import  Libro.StatoLettura;
import Operazioni.Filtra.*;
import Operazioni.Libreria;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFiltraggio {
    @Test
    public void TestFiltraPerGenere(){
        Libreria lib = new Libreria();
        Libro l1 = new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build();
        Libro l2 = new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(5).build();
        Libro l3 = new Libro.Builder("Harry Potter e la pietra filosofale","J. K. Rowling","421522257").setSl(StatoLettura.DA_LEGGERE).setGenere("Fantasy").setValutazione(1).build();
        Libro l4 = new Libro.Builder("Harry Potter e il calice di fuoco","J. K. Rowling","875924553").setSl(StatoLettura.IN_LETTURA).setGenere("Fantasy").setValutazione(3).build();
        Libro l5 = new Libro.Builder("Baudolino","U. Eco","745762157").build();

        lib.aggiungiLibro(l1);
        lib.aggiungiLibro(l2);
        lib.aggiungiLibro(l3);
        lib.aggiungiLibro(l4);
        lib.aggiungiLibro(l5);

        ContestoFiltra contesto = new ContestoFiltra();
        contesto.setStrategia(new FiltraPerGenere("Fantasy"));

        List<Libro> risultato = lib.filtra(contesto);
        assertEquals(2, risultato.size());
    }

    @Test
    public void TestFiltraPerAutore(){
        Libreria lib = new Libreria();
        Libro l1 = new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build();
        Libro l2 = new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(5).build();
        Libro l3 = new Libro.Builder("Harry Potter e la pietra filosofale","J. K. Rowling","421522257").setSl(StatoLettura.DA_LEGGERE).setGenere("Fantasy").setValutazione(1).build();
        Libro l4 = new Libro.Builder("Harry Potter e il calice di fuoco","J. K. Rowling","875924553").setSl(StatoLettura.IN_LETTURA).setGenere("Fantasy").setValutazione(3).build();
        Libro l5 = new Libro.Builder("Baudolino","U. Eco","745762157").build();

        lib.aggiungiLibro(l1);
        lib.aggiungiLibro(l2);
        lib.aggiungiLibro(l3);
        lib.aggiungiLibro(l4);
        lib.aggiungiLibro(l5);

        ContestoFiltra contesto = new ContestoFiltra();
        contesto.setStrategia(new FiltraPerAutore("Antoine de Saint-Exupéry"));

        List<Libro> risultato = lib.filtra(contesto);
        assertEquals(1, risultato.size());
    }

    @Test
    public void TestFiltraPerValutazione(){
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

        ContestoFiltra contesto = new ContestoFiltra();
        contesto.setStrategia(new FiltraPerValutazione(3));

        List<Libro> risultato = lib.filtra(contesto);
        assertEquals(2, risultato.size());
    }

}

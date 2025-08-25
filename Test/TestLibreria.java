package Test;

import Libro.Libro;
import Libro.StatoLettura;
import Operazioni.Libreria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLibreria {
    @Test
    public void TestAggiungiLibro(){
        Libreria lib = new Libreria();
        Libro l1 = new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build();
        Libro l2 = new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(5).build();

        lib.aggiungiLibro(l1);
        lib.aggiungiLibro(l2);
        int contaLibri = lib.getLibri().size();

        assertEquals(contaLibri, 2);
    }

    @Test
    public void TestRimuoviLibro(){
        Libreria lib = new Libreria();
        Libro l1 = new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build();
        Libro l2 = new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(5).build();

        lib.aggiungiLibro(l1);
        lib.aggiungiLibro(l2);
        int contaLibri = lib.getLibri().size();
        assertEquals(contaLibri, 2);

        lib.rimuoviLibro("123123123");
        contaLibri = lib.getLibri().size();
        assertEquals(contaLibri, 1);
    }

    @Test
    public void TestModificaPerGenere(){
        Libreria lib = new Libreria();
        Libro l1 = new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build();
        Libro l2 = new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(5).build();

        lib.aggiungiLibro(l1);
        lib.aggiungiLibro(l2);
        String Genere= "Fantasy";

        lib.modificaPerGenere("123123123",Genere);
        assertEquals(l2.getGenere(),Genere);
    }

    @Test
    public void TestModificaPerStatoLettura(){
        Libreria lib = new Libreria();
        Libro l1 = new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build();
        Libro l2 = new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(5).build();

        lib.aggiungiLibro(l1);
        lib.aggiungiLibro(l2);
        StatoLettura SL = StatoLettura.DA_LEGGERE;

        lib.modificaPerStatoLettura("123123123",SL);
        assertEquals(l2.getSl(),SL);
    }

    @Test
    public void TestModificaPerVautazione(){
        Libreria lib = new Libreria();
        Libro l1 = new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build();
        Libro l2 = new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(5).build();

        lib.aggiungiLibro(l1);
        lib.aggiungiLibro(l2);
        int Valutazione= 4;

        lib.modificaPerValutazione("123123123",Valutazione);
        assertEquals(l2.getValutazione(),Valutazione);
    }
}

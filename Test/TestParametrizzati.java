package Test;

import FacilitaCompito.Facade.LibreriaFacade;
import Libro.Libro;
import Libro.StatoLettura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestParametrizzati {

    private LibreriaFacade Facade;

    @BeforeEach
    void setup(){
        Facade = new LibreriaFacade();
        Facade.aggiungiLibro(new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build());
        Facade.aggiungiLibro(new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(3).build());
        Facade.aggiungiLibro(new Libro.Builder("Harry Potter e la pietra filosofale","J. K. Rowling","421522257").setSl(StatoLettura.DA_LEGGERE).setGenere("Fantasy").setValutazione(1).build());
        Facade.aggiungiLibro(new Libro.Builder("Harry Potter e il calice di fuoco","J. K. Rowling","875924553").setSl(StatoLettura.IN_LETTURA).setGenere("Fantasy").setValutazione(3).build());
        Facade.aggiungiLibro(new Libro.Builder("Baudolino","U. Eco","745762157").build());
    }

    @ParameterizedTest
    @CsvSource({
            "J. K. Rowling, 2",
            "Antoine de Saint-Exupéry, 1",
            "Dante Alighieri, 0"
    })
    void TestRicercaPerAutore(String autore, int risultatoAtteso){
        List<Libro> trovati = Facade.RicercaPerAutore(Facade.getLibri(), autore);
        assertEquals(risultatoAtteso, trovati.size());
    }

    @ParameterizedTest
    @CsvSource({
            "Romanzo, 3",
            "Narratico, 0",
            "Fantasy, 2"
    })
    void TestFiltraPerGenere(String genere, int risultatoAtteso){
        List<Libro> filtrati = Facade.FiltraPerGenere(Facade.getLibri(), genere);
        assertEquals(risultatoAtteso, filtrati.size());
    }

    @ParameterizedTest
    @CsvSource({
            "IN_LETTURA, LETTO",
            "Fantasy, Narrativo",
            "3,5"
    })
    void testModifiche(String corrente, String nuovoValore) {
        String isbn = "875924553";

        switch (corrente) {
            case "3" -> Facade.modificaPerValutazione(isbn, Integer.parseInt(nuovoValore));
            case "Fantasy" -> Facade.modificaPerGenere(isbn, nuovoValore);
            case "StatoLettura.IN_LETTURA" -> Facade.modificaPerStatoLettura(isbn, StatoLettura.valueOf(nuovoValore));
        }

        Libro modificato = Facade.getLibri().get(3);

        switch (corrente) {
            case "3" -> assertEquals(Integer.parseInt(nuovoValore), modificato.getValutazione());
            case "Fantasy" -> assertEquals(nuovoValore, modificato.getGenere());
            case "StatoLettura.IN_LETTURA" -> assertEquals(nuovoValore, modificato.getSl());
        }
    }
}

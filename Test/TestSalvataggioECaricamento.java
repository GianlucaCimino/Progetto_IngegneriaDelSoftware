package Test;

import GestioneFile.ContestoGestioneFile;
import GestioneFile.GestioneFile;
import GestioneFile.GestioneFileJSON;
import Libro.Libro;
import Libro.StatoLettura;
import Operazioni.Libreria;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSalvataggioECaricamento {
    private ContestoGestioneFile contesto;
    private String FileTest;

    @Test
    public void TestSalvataggio() {
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

        contesto = new ContestoGestioneFile(null);
        contesto.setStrategy(new GestioneFileJSON());

        FileTest = "testLibri.json";

        contesto.salva(lib.getLibri(),FileTest);
        File file = new File(FileTest);

        assertTrue(file.exists(), "Il file JSON non è stato creato.");
        assertTrue(file.length() > 0, "Il file JSON non contiene libri.");

        file.delete();
    }

    @Test
    public void TestCaricamento(){
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

        contesto = new ContestoGestioneFile(null);
        contesto.setStrategy(new GestioneFileJSON());

        FileTest = "testLibri.json";

        contesto.salva(lib.getLibri(), FileTest);
        File file = new File(FileTest);

        List<Libro> libreriaCaricata = contesto.carica(FileTest);

        assertEquals(5, libreriaCaricata.size());
        assertEquals("Piccolo Principe", libreriaCaricata.get(0).getTitolo());
        assertEquals("J. K. Rowling", libreriaCaricata.get(3).getAutore());

        file.delete();
    }
}

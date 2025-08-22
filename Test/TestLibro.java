package Test;
import Libro.Libro;
import Libro.StatoLettura;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestLibro {
    @Test
    public void TestLibroBase(){
        Libro l =  new Libro.Builder("Divina Commedia","Dante Alighieri","123456789").build();
        assertEquals("Divina Commedia",l.getTitolo());
        assertEquals("Dante Alighieri",l.getAutore());
        assertEquals("123456789",l.getCodISBN());
    }

    @Test
    public void TestLibroCompleto(){
        Libro l = new Libro.Builder("Il nome della rosa","U. Eco","123123123").setSl(StatoLettura.LETTO).setGenere("Romanzo").setValutazione(5).build();

        assertEquals("Il nome della rosa", l.getTitolo());
        assertEquals("U. Eco", l.getAutore());
        assertEquals("123123123", l.getCodISBN());
        assertEquals("Romanzo", l.getGenere());
        assertEquals(5, l.getValutazione());
        assertEquals(StatoLettura.LETTO, l.getSl());
    }

    @Test
    public void TestMancante(){
        Libro l = new Libro.Builder("Piccolo Principe","Antoine de Saint-Exupéry", "987123645").build();

        assertNull(l.getGenere());
        assertNull(l.getSl());
    }
}

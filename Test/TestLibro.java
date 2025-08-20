package Test;
import Libro.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestLibro {
    @Test
    public void TestLibroBase(){
        Libro l =  new Libro.Builder("Pippi","Dipre","123456789").build();
        assertEquals("Pippi",l.getTitolo());
    }
}

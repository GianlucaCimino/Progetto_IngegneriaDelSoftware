package FacilitaCompito.Command;

import FacilitaCompito.Facade.LibreriaFacade;
import Libro.Libro;

public class ModificaLibroPerValutazione implements Command{
    private LibreriaFacade Facade;
    private String ISBN;
    private int Valutazione;
    private int ValutazioneRimossa;

    public ModificaLibroPerValutazione(String isbn, int valutazione){
        this.Valutazione = valutazione;
        this.ISBN = isbn;
    }

    @Override
    public void execute() {
        for (Libro libro: Facade.getLibri()){
            if(libro.getCodISBN().equals(ISBN))
                ValutazioneRimossa = libro.getValutazione();
            break;
        }
        Facade.modificaPerValutazione(ISBN,Valutazione);
    }

    @Override
    public void undo() {
        Facade.modificaPerValutazione(ISBN,ValutazioneRimossa);
    }
}

package FacilitaCompito.Command;

import FacilitaCompito.Facade.LibreriaFacade;
import Libro.Libro;

public class RimuoviLibroCommand implements Command{
    private LibreriaFacade Facade;
    private Libro LibroRimosso;
    private String ISBN;

    public RimuoviLibroCommand(LibreriaFacade facade, String isbn){
        this.Facade = facade;
        this.ISBN = isbn;
    }

    @Override
    public void execute() {
        LibroRimosso = null;
        for (Libro libro: Facade.getLibri()){
            if(libro.getCodISBN().equals(ISBN)) {
                LibroRimosso = libro;
                break;
            }
        }
        Facade.rimuoviLibro(ISBN);
    }

    @Override
    public void undo() {
        if(LibroRimosso != null)
        Facade.aggiungiLibro(LibroRimosso);
    }
}

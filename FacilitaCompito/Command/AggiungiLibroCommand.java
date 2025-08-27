package FacilitaCompito.Command;

import FacilitaCompito.Facade.LibreriaFacade;
import Libro.Libro;

public class AggiungiLibroCommand implements Command{
    private LibreriaFacade Facade;
    private Libro Libro;

    public AggiungiLibroCommand(LibreriaFacade facade, Libro libro){
        this.Facade = facade;
        this.Libro = libro;
    }

    @Override
    public void execute() {
        Facade.aggiungiLibro(Libro);
    }

    @Override
    public void undo() {
        Facade.rimuoviLibro(Libro.getCodISBN());
    }
}

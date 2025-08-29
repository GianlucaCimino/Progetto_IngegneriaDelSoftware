package FacilitaCompito.Command;

import FacilitaCompito.Facade.LibreriaFacade;
import Libro.Libro;
import Libro.StatoLettura;

public class ModificaLibroPerStatoDiLettura implements Command{
    private LibreriaFacade Facade;
    private String ISBN;
    private StatoLettura SL;
    private StatoLettura SLRimosso;

    public ModificaLibroPerStatoDiLettura(String isbn, StatoLettura sl){
        this.SL = sl;
        this.ISBN = isbn;
    }

    @Override
    public void execute() {
        for (Libro libro: Facade.getLibri()){
            if(libro.getCodISBN().equals(ISBN))
                SLRimosso = libro.getSl();
            break;
        }
        Facade.modificaPerStatoLettura(ISBN,SL);
    }

    @Override
    public void undo() {
        Facade.modificaPerStatoLettura(ISBN,SLRimosso);
    }
}

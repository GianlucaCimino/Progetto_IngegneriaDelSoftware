package FacilitaCompito.Command;

import FacilitaCompito.Facade.LibreriaFacade;
import Libro.Libro;

public class ModificaLibroPerGenere implements Command{
    private LibreriaFacade Facade;
    private String ISBN;
    private String Genere;
    private String GenereRimosso;

    public ModificaLibroPerGenere(String isbn, String genere){
        this.Genere = genere;
        this.ISBN = isbn;
    }

    @Override
    public void execute() {
        for (Libro libro: Facade.getLibri()){
            if(libro.getCodISBN().equals(ISBN))
                GenereRimosso = libro.getGenere();
            break;
        }
        Facade.modificaPerGenere(ISBN,Genere);
    }

    @Override
    public void undo() {
        Facade.modificaPerGenere(ISBN,GenereRimosso);
    }
}

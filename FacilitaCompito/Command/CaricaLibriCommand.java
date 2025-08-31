package FacilitaCompito.Command;

import FacilitaCompito.Facade.LibreriaFacade;
import Libro.Libro;

import java.util.ArrayList;
import java.util.List;

public class CaricaLibriCommand implements Command{
    private LibreriaFacade Facade;
    private String NomeFile;
    private List<Libro> StatoPrecedente;

    public CaricaLibriCommand(LibreriaFacade facade, String nomeFile){
        this.Facade = facade;
        this.NomeFile = nomeFile;
    }

    @Override
    public void execute() {
        StatoPrecedente = new ArrayList<Libro>(Facade.getLibri());
        List<Libro> Caricati = Facade.CaricaLibri(NomeFile);
        if(Caricati != null){
            Facade.setLibri(Caricati);
        }
    }

    @Override
    public void undo() {
        if(StatoPrecedente != null)
            Facade.setLibri(StatoPrecedente);
    }
}

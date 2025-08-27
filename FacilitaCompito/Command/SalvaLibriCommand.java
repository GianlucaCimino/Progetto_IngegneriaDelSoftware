package FacilitaCompito.Command;

import FacilitaCompito.Facade.LibreriaFacade;

public class SalvaLibriCommand implements Command{
    private LibreriaFacade Facade;
    private String NomeFile;

    public SalvaLibriCommand(LibreriaFacade facade, String nomeFile){
        this.Facade = facade;
        this.NomeFile = nomeFile;
    }

    @Override
    public void execute() {
        Facade.SalvaLibri(Facade.getLibri(),NomeFile);
    }

    @Override
    public void undo() {

    }
}

package FacilitaCompito.Command;

import java.awt.*;
import java.util.Stack;

public class GestioneCommand {
    private final Stack<Command> Storico = new Stack<>();

    public void executeCommand(Command com){
        com.execute();
        Storico.push(com);
    }

    public void undoCommand(){
        if(!Storico.isEmpty()){
            Command com = Storico.pop();
            com.undo();
        }
    }

    public boolean esisteStorico(){
        return !Storico.isEmpty();
    }
}

package Operazioni;

import Libro.Libro;
import Libro.StatoLettura;

public class ModificaPerStatoLettura implements Modifica{
    private final String CodISBN;
    private final StatoLettura SL;

    public ModificaPerStatoLettura(String codISBN, StatoLettura sl) {
        this.CodISBN = codISBN;
        this.SL = sl;
    }

    @Override
    public void modifica(Libro libro) {
        if(libro.getCodISBN().equals(CodISBN)){
            libro.setSL(SL);
        }
    }
}

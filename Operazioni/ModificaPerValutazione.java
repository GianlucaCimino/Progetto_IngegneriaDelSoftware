package Operazioni;

import Libro.Libro;

public class ModificaPerValutazione implements Modifica{
    private final String CodISBN;
    private final int Valutazione;

    public ModificaPerValutazione(String codISBN, int valutazione) {
        this.CodISBN = codISBN;
        this.Valutazione = valutazione;
    }


    @Override
    public void modifica(Libro libro) {
        if(libro.getCodISBN().equals(CodISBN)){
            libro.setValutazione(Valutazione);
        }
    }
}

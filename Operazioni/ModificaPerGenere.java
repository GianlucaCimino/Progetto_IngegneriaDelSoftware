package Operazioni;

import Libro.Libro;

public class ModificaPerGenere implements Modifica{
    private final String CodISBN;
    private final String Genere;

    public ModificaPerGenere(String codISBN, String genere) {
        this.CodISBN = codISBN;
        this.Genere = genere;
    }

    @Override
    public void modifica(Libro libro) {
        if(libro.getCodISBN().equals(CodISBN)){
            libro.setGenere(Genere);
        }
    }
}

package Libro;

public class Libro {
    private final String Titolo;
    private final String Autore;
    private final String CodISBN;
    private String Genere;
    private int Valutazione;
    private StatoLettura SL;

    private Libro(Builder builder){
        this.Titolo = builder.titolo;
        this.Autore = builder.autore;
        this.CodISBN = builder.codISBN;
        this.Genere = builder.genere;
        this.Valutazione = builder.valutazione;
        this.SL = builder.sl;
    }

    public static class Builder{
        private final String titolo;
        private final String autore;
        private final String codISBN;
        private String genere = "Romanzo";
        private int valutazione = 1;
        private StatoLettura sl = StatoLettura.DA_LEGGERE;

        public Builder(String titolo, String autore, String codISBN){
            if (titolo == null || titolo.isBlank())
                throw new IllegalArgumentException("Titolo obbligatorio!");
            if (autore == null || autore.isBlank())
                throw new IllegalArgumentException("Autore obbligatorio!");
            if (codISBN == null || codISBN.isBlank())
                throw new IllegalArgumentException("Codice ISBN obbligatorio!");

            this.titolo = titolo;
            this.autore = autore;
            this.codISBN = codISBN;
        }

        public Builder setGenere(String genere) { this.genere = genere; return this;}
        public Builder setSl(StatoLettura sl) { this.sl = sl; return this;}
        public Builder setValutazione(int valutazione) { this.valutazione = valutazione; return this;}

        public Libro build(){
            return new Libro(this);
        }
    }

    public void setGenere(String gen) { this.Genere = gen;}
    public void setSL(StatoLettura stato){ this.SL = stato;}
    public void setValutazione(int val){ this.Valutazione = val;}
    public String getTitolo() { return Titolo;}
    public String getAutore() { return Autore;}
    public String getCodISBN() { return CodISBN;}
    public String getGenere() { return Genere;}
    public StatoLettura getSl() { return SL;}
    public int getValutazione() { return Valutazione;}

}
package GUI;

import FacilitaCompito.Command.*;
import FacilitaCompito.Facade.LibreriaFacade;
import FacilitaCompito.Observer.Observer;
import Libro.Libro;
import Libro.StatoLettura;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;

public class LibreriaGUI extends JFrame implements Observer {
    private GestioneCommand GesCom = new GestioneCommand();
    private LibreriaFacade Facade;
    private JTable table;
    private DefaultTableModel tableModel;

    public LibreriaGUI(LibreriaFacade facade){
        this.Facade = facade;
        facade.attach(this);

        setTitle("Libreria Personale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);

        String[] NomeColonne = {"Titolo","Autore","Codice ISBN","Genere","Valutazione","Stato di lettura"};
        tableModel = new DefaultTableModel(NomeColonne,0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel PannelloBottoni = new JPanel();
        JButton BottoneAggiungi = new JButton("Aggiungi Libro");
        JButton BottoneRimuovi = new JButton("Rimuovi Libro");
        JButton BottoneModifica = new JButton("Modifica");
        JButton BottoneSalva = new JButton("Salva Libreria");
        JButton BottoneCarica = new JButton("Carica Libreria");
        JButton BottoneFiltra = new JButton("Filtra");
        JButton BottoneOrdina = new JButton("Ordina");
        JButton BottoneRicerca = new JButton("Ricerca");
        JButton BottoneLibreria = new JButton("Torna alla libreria");
        JButton BottoneUndo = new JButton("Torna indietro");

        PannelloBottoni.add(BottoneUndo);
        PannelloBottoni.add(BottoneAggiungi);
        PannelloBottoni.add(BottoneRimuovi);
        PannelloBottoni.add(BottoneModifica);
        PannelloBottoni.add(BottoneSalva);
        PannelloBottoni.add(BottoneCarica);
        PannelloBottoni.add(BottoneFiltra);
        PannelloBottoni.add(BottoneOrdina);
        PannelloBottoni.add(BottoneRicerca);

        add(PannelloBottoni, BorderLayout.SOUTH);
        add(BottoneLibreria,BorderLayout.NORTH);

        BottoneAggiungi.addActionListener(e -> AzioneBottoneAggiungi());
        BottoneRimuovi.addActionListener(e -> AzioneBottoneRimuovi());
        BottoneModifica.addActionListener(e -> AzioneBottoneModifica());

        BottoneLibreria.addActionListener(e -> update(Facade.getLibri()));

        BottoneUndo.addActionListener(e -> {
            if(GesCom.esisteStorico()){
                GesCom.undoCommand();
                update(Facade.getLibri());
            }
            else{
                JOptionPane.showMessageDialog(this, "Nessuna azione da annullare!");
            }
        });

        BottoneFiltra.addActionListener(e -> AzioneBottoneFiltra());
        BottoneOrdina.addActionListener(e -> AzioneBottoneOrdina());
        BottoneRicerca.addActionListener(e -> AzioneBottoneRicerca());
        BottoneSalva.addActionListener(e -> AzioneBottoreSalva());
        BottoneCarica.addActionListener(e -> AzioneBottoreCarica());

    }

    private void AzioneBottoreCarica() {
        JFileChooser Caricato = new JFileChooser();
        Caricato.setDialogTitle("Seleziona file da caricare:");

        FileNameExtensionFilter filtra = new FileNameExtensionFilter("File JSON (*.json)", "json");
        Caricato.setFileFilter(filtra);

        int Risultato = Caricato.showOpenDialog(this);
        if (Risultato == JFileChooser.APPROVE_OPTION){
            File file = Caricato.getSelectedFile();

            try{
                GesCom.executeCommand(new CaricaLibriCommand(Facade, file.getAbsolutePath()));
                update(Facade.getLibri());
                JOptionPane.showMessageDialog(this,"Libreria caricata!");
            }catch (Exception e){
                JOptionPane.showMessageDialog(this,e.getMessage(),"Errore nel caricamento!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void AzioneBottoreSalva(){
        JFileChooser Salvato = new JFileChooser();
        Salvato.setDialogTitle("Seleziona dove salvare il file:");

        FileNameExtensionFilter filtra = new FileNameExtensionFilter("File JSON (*.json)", "json");
        Salvato.setFileFilter(filtra);

        int Risultato = Salvato.showSaveDialog(this);
        if (Risultato == JFileChooser.APPROVE_OPTION) {
            File file = Salvato.getSelectedFile();

            try {
                Facade.SalvaLibri(Facade.getLibri(), file.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Libreria salvata!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Errore nel salvataggio!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void AzioneBottoneRicerca() {
        int verifica = 0;
        String[] ricerche = { "Codice ISBN", "Autore"};
        JComboBox<String> Ric = new JComboBox<>(ricerche);

        Object[] Compilati = {
                "Scegli cosa cercare",Ric
        };

        List<Libro> ricercati = null;
        JTextField ISBN;
        JTextField Autore;

        int Risultato = JOptionPane.showConfirmDialog(this, Compilati, "Ricerca libri:", JOptionPane.OK_CANCEL_OPTION);

        if(Risultato == JOptionPane.OK_OPTION) {
            try {
                String ricerca = (String) Ric.getSelectedItem();
                ISBN = new JTextField();
                Autore = new JTextField();

                if (ricerca.equals("Codice ISBN")) {
                    Object[] CompilaISBN = {
                            "Codice ISBN", ISBN
                    };

                    int Risultato1 = JOptionPane.showConfirmDialog(this, CompilaISBN, "Inserisci un codice ISBN:", JOptionPane.OK_CANCEL_OPTION);
                    if(Risultato1 == JOptionPane.OK_OPTION){
                        String isbn = ISBN.getText();

                        for(Libro l: Facade.getLibri()) {
                            if (l.getCodISBN().equals(isbn)){
                                verifica = 0;
                                ricercati = Facade.RicercaPerISBN(Facade.getLibri(), isbn);
                                break;
                            }
                            verifica = 1;
                        }
                    }
                }
                else if (ricerca.equals("Autore")) {
                    Object[] CompilaAutore = {
                            "Autore", Autore
                    };

                    int Risultato2 = JOptionPane.showConfirmDialog(this, CompilaAutore, "Inserisci un autore:", JOptionPane.OK_CANCEL_OPTION);
                    if(Risultato2 == JOptionPane.OK_OPTION){
                        String autore = Autore.getText();

                        for(Libro l: Facade.getLibri()){
                            if(l.getAutore().equals(autore)) {
                                verifica = 0;
                                ricercati = Facade.RicercaPerAutore(Facade.getLibri(), autore);
                                break;
                            }
                            verifica = 2;
                        }
                    }
                }
                else
                    throw new IllegalArgumentException("Errore!");

            }catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this,e.getMessage(), "Errore!", JOptionPane.ERROR_MESSAGE);
            }
            if (ricercati!=null)
                update(ricercati);
            else{
                if(verifica==1){
                    JOptionPane.showMessageDialog(this, "Codice ISBN non trovato!");
                }
                else if(verifica==2){
                    JOptionPane.showMessageDialog(this, "Autore non trovato!");
                }
            }
        }
    }

    private void AzioneBottoneOrdina() {
        String[] ordine = { "Titolo", "Autore", "Valutazione"};
        JComboBox<String> Ord = new JComboBox<>(ordine);

        Object[] Compilati = {
                "Scegli come ordinare",Ord
        };

        List<Libro> ordinati = null;

        int Risultato = JOptionPane.showConfirmDialog(this, Compilati, "Ordina libri:", JOptionPane.OK_CANCEL_OPTION);

        if(Risultato == JOptionPane.OK_OPTION) {
            try {
                String ordina = (String) Ord.getSelectedItem();

                if (ordina.equals("Titolo")) {
                    ordinati = Facade.OrdinaPerTitolo(Facade.getLibri());
                }
                else if (ordina.equals("Valutazione")) {
                    ordinati = Facade.OrdinaPerValutazione(Facade.getLibri());
                }
                else if (ordina.equals("Autore")) {
                    ordinati = Facade.OrdinaPerAutore(Facade.getLibri());
                }
                else
                    throw new IllegalArgumentException("Errore!");

            }catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this,e.getMessage(), "Errore!", JOptionPane.ERROR_MESSAGE);
            }
            if (ordinati!=null)
                update(ordinati);
        }
    }

    private void AzioneBottoneFiltra() {
        String[] scelte = { "Genere", "Autore", "Valutazione"};
        JComboBox<String> Sce = new JComboBox<>(scelte);

        Object[] Compilati = {
                "Scegli come filtrare",Sce
        };

        List<Libro> filtrati = null;

        int Risultato = JOptionPane.showConfirmDialog(this, Compilati, "Filtra libri:", JOptionPane.OK_CANCEL_OPTION);

        if(Risultato == JOptionPane.OK_OPTION) {
            try {
                String scelta = (String) Sce.getSelectedItem();

                if (scelta.equals("Genere")) {
                    String gen = "";
                    String[] Generi = {"Romanzo", "Giallo", "Horror", "Storico", "Fantasy", "Avventura", "Narrativo", "Altro"};
                    JComboBox<String> Gen = new JComboBox(Generi);
                    Object[] CompilaGenere = {
                            "Genere", Gen
                    };
                    int Risultato1 = JOptionPane.showConfirmDialog(this, CompilaGenere, "Inserisci un genere:", JOptionPane.OK_CANCEL_OPTION);
                    if (Risultato1 == JOptionPane.OK_OPTION) {
                        gen = (String) Gen.getSelectedItem();
                    }
                    filtrati = Facade.FiltraPerGenere(Facade.getLibri(), gen);
                }
                else if (scelta.equals("Valutazione")) {
                    int val = 0;
                    Integer[] valutazione = {1, 2, 3, 4, 5};
                    JComboBox<Integer> Val = new JComboBox(valutazione);
                    Object[] CompilaValutazione = {
                            "Valutazione", Val
                    };
                    int Risultato2 = JOptionPane.showConfirmDialog(this, CompilaValutazione, "Inserisci una valutazione:", JOptionPane.OK_CANCEL_OPTION);
                    if (Risultato2 == JOptionPane.OK_OPTION) {
                        val = (Integer) Val.getSelectedItem();
                    }
                    filtrati = Facade.FiltraPerValutazione(Facade.getLibri(), val);
                }
                else if (scelta.equals("Autore")) {
                    JTextField Aut = new JTextField();
                    Object[] CompilaAutore = {
                            "Autore", Aut
                    };
                    int Risultato3 = JOptionPane.showConfirmDialog(this, CompilaAutore, "Inserisci un autore:", JOptionPane.OK_CANCEL_OPTION);
                    if (Risultato3 == JOptionPane.OK_OPTION) {
                        String aut = Aut.getText();
                        System.out.println("Scelta: '" + scelta + "'");
                        System.out.println("Autore inserito: '" + aut + "'");
                        filtrati = Facade.FiltraPerAutore(Facade.getLibri(), aut);
                    }
                }
                else
                    throw new IllegalArgumentException("Errore!");

            }catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this,e.getMessage(), "Errore!", JOptionPane.ERROR_MESSAGE);
            }
            if (filtrati!=null)
                update(filtrati);
        }
    }


    private void AzioneBottoneModifica() {
        JTextField ISBN = new JTextField();
        JComboBox Gen;
        JComboBox Val;
        JComboBox SL;

        String[] scelte = { "Genere", "Stato di Lettura", "Valutazione"};
        JComboBox<String> Sce = new JComboBox<>(scelte);

        Object[] Compilati = {
                "Codice ISBN",ISBN,
                "Scegli come modificare",Sce
        };

        int Risultato = JOptionPane.showConfirmDialog(this, Compilati, "Inserisci ISBN del libro che vuoi modificare:", JOptionPane.OK_CANCEL_OPTION);

        if(Risultato == JOptionPane.OK_OPTION) {
            try {
                String scelta = (String) Sce.getSelectedItem();
                String isbn = ISBN.getText();

                for(Libro l: Facade.getLibri()){
                    if (l.getCodISBN().equals(isbn) && scelta.equals("Genere")) {
                        String[] Generi = {"Romanzo","Giallo","Horror","Storico","Fantasy","Avventura","Narrativo","Altro"};
                        Gen = new JComboBox(Generi);
                        Object[] CompilaGenere = {
                                "Genere",Gen
                        };
                        int Risultato1 = JOptionPane.showConfirmDialog(this, CompilaGenere, "Inserisci il nuovo genere:", JOptionPane.OK_CANCEL_OPTION);
                        if (Risultato1 == JOptionPane.OK_OPTION){
                            try {
                                String gen = (String) Gen.getSelectedItem();
                                GesCom.executeCommand(new ModificaLibroPerGenere(Facade,isbn,gen));
                                update(Facade.getLibri());
                                break;
                            }catch (IllegalArgumentException e) {
                                JOptionPane.showMessageDialog(this,e.getMessage(), "Errore! Libro non riconosciuto.", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }

                    if (l.getCodISBN().equals(isbn) && scelta.equals("Valutazione")) {
                        Integer[] valutazione = {1,2,3,4,5};
                        Val = new JComboBox<>(valutazione);
                        Object[] CompilaValutazione = {
                                "Valutazione",Val
                        };
                        int Risultato2 = JOptionPane.showConfirmDialog(this, CompilaValutazione, "Inserisci la nuova valutazione:", JOptionPane.OK_CANCEL_OPTION);
                        if (Risultato2 == JOptionPane.OK_OPTION){
                            try {
                                int val = (Integer) Val.getSelectedItem();
                                GesCom.executeCommand(new ModificaLibroPerValutazione(Facade,isbn,val));
                                update(Facade.getLibri());
                                break;
                            }catch (IllegalArgumentException e) {
                                JOptionPane.showMessageDialog(this,e.getMessage(), "Errore! Libro non riconosciuto.", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }

                    if (l.getCodISBN().equals(isbn) && scelta.equals("Stato di Lettura")) {
                        String[] Stati = {"DA_LEGGERE","IN_LETTURA","LETTO"};
                        SL = new JComboBox(Stati);
                        Object[] CompilaGenere = {
                                "Stato di Lettura",SL
                        };
                        int Risultato3 = JOptionPane.showConfirmDialog(this, CompilaGenere, "Inserisci il nuovo stato di lettura:", JOptionPane.OK_CANCEL_OPTION);
                        if (Risultato3 == JOptionPane.OK_OPTION){
                            try {
                                StatoLettura sl = StatoLettura.valueOf((String) SL.getSelectedItem());
                                GesCom.executeCommand(new ModificaLibroPerStatoDiLettura(Facade,isbn,sl));
                                update(Facade.getLibri());
                                break;
                            }catch (IllegalArgumentException e) {
                                JOptionPane.showMessageDialog(this,e.getMessage(), "Errore! Libro non riconosciuto.", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }

            }catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this,e.getMessage(), "Errore! Libro non riconosciuto.", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void AzioneBottoneRimuovi() {
        JTextField ISBN = new JTextField();

        Object[] Compilati = {
                "Codice ISBN",ISBN,
        };

        int Risultato = JOptionPane.showConfirmDialog(this, Compilati, "Inserisci ISBN del libro che vuoi rimuovere:", JOptionPane.OK_CANCEL_OPTION);

        if(Risultato == JOptionPane.OK_OPTION) {
            try {

                String isbn = ISBN.getText();

                for(Libro l: Facade.getLibri()){
                    if (l.getCodISBN().equals(isbn)) {
                        GesCom.executeCommand(new RimuoviLibroCommand(Facade,isbn));
                        update(Facade.getLibri());
                        break;
                    }
                }

            }catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this,e.getMessage(), "Errore! Libro non riconosciuto.", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void AzioneBottoneAggiungi(){
        JTextField Tit = new JTextField();
        JTextField Aut = new JTextField();
        JTextField ISBN = new JTextField();

        Integer[] Valutazioni = {1,2,3,4,5};
        JComboBox<Integer> Val = new JComboBox<>(Valutazioni);

        String[] Generi = {"Romanzo","Giallo","Horror","Storico","Fantasy","Avventura","Narrativo","Altro"};
        JComboBox<String> Gen = new JComboBox<>(Generi);

        String[] Stati = {"DA_LEGGERE","IN_LETTURA","LETTO"};
        JComboBox<String> SL = new JComboBox<>(Stati);

        Object[] Compilati = {
                "Titolo",Tit,
                "Autore",Aut,
                "Codice ISBN",ISBN,
                "Genere",Gen,
                "Valutazione",Val,
                "Stato di lettura",SL
        };

        int Risultato = JOptionPane.showConfirmDialog(this, Compilati, "Inserisci Libro:", JOptionPane.OK_CANCEL_OPTION);

        if(Risultato == JOptionPane.OK_OPTION){
            try{
                String tit = Tit.getText();
                String aut = Aut.getText();
                String isbn = ISBN.getText();

                for(Libro l: Facade.getLibri()){
                    if (l.getCodISBN().equals(isbn))
                        throw new IllegalArgumentException("Codice ISBN già registrato!");
                }

                String gen = (String) Gen.getSelectedItem();
                int val = (Integer) Val.getSelectedItem();
                StatoLettura sl = StatoLettura.valueOf((String) SL.getSelectedItem());

                Libro LibroDaAggiungere = new Libro.Builder(tit,aut,isbn).setGenere(gen).setValutazione(val).setSl(sl).build();

                GesCom.executeCommand(new AggiungiLibroCommand(Facade,LibroDaAggiungere));
                update(Facade.getLibri());

            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this,e.getMessage(), "Errore! Libro non riconosciuto.", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Override
    public void update(List<Libro> libri) {
        tableModel.setRowCount(0);
        for (Libro libro: libri){
            tableModel.addRow(new Object[]{
                    libro.getTitolo(),
                    libro.getAutore(),
                    libro.getCodISBN(),
                    libro.getGenere(),
                    libro.getValutazione(),
                    libro.getSl()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibreriaFacade facade = new LibreriaFacade();
            new LibreriaGUI(facade).setVisible(true);
        });
    }
}

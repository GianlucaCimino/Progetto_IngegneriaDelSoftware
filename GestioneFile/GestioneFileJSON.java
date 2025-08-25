package GestioneFile;

import Libro.Libro;
import Libro.StatoLettura;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GestioneFileJSON implements GestioneFile{

    @Override
    public void salva(List<Libro> libri, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("[\n");
            for (int i = 0; i < libri.size(); i++) {
                Libro l = libri.get(i);
                writer.write("  {\n");
                writer.write("    \"titolo\": \"" + escape(l.getTitolo()) + "\",\n");
                writer.write("    \"autore\": \"" + escape(l.getAutore()) + "\",\n");
                writer.write("    \"isbn\": \"" + escape(l.getCodISBN()) + "\",\n");
                writer.write("    \"genere\": \"" + escape(l.getGenere()) + "\",\n");
                writer.write("    \"valutazione\": " + l.getValutazione() + ",\n");
                writer.write("    \"stato lettura\": \"" + l.getSl() + "\"\n");
                writer.write("  }" + (i < libri.size() - 1 ? "," : "") + "\n");
            }
            writer.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Libro> carica(String path) {
        List<Libro> libri = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            String titolo = null, autore = null, isbn = null, genere = "", stato = "";
            int valutazione = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("\"titolo\":")) titolo = extractValue(line);
                else if (line.startsWith("\"autore\":")) autore = extractValue(line);
                else if (line.startsWith("\"isbn\":")) isbn = extractValue(line);
                else if (line.startsWith("\"genere\":")) genere = extractValue(line);
                else if (line.startsWith("\"valutazione\":")) {
                    try { valutazione = Integer.parseInt(extractValue(line)); }
                    catch (NumberFormatException ignored) {}
                } else if (line.startsWith("\"stato lettura\":")) stato = extractValue(line);
                else if (line.equals("}")) {

                    if (titolo != null && autore != null && isbn != null) {
                        try {
                            Libro libro = new Libro.Builder(titolo,autore,isbn)
                                    .setGenere(genere)
                                    .setSl(StatoLettura.valueOf(stato))
                                    .setValutazione(valutazione)
                                    .build();
                            libri.add(libro);
                        } catch (IllegalArgumentException e) {
                            System.err.println("Libro ignorato: " + e.getMessage());
                        }
                    } else {
                        System.err.println("Libro ignorato: mancano campi obbligatori");
                    }
                    // reset variabili per il prossimo libro
                    titolo = autore = isbn = null;
                    genere = stato = "";
                    valutazione = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libri;
    }

    private String extractValue(String line) {
        int colon = line.indexOf(":");
        String val = line.substring(colon + 1).trim();
        val = val.replace("\"", "").replace(",", "");
        return val.isEmpty() ? null : val;
    }

    private String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}

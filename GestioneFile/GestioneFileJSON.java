package GestioneFile;

import Libro.Libro;
import Libro.StatoLettura;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestioneFileJSON implements GestioneFile {

    @Override
    public void salva(List<Libro> libri, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("[\n");
            for (int i = 0; i < libri.size(); i++) {
                Libro l = libri.get(i);
                writer.write("  {\n");
                writer.write("    \"Titolo\": \"" + escape(l.getTitolo()) + "\",\n");
                writer.write("    \"Autore\": \"" + escape(l.getAutore()) + "\",\n");
                writer.write("    \"Codice ISBN\": \"" + escape(l.getCodISBN()) + "\",\n");
                writer.write("    \"Genere\": \"" + escape(l.getGenere()) + "\",\n");
                writer.write("    \"Valutazione\": " + l.getValutazione() + ",\n");
                writer.write("    \"Stato di lettura\": \"" + (l.getSl() != null ? l.getSl() : "DA_LEGGERE") + "\"\n");
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
                if (line.startsWith("\"Titolo\":")) titolo = extractValue(line);
                else if (line.startsWith("\"Autore\":")) autore = extractValue(line);
                else if (line.startsWith("\"Codice ISBN\":")) isbn = extractValue(line);
                else if (line.startsWith("\"Genere\":")) genere = extractValue(line);
                else if (line.startsWith("\"Valutazione\":")) {
                    try {
                        String val = extractValue(line);
                        valutazione = val != null ? Integer.parseInt(val) : 0;
                    } catch (NumberFormatException ignored) {}
                } else if (line.startsWith("\"Stato di lettura\":")) stato = extractValue(line);
                else if (line.equals("}") || line.equals("},")) {
                    if (titolo != null && autore != null && isbn != null) {
                        try {
                            Libro libro = new Libro.Builder(titolo, autore, isbn)
                                    .setGenere(genere != null ? genere : "")
                                    .setSl(stato != null && !stato.isEmpty() ? StatoLettura.valueOf(stato) : StatoLettura.DA_LEGGERE)
                                    .setValutazione(valutazione)
                                    .build();
                            libri.add(libro);
                        } catch (IllegalArgumentException e) {
                            System.err.println("Libro ignorato durante il caricamento: " + e.getMessage());
                        }
                    } else {
                        System.err.println("Libro ignorato: mancano campi obbligatori");
                    }
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
        if (colon < 0) return null;
        String val = line.substring(colon + 1).trim().replace("\"", "").replace(",", "");
        return val.isEmpty() ? null : val;
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}

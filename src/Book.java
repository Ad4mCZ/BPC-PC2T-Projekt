import java.util.ArrayList;
import java.util.Arrays;

abstract public class Book {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> autor;
    public String getAutor() {
        String txt = "";
        for (String item : autor) {
            txt = String.format("%s%s, ", txt, item);
        }
        return String.format("%s\b\b", txt);
    }
    public void setAutor(String autor) {
        String[] autorField = autor.split(", ");
        this.autor = new ArrayList<>();
        this.autor.addAll(Arrays.asList(autorField));
    }

    private int publishYear;
    public int getPublishYear() {
        return publishYear;
    }
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    private boolean availability;
    public String getAvailability() {

        if (!availability) {
            return "k dispozici";
        } else {
            return "vypůjčeno";
        }
    }
    public void setAvailability(String availability) {
        if (availability.toLowerCase() == "yes") {
            this.availability = true;
        }
        else {
            this.availability = false;
        }
    }

    public Book(String name, String autor, int publishYear, boolean availability) {
        this.name = name;
        this.publishYear = publishYear;
        this.availability = availability;
        setAutor(autor);
    }
}
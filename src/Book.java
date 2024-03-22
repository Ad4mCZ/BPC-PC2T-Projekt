import java.util.ArrayList;

public class Book {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ArrayList<String> autor;

    public String getAutor() {
        String txt = "";
        for (String item : autor) {
            if (autor.get(autor.size() - 1) != item) {
                txt = String.format("%s%s, ", txt, item);
            } else {
                txt = String.format("%s%s", txt, item);
            }
        }
        return txt;
    }

    public void setAutor(ArrayList<String> autor) {
        this.autor = autor;
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

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Book(String name, ArrayList<String> autor, int publishYear, boolean availability) {
        this.name = name;
        this.autor = autor;
        this.publishYear = publishYear;
    }
}
import java.util.ArrayList;

abstract public class Book {

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
    public void setAutor(String autor) {
        String[] autorField = autor.split(", ");
        for (String item : autorField) {
            this.autor.add(item);
        }
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
        setAutor(autor);
        this.publishYear = publishYear;
        this.availability = availability;
    }
}
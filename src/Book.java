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
    public ArrayList<String> getAutor() {
        return autor;
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

    private Boolean availability;
    public Boolean getAvailability() {
        return availability;
    }
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
    
    public Book(String name, ArrayList<String> autor, int publishYear, Boolean availability) {
        this.name = name;
        this.autor = autor;
        this.publishYear = publishYear;
        this.availability = availability;
    }
}

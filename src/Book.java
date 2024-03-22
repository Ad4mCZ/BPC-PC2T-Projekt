public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private Availability availability;

    public Book(String title, String author, int publicationYear, Availability availability) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}

enum Availability {available, borrowed}
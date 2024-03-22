
public class Novel extends Book {
    private Genre genre;

    public Novel(String title, String author, int publicationYear, Availability availability, Genre genre) {
        super(title, author, publicationYear, availability);
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}

enum Genre {romance, fantasy, scifi, mystery, biography}

import java.util.ArrayList;

public class Novel extends Book {
    private Genre genre;

    public Novel(String title, ArrayList<String> autor, int publicationYear, boolean availability, Genre genre) {
        super(title, autor, publicationYear, availability);
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

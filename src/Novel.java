public class Novel extends Book {
    public enum Genre {romance, fantasy, scifi, mystery, biography}
    private Genre genre;
    public String getGenreString() {return  genre.toString();}

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    public Novel(String title, String autor, int publicationYear, boolean availability, Genre genre) {
        super(title, autor, publicationYear, availability);
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "román";
    }
}


public class Textbook extends Book {
    private int suitableGrade;

    public Textbook(String title, String author, int publicationYear, Availability availability, int suitableGrade) {
        super(title, author, publicationYear, availability);
        this.suitableGrade = suitableGrade;
    }

    public int getSuitableGrade() {
        return suitableGrade;
    }

    public void setSuitableGrade(int suitableGrade) {
        this.suitableGrade = suitableGrade;
    }
}

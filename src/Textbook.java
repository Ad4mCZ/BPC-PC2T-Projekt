public class Textbook extends Book {

    private int suitableGrade;
    public int getSuitableGrade() {
        return suitableGrade;
    }
    public void setSuitableGrade(int suitableGrade) {
        this.suitableGrade = suitableGrade;
    }

    public Textbook(String title, String autor, int publicationYear, boolean availability, int suitableGrade) {
        super(title, autor, publicationYear, availability);
        this.suitableGrade = suitableGrade;
    }
}

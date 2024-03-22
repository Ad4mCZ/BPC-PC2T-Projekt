import java.util.ArrayList;

public class Textbook extends Book {
    private int suitableGrade;

    public Textbook(String title, ArrayList<String> autor, int publicationYear, boolean availability, int suitableGrade) {
        super(title, autor, publicationYear, availability);
        this.suitableGrade = suitableGrade;
    }

    public int getSuitableGrade() {
        return suitableGrade;
    }

    public void setSuitableGrade(int suitableGrade) {
        this.suitableGrade = suitableGrade;
    }
}

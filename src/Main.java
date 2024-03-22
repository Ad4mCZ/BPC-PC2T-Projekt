import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        ArrayList<String> autors = new ArrayList<String>(Arrays.asList("Karek", "Petr"));
        Book book = new Book("Dune", autors, 1980, true);


        System.out.printf("Název: %s\nAutor/Autoři: %s\nRok vydání: %d\nDostupnost: %s\n", book.getName(), book.getAutor(), book.getPublishYear(), book.getAvailability());
    }
}
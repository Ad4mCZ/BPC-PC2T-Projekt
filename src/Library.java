import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Library {

    //static ArrayList<Book> books = new ArrayList<Book>();
    static ArrayList<Book> books = new ArrayList<>();

    public static void addBook() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Zadejte název knihy: ");
        String title = reader.readLine();
        System.out.print("Zadejte autora/y knihy ve formátu [jméno příjmení, jméno příjmění, ...]: ");
        String autor = reader.readLine();
        System.out.print("Zadejte rok vydání knihy: ");
        int publishYear = Integer.parseInt(reader.readLine());

        int bookType;
        System.out.print("Jedná se o román (1) nebo učebnici (2)?: ");
        while (true) {
            bookType = Integer.parseInt(reader.readLine());
            if (bookType == 1 || bookType == 2) break;
            else System.out.print("Zadejte 1 nebo 2: ");
        }
        switch (bookType) {
            case 1:
                System.out.print("Vyberte žánr (");
                for (Novel.Genre genre : Novel.Genre.values()) {
                    System.out.print(genre + ", ");
                }
                System.out.print("\b\b): ");

                while (true) {
                    String stringGenre = reader.readLine();

                    boolean found = false;
                    for (Novel.Genre genre : Novel.Genre.values()) {
                        if (stringGenre.equalsIgnoreCase(String.valueOf(genre))) {
                            Novel novel = new Novel(title, autor, publishYear, true, genre);
                            System.out.println("Přidán román: " + novel.getName());
                            books.add(novel);
                            found = true;
                            break;
                        }
                    }
                    if (found) break;
                    else System.out.print("Nenalezen žánr, zkus to prosím znovu: ");
                }
                break;

            case 2:
                System.out.print("Napište vhodný ročník: ");
                int grade = 0;
                try {
                    grade = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Neplatná volba. Zadejte prosím číslo.");
                }
                //sem asi pridat while
                Textbook textbook = new Textbook(title, autor, publishYear, true, grade);
                System.out.println("Přidána učebnice: " + textbook.getName());
                books.add(textbook);
                break;
        }
    }

    public static void editBook() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (Book book : books) {
            System.out.printf("%s\n", book.getName());
        }
        boolean endLoop = true;
        while (endLoop) {
            System.out.print("vyberte si jakou knihu chcete změnit: ");
            String choice = reader.readLine();

            for (Book book : books) {
                if (book.getName().equalsIgnoreCase(choice)) {
                    System.out.println("nalezeno\n----------------------");

                    System.out.printf("Změnit název knihy [%s]: ", book.getName());
                    String newName = reader.readLine();
                    if (!newName.isEmpty()) {
                        book.setName(newName);
                    }

                    System.out.printf("Změnit autora/y knihy [%s]: ", book.getAutor());
                    String newAutor = reader.readLine();
                    if (!newAutor.isEmpty()) {
                        book.setAutor(newAutor);
                    }

                    System.out.printf("Změnit rok vydání knihy [%d]: ", book.getPublishYear());
                    String newPublishYear = reader.readLine();
                    try {
                        if (!newPublishYear.isEmpty()) {
                            book.setPublishYear(Integer.parseInt(newPublishYear));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Neplatný formát ročníku. Zůstává původní rok.");
                    }
                    endLoop = false;
                    // Zadání nových parametrů
                }
            }
            if (endLoop)
                System.out.println("Nenalezeno, zkus to prosím znovu");
        }
    }
}

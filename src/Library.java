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
        System.out.print("Jedná se o román (1) nebo učebnici (2)?: ");
        int bookType = Integer.parseInt(reader.readLine());
        switch (bookType) {
            case 1:
                System.out.print("Napište žánr (");
                for (Novel.Genre genre : Novel.Genre.values()) {
                    System.out.print(genre + ", ");
                }
                System.out.print("\b\b): ");
                Novel.Genre genre = Novel.Genre.valueOf((reader.readLine().toLowerCase()));
                //zkontrolovat jestli se jedna o zanr
                Novel novel = new Novel(title, autor, publishYear, true, genre);
                System.out.println("Přidán román: " + novel);
                books.add(novel);
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
                System.out.println("Přidána učebnice: " + textbook);
                books.add(textbook);
                break;
        }

        //System.out.println("Knihu '" + title + "' od autora/ů " + autor + ", vydanou v roce " + publishYear + ", jste úspěšně přidali.");
    }

    public static void editBook() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (Book book : books) {
            System.out.printf("%s\n",book.getName());
        }
        System.out.print("vyberte si jakou knihu chcete změnit: ");
        String choice = reader.readLine();

        boolean found = false;
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(choice)) {
                found = true;
                System.out.println("nalezeno\n----------------------");

                // Zadání nových parametrů

                System.out.printf("Změnit název knihy [%s]: ", book.getName());
                String newName = reader.readLine();
                if (!newName.isEmpty()) {
                    book.setName(newName);
                }

                System.out.printf("Změnit autora/y knihy [%s]", book.getAutor());
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
                }
                catch (NumberFormatException e) {
                    System.out.println("Neplatný formát ročníku. Zůstává původní rok.");
                }

                break;
            }
            else System.out.println("nenalezeno");
        }

    }
}

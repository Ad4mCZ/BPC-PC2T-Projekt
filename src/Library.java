import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Library {

    static ArrayList<Book> books = new ArrayList<Book>();

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

    public static void changeBook(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("vyberte si jakou knihu chcete změnit:");
        for (Book book : books) {
            System.out.printf("%s\n", book.getName());
        }
        
    }
}

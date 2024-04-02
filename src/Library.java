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
                    else {
                        newName = book.getName();
                    }

                    System.out.printf("Změnit autora/y knihy [%s]: ", book.getAutor());
                    String newAutor = reader.readLine();
                    if (!newAutor.isEmpty()) {
                        book.setAutor(newAutor);
                    }
                    else {
                        newAutor = book.getAutor();
                    }

                    System.out.printf("Změnit rok vydání knihy [%d]: ", book.getPublishYear());
                    String input = reader.readLine();
                    int newPublishYear = 0;
                    try {
                        if (!input.isEmpty()) {
                            newPublishYear = Integer.parseInt(input);
                            book.setPublishYear(newPublishYear);
                        }
                        else {
                            newPublishYear = book.getPublishYear();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Neplatný formát ročníku. Zůstává původní rok.");
                        newPublishYear = book.getPublishYear();
                    }

                    String typeChange;
                    System.out.printf("Kniha je %s\nZměnit [a/n]: ", book);
                    while (true) {
                        typeChange = reader.readLine();
                        if (typeChange.equals("a") || typeChange.equals("n")) break;
                        else System.out.print("Změnit [a/n]: ");
                    }

                    if (typeChange.equals("n")) {
                        if (book instanceof Novel) {
                            ((Novel)book).setGenre(changeGenre());
                        }
                        else {
                            ((Textbook)book).setSuitableGrade(changeGrade());
                        }
                    }
                    else {
                        if (book instanceof Novel) {
                            book = new Textbook(newName, newAutor, newPublishYear, true, changeGrade());
                        }
                        else {
                            book = new Novel(newName, newAutor, newPublishYear, true, changeGenre());
                        }
                    }
                    endLoop = false;
                }
            }
            if (endLoop)
                System.out.println("Nenalezeno, zkus to prosím znovu");
        }
    }

    static Novel.Genre changeGenre() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Vyberte žánr (");
        for (Novel.Genre genre : Novel.Genre.values()) {
            System.out.print(genre + ", ");
        }
        System.out.print("\b\b): ");
        while (true) {
            String stringGenre = reader.readLine();
            for (Novel.Genre genre : Novel.Genre.values()) {
                if (stringGenre.equalsIgnoreCase(String.valueOf(genre))) {
                    return genre;
                }
            }
            System.out.println("Špatný vstup zkus to znovu.");
        }        
    }

    static int changeGrade() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Napište vhodný ročník: ");
        int grade = 0;
        try {
            grade = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Neplatná volba. Zadejte prosím číslo.");
        }
        //sem asi pridat while

        return grade;
    }
}

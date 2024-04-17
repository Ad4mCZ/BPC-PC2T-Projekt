import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookPrint {

    public static void print() {
        System.out.println("--- MENU VÝPISU ---");
        System.out.println("1. Vypsat knihy podle abecedy");
        System.out.println("2. Vypsat info o určité knize");
        System.out.println("3. Vypsat knihy určitého autora");
        System.out.println("4. Vypsat knihy určitého žánru");
        System.out.println("5. Vypsat nedostupné knihy");
        System.out.print("Vyberte možnost: ");

        switch (inputCheck.checkInt()) {
            case 1 -> AlfabetList();
            case 2 -> printBookInfo();
            case 3 -> autorsBooks();
            case 4 -> booksByGenre();
            case 5 -> borrowedBooks();
            default -> System.out.println("Neplatná volba.");
        }
    }

    public static void AlfabetList() {
        Scanner sc = new Scanner(System.in);
        List<Book> books = Library.books;
        Collections.sort(books, Comparator.comparing(o -> o.getName().toLowerCase()));
        for (Book book : books) {
            System.out.printf(
                "------------------\nNázev knihy: %s\nAutor/ři knihy: %s\nRok vydání knihy: %d\nKniha je %s\n",
                book.getName(), book.getAutor(), book.getPublishYear(),
                book.getStringAvailability());
        if (book instanceof Novel) {

            System.out.printf("Kniha je román a žánr je %s.\n", ((Novel) book).getGenre());
        } else {
            System.out.printf("Kniha je učebnice a je vhodná pro %d. ročník.\n",
                    ((Textbook) book).getSuitableGrade());
        }
        }
        System.out.println("------------------\nStiskněte Enter pro návrat do menu: ");
        sc.nextLine();
    }

    public static void printBookInfo() {
        Scanner sc = new Scanner(System.in);
        Book bookToPrintInfo = Library.findBook();
        if (bookToPrintInfo == null) {
            return;
        }
        System.out.printf(
                "------------------\nNázev knihy: %s\nAutor/ři knihy: %s\nRok vydání knihy: %d\nKniha je %s\n",
                bookToPrintInfo.getName(), bookToPrintInfo.getAutor(), bookToPrintInfo.getPublishYear(),
                bookToPrintInfo.getStringAvailability());
        if (bookToPrintInfo instanceof Novel) {

            System.out.printf("Kniha je román a žánr je %s.\n", ((Novel) bookToPrintInfo).getGenre());
        } else {
            System.out.printf("Kniha je učebnice a je vhodná pro %d. ročník.\n",
                    ((Textbook) bookToPrintInfo).getSuitableGrade());
        }
        System.out.println("------------------\nStiskněte Enter pro návrat do menu: ");
        sc.nextLine();
    }

    public static void autorsBooks() {
        Scanner sc = new Scanner(System.in);
        List<String> autorsBooks = findAutor();
        Collections.sort(autorsBooks, String.CASE_INSENSITIVE_ORDER);
        for (String book : autorsBooks) {
            System.out.println(book);
        }
        System.out.println("------------------\nStiskněte Enter pro návrat do menu: ");
        sc.nextLine();
    }

    public static ArrayList<String> findAutor() {
        ArrayList<String> autorsBooks = new ArrayList<>();
        ArrayList<String> autors = new ArrayList<>();
        if (!Library.books.isEmpty()) {
            System.out.println("------------------");
            for (Book book : Library.books) {
                if (!autors.contains(book.getAutor())) {
                    System.out.printf("%s\n", book.getAutor());
                    autors.add(book.getAutor());
                }
            }
            System.out.println("------------------");

            while (true) {
                System.out.print("Zadejte autora jehož knihy chcete vypsat: ");
                String choice = inputCheck.checkNullString();

                for (Book book : Library.books) {
                    if (book.getAutor().equalsIgnoreCase(choice)) {
                        autorsBooks.add(book.getName());
                    }
                }

                if (!autorsBooks.isEmpty()) {
                    return autorsBooks;
                }
                else {
                    System.out.println("Nenalezeno, zkus to prosím znovu.");
                }
            }
        }
        System.out.println("V seznamu nejsou žádné knihy.");
        return null;
    }

    public static void booksByGenre() {
        Scanner sc = new Scanner(System.in);    
        if (!Library.books.isEmpty()) {
            System.out.println("------------------");
            for (Novel.Genre genre : Novel.Genre.values()) {
                System.out.printf("%s\n", genre);
            }
            System.out.println("------------------");

            while (true) {
                System.out.print("Zadejte žánr pro výpis příslušných knih: ");
                String choice = inputCheck.checkNullString();

                for (Book book : Library.books) {
                    if (book instanceof Novel) {
                        String genre =  String.format("%s", ((Novel) book).getGenre());
                        if (genre.equalsIgnoreCase(choice)) {
                            System.out.println(book.getName());
                        }
                    }
                }
                System.out.println("------------------\nStiskněte Enter pro návrat do menu: ");
                sc.nextLine();
                return;
            }
        }
        System.out.println("V seznamu nejsou žádné knihy.");

        System.out.println("------------------\nStiskněte Enter pro návrat do menu: ");
        sc.nextLine();
    }

    public static void borrowedBooks() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------");
        for (Book book : Library.books) {
            if (!book.getAvailability()) {
                System.out.printf("Název: %s", book.getName());
                if (book instanceof Novel) {
                    System.out.println(", typ: román");
                }
                else {
                    System.out.println(", typ: učebnice");
                }
            }
        }
        System.out.println("------------------\nStiskněte Enter pro návrat do menu: ");
        sc.nextLine();
    }
}

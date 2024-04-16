import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookPrint {

    public static void print() {
        AlfabetList();
    }

    public static void AlfabetList() {
        Scanner sc = new Scanner(System.in);
        List<Book> books = Library.books;
        Collections.sort(books, Comparator.comparing(o -> o.getName().toLowerCase()));
        for (Book book : books) {
            System.out.printf(
                "-------------------------\nNázev knihy: %s\nAutor/ři knihy: %s\nRok vydání knihy: %d\nKniha je %s\n",
                book.getName(), book.getAutor(), book.getPublishYear(),
                book.getStringAvailability());
        if (book instanceof Novel) {

            System.out.printf("Kniha je román a žánr je %s.\n", ((Novel) book).getGenre());
        } else {
            System.out.printf("Kniha je učebnice a je vhodná pro %d. ročník.\n",
                    ((Textbook) book).getSuitableGrade());
        }
        }
        System.out.println("-------------------------\nStiskněte Enter pro návrat do menu: ");
        sc.nextLine();
    }

    public static void printBookInfo() {
        Scanner sc = new Scanner(System.in);
        Book bookToPrintInfo = Library.findBook();
        if (bookToPrintInfo == null) {
            return;
        }
        System.out.printf(
                "-------------------------\nNázev knihy: %s\nAutor/ři knihy: %s\nRok vydání knihy: %d\nKniha je %s\n",
                bookToPrintInfo.getName(), bookToPrintInfo.getAutor(), bookToPrintInfo.getPublishYear(),
                bookToPrintInfo.getStringAvailability());
        if (bookToPrintInfo instanceof Novel) {

            System.out.printf("Kniha je román a žánr je %s.\n", ((Novel) bookToPrintInfo).getGenre());
        } else {
            System.out.printf("Kniha je učebnice a je vhodná pro %d. ročník.\n",
                    ((Textbook) bookToPrintInfo).getSuitableGrade());
        }
        System.out.println("-------------------------\nStiskněte Enter pro návrat do menu: ");
        sc.nextLine();
    }

    public static void autorsBooks() {
        Scanner sc = new Scanner(System.in);

        System.out.println("-------------------------\nStiskněte Enter pro návrat do menu: ");
        sc.nextLine();
    }

    public static void booksByGenre() {
        Scanner sc = new Scanner(System.in);

        System.out.println("-------------------------\nStiskněte Enter pro návrat do menu: ");
        sc.nextLine();
    }

    public static void borrowedBooks() {
        Scanner sc = new Scanner(System.in);

        System.out.println("-------------------------\nStiskněte Enter pro návrat do menu: ");
        sc.nextLine();
    }
}

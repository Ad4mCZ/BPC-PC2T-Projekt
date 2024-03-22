import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        int choice;
        while (run) {
            System.out.println("1. Přidání nové knihy");
            System.out.println("2. Úprava knihy");
            System.out.println("3. Smazání knihy");
            System.out.println("4. Změnit dostupnost knihy");
            System.out.println("5. Výpis knih");
            System.out.println("6. Vyhledání knihy");
            System.out.println("7. Vypnutí programu");
            System.out.print("Vyberte možnost: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Neplatná volba. Zadejte prosím číslo.");
                System.out.print("Vyberte možnost: ");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // AddBook();
                    System.out.println("Vybrali jste volbu 1.");

                    break;
                case 2:
                    System.out.println("Vybrali jste volbu 2.");
                    break;
                case 3:
                    System.out.println("Vybrali jste volbu 3.");
                    break;
                case 4:
                    System.out.println("Vybrali jste volbu 4.");
                    break;
                case 5:
                    System.out.println("Vybrali jste volbu 5.");
                    break;
                case 6:
                    System.out.println("Vybrali jste volbu 6.");
                    break;
                case 7:
                    System.out.println("Program se ukončuje.");
                    break;
                default:
                    System.out.println("Neplatná volba. Zadejte prosím číslo od 1 do 7.");
            }
        }

        scanner.close();
    }

    public static void AddBook() {
        ArrayList<String> autors = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Zadejte název knihy: ");
        String title = sc.next();
        System.out.print("Zadejte rok vydání knihy: ");
        int publishYear = sc.nextInt();
        System.out.print("Zadejte autora/y knihy: ");
        String autor = sc.next();
        autors.add(autor);
        Book book = new Book(title, autors, publishYear, true);
        System.out.println(book.getAutor() + book.getName() + book.getPublishYear());
        sc.close();
    }
}
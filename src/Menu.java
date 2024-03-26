import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    public static void mainMenu() throws IOException {
        int choice;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        do {

            String BLUE = "\u001B[34m";
            String GREEN = "\u001B[32m";
            String RESET = "\u001B[0m";

            System.out.println(BLUE + "=== HLAVNÍ MENU ===");
            System.out.println(GREEN + "1. Přidání nové knihy");
            System.out.println("2. Úprava knihy");
            System.out.println("3. Smazání knihy");
            System.out.println("4. Změnit dostupnost knihy");
            System.out.println("5. Výpis knih");
            System.out.println("6. Vyhledání knihy");
            System.out.println("7. Vypnutí programu");
            System.out.print(RESET + "Vyberte možnost: ");

            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Neplatná volba. Zadejte prosím číslo.");
                continue;
            }

            switch (choice) {
                case 1 -> Library.addBook();
                case 2 -> Library.changeBook();
                case 3 -> System.out.println("Vybrali jste volbu 3.");
                case 4 -> System.out.println("Vybrali jste volbu 4.");
                case 5 -> System.out.println("Vybrali jste volbu 5.");
                case 6 -> System.out.println("Vybrali jste volbu 6.");
                case 7 -> {
                    System.out.println("Program se ukončuje.");
                    return;
                }
                default -> System.out.println("Neplatná volba. Zadejte prosím číslo od 1 do 7.");
            }
        } while (true);
    }
}
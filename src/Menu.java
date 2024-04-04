public class Menu {

    public static void mainMenu() {

        String BLUE = "\u001B[34m";
        String GREEN = "\u001B[32m";
        String RESET = "\u001B[0m";

        do {
            System.out.println(BLUE + "=== HLAVNÍ MENU ===");
            System.out.println(GREEN + "1. Přidání nové knihy");
            System.out.println("2. Úprava knihy");
            System.out.println("3. Smazání knihy");
            System.out.println("4. Změnit dostupnost knihy");
            System.out.println("5. Výpis knih");
            System.out.println("6. Vyhledání knihy");
            System.out.println("7. Vypnutí programu");
            System.out.print(RESET + "Vyberte možnost: ");

            switch (inputCheck.checkInt()) {
                case 1 -> Library.addBook();
                case 2 -> Library.editBook();
                case 3 -> Library.deleteBook();
                case 4 -> Library.changeBookAvailbility();
                case 5 -> Library.listOfBooks();
                case 6 -> Library.printBookInfo();
                case 7 -> {
                    System.out.println("Program se ukončuje.");
                    return;
                }
                default -> System.out.println("Neplatná volba. Zadejte prosím číslo od 1 do 7.");
            }
        } while (true);
    }
}
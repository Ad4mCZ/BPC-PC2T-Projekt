import java.util.*;

public class Library {

    static ArrayList<Book> books = new ArrayList<>();

    public static void addBook() {

        System.out.print("Zadejte název knihy: ");
        String title = inputCheck.checkNullString();
        System.out.print("Zadejte autora/y knihy ve formátu [jméno příjmení, jméno příjmění, ...]: ");
        String autor = inputCheck.checkNullString();

        System.out.print("Zadejte rok vydání knihy: ");
        int publishYear = inputCheck.checkInt();

        System.out.print("Jedná se o román (1) nebo učebnici (2)?: ");
        int bookType = inputCheck.checkInt();
        do {
            if (bookType != 1 && bookType != 2) {
                System.out.print("Neplatná volba. Zadejte prosím 1 nebo 2: ");
                bookType = inputCheck.checkInt();
            }
        } while (bookType != 1 && bookType != 2);

        switch (bookType) {
            case 1:
                System.out.print("Vyberte žánr (");
                for (Novel.Genre genre : Novel.Genre.values()) {
                    System.out.print(genre + ", ");
                }
                System.out.print("\b\b): ");

                while (true) {
                    String stringGenre = inputCheck.checkNullString();

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
                    if (found)
                        break;
                    else
                        System.out.print("Nenalezen žánr, zkus to prosím znovu: ");
                }
                break;

            case 2:
                System.out.print("Napište vhodný ročník: ");
                int grade = inputCheck.checkInt();
                Textbook textbook = new Textbook(title, autor, publishYear, true, grade);
                System.out.println("Přidána učebnice: " + textbook.getName());
                books.add(textbook);
                break;
        }
    }

    public static void editBook() {
        Book bookToEdit = findBook();
        if (bookToEdit == null)
            return;

        System.out.printf("Změnit název knihy [%s]: ", bookToEdit.getName());
        String newName = inputCheck.checkString();

        if (!newName.isEmpty()) {
            bookToEdit.setName(newName);
        } else {
            newName = bookToEdit.getName();
        }

        System.out.printf("Změnit autora/y knihy [%s]: ", bookToEdit.getAutor());
        String newAutor = inputCheck.checkString();

        if (!newAutor.isEmpty()) {
            bookToEdit.setAutor(newAutor);
        } else {
            newAutor = bookToEdit.getAutor();
        }

        // TOHLE NEVIM JAK UDELAT LEPE, ABY TAM NEMUSELY BYT DVE PROMENNE.
        System.out.printf("Změnit rok vydání knihy [%d]: ", bookToEdit.getPublishYear());
        String input = inputCheck.checkString();
        int newPublishYear;
        try {
            if (!input.isEmpty()) {
                newPublishYear = Integer.parseInt(input);
                bookToEdit.setPublishYear(newPublishYear);
            } else {
                newPublishYear = bookToEdit.getPublishYear();
            }
        } catch (NumberFormatException e) {
            System.out.println("Neplatný formát ročníku. Zůstává původní rok.");
            newPublishYear = bookToEdit.getPublishYear();
        }

        System.out.printf("Kniha je %s, Chcete změnit typ? [a/n]: ", bookToEdit);
        String typeChange = inputCheck.checkString();

        if (typeChange.equals("n") || typeChange.isEmpty()) {
            if (bookToEdit instanceof Novel) {
                ((Novel) bookToEdit).setGenre(changeGenre());
            } else {
                ((Textbook) bookToEdit).setSuitableGrade(changeGrade());
            }
        } else {
            if (bookToEdit instanceof Novel) {
                bookToEdit = new Textbook(newName, newAutor, newPublishYear, true, changeGrade());
            } else {
                bookToEdit = new Novel(newName, newAutor, newPublishYear, true, changeGenre());
            }
        }

    }

    static Novel.Genre changeGenre() {
        System.out.print("Vyberte žánr (");
        for (Novel.Genre genre : Novel.Genre.values()) {
            System.out.print(genre + ", ");
        }
        System.out.print("\b\b): ");
        while (true) {
            String stringGenre = inputCheck.checkNullString();
            for (Novel.Genre genre : Novel.Genre.values()) {
                if (stringGenre.equalsIgnoreCase(String.valueOf(genre))) {
                    return genre;
                }
            }
            System.out.print("Špatný vstup zkus to znovu: ");
        }
    }

    static int changeGrade() {
        System.out.print("Napište vhodný ročník: ");
        return inputCheck.checkInt();
    }

    public static void deleteBook() {
        Book bookToDelete = findBook();

        if (bookToDelete == null) {
            return;
        }
        books.remove(bookToDelete);
        String name = bookToDelete.getName();
        System.out.printf("Kniha %s byla smazána.%n", name);
    }

    public static Book findBook() {
        if (!books.isEmpty()) {
            System.out.println("------------------");
            for (Book book : books) {
                System.out.printf("%s\n", book.getName());
            }
            System.out.println("------------------");
            while (true) {
                System.out.print("Zadejte název knihy: ");
                String choice = inputCheck.checkNullString();

                for (Book book : books) {
                    if (book.getName().equalsIgnoreCase(choice)) {
                        return book;
                    }
                }
                System.out.println("Nenalezeno, zkus to prosím znovu.");
            }
        }
        System.out.println("Seznam knih je prázdný.");
        return null;
    }

    public static void changeBookAvailability() {
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        Book bookToChangeAvailability = findBook();
        if (bookToChangeAvailability == null) {
            return;
        }
        String availabilityChange;
        System.out.printf("Kniha je " + RED + "%s" + RESET + ", Chcete změnit dostupnost? [a/n]: ",
                bookToChangeAvailability.getStringAvailability());
        while (true) {
            availabilityChange = inputCheck.checkNullString();
            if (availabilityChange.equals("a") || availabilityChange.equals("n"))
                break;
            else
                System.out.print("Změnit [a/n]: ");
        }
        if (availabilityChange.equals("a")) {
            bookToChangeAvailability.setAvailability(!bookToChangeAvailability.getAvailability());
        }
    }

    public static void listOfBooks() {

    }

    public static void printBookInfo() {
        Book bookToPrintInfo = findBook();
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
    }
}
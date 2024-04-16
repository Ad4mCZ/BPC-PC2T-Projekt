import java.util.*;

public class Library {

    static ArrayList<Book> books = new ArrayList<>();

    //
    //
    //

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
                            FilesHandling.Serialization(novel);
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

    //
    //
    //
    
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

    //
    //
    //
    
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
        int grade = inputCheck.checkInt();
        return grade;
    }

    //
    //
    //
    
    public static void deleteBook() {
        Book bookToDelete = findBook();

        if (bookToDelete == null) {
            return;
        }
        books.remove(bookToDelete);
        String name = bookToDelete.getName();
        System.out.printf("Kniha %s byla smazána.%n", name);
    }

    //
    //
    //
    
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

    public static ArrayList<String> findAutor() {
        ArrayList<String> autorsBooks = new ArrayList<>();
        ArrayList<String> autors = new ArrayList<>();
        if (!books.isEmpty()) {
            System.out.println("------------------");
            for (Book book : books) {
                if (!autors.contains(book.getAutor())) {
                    System.out.printf("%s\n", book.getAutor());
                    autors.add(book.getAutor());
                }
            }
            System.out.println("------------------");

            while (true) {
                System.out.print("Zadejte autora jehož knihy chcete vypsat: ");
                String choice = inputCheck.checkNullString();

                for (Book book : books) {
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


    public static void changeBookAvailbility() {
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        Book bookToChangeAvaibility = findBook();
        String avaibilityChange;
        System.out.printf("Kniha je " + RED + "%s" + RESET + ", Chcete změnit dostupnost? [a/n]: ",
                bookToChangeAvaibility.getStringAvailability());
        while (true) {
            avaibilityChange = inputCheck.checkNullString();
            if (avaibilityChange.equals("a") || avaibilityChange.equals("n"))
                break;
            else
                System.out.print("Změnit [a/n]: ");
        }
        if (avaibilityChange.equals("a")) {
            bookToChangeAvaibility.setAvailability(!bookToChangeAvaibility.getAvailability());
        }
    }
}
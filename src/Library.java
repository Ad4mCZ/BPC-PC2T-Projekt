import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Library {

    static ArrayList<Book> books = new ArrayList<>();

    public static void addBook()  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Zadejte název knihy: ");
        String title;
        try {
            title = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Zadejte autora/y knihy ve formátu [jméno příjmení, jméno příjmění, ...]: ");
        String autor;
        try {
            autor = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Zadejte rok vydání knihy: ");
        int publishYear;
        try {
            publishYear = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int bookType;
        System.out.print("Jedná se o román (1) nebo učebnici (2)?: ");
        while (true) {
            try {
                bookType = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
                    String stringGenre;
                    try {
                        stringGenre = reader.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

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
                } catch (NumberFormatException | IOException e) {
                    System.out.println("Neplatná volba. Zadejte prosím číslo.");
                }
                //sem asi pridat while
                Textbook textbook = new Textbook(title, autor, publishYear, true, grade);
                System.out.println("Přidána učebnice: " + textbook.getName());
                books.add(textbook);
                break;
        }
    }

    public static void editBook() {
        Book bookToEdit = findBook();
        if (bookToEdit == null) return;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.printf("Změnit název knihy [%s]: ", bookToEdit.getName());
        String newName;
        try {
            newName = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!newName.isEmpty()) {
            bookToEdit.setName(newName);
        } else {
            newName = bookToEdit.getName();
        }

        System.out.printf("Změnit autora/y knihy [%s]: ", bookToEdit.getAutor());
        String newAutor;
        try {
            newAutor = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!newAutor.isEmpty()) {
            bookToEdit.setAutor(newAutor);
        } else {
            newAutor = bookToEdit.getAutor();
        }

        System.out.printf("Změnit rok vydání knihy [%d]: ", bookToEdit.getPublishYear());
        String input;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        String typeChange;
        System.out.printf("Kniha je %s, Chcete změnit typ? [a/n]: ", bookToEdit);
        while (true) {
            try {
                typeChange = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (typeChange.equals("a") || typeChange.equals("n")) break;
            else System.out.print("Změnit [a/n]: ");
        }

        if (typeChange.equals("n")) {           //tady pridat jeste if, pokud nechce menit (neda input)
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Vyberte žánr (");
        for (Novel.Genre genre : Novel.Genre.values()) {
            System.out.print(genre + ", ");
        }
        System.out.print("\b\b): ");
        while (true) {
            String stringGenre;
            try {
                stringGenre = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (Novel.Genre genre : Novel.Genre.values()) {
                if (stringGenre.equalsIgnoreCase(String.valueOf(genre))) {
                    return genre;
                }
            }
            System.out.print("Špatný vstup zkus to znovu: ");
        }
    }

    static int changeGrade() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Napište vhodný ročník: ");
        int grade = 0;
        try {
            grade = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.print("Neplatná volba. Zadejte prosím číslo: ");
        }
        //sem asi pridat while

        return grade;
    }

    public static void deleteBook() {
        Book bookToDelete = findBook();

        if (bookToDelete != null) {
            books.remove(bookToDelete);
            String name = bookToDelete.getName();
            System.out.printf("Kniha %s byla smazána.%n", name);
        }
    }

    public static Book findBook() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (!books.isEmpty()) {
            System.out.println("------------------");
            for (Book book : books) {
                System.out.printf("%s\n", book.getName());
            }
            System.out.println("------------------");
            while (true) {
                System.out.print("Zadejte název knihy: ");
                String choice;
                try {
                    choice = reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                for (Book book : books) {
                    if (book.getName().equalsIgnoreCase(choice)) {
                        //System.out.println("nalezeno\n----------------------");
                        return book;
                    }
                }
                System.out.println("Nenalezeno, zkus to prosím znovu.");
            }
        }
        System.out.println("Seznam knih je prázdný.");
        return null;
    }

    public static void changeBookAvailbility() {
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Book bookToChangeAvaibility = findBook();
        String avaibilityChange;
        System.out.printf("Kniha je " + RED + "%s" + RESET + ", Chcete změnit dostupnost? [a/n]: ", bookToChangeAvaibility.getStringAvailability());
        while (true) {
            try {
                avaibilityChange = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (avaibilityChange.equals("a") || avaibilityChange.equals("n")) break;
            else System.out.print("Změnit [a/n]: ");
        }
        if (avaibilityChange.equals("a")) {
            bookToChangeAvaibility.setAvailability(!bookToChangeAvaibility.getAvailability());
        }
    }

    public static void listOfBooks() {

    }

    public static void printBookInfo() {
        Book bookToPrintInfo = findBook();
        System.out.printf("-------------------------\nNázev knihy: %s\nAutor/ři knihy: %s\nRok vydání knihy: %d\nKniha je %s\n", bookToPrintInfo.getName(), bookToPrintInfo.getAutor(), bookToPrintInfo.getPublishYear(), bookToPrintInfo.getStringAvailability());
        if (bookToPrintInfo instanceof Novel) {

            System.out.printf("Kniha je román a žánr je %s\n", ((Novel) bookToPrintInfo).getGenre());
        }
        else {
            System.out.printf("Kniha je učebnice a je vhodná pro %d. ročníky\n\n", ((Textbook) bookToPrintInfo).getSuitableGrade());
        }
    }
}
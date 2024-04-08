import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class inputCheck {
    public static int checkInt() {
        int choice;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                choice = Integer.parseInt(reader.readLine());
                return choice;
            } catch (NumberFormatException e) {
                System.out.print("Neplatná volba. Zadejte prosím číslo: ");
            } catch (IOException e) {
                System.out.println("Nastala vyjimka." + e.getMessage());
                System.exit(1);
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Nastala vyjimka." + e.getMessage());
                }
            }
        } while (true);
    }

    public static String checkNullString() {
        String choice;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                choice = reader.readLine();
                if (choice.trim().isEmpty()) {
                    System.out.print("Tato položka nesmí být prázdná: ");
                } else
                    return choice;
            } catch (IOException e) {
                System.out.println("Nastala vyjimka." + e.getMessage());
                System.exit(1);
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Nastala vyjimka." + e.getMessage());
                }
            }
        } while (true);
    }

    public static String checkString() {
        String choice = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            choice = reader.readLine();

        } catch (IOException e) {
            System.out.println("Nastala vyjimka. " + e.getMessage());
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Nastala vyjimka. " + e.getMessage());
            }
        }
        return choice;
    }
}

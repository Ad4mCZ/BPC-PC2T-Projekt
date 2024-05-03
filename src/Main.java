public class Main {
    public static void main(String[] args) {
        FilesHandling.DeSerialization();
        Database.dbLoad();
        Menu.mainMenu();
    }
}
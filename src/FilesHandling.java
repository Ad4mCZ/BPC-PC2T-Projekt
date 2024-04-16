import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FilesHandling {
    public static void Serialization(Book book) {
        String folderPath = "books/";
        String fileName = folderPath + "Object_" + UUID.randomUUID().toString() + ".ser";
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(book);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void DeSerializaton() {
        String folderPath = "books/";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        List<Book> deserializedBooks = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".ser")){
                    try{
                    FileInputStream fileIn = new FileInputStream(file);
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    Book deserializedBook = (Book) objectIn.readObject();
                    deserializedBooks.add(deserializedBook);
                    objectIn.close();;
                    fileIn.close();
                    }catch (IOException | ClassNotFoundException e ){
                        e.printStackTrace();
                    }

                }
            }
        }
        Library.books.addAll(deserializedBooks);

    }
}

import java.sql.*;
import java.util.List;

public class Database {

    private static void dbCreate() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:library.db");

            String sql = """
                    CREATE TABLE IF NOT EXISTS Textbooks(
                    title VARCHAR(100) PRIMARY KEY,
                    authors VARCHAR(100),
                    publicationYear INT,
                    availability BOOLEAN,
                    grade INT
                    );""";
            Statement statement = conn.createStatement();
            statement.execute(sql);
            sql = """
                    CREATE TABLE IF NOT EXISTS Novels(
                    title VARCHAR(100) PRIMARY KEY ,
                    authors VARCHAR(100),
                    publicationYear INT,
                    availability BOOLEAN,
                    genre VARCHAR(100)
                    );""";
            statement.execute(sql);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void dbDelete() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:library.db");
            String sql = "DELETE FROM Novels";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            sql = "DELETE FROM Textbooks";
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dbSave() {
        dbDelete();
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:library.db");
            List<Book> library = Library.books;
            for (Book b : library) {
                String sql = null;
                if (b instanceof Textbook)
                    sql = "INSERT INTO Textbooks (title, authors, publicationYear, availability, grade) VALUES (?,?,?,?,?)";
                else if (b instanceof Novel)
                    sql = "INSERT INTO Novels (title, authors, publicationYear, availability, genre) VALUES (?,?,?,?,?)";
                PreparedStatement prStatement = conn.prepareStatement(sql);
                prStatement.setString(1, b.getName());
                prStatement.setString(2, b.getAutor());
                prStatement.setInt(3, b.getPublishYear());
                prStatement.setBoolean(4, b.getAvailability());
                if (b instanceof Textbook) prStatement.setInt(5, ((Textbook) b).getSuitableGrade());
                else if (b instanceof Novel) prStatement.setString(5, ((Novel) b).getGenreString());
                prStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dbLoad() {
        dbCreate();
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:library.db");

            String sql;
            for (int i = 0; i <= 1; i++) {
                if (i == 0)
                    sql = "SELECT * FROM Textbooks";
                else
                    sql = "SELECT * FROM Novels";

                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String nazev = rs.getString("title");
                    String autori = rs.getString("authors");
                    int rok = rs.getInt("publicationYear");
                    boolean vypujceno = rs.getBoolean("availability");
                    if (i == 0)
                        Library.books.add(new Textbook(nazev, autori, rok,vypujceno, rs.getInt("grade")));
                    else
                        Library.books.add(new Novel(nazev, autori, rok, vypujceno, Novel.Genre.valueOf(rs.getString("genre"))));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

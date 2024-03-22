import java.util.ArrayList;
import java.util.HashMap;

public class Novel extends Book {

    static final private HashMap<Integer,String> genres = new HashMap<Integer, String>();

    public Novel(String name, ArrayList<String> autor, int publishYear, Boolean availability) {
        super(name, autor, publishYear, availability);
    }
    
}

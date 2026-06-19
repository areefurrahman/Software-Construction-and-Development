import java.util.HashMap;
import java.util.Map;

public class LibraryManager {

    Map<String, String> books = new HashMap<>();

    Map<String, Boolean> borrowedStatus = new HashMap<>();




    public  int addBook(String bookId, String title){
        if (bookId.trim().isEmpty() || bookId == null) return  books.size();
        if (title.trim().isEmpty() || title == null) return  books.size();
        if (books.containsKey(bookId)) return  books.size();

        books.put(bookId, title);
        borrowedStatus.put(bookId, false);
        return books.size();
    }

    public boolean borrowBook(String bookId){

        if (!(books.containsKey(bookId))){
            return false;
        }
        if (borrowedStatus.get(bookId)){
            return false;
        }
        borrowedStatus.put(bookId, true);
        return true;
    }

    public boolean returnBook(String bookId){
        if (!books.containsKey(bookId)) return false;
        if (!borrowedStatus.get(bookId)) return false;

        borrowedStatus.put(bookId,false);
        return true;
    }

    public int calculateLateFee( String bookId, int daysLate){
        int fine;
        if (!books.containsKey(bookId)) return 0;
        if (daysLate <= 0) return 0;

        if (daysLate < 20) {
            fine = 50 * daysLate;
        }else {
            fine = 1000;
        }
        return fine;
    }
    public String getBookStatus(String bookId){
        if (!borrowedStatus.containsKey(bookId)) return "Book Not Found";
        return borrowedStatus.get(bookId) ? "Borrowed" : "Available";
    }


}

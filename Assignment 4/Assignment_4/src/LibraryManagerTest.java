import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryManagerTest {

    private LibraryManager library;

    @BeforeEach
    void setUp() {
        library = new LibraryManager(); // fresh object before every test
    }



    // addBook
    @Test
    void testAddBook_Valid() {
        assertEquals(1, library.addBook("B101", "Clean Code"));
    }

    @Test
    void testAddBook_BlankId_Invalid() {
        assertEquals(0, library.addBook("", "Clean Code"));
    }

    @Test
    void testAddBook_BlankTitle_Invalid() {
        assertEquals(0, library.addBook("B102", "   "));
    }

    @Test
    void testAddBook_DuplicateId_Invalid() {
        library.addBook("B101", "Clean Code");
        assertEquals(1, library.addBook("B101", "Different Title")); // count unchanged
    }

    // borrowBook

    @Test
    void testBorrowBook_Valid() {
        library.addBook("B101", "Clean Code");
        assertTrue(library.borrowBook("B101"));
    }

    @Test
    void testBorrowBook_MissingBook_Invalid() {
        assertFalse(library.borrowBook("B999"));
    }

    @Test
    void testBorrowBook_AlreadyBorrowed_Invalid() {
        library.addBook("B101", "Clean Code");
        library.borrowBook("B101");
        assertFalse(library.borrowBook("B101")); // borrowing it a second time
    }

    //returnBook

    @Test
    void testReturnBook_Valid() {
        library.addBook("B101", "Clean Code");
        library.borrowBook("B101");
        assertTrue(library.returnBook("B101"));
    }

    @Test
    void testReturnBook_MissingBook_Invalid() {
        assertFalse(library.returnBook("B999"));
    }

    @Test
    void testReturnBook_AlreadyAvailable_Invalid() {
        library.addBook("B101", "Clean Code"); // never borrowed
        assertFalse(library.returnBook("B101"));
    }

    //calculateLateFee

    @Test
    void testCalculateLateFee_OneDay_Valid() {
        library.addBook("B101", "Clean Code");
        assertEquals(50, library.calculateLateFee("B101", 1));
    }

    @Test
    void testCalculateLateFee_ZeroDays_Invalid() {
        library.addBook("B101", "Clean Code");
        assertEquals(0, library.calculateLateFee("B101", 0));
    }

    @Test
    void testCalculateLateFee_TwentyDays_BoundaryAtCap() {
        library.addBook("B101", "Clean Code");
        assertEquals(1000, library.calculateLateFee("B101", 20));
    }

    @Test
    void testCalculateLateFee_MoreThanTwentyDays_StaysCapped() {
        library.addBook("B101", "Clean Code");
        assertEquals(1000, library.calculateLateFee("B101", 25));
    }

    //getBookStatus

    @Test
    void testGetBookStatus_Available_Valid() {
        library.addBook("B101", "Clean Code");
        assertEquals("Available", library.getBookStatus("B101"));
    }

    @Test
    void testGetBookStatus_Borrowed_Valid() {
        library.addBook("B101", "Clean Code");
        library.borrowBook("B101");
        assertEquals("Borrowed", library.getBookStatus("B101"));
    }

    @Test
    void testGetBookStatus_NotFound_Invalid() {
        assertEquals("Book Not Found", library.getBookStatus("B999"));
    }
}
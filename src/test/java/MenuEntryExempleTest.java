import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class MenuEntryExempleTest {

    @Test
    public void testReadCount_ValidInput() {
        String input = "5\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = MenuEntryExemple.readCount("How many buses? ", scanner);
        assertEquals(5, result);
    }

    @Test
    public void testReadCount_BoundaryMin() {
        String input = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = MenuEntryExemple.readCount("How many buses? ", scanner);
        assertEquals(1, result);
    }

    @Test
    public void testReadCount_BoundaryMax() {
        String input = "50\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = MenuEntryExemple.readCount("How many buses? ", scanner);
        assertEquals(50, result);
    }

    @Test
    public void testReadCount_RejectNegative() {
        String input = "-10\n3\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = MenuEntryExemple.readCount("How many buses? ", scanner);
        assertEquals(3, result);
    }

    @Test
    public void testReadCount_RejectZero() {
        String input = "0\n7\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = MenuEntryExemple.readCount("How many buses? ", scanner);
        assertEquals(7, result);
    }

    @Test
    public void testReadCount_RejectTooBig() {
        String input = "100\n40\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = MenuEntryExemple.readCount("How many buses? ", scanner);
        assertEquals(40, result);
    }

    @Test
    public void testReadCount_RejectNonInteger() {
        String input = "abc\n42\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = MenuEntryExemple.readCount("How many buses? ", scanner);
        assertEquals(42, result);
    }

    @Test
    public void testReadCount_RejectEmptyInput() {
        String input = "\n\n15\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = MenuEntryExemple.readCount("How many buses? ", scanner);
        assertEquals(15, result);
    }

    @Test
    public void testReadCount_MultipleWrongInputs() {
        String input = "-5\n0\nabc\n60\n25\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = MenuEntryExemple.readCount("How many buses? ", scanner);
        assertEquals(25, result);
    }
}
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StudentFileParserTest {

    @Test
    public void testParseFile() {
        StudentFileParser studentFileParser = new StudentFileParser();
        List<String> studentAnswers = studentFileParser.parseFile("src\\main\\resources\\studentFile.txt");

        assertNotEquals(9, studentAnswers.size());
        assertEquals(10, studentAnswers.size());
        assertNotEquals(11, studentAnswers.size());
    }
}
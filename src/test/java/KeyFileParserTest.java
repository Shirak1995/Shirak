import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KeyFileParserTest {

    @Test
    public void testParseFile() {
        KeyFileParser keyFileParser = new KeyFileParser();
        List<String> keyAnswers = keyFileParser.parseFile("src\\main\\resources\\keyFile.txt");

        assertNotEquals(9, keyAnswers.size());
        assertEquals(10, keyAnswers.size());
        assertNotEquals(11, keyAnswers.size());
    }
}

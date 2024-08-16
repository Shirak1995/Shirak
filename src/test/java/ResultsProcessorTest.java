import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ResultsProcessorTest {

    @Test
    public void testResult() {
        ResultsProcessor resultsProcessor = new ResultsProcessor();
        int total = resultsProcessor.calculateTotalPoints("src\\main\\resources\\keyFile.txt", "src\\main\\resources\\studentFile.txt");
        assertEquals(12, total);
        assertNotEquals(18, total);
    }
}

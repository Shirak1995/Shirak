import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveryCostCalculatorTest {

    @Test
    @Tag("basic")
    public void testCalculateDeliveryCost_Basic() {
        int cost = DeliveryCostCalculator.calculateDeliveryCost(5, "small", false, "normal");
        assertEquals(400, cost);
    }

    @ParameterizedTest
    @CsvSource({
            "5, small, false, normal, 400",
            "5, large, false, increased, 400",
            "31, small, false, increased, 480",
            "10, large, true, normal, 600",
            "31, small, false, normal, 400",
            "31, large, false, very high, 800",
            "5, small, true, normal, 500",
            "5, large, true, normal, 600",
            "31, small, true, normal, Exception" // ожидаем исключение
    })

    @Tag("parameterized")
    public void testCalculateDeliveryCost_Parameterized(int distance, String size, boolean isFragile, String loadLevel, String expected) {
        if ("Exception".equals(expected)) {
            assertThrows(IllegalArgumentException.class, () -> {
                DeliveryCostCalculator.calculateDeliveryCost(distance, size, isFragile, loadLevel);
            });
        } else {
            int cost = DeliveryCostCalculator.calculateDeliveryCost(distance, size, isFragile, loadLevel);
            assertEquals(Integer.parseInt(expected), cost);
        }
    }
}

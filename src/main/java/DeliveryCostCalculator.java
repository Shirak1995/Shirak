public class DeliveryCostCalculator {

    public static final int MIN_DELIVERY_COST = 400;

    public static int calculateDeliveryCost(int distance, String size, boolean isFragile, String loadLevel) {
        int cost = 0;

        // Расчет стоимости в зависимости от расстояния
        if (distance > 30) {
            cost += 300;
        } else if (distance > 10) {
            cost += 200;
        } else if (distance > 2) {
            cost += 100;
        } else {
            cost += 50;
        }

        // Расчет стоимости в зависимости от габаритов
        if ("large".equalsIgnoreCase(size)) {
            cost += 200;
        } else if ("small".equalsIgnoreCase(size)) {
            cost += 100;
        }

        // Проверка на хрупкость
        if (isFragile) {
            if (distance > 30) {
                throw new IllegalArgumentException("Fragile items cannot be delivered over 30 km.");
            }
            cost += 300;
        }

        // Коэффициент загруженности
        double loadCoefficient = 1.0;
        switch (loadLevel.toLowerCase()) {
            case "very high":
                loadCoefficient = 1.6;
                break;
            case "high":
                loadCoefficient = 1.4;
                break;
            case "increased":
                loadCoefficient = 1.2;
                break;
        }

        // Применение коэффициента
        cost *= loadCoefficient;

        // Проверка на минимальную стоимость
        return Math.max(cost, MIN_DELIVERY_COST);
    }
}

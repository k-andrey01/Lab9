public class Main {
    public static void main(String[] args) {
        int weeklyDemand = 300;
        int weeklyOrderAmount = 300;
        int orderCost = 20;
        double storageDailyCost = 0.03;
        int timeToDelivery = 0;

        double storageWeeklyCost = storageDailyCost * 7;

        System.out.println("Пункт а: Недельные затраты по существующей стратегии");
        double weeklyCostByCurrentStrategy = getWeeklyCost(weeklyDemand, weeklyOrderAmount, orderCost, storageWeeklyCost);
        System.out.println(weeklyCostByCurrentStrategy + "\n");

        System.out.println("Пункт b: Оптимальный заказ");
        int optimalOrderAmount = getOptimalOrderAmount(orderCost, weeklyDemand, storageWeeklyCost);
        System.out.println("Объём заказа: " + optimalOrderAmount);
        double weeklyCostByOptimalStrategy = getWeeklyCost(weeklyDemand, optimalOrderAmount, orderCost, storageWeeklyCost);
        System.out.printf("Недельные затраты по оптимальной стратегии: %.2f\n", weeklyCostByOptimalStrategy);
        double orderFrequency = getOrderFrequency(optimalOrderAmount, weeklyDemand);
        System.out.printf("Новый заказ через: %.2f недели (или %.2f дней)\n", orderFrequency, (7 * orderFrequency));
        int reserveForOrder = getReserveForOrder(weeklyDemand, timeToDelivery);
        System.out.println("Заказ подавать при уровне запаса " + reserveForOrder +" фунтов");

        System.out.println("\nПункт с: Разница затрат");
        double differentOfStrategies = weeklyCostByCurrentStrategy - weeklyCostByOptimalStrategy;
        System.out.printf("%.2f", differentOfStrategies);
    }

    static double getWeeklyCost(int weeklyDemand, int weeklyOrderAmount ,int orderCost, double storageWeeklyCost){
        double weeklyCost = orderCost * (weeklyDemand / weeklyOrderAmount) + storageWeeklyCost * (weeklyOrderAmount / 2);
        return weeklyCost;
    }

    static int getOptimalOrderAmount(int orderCost, int weeklyDemand, double storageWeeklyCost){
        int optimalOrderAmount = (int) Math.sqrt((2 * orderCost * weeklyDemand) / storageWeeklyCost);
        return optimalOrderAmount;
    }

    static double getOrderFrequency(int optimalOrderAmount, int weeklyDemand){
        double orderFrequency = (double) optimalOrderAmount / weeklyDemand;
        return orderFrequency;
    }

    static int getReserveForOrder(int weeklyDemand, int timeToDelivery){
        int reserveForOrder = (weeklyDemand / 7) * timeToDelivery;
        return reserveForOrder;
    }
}
import java.util.*;

public class StockProfit {

    public static int maxProfit(int[] prices) {
        int profit = 0;
        List<Integer> inventory = new ArrayList<>();

        for (int i = 0; i < prices.length; i++) {
            int todayPrice = prices[i];

            // Look ahead: if future price is higher, hold
            boolean hasHigherFuture = false;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > todayPrice) {
                    hasHigherFuture = true;
                    break;
                }
            }

            if (hasHigherFuture) {
                // Buy today (can buy 1 stock per day)
                inventory.add(todayPrice);
            } else {
                // Sell all inventory at today's price
                for (int buyPrice : inventory) {
                    profit += todayPrice - buyPrice;
                }
                inventory.clear(); // all stocks sold
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 5, 3, 4};
        System.out.println(maxProfit(prices)); // Should print 8
    }
}

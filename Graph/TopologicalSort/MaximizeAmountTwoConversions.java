import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MaximizeAmountTwoConversions {
  public double maxAmount(String initialCurrency,
      List<List<String>> pairs1, double[] rates1,
      List<List<String>> pairs2, double[] rates2) {

    Map<String, Map<String, Double>> map1 = buildGraph(pairs1, rates1);
    Map<String, Map<String, Double>> map2 = buildGraph(pairs2, rates2);

    // conversion for day1
    Map<String, Double> day1 = new HashMap<>();
    day1.put(initialCurrency, 1.0);
    performConversion(map1, day1);

    // conversion for day2
    Map<String, Double> day2 = new HashMap<>(day1);
    performConversion(map2, day2);
    return day2.getOrDefault(initialCurrency, 1.0);
  }

  public Map<String, Map<String, Double>>  buildGraph(List<List<String>> pairs, double[] rates) {
    Map<String, Map<String, Double>> map = new HashMap<>();
    for (int i = 0; i < pairs.size(); i++) {
      String from = pairs.get(i).get(0);
      String to = pairs.get(i).get(1);
      double rate = rates[i];

      map.putIfAbsent(from, new HashMap<>());
      map.putIfAbsent(to, new HashMap<>());
      map.get(from).put(to, rate);
      map.get(to).put(from, 1.0 / rate);
    }
    return map;
  }

  public void performConversion(Map<String, Map<String, Double>> map, Map<String, Double> curr) {
    Queue<String> queue = new LinkedList<>();

    for(String currency : curr.keySet()) {
      queue.offer(currency);
    }

    while(!queue.isEmpty()) {
      String currency = queue.poll();
      double currValue = curr.get(currency);
      if(map.containsKey(currency) && map.get(currency) != null) {
        for(Map.Entry<String, Double> entry : map.get(currency).entrySet()) {
          String next = entry.getKey();
          double rate = entry.getValue();
          double newRate = rate * currValue;

          if(!curr.containsKey(next) || curr.get(next) < newRate) {
            curr.put(next, newRate);
            queue.offer(next);
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    MaximizeAmountTwoConversions m = new MaximizeAmountTwoConversions();

    String initialCurrency = "EUR";
    List<List<String>> pairs1 = new ArrayList<>();
    pairs1.add(List.of("EUR","USD"));
    pairs1.add(List.of("USD","JPY"));
    double[] rates1 = {2.0,3.0};

    List<List<String>> pairs2 = new ArrayList<>();
    pairs2.add(List.of("JPY", "USD"));
    pairs2.add(List.of("USD", "CHF"));
    pairs2.add(List.of("CHF", "EUR"));

    double[] rates2 = {4.0, 5.0, 6.0};

    double result = m.maxAmount(initialCurrency, pairs1, rates1, pairs2, rates2);
    System.out.println("Maximum amount of " + initialCurrency + ": " + result);
  }
}

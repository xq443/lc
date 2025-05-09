package Meta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
  public List<String> topKFrequent(String text, int k) {
    String[] words = text.split("\\s+"); // one or more whitespace
    Map<String, Integer> freq = new HashMap<>();
    for (String word : words) {
      freq.put(word, freq.getOrDefault(word, 0) + 1);
    }

    // Min-heap: lowest freq (or lexically larger if freq same) gets removed first
    PriorityQueue<String> pq = new PriorityQueue<>(
        (a, b) -> freq.get(a).equals(freq.get(b))? b.compareTo(a): freq.get(a) - freq.get(b));

    for(String word : freq.keySet()) {
      pq.add(word);
      if(pq.size() > k) pq.poll();
    }

    List<String> ret = new ArrayList<>();
    while(!pq.isEmpty()) {
      ret.add(pq.poll());
    }
    Collections.reverse(ret);
    return ret;
  }

  public static void main(String[] args) {
    TopKFrequentWords t = new TopKFrequentWords();
    String text = "the day is sunny the the sunny is is";
    List<String> sortedWords = t.topKFrequent(text, 3);
    for (String word : sortedWords) {
      System.out.println(word);
    }
  }
}

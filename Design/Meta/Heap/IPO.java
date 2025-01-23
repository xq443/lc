package Meta.Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class IPO {
  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    List<int[]> tasks = new ArrayList<>();
    for(int i = 0; i < profits.length; i++) {
      tasks.add(new int[]{profits[i], capital[i]});
    }

    // sort capital in asc order
    tasks.sort(Comparator.comparingInt(a -> a[1]));

    // max heap
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

    int i = 0;
    while(k-- > 0) {
      while(i < profits.length && w >= tasks.get(i)[1]) {
        pq.offer(tasks.get(i)[0]);
        i++;
      }
      if(!pq.isEmpty()) w += pq.poll();
    }

    return w;
  }

  public static void main(String[] args) {
    IPO i = new IPO();
    int k = 2, w = 0;
    int[] profits = {1,2,3}, capital = {0,1,1};
    System.out.println(i.findMaximizedCapital(k, w, profits, capital));
  }
}

// TC O(NLogN)
// SC O(N)

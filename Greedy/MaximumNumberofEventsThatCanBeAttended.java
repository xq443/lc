import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumNumberofEventsThatCanBeAttended {
  public int maxEvents(int[][] events) {
    if(events == null || events.length == 0) return 0;
    Arrays.sort(events, (a, b) -> (a[0] - b[0]));
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int idx = 0, curr = 0, ret = 0;
    while(!pq.isEmpty() || idx < events.length) {
      if(pq.isEmpty()) {
        curr = events[idx][0];
      }

      // add the available events held before or equal to curr day
      while(idx < events.length && events[idx][0] <= curr) {
        pq.offer(events[idx][1]);
        idx++;
      }

      // remove the event that is overdue
      while(!pq.isEmpty() && pq.peek() < curr) {
        pq.poll();
      }

      // remove the already attended events and increment the ret
      if(!pq.isEmpty()) {
        pq.poll();
        ret++;
        curr++;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    MaximumNumberofEventsThatCanBeAttended m = new MaximumNumberofEventsThatCanBeAttended();
    int[][] events =  {{1,2},{2,3},{3,4}};
    System.out.println(m.maxEvents(events));
  }
}

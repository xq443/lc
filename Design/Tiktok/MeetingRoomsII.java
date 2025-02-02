package Tiktok;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsII {
  public int minMeetingRooms(int[][] intervals) {
    Arrays.sort(intervals, (int[] a, int[] b) -> (a[0] - b[0]));
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (a - b));
    pq.offer(intervals[0][1]);
    for (int i = 1; i < intervals.length; i++) {
      if(pq.isEmpty()) break;
      if(pq.peek() <= intervals[i][0]) { // no overlap
        pq.poll(); // free up the previous end meeting and reuse the room
      }
      pq.offer(intervals[i][1]);
    }
    return pq.size();
  }

  public static void main(String[] args) {
    MeetingRoomsII m = new MeetingRoomsII();
    int[][] intervals = {
        {0, 30},
        {5, 10},
        {15, 20}
    };
    int ret = m.minMeetingRooms(intervals);
    System.out.println(ret);
  }
}

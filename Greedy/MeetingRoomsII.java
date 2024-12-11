import java.util.PriorityQueue;

public class MeetingRoomsII {
  public int minMeetingRooms(int[][] intervals) {
    if(intervals.length <= 1) return intervals.length;
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->(a - b));
    pq.offer(intervals[0][1]);
    for(int i = 1; i < intervals.length; i++) {
      if(intervals[i][0] >= pq.peek()) {
        pq.poll();
      }
      pq.offer(intervals[i][1]);
    }
    return pq.size();
  }

  public static void main(String[] args) {
    MeetingRoomsII obj = new MeetingRoomsII();
    int[][] intervals = {{0,30},{5,10},{15,20}};
    System.out.println(obj.minMeetingRooms(intervals));
  }
}

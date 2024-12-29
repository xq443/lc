import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsIII {
  public int mostBooked(int n, int[][] meetings) {
    Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

    PriorityQueue<Integer> availableRooms = new PriorityQueue<>((a, b) -> (a - b));
    for(int i = 0; i < n; i++){
      availableRooms.add(i);
    }

    // end time, room
    PriorityQueue<int[]> ongoingMeetings = new PriorityQueue<>((a, b) ->
        (a[0] != b[0]) ? a[0] - b[0] : a[1] - b[1]);

    int[] usage = new int[n];

    for(int[] meeting : meetings){
      int start = meeting[0];
      int end = meeting[1];

      // Free up rooms that have completed their meetings
      while(!ongoingMeetings.isEmpty() && ongoingMeetings.peek()[0] < start){
        int[] finish = ongoingMeetings.poll();
        availableRooms.add(finish[1]);
      }

      if(!availableRooms.isEmpty()){
        int room = availableRooms.poll();
        usage[room]++;
        ongoingMeetings.add(new int[]{end, room});
      } else {
        int[] newRoom = ongoingMeetings.poll();
        assert newRoom != null;
        int room = newRoom[1];
        int newStart = newRoom[0];
        int newEnd = newStart + (end - start);
        usage[room]++;
        ongoingMeetings.add(new int[]{newEnd, room});
      }
    }

    int max = 0;
    int retRoom = 0;
    for(int i = 0; i < usage.length; i++){
      if(usage[i] > max){
        max = usage[i];
        retRoom = i;
      }
    }
    return retRoom;
  }

   public static void main(String[] args) {
     MeetingRoomsIII solver = new MeetingRoomsIII();
     int n1 = 2;
     int[][] meetings1 = {{0, 10}, {1, 5}, {2, 7}, {3, 4}};
     System.out.println(solver.mostBooked(n1, meetings1)); // Output: 0

     int n2 = 3;
     int[][] meetings2 = {{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}};
     System.out.println(solver.mostBooked(n2, meetings2)); // Output: 1
   }
}

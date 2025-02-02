package Tiktok;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsIII {
  public int mostBooked(int n, int[][] meetings) {
    // min heap: sort available rooms by room number
    PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
    for (int i = 0; i < n; i++) {
      freeRooms.add(i);
    }

    PriorityQueue<long[]> ongoingMeetings = new PriorityQueue<>((a, b) -> a[0] != b[0] ?
                          Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));
    // min heap: sort by end time, and then idx {end, idx}

    Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
    // record the usage times for each room
    int[] roomUsage = new int[n];

    for(int[] meeting: meetings) {
      int start = meeting[0];
      int end = meeting[1];

      // free up rooms that have completed the meetings
      while(!ongoingMeetings.isEmpty() && ongoingMeetings.peek()[0] <= start) {
        long[] finished = ongoingMeetings.poll();
        freeRooms.add((int)finished[1]);
      }

      // assign the meeting rooms
      if(!freeRooms.isEmpty()) {
        int room = freeRooms.poll();
        roomUsage[room]++;
        ongoingMeetings.add(new long[]{end, room});
      } else { // no room is available, wait and delay the meeting
        long[] next = ongoingMeetings.poll();
        long newStart = next[0];
        int newRoom = (int)next[1];
        long newEnd = newStart + (end - start);
        roomUsage[newRoom]++;
        ongoingMeetings.add(new long[]{newEnd, newRoom});
      }
    }

    int ret = 0; // max usage
    int room = 0; // room number
    for(int i = 0; i < roomUsage.length; i++) {
      if(roomUsage[i] > ret) {
        ret = roomUsage[i];
        room = i;
      }
    }
    return room;
  }

  public static void main(String[] args) {
    MeetingRoomsIII m = new MeetingRoomsIII();
    int[][] meetings1 = {{0, 10}, {1, 5}, {2, 7}, {3, 4}};
    int n1 = 2;
    System.out.println(m.mostBooked(n1, meetings1)); // Output: 0

    int n2 = 3;
    int[][] meetings2 = {{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}};
    System.out.println(m.mostBooked(n2, meetings2)); // Output: 1
  }
}

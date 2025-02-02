package Tiktok;

import java.util.Arrays;

public class MeetingRoomsIITwoPointers {
  public int minMeetingRooms(int[][] intervals) {
    int[] start = new int[intervals.length];
    int[] end = new int[intervals.length];
    for(int i = 0; i < intervals.length; i++) {
      start[i] = intervals[i][0];
      end[i] = intervals[i][1];
    }

    Arrays.sort(start);
    Arrays.sort(end);
    int ret = 0;

    int endIdx = 0;
    for(int startIdx = 0; startIdx < intervals.length; startIdx++) {
      if(start[startIdx] < end[endIdx]) {
        ret++;
      } else {
        endIdx++;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    MeetingRoomsIITwoPointers m = new MeetingRoomsIITwoPointers();
    int[][] intervals = {
        {0, 30},
        {5, 10},
        {15, 20}
    };
    int ret = m.minMeetingRooms(intervals);
    System.out.println(ret);
  }
}

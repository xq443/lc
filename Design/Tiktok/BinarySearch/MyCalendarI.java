package Tiktok.BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class MyCalendarI {
  List<int[]> list;
  public MyCalendarI() {
    list = new ArrayList<>();
  }

  public boolean book(int start, int end) {
    int left = 0, right = list.size() - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      int[] event = list.get(mid);

      if (event[0] >= end) {
        right = mid - 1;
      } else if (event[1] <= start) {
        left = mid + 1;
      } else { // overlapping
        return false;
      }
    }
    list.add(left, new int[]{start, end});
    return true;
  }

  public static void main(String[] args) {
    MyCalendarI myCalendar = new MyCalendarI();
    System.out.println(myCalendar.book(10, 20)); // true
//    System.out.println(myCalendar.book(15, 25)); // false
//    System.out.println(myCalendar.book(20, 30)); // false
  }
}

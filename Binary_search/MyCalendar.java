import java.util.ArrayList;
import java.util.List;

class MyCalendar {
    List<int[]> calendar;

    public MyCalendar() {
        this.calendar = new ArrayList<>();
    }

    public boolean book(int startTime, int endTime) {
        // find the first index >= last event's endTime
        if(calendar.isEmpty()) {
            calendar.add(new int[]{startTime, endTime});
            return true;
        }

        // Binary search : O(logN)
        int left = 0, right = calendar.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int[] curr = calendar.get(mid);
            if(curr[0] >= endTime) {
                right = mid - 1;
            } else if(curr[1] <= startTime) {
                left = mid + 1;
            } else {
                return false;
            }
        }
        calendar.add(left, new int[]{startTime, endTime}); // TC: O(N) -> dominates
        return true;
    }

    public static void main(String[] args) {
        MyCalendar mc = new MyCalendar();
        System.out.println(mc.book(10, 20));
        System.out.println(mc.book(15, 25));
        System.out.println(mc.book(20, 30));
    }

    // TC: O(N)
    // SC: O(N)
}
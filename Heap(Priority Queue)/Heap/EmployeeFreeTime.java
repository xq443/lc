import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeFreeTime {
  static class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public String toString() {
      return start + "," + end;
    }
  }

  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    List<Interval> flatAll = new ArrayList<>();
    for(List<Interval> intervals : schedule) {
      flatAll.addAll(intervals);
    }

    // sort by the start time
    flatAll.sort(Comparator.comparingInt(a -> a.start));

    List<Interval> ret = new ArrayList<>();
    int end = flatAll.get(0).end;

    for(Interval interval : flatAll) {
      if(interval.start > end) {
        ret.add(interval);
      }
      end = Math.max(end, interval.end);
    }
    return ret;
  }

  public static void main(String[] args) {
    EmployeeFreeTime employeeFreeTime = new EmployeeFreeTime();

    List<List<Interval>> schedule = new ArrayList<>();
    List<Interval> employee1 = new ArrayList<>();
    employee1.add(new Interval(1, 2));
    employee1.add(new Interval(5, 6));
    List<Interval> employee2 = new ArrayList<>();
    employee2.add(new Interval(1, 3));
    List<Interval> employee3 = new ArrayList<>();
    employee3.add(new Interval(4, 10));

    schedule.add(employee1);
    schedule.add(employee2);
    schedule.add(employee3);

    System.out.println(employeeFreeTime.employeeFreeTime(schedule).toString());
  }
}

// TC: nlogn
// SC: n
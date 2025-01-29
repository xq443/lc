package Apple;

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
      return "Interval{" +
          "start=" + start +
          ", end=" + end +
          '}';
    }
  }
  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    List<Interval> ret = new ArrayList<>();
    List<Interval> flattern = new ArrayList<>();
    for(List<Interval> i : schedule) {
      flattern.addAll(i);
    }

    //flattern.sort((a, b) -> (a.start - b.start));
    flattern.sort(Comparator.comparingInt(a -> a.start));

    int end = flattern.get(0).end;
    for(Interval i : flattern) {
      if(end < i.start) {
        ret.add(new Interval(end, i.start));
      }
      end = Math.max(end, i.end);
    }
    return ret;
  }

  public static void main(String[] args) {
    EmployeeFreeTime e = new EmployeeFreeTime();
    List<List<Interval>> schedule = new ArrayList<>();
    List<Interval> flattern = new ArrayList<>();
    flattern.add(new Interval(1, 2));
    flattern.add(new Interval(5, 6));
    schedule.add(flattern);
    List<Interval> flattern1 = new ArrayList<>();
    flattern1.add(new Interval(1, 3));
    schedule.add(flattern1);
    List<Interval> flattern2 = new ArrayList<>();
    flattern2.add(new Interval(4, 10));
    schedule.add(flattern2);

    System.out.println(e.employeeFreeTime(schedule));
  }
}

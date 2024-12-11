import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    if(intervals == null || intervals.length == 0) return new int[0][0];
    Arrays.sort(intervals, (a, b) -> (a[0]- b[0]));

    List<int[]> ret = new ArrayList<>();
    ret.add(intervals[0]);
    for(int i = 1; i < intervals.length; i++) {
      int[] curr = ret.get(ret.size() - 1);
      if(curr[1] >= intervals[i][0]) {
        curr[1] = Math.max(curr[1], intervals[i][1]);
      } else {
        ret.add(intervals[i]);
      }
    }
    return ret.toArray(new int[ret.size()][2]);
  }

  public static void main(String[] args) {
    MergeIntervals m = new MergeIntervals();
    int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

    //  Arrays.toString(): only works for one-dimensional arrays
    //  deepToString(): handles nested arrays recursively
    System.out.println(Arrays.deepToString(m.merge(intervals)));
  }
}

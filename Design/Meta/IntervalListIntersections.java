package Meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalListIntersections {
  public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
    List<int[]> ret = new ArrayList<>();
    int i = 0, j = 0;
    while (i < firstList.length && j < secondList.length) {
      if(firstList[i][1] < secondList[j][0]) i++;
      else if(firstList[i][0] > secondList[j][1]) j++;
      else {
        ret.add(new int[]{Math.max(firstList[i][0], secondList[j][0]), Math.min(firstList[i][1], secondList[j][1])});
        if(firstList[i][0] < secondList[j][0]) i++;
        else j++;
      }
    }
    return ret.toArray(new int[ret.size()][2]);
  }

  public static void main(String[] args) {
    IntervalListIntersections i = new IntervalListIntersections();
    int[][] first = new int[][] {
        {0,2},{5,10},{13,23},{24,25}
    };
    int[][] second = new int[][] {
        {1,5},{8,12},{15,24},{25,26}
    };
    int[][] res = i.intervalIntersection(first, second);
    System.out.println(Arrays.deepToString(res));
  }
}

package Google;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsLine {
  public int maxPoints(int[][] points) {
    if(points.length <= 2) return points.length;

    int ret = 0;
    for(int i = 0; i < points.length; i++){
      Map<String, Integer> map = new HashMap<>();
      int currMax = 0;
      int duplicate = 0;
      int vertical = 0;

      for(int j = i + 1; j < points.length; j++){
        int dx = points[j][0] - points[i][0];
        int dy = points[j][1] - points[i][1];

        if(dx == 0 && dy == 0) {
          duplicate++;
          continue;
        }

        if(dx == 0) {
          vertical++;
          continue;
        }

        int gcd = gcd(dx, dy);
        dx /= gcd;
        dy /= gcd;

        String slope = dx + "/" + dy;
        map.put(slope, map.getOrDefault(slope, 0) + 1);
        currMax = Math.max(currMax, map.get(slope));
      }

      currMax = Math.max(currMax, vertical);
      ret = Math.max(ret, currMax + duplicate + 1);
    }
    return ret;
  }

  private int gcd(int a, int b) {
    while(b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  public static void main(String[] args) {
    MaxPointsLine m =  new MaxPointsLine();
    int[][] points =  {{1,1},{2,2},{3,3}};
    System.out.println(m.maxPoints(points));
  }
}

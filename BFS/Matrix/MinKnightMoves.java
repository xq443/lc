package Matrix;

import java.util.LinkedList;
import java.util.Queue;

public class MinKnightMoves {
  public int M = 300;
  public int[][] dirs = {{2, 1}, {1, 2}, {-2, -1}, {-1, -2}, {2, -1}, {1, -2}, {-2, 1}, {-1, 2}};
  public int minKnightMoves(int x, int y) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{0,0});
    // mark if visited
    boolean[][] visited = new boolean[2*M + 1][2*M + 1];

    int ret = 0;
    while(!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        int currX = curr[0];
        int currY = curr[1];
        if(currX == x && currY == y) return ret;

        for(int[] d : dirs) {
          int newX = currX + d[0];
          int newY = currY + d[1];
          if(newX < -M || newX > M || newY < -M || newY > M || visited[newX + M][newY + M]) continue;
          visited[newX + M][newY + M] = true;
          queue.add(new int[]{newX, newY});
        }
      }
      ret++;
    }
    return ret;
  }

  public static void main(String[] args) {
    MinKnightMoves m = new MinKnightMoves();
    System.out.println(m.minKnightMoves(2, 1));
    System.out.println(m.minKnightMoves(5, 5));
  }
}
// TC SC: O(N), where N = (2M + 1)²
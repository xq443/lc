package Matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SnakesLadders {
  public int snakesAndLadders(int[][] board) {
    int n = board.length;
    int[] flattenedBoard = flattened(board);
    Queue<int[]> queue = new LinkedList<>(); // position, move
    boolean[] visited = new boolean[n * n + 1];

    queue.offer(new int[] {1, 0});
    visited[1] = true;

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int pos = current[0];
      int move = current[1];

      if(pos == n * n) return move;
      for(int i = 1; i <= 6; i++) {
        int next = pos + i;
        if(next > n * n) break;
        if(flattenedBoard[next] != -1) {
          next = flattenedBoard[next];
        }
        if(!visited[next]) {
          visited[next] = true;
          queue.offer(new int[] {next, move + 1});
        }
      }
    }
    return -1;
  }
  private int[] flattened(int[][] board) {
    int n = board.length;
    boolean flag = true;

    int[] ret = new int[n * n + 1];
    Arrays.fill(ret, -1);
    int idx = 1;

    for(int row = n - 1; row >= 0; row--) {
      if(flag) {
        for(int col = 0; col < n; col++) {
          ret[idx++] = board[row][col];
        }
      } else {
        for(int col = n - 1; col >= 0; col--) {
          ret[idx++] = board[row][col];
        }
      }
      flag = !flag;
    }
    return ret;
  }

  public static void main(String[] args) {
    SnakesLadders s = new SnakesLadders();
    int[][] board = {
        {-1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1},
        {-1, 35, -1, -1, 13, -1},
        {-1, -1, -1, -1, -1, -1},
        {-1, 15, -1, -1, -1, -1}
    };

    System.out.println(s.snakesAndLadders(board));
  }
}

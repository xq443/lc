package Matrix;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
  public int shortestBridge(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    boolean[][] visited = new boolean[n][m];
    Queue<int[]> queue = new LinkedList<>();

    boolean found = false;
    for (int i = 0; i < n && !found; i++) {
      for (int j = 0; j < m && !found; j++) {
        if (grid[i][j] == 1) {
          dfs(grid, visited, i, j, queue);
          found = true;
        }
      }
    }

    // bfs
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int ret = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] cur = queue.poll();
        for (int[] dir : dirs) {
          assert cur != null;
          int x = cur[0] + dir[0];
          int y = cur[1] + dir[1];
          if(x < 0 || x >= n || y < 0 || y >= m || visited[x][y]) continue;
          if(grid[x][y] == 1) {
            return ret;
          }
          visited[x][y] = true;
          queue.add(new int[]{x, y});
        }
      }
      ret++;
    }
    return -1;
  }

  // dfs to find the first island
  private void dfs(int[][] grid, boolean[][] visited, int i, int j, Queue<int[]> queue) {
    int n = grid.length;
    int m = grid[0].length;
    if(i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == 0 || visited[i][j]) return;

    visited[i][j] = true;
    queue.add(new int[]{i, j});
    dfs(grid, visited, i + 1, j, queue);
    dfs(grid, visited, i - 1, j, queue);
    dfs(grid, visited, i, j + 1, queue);
    dfs(grid, visited, i, j - 1, queue);
  }

  public static void main(String[] args) {
    ShortestBridge shortestBridge = new ShortestBridge();
    int[][] grid = {
        {1,1,1,1,1},
        {1,0,0,0,1},
        {1,0,1,0,1},
        {1,0,0,0,1},
        {1,1,1,1,1}
        };

    System.out.println(shortestBridge.shortestBridge(grid));
  }
}

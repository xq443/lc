package Apple;

public class NumberOfIslands {
  public int numIslands(char[][] grid) {
    if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    int ret = 0;
    int n = grid.length;
    int m = grid[0].length;
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        if(grid[i][j] == '1') {
          numIslandDfs(grid, i, j);
          ret++;
        }
      }
    }
    return ret;
  }

  public void numIslandDfs(char[][] grid, int i, int j) {
    if(i < 0 || i >= grid.length ||  j < 0 || j >= grid[i].length || grid[i][j] != '1') return;
    grid[i][j] = '0';
    numIslandDfs(grid, i - 1, j);
    numIslandDfs(grid, i + 1, j);
    numIslandDfs(grid, i, j - 1);
    numIslandDfs(grid, i, j + 1);
  }
}

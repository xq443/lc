package Snowflake;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistancefromAllBuildings {
    int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] dis;
    int[][] visited;
    int cnt;
    public int shortestDistance(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        this.dis = new int[n][m];
        this.visited = new int[n][m];
        this.cnt = 0;

        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    cnt++;
                    bfs(grid, i, j);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 0 && visited[i][j] == cnt) {
                    ret = Math.min(ret, dis[i][j]);
                }
            }
        }
        return (ret == Integer.MAX_VALUE) ? -1 : ret;
    }

    public void bfs(int[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        int distance = 1;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int k = 0; k < size; k++) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                for(int[] dir : dir) {
                    int newX = x + dir[0], newY = y + dir[1];
                    if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length &&
                    grid[newX][newY] == 0 && visited[newX][newY] == cnt - 1) {
                        visited[newX][newY] += 1;
                        dis[newX][newY] += distance;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
            distance++;
        }
    }

    public static void main(String[] args) {
        ShortestDistancefromAllBuildings s = new ShortestDistancefromAllBuildings();
        System.out.println(s.shortestDistance(new int[][] {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}}));
    }

    // TC: O(cnt * M * N)
    // SC: O(M * N)
}

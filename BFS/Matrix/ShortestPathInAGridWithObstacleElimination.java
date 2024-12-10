package Matrix;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class ShortestPathInAGridWithObstacleElimination {

    public int ShortestPath(int[][] grid, int k){
        int[][][] visited = new int[40][40][1601];
        int[][] dir = {{1,0},{-1,0},{0,1}, {0,-1}};

        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0});
        visited[0][0][0] = 1;
        int step = 0;

        while(!q.isEmpty()){
            int len = q.size();
            while(len -->0){
                int[] curr = q.poll();
                assert curr != null;
                int x = curr[0];
                int y = curr[1];
                int z = curr[2];

                for(int t = 0; t < 4; t++){
                    int i = x + dir[t][0];
                    int j = y + dir[t][1];

                    if(i < 0 || j < 0 || i >=m || j >= n) continue;
                    if(i == m - 1 && j == y - 1) return step + 1;

                    if(Objects.equals(grid[i][j], 1)) {
                        if(z == k) continue;
                        if (visited[i][j][z + 1] == 1) continue;
                        visited[i][j][z + 1] = 1;
                        q.add(new int[]{i, j, z + 1});
                    }else {
                        if (visited[i][j][z] == 1) continue;
                        visited[i][j][z] = 1;
                        q.add(new int[]{i, j, z});
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathInAGridWithObstacleElimination shortestPathInAGridWithObstacleElimitation
            = new ShortestPathInAGridWithObstacleElimination();
        int[][] grid = {{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        int k = 1;
        System.out.println(shortestPathInAGridWithObstacleElimitation.ShortestPath(grid,k));
    }

}
//000
//110
//000
//011
//000
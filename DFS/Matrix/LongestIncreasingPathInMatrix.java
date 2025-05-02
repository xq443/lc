public class LongestIncreasingPathInMatrix {
    int[][] memo;
    int ret;
    int m;
    int n;
    int[][] matrix;
    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.ret = 0;
        this.memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(memo[i][j] == 0) dfs(i, j);
            }
        }
        return ret;
    }

    public int dfs(int x, int y) {
        if(memo[x][y] != 0) return memo[x][y];
        int up = 0, down = 0, left = 0, right = 0;

        if(x > 0 && matrix[x - 1][y] > matrix[x][y]) up = dfs(x - 1, y);
        if(x < m - 1 && matrix[x + 1][y] > matrix[x][y])  down = dfs(x + 1, y);
        if(y > 0 && matrix[x][y - 1] > matrix[x][y]) left = dfs(x, y - 1);
        if(y < n - 1 && matrix[x][y + 1] > matrix[x][y]) right = dfs(x, y + 1);

        memo[x][y] = 1 + Math.max(up, Math.max(down, Math.max(left, right)));
        ret = Math.max(ret, memo[x][y]);
        return memo[x][y];
    }

    public static void main(String[] args) {
        LongestIncreasingPathInMatrix l = new LongestIncreasingPathInMatrix();
        int[][] matrix = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(l.longestIncreasingPath(matrix));
    }
}
// TC: O(m * n)
// SC: O(m * n)
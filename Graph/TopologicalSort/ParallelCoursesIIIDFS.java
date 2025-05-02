import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ParallelCoursesIIIDFS {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> adj = new ArrayList<>(); // TC: O(E) SC: O(V + E)
        int[] memo = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] relation : relations) {
            int pre = relation[0];
            int post = relation[1];
            adj.get(pre).add(post);
        }

        int ret = 0;
        for(int i = 1; i <= n; i++) {
            ret = Math.max(ret, dfs(i, visited, memo, time, adj));
        }
        return ret;
    }

    public int dfs(int node, boolean[] visited, int[] totalTime, int[] time,  List<List<Integer>> adj) {
        // TC: O(V + E)
        // SC: O(V)
        if(visited[node]) return totalTime[node];
        visited[node] = true;

        int ret = time[node - 1]; // init max
        for(int next: adj.get(node)) {
            ret = Math.max(ret, time[node - 1] + dfs(next, visited, totalTime, time, adj));
        }
        totalTime[node] = ret;
        return ret;
    }

    public static void main(String[] args) {
        ParallelCoursesIIIDFS pc = new ParallelCoursesIIIDFS();
        System.out.println(pc.minimumTime(3, new int[][]{{1,3},{2,3}}, new int[]{3,2,5}));
    }
}

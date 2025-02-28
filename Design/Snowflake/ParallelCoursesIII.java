package Snowflake;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ParallelCoursesIII {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n + 1];
        int[] totalTime = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] relation : relations) {
            int pre = relation[0];
            int post = relation[1];
            graph.get(pre).add(post);
            inDegree[post]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                totalTime[i] = time[i - 1];
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.get(cur)) {
                inDegree[next]--;
                totalTime[next] = Math.max(totalTime[next], totalTime[cur] + time[next - 1]);
                if (inDegree[next] == 0) queue.add(next);
            }
        }
        int ret = 0;
        for(int i = 1; i <= n; i++) {
            ret = Math.max(ret, totalTime[i]);
        }
        return ret;
    }

    public static void main(String[] args) {
        ParallelCoursesIII pc = new ParallelCoursesIII();
        int[][] relations = new int[][]{{1,3}, {2,3}};
        System.out.println(pc.minimumTime(3, relations, new int[]{2,3,5}));
    }

    // TC :O(V + E) where V is # of nodes, E is the # of edges(relations)
    // SC :O(V + E)
}

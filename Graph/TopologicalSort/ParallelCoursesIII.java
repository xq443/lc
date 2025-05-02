import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ParallelCoursesIII {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] totalTime = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[n + 1];
        for(int[] relation : relations) {
            int pre = relation[0];
            int post = relation[1];
            adj.get(pre).add(post);
            inDegree[post]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
                totalTime[i] = time[i - 1];
            }
        }

        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int nxt: adj.get(curr)) {
                inDegree[nxt]--;
                totalTime[nxt] = Math.max(totalTime[nxt], totalTime[curr] + time[nxt - 1]);
                if(inDegree[nxt] == 0) queue.add(nxt);
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
        System.out.println(pc.minimumTime(3, new int[][]{{1,3},{2,3}}, new int[]{3,2,5}));
    }
}

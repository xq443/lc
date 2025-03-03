package Snowflake.graph;

import java.util.*;

public class CouseSchduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for(int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for(int[] prerequisite : prerequisites) {
            int pre = prerequisite[0];
            int course = prerequisite[1];
            adjacencyList.get(pre).add(course);
            inDegree[course]++;
            // TC: O(V + E)
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int[] ret = new int[numCourses];
        int idx = 0;

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            ret[idx++] = cur;
            for(int adj : adjacencyList.get(cur)) {
                inDegree[adj]--;
                if(inDegree[adj] == 0) {
                    queue.add(adj);
                }
            }
        }
        return (idx == numCourses) ? ret : new int[0];

    }
    // TC: O(V + E)
    // SC: O(V + E)

}

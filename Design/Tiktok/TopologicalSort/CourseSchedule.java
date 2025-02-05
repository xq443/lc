package Tiktok.TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    // build graph
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }
    // build inDegree
    int[] inDegree = new int[numCourses];
    for (int[] prerequisite : prerequisites) {
      int course = prerequisite[0];
      int pre = prerequisite[1];
      graph.get(pre).add(course);
      inDegree[course]++;
    }

    // topological sort by using bfs
    int ret = 0;
    Queue<Integer> queue = new LinkedList<>();
    for(int i = 0; i < numCourses; i++) {
      if (inDegree[i] == 0) {
        queue.add(i);
        ret++;
      }
    }
    while(!queue.isEmpty()) {
      int curr = queue.poll();
      for(int next : graph.get(curr)) {
        inDegree[next]--;
        if (inDegree[next] == 0) {
          queue.add(next);
          ret++;
        }
      }
    }
    return ret == numCourses;
  }

  public static void main(String[] args) {
    CourseSchedule c = new CourseSchedule();
    int numCourses = 2;
    int[][] prerequisites = {{1,0}};
    System.out.println(c.canFinish(numCourses, prerequisites));
  }
}

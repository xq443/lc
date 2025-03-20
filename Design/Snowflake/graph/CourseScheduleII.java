package Snowflake.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      adj.add(new ArrayList<>());
    }

    int[] inDegree = new int[numCourses];
    for(int[] prerequisite : prerequisites) {
      int pre = prerequisite[1];
      int course = prerequisite[0];
      adj.get(pre).add(course);
      inDegree[course]++;
    }

    int[] ret = new int[numCourses];
    int idx = 0;
    Queue<Integer> queue = new LinkedList<>();
    for(int i = 0; i < numCourses; i++) {
      if(inDegree[i] == 0) {
        queue.add(i);
      }
    }

    while(!queue.isEmpty()) {
      int cur = queue.poll();
      ret[idx++] = cur;
      for(int next : adj.get(cur)) {
        inDegree[next]--;
        if(inDegree[next] == 0) {
          queue.add(next);
        }
      }
    }

    if(idx == numCourses) return ret;
    return new int[0];
  }

  public static void main(String[] args) {
    CourseScheduleII c = new CourseScheduleII();
    int numCourses = 4;
    int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
    System.out.println(Arrays.toString(c.findOrder(numCourses, prerequisites)));
  }
}

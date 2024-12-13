import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SequenceReconstruction {
  public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
    int n = nums.length;
    Map<Integer, List<Integer>> map = new HashMap<>();
    int[] inDegree = new int[n + 1];

    // build the graph
    for(List<Integer> seq : sequences) {
      for(int i = 0; i < seq.size() - 1; i++) {
        int source = seq.get(i);
        int dest = seq.get(i + 1);
        map.computeIfAbsent(source, k -> new ArrayList<>()).add(dest);
        inDegree[dest]++;
      }
    }

    // topological sort
    Queue<Integer> queue = new LinkedList<>();
    for(int i = 1; i <= n; i++) {
      if(inDegree[i] == 0) {
        queue.add(i);
      }
    }

    int index = 0;
    while(!queue.isEmpty()) {
      if(queue.size() > 1) return false;
      int curr = queue.poll();
      if(nums[index++] != curr) return false;

      if(map.containsKey(curr)) {
        for(int neighbours : map.get(curr)) {
          if(--inDegree[neighbours] == 0) {
            queue.add(neighbours);
          }
        }
      }
    }
    return index == n;
  }

  public static void main(String[] args) {
    SequenceReconstruction sr = new SequenceReconstruction();
    int[] nums = {1, 2, 3};
    List<List<Integer>> sequences = new ArrayList<>();
    sequences.add(Arrays.asList(1, 2));
    sequences.add(Arrays.asList(1, 3));

    System.out.println(sr.sequenceReconstruction(nums, sequences)); // false
  }
}

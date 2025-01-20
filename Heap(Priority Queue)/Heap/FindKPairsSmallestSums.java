package Google;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsSmallestSums {
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> ret = new ArrayList<>();
    if(nums1.length == 0 || nums2.length == 0 || k == 0) return ret;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

    for(int i = 0; i < nums1.length && i < k; i++){
      pq.offer(new int[]{nums1[i], nums2[0], 0});
    }

    while(!pq.isEmpty()){
      int[] curr = pq.poll();
      List<Integer> sub = new ArrayList<>();
      sub.add(curr[0]);
      sub.add(curr[1]);
      ret.add(sub);
      k--;
      if(k == 0) break;

      if(curr[2] == nums2.length - 1) continue;
      pq.offer(new int[]{curr[0], nums2[curr[2] + 1], curr[2] + 1});
    }

    return ret;
  }

  public static void main(String[] args) {
    FindKPairsSmallestSums f = new FindKPairsSmallestSums();
    int[] nums1 = {1,7,11};
    int[] nums2 = {2,4,6};
    int k = 3;
    System.out.println(f.kSmallestPairs(nums1, nums2, k));
  }
}

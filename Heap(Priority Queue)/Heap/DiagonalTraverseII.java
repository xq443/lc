import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DiagonalTraverseII {
  public static class Pair {
    int row;
    int val;
    int sum;
    public Pair(int row, int col, int sum) {
      this.row = row;
      this.val = col;
      this.sum = sum;
    }
  }
  public int[] findDiagonalOrder(List<List<Integer>> nums) {
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
      if(a.sum != b.sum) return a.sum - b.sum;
      return b.row - a.row;
    });

    int cnt = 0;
    for(int i = 0; i < nums.size(); i++) {
      cnt += nums.get(i).size();
      for(int j = 0; j < nums.get(i).size(); j++) {
        int sum = i + j;
        Pair pair = new Pair(i, nums.get(i).get(j), sum);
        pq.offer(pair);
      }
    }

    int[] ret = new int[cnt];
    int idx = 0;
    while(!pq.isEmpty()) {
      Pair pair = pq.poll();
      ret[idx++] = pair.val;
    }
    return ret;
  }

  public static void main(String[] args) {
    DiagonalTraverseII d = new DiagonalTraverseII();

    List<List<Integer>> nums1 = new ArrayList<>();
    nums1.add(List.of(1, 2, 3));
    nums1.add(List.of(4, 5));
    nums1.add(List.of(6));
    System.out.println(Arrays.toString(d.findDiagonalOrder(nums1)));

    List<List<Integer>> nums2 = new ArrayList<>();
    nums2.add(List.of(1, 2, 3, 4));
    System.out.println(Arrays.toString(d.findDiagonalOrder(nums2)));

    List<List<Integer>> nums3 = new ArrayList<>();
    nums3.add(List.of(1));
    nums3.add(List.of(2, 3));
    nums3.add(List.of(4, 5, 6));
    nums3.add(List.of(7));
    System.out.println(Arrays.toString(d.findDiagonalOrder(nums3)));
  }
}

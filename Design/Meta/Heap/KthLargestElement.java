package Meta.Heap;

import java.util.PriorityQueue;

public class KthLargestElement {
  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // max heap
    for(int num : nums) {
      pq.offer(num);
    }

    int ret = -1;
    while(k-- > 0) {
      ret = pq.poll();
    }
    return ret;
  }

  public int findKthLargest_two_pointers(int[] nums, int k) {
    int left = Integer.MIN_VALUE / 2, right = Integer.MAX_VALUE / 2;
    while(left < right) {
      int mid = right - (right - left) / 2;
      if(counter(mid, nums) >= k) {
        left = mid;
      } else {
        right = mid - 1;
      }
    }
    return left;
  }

  private int counter(int mid, int[] nums) {
    int cnt = 0;
    for(int n : nums) {
      cnt += (n >= mid) ? 1: 0;
    }
    return cnt;
  }

  public static void main(String[] args) {
    KthLargestElement k = new KthLargestElement();
    System.out.println(k.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    System.out.println(k.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    System.out.println(k.findKthLargest_two_pointers(new int[]{3,2,1,5,6,4}, 2));
    System.out.println(k.findKthLargest_two_pointers(new int[]{3,2,3,1,2,4,5,5,6}, 4));
  }
}

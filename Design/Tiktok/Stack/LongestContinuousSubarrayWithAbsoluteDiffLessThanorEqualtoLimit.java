package Tiktok.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit {
  public int longestSubarray(int[] nums, int limit) {
    Deque<Integer> max = new ArrayDeque<>();
    Deque<Integer> min = new ArrayDeque<>();

    int left = 0, right = 0, ret = 0;
    while (right < nums.length) {
      while(!max.isEmpty() && nums[right] > max.peekLast()) {
        max.pollLast();
      }
      while(!min.isEmpty() && nums[right] < min.peekLast()) {
        min.pollLast();
      }
      max.offerLast(nums[right]);
      min.offerLast(nums[right]);

      while(max.peekFirst() - min.peekFirst() > limit) {
        if(nums[left] == max.peekFirst()) {
          max.pollFirst();
        }
        if(nums[left] == min.peekFirst()) {
          min.pollFirst();
        }
        left++;
      }
      ret = Math.max(ret, right - left + 1);
      right++;
    }
    return ret;
  }

  public static void main(String[] args) {
    LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit l = new LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit();
    int[] nums = new int[]{8,2,4,7};
    int limit = 4;
    System.out.println(l.longestSubarray(nums, limit));
  }

}

package SlidingWindow;

public class MinSizeSubarraySum {
  public int minSubArrayLen(int target, int[] nums) {
    int n = nums.length;
    int left = 0, right = 0, sum = 0;
    int len = Integer.MAX_VALUE;

    while (right < n) {
      sum += nums[right];
      while(sum >= target && left <= right) {
        len = Math.min(len, right - left + 1);
        sum -= nums[left++];
      }
      right++;
    }
    return len == Integer.MAX_VALUE ? 0 : len;
  }

  public static void main(String[] args) {
    MinSizeSubarraySum m = new MinSizeSubarraySum();
    System.out.println(m.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
  }
}

package SlidingWindow;

public class LongestSubarray1AfterDeletingOneElement {
  public int longestSubarray(int[] nums) {
    int left = 0, right = 0, ret = 0;
    int cnt = 0;

    while (right < nums.length) {
      if(nums[right] == 0) cnt++;
      while(cnt > 1) {
        if(nums[left] == 0) {
          cnt--;
        }
        left++;
      }
      ret = Math.max(ret, right - left + 1);
      right++;
    }
    return ret - 1;
  }

  public static void main(String[] args) {
    LongestSubarray1AfterDeletingOneElement l = new LongestSubarray1AfterDeletingOneElement();
    int[] nums = {1,1,0,1};
    System.out.println(l.longestSubarray(nums));
  }
}

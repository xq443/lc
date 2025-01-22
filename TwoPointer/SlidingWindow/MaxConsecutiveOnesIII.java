package SlidingWindow;

public class MaxConsecutiveOnesIII {
  public int longestOnes(int[] nums, int k) {
    int left = 0, right = 0, ret = 0;
    int cnt = 0;
    while (right < nums.length) {
      if(nums[right] == 1){
        ret = Math.max(ret, right - left + 1);
      } else { // 0
        cnt++;
        while(cnt > k) {
          if(nums[left] == 0) {
            cnt--;
          }
          left++;
        }
        ret = Math.max(ret, right - left + 1);
      }
      right++;
    }
    return ret;
  }

  public static void main(String[] args) {
    MaxConsecutiveOnesIII m = new MaxConsecutiveOnesIII();
    int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
    int k = 2;
    System.out.println(m.longestOnes(nums, k));

    int[] nums2 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
    int k2 = 3;
    System.out.println(m.longestOnes(nums2, k2));
  }
}

package Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
  public List<List<Integer>> threeSum(int[] nums) {
    // edge case
    if(nums == null || nums.length < 3) return new ArrayList<>();
    Arrays.sort(nums);
    List<List<Integer>> ret = new ArrayList<>();
    for(int i = 0; i < nums.length - 2; i++) {
      if(i > 0 && nums[i] == nums[i - 1]) continue;
      int left = i + 1, right = nums.length - 1;

      while(left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if(sum == 0) {
          ret.add(Arrays.asList(nums[i], nums[left], nums[right]));

          while(left < right && nums[left] == nums[left + 1]) left++;
          left++;

          while(left < right && nums[right] == nums[right - 1]) right--;
          right--;
        } else if(sum < 0) {
          left++;
        } else
          right--;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    ThreeSum threeSum = new ThreeSum();
    System.out.println(threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
  }
}

import java.util.Arrays;

public class MaximizeGreatnessofanArray {
  public int maximizeGreatness(int[] nums) {
    // 1 3 5 2 1 3 1
    // 1 1 1 2 3 3 5 - sorted
    Arrays.sort(nums);
    int n = nums.length;
    int ret = 0;
    int j = 0;
    for(int i = 0; i < n; i++) {
      while(j < n && nums[j] <= nums[i]) {
        j++;
      }
      if(j < n) {
        ret++;
        j++;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    MaximizeGreatnessofanArray obj = new MaximizeGreatnessofanArray();
    System.out.println(obj.maximizeGreatness(new int[]{1,3,5,2,1,3,1}));
    System.out.println(obj.maximizeGreatness(new int[]{1,1,1,2}));
  }
}
//TC O(n log n).
//SC O(1)
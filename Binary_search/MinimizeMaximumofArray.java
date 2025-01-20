public class MinimizeMaximumofArray {
  public int minimizeArrayValue(int[] nums) {
    int left = 0, right = 0;
    for(int m : nums) {
      right = Math.max(right, m);
    }

    while(left < right) {
      int mid = left + (right - left) / 2;
      long buffer = 0;
      int flag = 1;

      for(int i = 0; i < nums.length; i++) {
        if(nums[i] > mid) {
          buffer -= (nums[i] - mid);
        } else {
          buffer += (mid - nums[i]);
        }
        if(buffer < 0) {
          flag = 0;
          break;
        }
      }

      if(flag == 1) {
        right = mid;
      } else left = mid + 1;
    }
    return left;
  }

  public static void main(String[] args) {
    MinimizeMaximumofArray m = new MinimizeMaximumofArray();
    int[] nums = {3,7,1,6};
    System.out.println(m.minimizeArrayValue(nums));
  }
}

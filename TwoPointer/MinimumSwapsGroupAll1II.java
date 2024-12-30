public class MinimumSwapsGroupAll1II {
  public int minSwaps(int[] nums) {
    int n = nums.length;

    // window size
    int ones = 0;
    for(int num : nums) {
      if(num == 1) ones++;
    }

    // circular array
    int[] temp = new int[2 * n];
    for(int i = 0; i < n; i++) {
      temp[i] = nums[i];
    }
    for(int i = n; i < 2 * n; i++) {
      temp[i] = nums[i - n];
    }

    // sliding window
    int curr = 0;
    int max = Integer.MIN_VALUE;

    for(int i = 0; i < ones; i++) {
      curr += temp[i];
    }
    max = curr;

    for(int i = ones; i < 2 * n; i++) {
      curr += temp[i] - temp[i - ones];
      max = Math.max(max, curr);
    }
    return ones - max;
  }

  public static void main(String[] args) {
    MinimumSwapsGroupAll1II m = new MinimumSwapsGroupAll1II();
    System.out.println(m.minSwaps(new int[]{0,1,0,1,1,0,0}));
    System.out.println(m.minSwaps(new int[]{1,1,0,0,1}));
  }
}

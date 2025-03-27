package Tiktok;

public class FrequencyControl {
  public boolean control(int[] nums, int n, int m, int x) {
    int maxDay = 0;
    for (int num : nums) {
      maxDay = Math.max(maxDay, num);
    }
    maxDay = Math.max(maxDay, x);

    int[] bucket = new int[maxDay + 2];
    for (int num : nums) {
      bucket[num]++;
    }
    bucket[x]++;

    int curr = 0;
    int left = 0;
    for (int right = 1; right <= maxDay; right++) { // 1 -> 1, 2 -> 1, 5 -> 2
      curr += bucket[right];

      // Maintain window size n
      if (right - left + 1 > n) {
        curr -= bucket[left];
        left++;
      }

      // If at any point count exceeds m, fail
      if (curr > m) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    FrequencyControl fc = new FrequencyControl();
    int[] nums = {10, 1, 2, 5, 7, 8};
    int n = 3;
    int m = 2;
    System.out.println(fc.control(nums, n, m, 2));
    System.out.println(fc.control(nums, n, m, 16));
  }
}

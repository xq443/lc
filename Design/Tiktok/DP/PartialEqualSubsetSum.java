package Tiktok.DP;

public class PartialEqualSubsetSum {
  public boolean canPartition(int[] nums) {
    int n = nums.length;
    int sum = 0;
    for(int num : nums) {
      sum += num;
    }
    if(sum % 2 != 0) return false;

    boolean[][] dp = new boolean[n + 1][sum / 2 + 1];
    dp[0][0] = true;

    for(int i = 1; i <= n; i++) {
      for(int s = 0; s <= sum / 2; s++) {
        dp[i][s] = dp[i - 1][s] || (s >= nums[i - 1] && dp[i - 1][s - nums[i - 1]]);
      }
    }
    return dp[n][sum / 2];
  }

  public static void main(String[] args) {
    PartialEqualSubsetSum p = new PartialEqualSubsetSum();
    System.out.println(p.canPartition(new int[]{1,5,11,5}));
    System.out.println(p.canPartition(new int[]{1,2,3,5}));
  }
}

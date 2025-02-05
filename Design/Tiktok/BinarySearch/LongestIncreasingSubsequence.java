package Tiktok.BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {
  public int longestIncreasingSubsequence(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
      for(int j = 0; j < i; j++) {
        if(nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }
    int ret = 0;
    for(int i = 0; i < n; i++) {
      ret = Math.max(ret, dp[i]);
    }
    return ret;
  }

  public int longestIncreasingSubsequence_binarySearch(int[] nums) {
    List<Integer> ret = new ArrayList<>();
    for(int num : nums) {
      int pos = binarySearch(ret, num); // the leftmost/ first index that the num fits
      if(pos == ret.size()) ret.add(num);
      else ret.set (pos, num);
    }
    return ret.size();
  }

  private int binarySearch(List<Integer> ret, int num) {
    int left = 0, right = ret.size() - 1;
    while(left <= right) {
      int mid = left + (right - left) / 2;
      if(num < ret.get(mid)) left = mid + 1;
      else right = mid - 1;
    }
    return left;
  }

  public static void main(String[] args) {
    LongestIncreasingSubsequence l  = new LongestIncreasingSubsequence();
    System.out.println(l.longestIncreasingSubsequence(new int[]{10,9,2,5,3,7,101,18}));
    System.out.println(l.longestIncreasingSubsequence_binarySearch(new int[]{10,9,2,5,3,7,101,18}));
  }
}

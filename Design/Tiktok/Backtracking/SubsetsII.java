package Tiktok.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    Arrays.sort(nums);
    dfs(ret, nums, 0, new ArrayList<>());
    return ret;
  }

  private void dfs(List<List<Integer>> ret, int[] nums, int index, List<Integer> temp) {
    ret.add(new ArrayList<>(temp));
    for (int i = index; i < nums.length; i++) {
      if(i > index && nums[i] == nums[i - 1]) continue;
      temp.add(nums[i]);
      dfs(ret, nums, i + 1, temp);
      temp.removeLast();
    }
  }

  public static void main(String[] args) {
    SubsetsII s = new SubsetsII();
    System.out.println(s.subsetsWithDup(new int[]{1, 2, 2}));
  }
}
// TC: O(N * 2^N)
// SC:  The ret list stores all subsets, which can be up to 2^N subsets
// and each subset can have up to n elements
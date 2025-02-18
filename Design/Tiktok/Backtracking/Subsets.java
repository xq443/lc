package Tiktok.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    dfs(ret, nums, 0, new ArrayList<>());
    return ret;
  }

  private void dfs(List<List<Integer>> ret, int[] nums, int index, List<Integer> temp) {
    ret.add(new ArrayList<>(temp));
    for(int i = index; i < nums.length; i++) {
      temp.add(nums[i]);
      dfs(ret, nums, i + 1, temp);
      temp.removeLast();
    }
  }

  public static void main(String[] args) {
    Subsets s = new Subsets();
    System.out.println(s.subsets(new int[]{1, 2, 3}));
  }
}

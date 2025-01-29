package Apple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for(int i = 0; i < nums.length; i++) {
      int compl = target - nums[i];
      if(map.containsKey(compl)) {
        return new int[]{map.get(compl), i};
      }
      map.put(nums[i], i);
    }
    return new int[]{};
  }

  public static void main(String[] args) {
    TwoSum t = new TwoSum();
    System.out.println(Arrays.toString(t.twoSum(new int[]{2, 7, 11, 15}, 9)));
  }
}

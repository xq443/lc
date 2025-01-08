package Apple;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
  public int subarraySum(int[] nums, int k) {
    int ret = 0, prefix = 0;

    Map<Integer, Integer> map = new HashMap<>(); // k: num; v: count
    map.put(0, 1);
    for(int i = 0; i < nums.length; i++){
      prefix += nums[i];
      if(map.containsKey(prefix - k)){
        ret += map.get(prefix - k);
      }
      map.put(prefix, map.getOrDefault(nums[i], 0) + 1);
    }
    return ret;
  }

  public static void main(String[] args) {
    SubarraySumEqualsK s = new SubarraySumEqualsK();
    System.out.println(s.subarraySum(new int[]{1,2,3}, 3));
    System.out.println(s.subarraySum(new int[]{1,1,1}, 2));
  }
}

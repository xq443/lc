package Apple;

import java.util.HashMap;
import java.util.Map;

public class NumberofPairsofStringsWithConcatenationTarget {
  public int numOfPairs(String[] nums, String target) {
    Map<String, Integer> map = new HashMap<>();
    int ret = 0;
    for (String num : nums) {
      if(target.startsWith(num)) {
        String remaining = target.substring(num.length());
        ret += map.getOrDefault(remaining, 0);
      }

      if(target.endsWith(num)) {
        String remaining = target.substring(0, target.length() - num.length());
        ret += map.getOrDefault(remaining, 0);
      }

      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    return ret;
  }

  public static void main(String[] args) {
    NumberofPairsofStringsWithConcatenationTarget n = new  NumberofPairsofStringsWithConcatenationTarget();
    String[] nums = {"777","7","77","77"};
    String target = "7777";
    System.out.println(n.numOfPairs(nums, target));
  }
}

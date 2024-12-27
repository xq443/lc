package HashTable;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
  public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for(int num : nums) {
      set.add(num);
    }

    int ret = 0;
    for(int num : nums) {
      if(!set.contains(num - 1)) { // left boundary
        int len = 0;
        while(set.contains(num)) {
          len++;
          num++;
        }
        ret = Math.max(ret, len);
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    LongestConsecutiveSequence l = new LongestConsecutiveSequence();
    int[] arr1 = {100,4,200,1,3,2};
    System.out.println(l.longestConsecutive(arr1));

    int[] arr2 = {0,3,7,2,5,8,4,6,0,1};
    System.out.println(l.longestConsecutive(arr2));
  }
}

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountCompleteSubarrays {
  public int countCompleteSubarrays(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for(int num : nums) {
      set.add(num);
    }
    int size = set.size();
    int left = 0, right = 0;
    Map<Integer, Integer> map = new HashMap<>();
    int ret = 0;
    while(right < nums.length) {
      map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
      while(map.size() == size) {
        ret += nums.length - right;
        map.put(nums[left], map.get(nums[left]) - 1);
        if(map.get(nums[left]) == 0) map.remove(nums[left]);
        left++;
      }
      right++;
    }
    return ret;
  }

  public static void main(String[] args) {
    CountCompleteSubarrays c = new CountCompleteSubarrays();
    int[] nums = new int[]{1,3,1,2,2};
    System.out.println(c.countCompleteSubarrays(nums));
    int[] nums2 = new int[]{5,5,5,5};
    System.out.println(c.countCompleteSubarrays(nums2));
  }
}

package Google;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
  public List<String> summaryRanges(int[] nums) {
    List<String> ret = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int start = nums[i];
      while(i < nums.length - 1 && nums[i] == nums[i + 1] - 1) {
        i++;
      }
      int end = nums[i];
      if(start != end) {
        ret.add(start + "->" + end);
      } else ret.add(String.valueOf(start));
    }
    return ret;
  }

  public static void main(String[] args) {
    SummaryRanges s = new SummaryRanges();
    System.out.println(s.summaryRanges(new int[]{0,1,2,4,5,7}));
  }
}

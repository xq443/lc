package Snowflake;

import java.util.ArrayList;
import java.util.List;

public class MissingRange {
    List<List<Integer>> ret;
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        this.ret = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            addRange(nums, lower, upper);
            return ret;
        }

        if(lower < nums[0]) addRange(nums, lower, nums[0] - 1);
        for(int i = 1; i < nums.length; i++) {
            if(nums[i - 1] + 1 < nums[i]) {
                addRange(nums, nums[i - 1] + 1, nums[i] - 1);
            }
        }

        if(upper > nums[nums.length - 1]) {
            addRange(nums, nums[nums.length - 1] + 1, upper);
        }
        return ret;
    }

    public void addRange(int[] nums, int lower, int upper) {
        List<Integer> sub = new ArrayList<>();
        sub.add(lower);
        sub.add(upper);
        ret.add(sub);
    }

    public static void main(String[] args) {
        MissingRange m = new MissingRange();
        System.out.println(m.findMissingRanges(new int[] {0,1,3,50,75}, 0, 99));
    }
}

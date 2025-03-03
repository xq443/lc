package Snowflake.dp;

public class DeleteandEarn {
    public int deleteAndEarn(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] points = new int[max - min + 1];
        for(int num : nums) {
            points[num - min] += num;
        }
        return helper(points);
    }

    public int helper(int[] points) {
        int prevY = points[0];
        int prevN = 0;

        for(int i = 1; i < points.length; i++) {
            int currY = prevN + points[i];
            int currN = Math.max(prevY, prevN);

            prevY = currY;
            prevN = currN;
        }
        return Math.max(prevY, prevN);
    }

    public static void main(String[] args) {
        DeleteandEarn deleteandEarn = new DeleteandEarn();
        int[] nums = {3, 4, 2};
        System.out.println(deleteandEarn.deleteAndEarn(nums));  // Output should be 6`
    }
}

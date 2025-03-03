package Snowflake.binarysearch;

import java.util.Arrays;

public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses); //  TC: O(m log m)
        Arrays.sort(heaters); //  TC: O(n log n)
        int ret = 0;

        for(int house : houses) {
            // find the first heater that is >= house
            int idx = bs(house, heaters); //  TC: O(m log n)
            // houses = [1, 5, 10]
            // heaters = [2, 7, 11]
            int right = (idx < heaters.length) ? heaters[idx] - house : Integer.MAX_VALUE;
            int left = (idx >= 1) ? house - heaters[idx - 1]: Integer.MAX_VALUE;
            int dis = Math.min(left, right);

            ret = Math.max(ret, dis);
        }
        return ret;
    }

    public int bs(int target, int[] heaters) {
        int left = 0, right = heaters.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(heaters[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        Heaters heaters = new Heaters();
        System.out.println(heaters.findRadius(new int[]{1,2,3}, new int[]{2}));
    }
}

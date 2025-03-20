package Snowflake.binarysearch;

public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int days) {
        // least weight capacity
        if (weights == null || weights.length == 0) {
            return 0;
        }
        int left = 0, right = 0;
        for (int i = 0; i < weights.length; i++) {
            left = Math.max(weights[i], left);
            right += weights[i];
        }

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(capacity(mid, weights) > days) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int capacity(int N, int[] weights) {
        int ret = 1, curr = 0;
        for (int i = 0; i < weights.length; i++) {
            curr += weights[i];
            if(curr > N) {
                ret++;
                curr = weights[i]; // 4 7 8  target = 13
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        CapacityToShipPackagesWithinDDays c = new CapacityToShipPackagesWithinDDays();
        // 1,2,3,4,5,6,7,8,9,10], days = 5
        System.out.println(c.shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
    }
}

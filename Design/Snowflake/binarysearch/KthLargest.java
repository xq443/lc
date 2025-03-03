package Snowflake.binarysearch;

public class KthLargest {
    public int findKthLargest(int[] nums, int k) {
        int left = Integer.MIN_VALUE / 16;
        int right = Integer.MAX_VALUE / 16;
        while(left < right) {
            int mid = right - (right - left) / 2;
            if(count (mid, nums) >= k) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int count(int num, int[] nums) {
        int ret = 0;
        for(int n : nums) {
            ret += (n >= num) ? 1 : 0;
        }
        return ret;
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest();
        System.out.println(kthLargest.findKthLargest(new int[] {3,2,1,5,6,4}, 2));
        System.out.println(kthLargest.findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4));
    }
    // TC: O(NlogN)
    // SC: O(1)
}

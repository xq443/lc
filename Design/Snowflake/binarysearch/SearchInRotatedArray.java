package Snowflake.binarysearch;

public class SearchInRotatedArray {
    // Input: nums = [4,5,6,7,0,1,2], target = 0
    // Output: 4
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] >= nums[0] == target >= nums[0]) {
                if (nums[mid] > target) {
                    right = mid - 1;
                } else left = mid + 1;
            } else if (nums[mid] < target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedArray s = new SearchInRotatedArray();
        System.out.println(s.search(new int[] {4,5,6,7,0,1,2}, 0));
    }
}

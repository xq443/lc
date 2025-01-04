package Tiktok;

public class SearchinRotatedSortedArray {
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) return -1;
    int left = 0, right = nums.length - 1;

    while(left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) return mid;

      if(target >= nums[0] == nums[mid] >= nums[0]) {
        if(target > nums[mid]) {
          left = mid + 1;
        } else right = mid - 1;
      } else if(target >= nums[0]) {
        right = mid - 1;
      } else left = mid + 1;
    }
    return -1;
  }

  public static void main(String[] args) {
    SearchinRotatedSortedArray s = new SearchinRotatedSortedArray();
    int[] nums = {4,5,6,7,0,1,2};
    int target = 0;
    System.out.println(s.search(nums, target));
  }

}

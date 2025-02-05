package Tiktok;

public class FindMinInRotatedSortedArray {
  public int findMin(int[] nums) {
    if(nums.length == 0) return -1;
    if(nums.length == 1) return nums[0];

    int left = 0, right = nums.length - 1;
    int target = nums[nums.length - 1];
    while(left < right) {
      int mid = left + (right - left) / 2;
      if(nums[mid] == target) return nums[mid];
      else if(nums[mid] > target) {
        left = mid + 1;
      } else right = mid;
    }
    return nums[right];
  }

  public static void main(String[] args) {
    FindMinInRotatedSortedArray f = new FindMinInRotatedSortedArray();
    int[] nums = {3,4,5,1,2};
    System.out.println(f.findMin(nums));
  }
}

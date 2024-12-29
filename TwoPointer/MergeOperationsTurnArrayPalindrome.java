public class MergeOperationsTurnArrayPalindrome {
  public int minimumOperations(int[] nums) {
    int left = 0, right = nums.length - 1;
    int leftSum = nums[left], rightSum = nums[right];
    int ret = 0;

    while (left < right) {
      if(leftSum < rightSum) {
        left++;
        leftSum += nums[left];
        ret++;
      } else if(leftSum > rightSum) {
        right--;
        rightSum += nums[right];
        ret++;
      } else {
        left++;
        right--;
        leftSum += nums[left];
        rightSum -= nums[right];
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    MergeOperationsTurnArrayPalindrome m =  new MergeOperationsTurnArrayPalindrome();
    System.out.println(m.minimumOperations(new int[]{4,3,2,1,2,3,1}));
    System.out.println(m.minimumOperations(new int[]{1,2,3,4}));
  }
}

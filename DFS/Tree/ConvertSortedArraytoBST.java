package Apple;

public class ConvertSortedArraytoBST {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    return createBST(nums, 0, nums.length - 1);
  }

  public TreeNode createBST(int[] nums, int left, int right) {
    if (left > right) return null;
    int mid = left + (right - left) / 2;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = createBST(nums, left, mid - 1);
    root.right = createBST(nums, mid + 1, right);
    return root;
  }

  public void inorderTraversal(TreeNode root) {
    if (root != null) {
      inorderTraversal(root.left);
      System.out.print(root.val + " ");
      inorderTraversal(root.right);
    }
  }

  public static void main(String[] args) {
    ConvertSortedArraytoBST c = new ConvertSortedArraytoBST();
    int[] nums = {-10, -3, 0, 5, 9};
    TreeNode root = c.sortedArrayToBST(nums);
    c.inorderTraversal(root);
  }
}

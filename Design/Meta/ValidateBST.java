package Meta;

public class ValidateBST {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
      val = x;
    }
  }

  public boolean isValidBST(TreeNode root) {
    if(root == null) return true;
    return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public boolean helper(TreeNode node, int min, int max) {
    if(node == null) return true;
    if(node.val >= max || node.val <= min) return false;
    return helper(node.left, min, node.val) && helper(node.right, node.val, max);
  }

  public static void main(String[] args) {
    ValidateBST v = new ValidateBST();
    TreeNode root = new TreeNode(2);
    root.left = new TreeNode(1);
    root.right = new TreeNode(3);
    System.out.println(v.isValidBST(root));
  }

  // TC: O(N)
  // SC: O(H)
}

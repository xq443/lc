package Snowflake.tree.UpdateTreeSum;

public class UpdateTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
      this.val = val;
      this.left = null;
      this.right = null;
    }
  }

  public int updateTree(TreeNode root) {
    if(root == null) return 0;
    int leftSum = updateTree(root.left);
    int rightSum = updateTree(root.right);
    root.val = leftSum + rightSum + root.val;
    return root.val;
  }

  public void inOrder(TreeNode root) {
    if (root == null) return;
    inOrder(root.left);
    System.out.print(root.val + " ");
    inOrder(root.right);
  }

  public static void main(String[] args) {
    UpdateTree u = new UpdateTree();
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(3);
    root.right = new TreeNode(8);
    root.left.left = new TreeNode(2);
    root.left.right = new TreeNode(1);
    root.right.right = new TreeNode(7);

    u.updateTree(root);

    u.inOrder(root);  // Output should show updated values
  }
}

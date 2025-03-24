package Snowflake.Tree.UpdateTreeSum;

public class MergeUpdateTree {
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

  public TreeNode merge(TreeNode root1, TreeNode root2) {
    if(root1 == null) return root2;
    if(root2 == null) return root1;

    TreeNode merged = new TreeNode(root1.val + root2.val);
    merged.left = merge(root1.left, root2.left);
    merged.right = merge(root1.right, root2.right);
    return merged;
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
    MergeUpdateTree m = new MergeUpdateTree();
    TreeNode t1 = new TreeNode(5);
    t1.left = new TreeNode(3);
    t1.right = new TreeNode(8);
    t1.left.left = new TreeNode(2);
    t1.left.right = new TreeNode(1);

    TreeNode t2 = new TreeNode(4);
    t2.left = new TreeNode(2);
    t2.right = new TreeNode(6);
    t2.right.right = new TreeNode(7);

    TreeNode mergedTree = m.merge(t1, t2);
    m.updateTree(mergedTree);

    m.inOrder(mergedTree);  // Output should show updated values
  }
}

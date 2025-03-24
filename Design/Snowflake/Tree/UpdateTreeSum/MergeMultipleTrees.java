package Snowflake.Tree.UpdateTreeSum;

import java.util.List;

public class MergeMultipleTrees {
  public static class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
      this.left = this.right = null;
    }
  }

  // Merge two binary trees
  public TreeNode mergeTwoTrees(TreeNode t1, TreeNode t2) {
    if (t1 == null) return t2;
    if (t2 == null) return t1;

    TreeNode merged = new TreeNode(t1.val + t2.val);
    merged.left = mergeTwoTrees(t1.left, t2.left);
    merged.right = mergeTwoTrees(t1.right, t2.right);

    return merged;
  }

  // Merge N binary trees
  public TreeNode mergeNTrees(List<TreeNode> trees) {
    TreeNode mergedTree = null;
    for (TreeNode tree : trees) {
      mergedTree = mergeTwoTrees(mergedTree, tree);
    }
    return mergedTree;
  }

  // Update tree: set node->val = sum of all its descendants (including itself)
  public int updateTree(TreeNode root) {
    if (root == null) return 0;

    int leftSum = updateTree(root.left);
    int rightSum = updateTree(root.right);

    root.val = leftSum + rightSum + root.val;
    return root.val;  // Return total sum including this node
  }

  // Helper function to print tree in-order (for verification)
  public void inOrder(TreeNode root) {
    if (root == null) return;
    inOrder(root.left);
    System.out.print(root.val + " ");
    inOrder(root.right);
  }

  public static void main(String[] args) {
    MergeMultipleTrees m = new MergeMultipleTrees();
    TreeNode t1 = new TreeNode(5);
    t1.left = new TreeNode(3);
    t1.right = new TreeNode(8);

    TreeNode t2 = new TreeNode(4);
    t2.left = new TreeNode(2);
    t2.right = new TreeNode(6);

    TreeNode t3 = new TreeNode(1);
    t3.left = new TreeNode(7);
    t3.right = new TreeNode(9);

    List<TreeNode> trees = List.of(t1, t2, t3);
    TreeNode mergedTree = m.mergeNTrees(trees);
    m.updateTree(mergedTree);

    m.inOrder(mergedTree);  // Output should show updated values
  }
}

package Snowflake.Tree.PerfectTree;
public class PerfectBinaryTreeDFS {
  // Method to check if a tree is perfect
  public boolean isPerfect(TreeNode root) {
    if (root == null) return true;

    // Find the depth of the leftmost leaf node
    int depth = findDepth(root);

    // Start DFS to check if the tree is perfect
    return dfs(root, 0, depth);
  }

  // Method to find the depth of the leftmost leaf node
  private int findDepth(TreeNode node) {
    int depth = 0;
    while (node != null) {
      depth++;
      node = node.left;  // Keep going left to find the depth of the leftmost leaf
    }
    return depth;
  }

  // DFS method to validate if the tree is perfect
  private boolean dfs(TreeNode node, int level, int depth) {
    if (node == null) {
      return true;
    }

    // If this is a leaf node, it should be at the correct level
    if (node.left == null && node.right == null) {
      return level == depth;
    }

    // If this is an internal node, it must have both left and right children
    if (node.left == null || node.right == null) {
      return false;
    }

    // Recursively check both subtrees
    return dfs(node.left, level + 1, depth) && dfs(node.right, level + 1, depth);
  }

  public static void main(String[] args) {
    PerfectBinaryTreeDFS p = new PerfectBinaryTreeDFS();
    // Test case: Tree that is missing nodes in multiple levels
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.left = new TreeNode(6);

    System.out.println("Is perfect tree? " + p.isPerfect(root)); // false
   // System.out.println("Minimum operations to make perfect: " + p.transformToPerfect(root)); // Expected: 1
  }
}

package Snowflake.Tree.PerfectTree;

public class TransformToPerfect {

  // Method to transform the tree into a perfect binary tree and return the number of operations
  public int transformToPerfect(TreeNode root) {
    if (root == null) return 0;

    // Find the depth of the leftmost leaf node (to calculate expected size at each level)
    int depth = findDepth(root);

    // Start DFS to count operations
    return dfs(root, 0, depth);
  }

  // Method to find the depth of the leftmost leaf node
  private int findDepth(TreeNode node) {
    int depth = 0;
    while (node != null) {
      depth++;
      node = node.left;
    }
    return depth;
  }

  // DFS method to calculate the number of operations needed
  private int dfs(TreeNode node, int level, int depth) {
    if (node == null) return 0;

    // Calculate the expected size at the current level (2^level)
    int expectedSize = (int) Math.pow(2, level);
    int currentSize = 0;  // Count the nodes at the current level

    // Calculate the number of nodes at this level
    if (node.left != null) currentSize++;
    if (node.right != null) currentSize++;

    // If the current level has fewer nodes than expected, we need operations
    int operations = 0;
    if (currentSize != expectedSize) {
      operations += Math.abs(currentSize - expectedSize); // Add/remove nodes to make it perfect
    }

    // Recursively process left and right children
    operations += dfs(node.left, level + 1, depth);
    operations += dfs(node.right, level + 1, depth);

    return operations;
  }
}

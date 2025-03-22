package Snowflake.tree;

public class Test {

  public int minOperations(TreeNode root) {
    Result result = dfs(root);
    return result.minOps;
  }

  // Helper function to perform DFS and calculate the minimum operations
  private Result dfs(TreeNode root) {
    // Base case: If the node is null, it's already perfect with 0 operations
    if (root == null) {
      return new Result(0, 0);  // No operations and height 0 for null node
    }

    // Recursively compute the result for left and right subtrees
    Result left = dfs(root.left);
    Result right = dfs(root.right);

    // If both left and right subtrees have the same height, it's a perfect subtree.
    // If not, calculate the cost of balancing them.

    // Case 1: No need to add or remove nodes (perfect subtree)
    int minOps = Integer.MAX_VALUE;

    // Case 2: Remove nodes to balance heights (if one side is taller than the other)
    if (Math.abs(left.height - right.height) <= 1) {
      minOps = Math.min(minOps, left.minOps + right.minOps);  // No new nodes, just balance the tree
    }

    // Case 3: Add or remove nodes to make both children have equal height.
    if (left.height > right.height) {
      minOps = Math.min(minOps, left.minOps + right.minOps + 1); // 1 operation to balance
    } else if (right.height > left.height) {
      minOps = Math.min(minOps, left.minOps + right.minOps + 1); // 1 operation to balance
    }

    // Return the result for this node
    return new Result(minOps, Math.max(left.height, right.height) + 1);
  }

  // Helper class to store the DP state: minimum operations and height of the tree
  private class Result {
    int minOps;  // Minimum number of operations to make the tree perfect
    int height;  // Height of the subtree
    Result(int minOps, int height) {
      this.minOps = minOps;
      this.height = height;
    }
  }

  // Example usage
  public static void main(String[] args) {
    Test sol = new Test();

    // Constructing an imperfect binary tree
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);  // Imperfect tree

    System.out.println("Minimum operations to make the tree perfect: " + sol.minOperations(root));
  }
}

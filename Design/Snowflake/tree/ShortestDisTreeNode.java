package Snowflake.tree;

public class ShortestDisTreeNode {
  // TreeNode class to represent each node of the tree
  static class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
      this.left = this.right = null;
    }
  }

  // Method to find the LCA of two nodes
  public static TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
      return root;
    }

    TreeNode left = findLCA(root.left, p, q);
    TreeNode right = findLCA(root.right, p, q);

    // If both left and right are non-null, the current node is the LCA
    if (left != null && right != null) {
      return root;
    }

    // Otherwise, return the non-null side (if any)
    return left != null ? left : right;
  }

  // Method to find the distance from a node to the root
  public static int findDistance(TreeNode root, TreeNode node) {
    if (root == null) return -1;
    if (root == node) return 0;

    int leftDist = findDistance(root.left, node);
    int rightDist = findDistance(root.right, node);

    if (leftDist == -1 && rightDist == -1) return -1;

    // If the node is found in either left or right, return the distance
    return Math.max(leftDist, rightDist) + 1;
  }

  // Method to find the shortest path between two nodes
  public static int shortestPath(TreeNode root, TreeNode p, TreeNode q) {
    // Find the LCA of the two nodes
    TreeNode lca = findLCA(root, p, q);

    // Calculate the distance from LCA to p and LCA to q
    int distP = findDistance(lca, p);
    int distQ = findDistance(lca, q);

    // The shortest path is the sum of the distances from LCA to both nodes
    return distP + distQ;
  }

  public static void main(String[] args) {
    // Create a sample tree
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);

    TreeNode p = root.left.left; // Node with value 4
    TreeNode q = root.right.right; // Node with value 7

    // Find the shortest path between nodes p and q
    int result = shortestPath(root, p, q);
    System.out.println("Shortest path between nodes " + p.val + " and " + q.val + " is: " + result);
  }
}

package Snowflake.Tree.view;

import java.util.*;

public class BottomViewBinaryTree {
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

    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public List<Integer> bottomView(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        if (root == null) return new ArrayList<>();

        // boundaries for the vertical levels
        findBoundary(root, 0);

        // level-order traversal
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> idx = new LinkedList<>();
        q.add(root);
        idx.add(-min); // Normalize the index

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                int currIdx = idx.poll();
                map.put(currIdx, node.val);  // Record the bottom-most node for the current vertical level

                // Add left and right children to the queue and update their indices
                if (node.left != null) {
                    q.add(node.left);
                    idx.add(currIdx - 1);
                }
                if (node.right != null) {
                    q.add(node.right);
                    idx.add(currIdx + 1);
                }
            }
        }

        System.out.println(map.entrySet());

        List<Integer> bottom = new ArrayList<>();
        for (int i = 0; i <= max - min; i++) {
            bottom.add(map.get(i));
        }
        return bottom;
    }

    // Helper function to find the minimum and maximum vertical levels of the tree
    public void findBoundary(TreeNode root, int idx) {
        if (root == null) return;
        findBoundary(root.left, idx - 1);
        min = Math.min(min, idx);
        findBoundary(root.right, idx + 1);
        max = Math.max(max, idx);
    }

    // Helper method to create the binary tree and test the bottom view
    public static void main(String[] args) {
        BottomViewBinaryTree b = new BottomViewBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);

        List<Integer> bottomView = b.bottomView(root);
        System.out.println("Bottom view of the binary tree: " + bottomView);
    }
}

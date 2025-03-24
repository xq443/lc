import java.util.LinkedList;
import java.util.Queue;

public class BSTHeightReducerBFS {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }
    public int minimizeDeletions(TreeNode root, int maxHeight) {
        if (root == null) return 0;

        int nodesToDelete = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currentHeight = 0;

        while (!queue.isEmpty()) {
            currentHeight++;
            int levelSize = queue.size();

            if (currentHeight > maxHeight) {
                nodesToDelete += queue.size();
            }

            // Process the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
        }

        return nodesToDelete;
    }

    public static void main(String[] args) {
        BSTHeightReducerBFS b = new BSTHeightReducerBFS();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);

        int maxHeight = 1;
        System.out.println("Minimum deletions required: " + b.minimizeDeletions(root, maxHeight));
    }
}

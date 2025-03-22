import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DeleteNodeInBST {
    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node deleteNodesFromList(Node root, List<Node> list) {
        for (Node node : list) {
            root = deleteNode(root, node.val); // Update the root after each deletion
        }
        return root;
    }

    // Delete a node from the BST
    public Node deleteNode(Node root, int key) {
        if(root == null) return null;
        if(root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if(root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if(root.left == null) root = root.right;
            else if(root.right == null) root = root.left;
            else {
                Node min = root.right;
                while(min.left != null) min = min.left;
                root.val = min.val;
                root.right = deleteNode(root.right, min.val);
            }
        }
        return root;
    }

    public int height(Node root) {
        if(root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    // Print the tree level by level (BFS)
    public void printLevelOrder(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.val + " "); // Print on the same line
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        System.out.println(); // Print a newline after all values are printed
    }

    public static void main(String[] args) {
        DeleteNodeInBST d = new DeleteNodeInBST();

        // Build the tree
        Node root1 = new Node(3);
        root1.left = new Node(1);
        root1.right = new Node(4);
        root1.left.right = new Node(2);
        Node extra = new Node(5);

        //System.out.println(d.height(d.deleteNode(root1, 4)));
        d.printLevelOrder(d.deleteNodesFromList(root1, Arrays.asList(extra, root1.left)));
    }
}

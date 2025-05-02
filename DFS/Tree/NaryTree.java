import java.util.ArrayList;
import java.util.List;

public class NaryTree {
    public static class Node {
        int val;
        List<Node> children;

        public Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        public void add(Node child) {
            children.add(child);
        }
    }

    public int countNodes(Node root) {
        if (root == null) return 0;
        int cnt = 1;
        for(Node child : root.children) {
            cnt += countNodes(child);
        }
        return cnt;
    }

    public static void main(String[] args) {
        NaryTree n = new NaryTree();
        // Create nodes for the example tree
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        Node child4 = new Node(5);

        // Building the n-ary tree
        root.add(child1);
        root.add(child2);
        root.add(child3);
        child3.add(child4);

        // Calculate the number of nodes
        int nodeCount = n.countNodes(root);
        System.out.println("Total number of nodes in the tree: " + nodeCount);
    }
}

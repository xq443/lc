import java.util.ArrayList;
import java.util.List;

class Node {
    int id;
    List<Node> children;
    int nodeCount;

    // To keep track of the parent of each node
    Node parent;

    public Node(int id) {
        this.id = id;
        this.children = new ArrayList<>();
        this.nodeCount = 0;  // Initially, the node count is 0.
    }

    public void addChild(Node child) {
        children.add(child);
        child.parent = this;  // Set the parent of the child node
    }

    // Simulates sending a message to another node
    public void send(Node targetNode, String message) {
        System.out.println("Node " + id + " sending message: '" + message + "' to Node " + targetNode.id);
        targetNode.receive(this, message);
    }

    // Simulates receiving a message from another node
    public void receive(Node sender, String message) {
        // Check if the message is coming from the parent; avoid sending a message back to the parent
        if (sender == parent && message.equals("REQUEST_SIZE")) {
            return;  // Do nothing if the message is coming from the parent to avoid infinite loop
        }

        System.out.println("Node " + id + " received message: '" + message + "' from Node " + sender.id);

        if (message.equals("REQUEST_SIZE")) {
            // Requesting the size of the current node's subtree
            nodeCount = 1; // Count this node itself
            for (Node child : children) {
                // Recursively send the request to children
                child.send(this, "REQUEST_SIZE");
            }
        } else if (message.startsWith("SUBTREE_SIZE:")) {
            // Receiving subtree size from a child
            int childSubtreeSize = Integer.parseInt(message.split(":")[1]);
            nodeCount += childSubtreeSize; // Add the subtree size of this child
        }
    }

    // Function to start the distributed process to count nodes
    public void startCounting() {
        send(this, "REQUEST_SIZE"); // Start by sending a request for the size of the tree
    }

    // Getter to retrieve the total node count for the tree
    public int getNodeCount() {
        return nodeCount;
    }

    public static void main(String[] args) {
        // Create nodes for the example n-ary tree
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        Node child4 = new Node(5);

        // Building the n-ary tree structure
        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        child3.addChild(child4);

        // Start the distributed system process
        root.startCounting();

        // Output the final result: total number of nodes
        System.out.println("Total number of nodes in the tree: " + root.getNodeCount());
    }
}

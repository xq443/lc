package Snowflake.Tree.NaryCounter;

import java.util.HashSet;
import java.util.Set;

class NodeCounter {
  static public class Node {
    // ID of this node
    private final int nodeId;
    // IDs of all child nodes
    private Set<Integer> childrenIds = new HashSet<>();

    // Parent node ID
    private int parentNodeId = -1;
    // Total number of nodes in the subtree
    private int totalNodeInSubtree = 0;
    // Number of messages received from children
    private int currentReceivedCount = 0;

    // Message constants
    private static final String MSG_ACK_COUNT = "ack_count";  // ack_count:123
    private static final String MSG_DO_COUNT = "do_count";
    private static final String MSG_LEAF_NODE = "ack_count:1";

    // Constructor
    public Node(int nodeId, Set<Integer> children) {
      this.nodeId = nodeId;
      this.childrenIds = children;
    }

    // Check if a given node ID is a child
    private boolean isMyChild(int nodeId) {
      return childrenIds.contains(nodeId);
    }

    // Placeholder for async message sending
    public void sendAsync(int nodeId, String msg) {
      // Simulated async call
      System.out.println("Sending to " + nodeId + ": " + msg);
    }

    public void receive(int sourceNodeId, String msg) {
      // Case 1: Receiving message from a child
      if (isMyChild(sourceNodeId)) {
        if (!msg.startsWith(MSG_ACK_COUNT)) {
          // Invalid message, ignore
          return;
        }
        String sub = msg.substring(MSG_ACK_COUNT.length() + 1);
        int ackCount;
        try {
          ackCount = Integer.parseInt(sub);
        } catch (Exception e) {
          // Logging can be added here
          return;
        }
        totalNodeInSubtree += ackCount;
        currentReceivedCount++;

        // If all child responses are received, send aggregated count to parent
        if (currentReceivedCount == this.childrenIds.size()) {
          totalNodeInSubtree++;
          sendAsync(this.parentNodeId, MSG_ACK_COUNT + ":" + totalNodeInSubtree);
        }
      }
      // Case 2: Receiving message from the parent
      else {
        if (!msg.startsWith(MSG_DO_COUNT)) {
          // Invalid message, ignore
          return;
        }
        parentNodeId = sourceNodeId;

        // If this is a leaf node, return count to parent
        if (childrenIds.isEmpty()) {
          totalNodeInSubtree = 1;
          sendAsync(parentNodeId, MSG_ACK_COUNT + ":1");
          return;
        }

        // Send count request to all children
        for (int childId : childrenIds) {
          sendAsync(childId, MSG_DO_COUNT);
        }
      }
    }
  }

  public static void main(String[] args) {
    // Sample usage can be added here for testing
  }
}

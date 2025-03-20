package Snowflake.tree.NaryCounter;

import Snowflake.tree.NaryCounter.Node.Msg;
import java.util.*;

// Node class representing a node in the distributed system
class Node {

  // Message class representing messages exchanged between nodes
  static class Msg {
    String type; // Message type: "REQ", "REP", "TOT"
    int value;  // Value: REP - subtree node count, TOT - total tree node count

    Msg(String type, int value) {
      this.type = type;
      this.value = value;
    }
  }

  int id;
  List<Node> children = new ArrayList<>();
  Node parent = null;
  boolean requestReceived = false;
  int nodeCount = 1;
  int expectedReplies = 0;
  Set<Integer> receivedReplies = new HashSet<>();
  boolean totalProcessed = false;
  int totalNodes = 0;

  Node(int id) {
    this.id = id;
  }

  // Simulates sending a message to another node
  public void send(Node to, Msg msg) {
    to.receive(this, msg);
  }

  // Handles received messages
  public void receive(Node from, Msg msg) {
    switch (msg.type) {
      case "REQ":
        handleRequest(from);
        break;
      case "REP":
        handleReply(from, msg.value);
        break;
      case "TOT":
        handleTotal(msg.value);
        break;
    }
  }

  private void handleRequest(Node from) {
    if (requestReceived) return;
    requestReceived = true;

    if (from.id != -1) parent = from;
    expectedReplies = children.size();

    if (expectedReplies == 0) {
      if (parent != null) {
        send(parent, new Msg("REP", 1));
      } else {
        totalNodes = 1;
      }
    } else {
      for (Node child : children) {
        send(child, new Msg("REQ", 0));
      }
    }
  }

  private void handleReply(Node from, int value) {
    if (!receivedReplies.add(from.id)) return;
    nodeCount += value;

    if (receivedReplies.size() == expectedReplies) {
      if (parent != null) {
        send(parent, new Msg("REP", nodeCount));
      } else {
        totalNodes = nodeCount;
        broadcastTotal();
      }
    }
  }

  private void handleTotal(int value) {
    if (totalProcessed) return;
    totalProcessed = true;
    totalNodes = value;
    broadcastTotal();
  }

  private void broadcastTotal() {
    for (Node child : children) {
      send(child, new Msg("TOT", totalNodes));
    }
  }

  public static void main(String[] args) {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);

    // Establish parent-child relationships
    n1.children.addAll(Arrays.asList(n2, n3));
    n2.children.addAll(Arrays.asList(n4, n5));
    n3.children.addAll(Arrays.asList(n6, n7));

    // Dummy node (-1) to initiate request
    Node dummy = new Node(-1);
    dummy.send(n1, new Msg("REQ", 0));

    // Output total node count for each node
    for (Node node : Arrays.asList(n1, n2, n3, n4, n5, n6, n7)) {
      System.out.println("Node " + node.id + " Total Nodes: " + node.totalNodes);
    }
  }
}

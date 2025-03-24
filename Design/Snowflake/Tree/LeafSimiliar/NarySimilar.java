package Snowflake.Tree.LeafSimiliar;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


class NarySimilar {
  static class Node {
    String val;
    List<Node> children; // 子节点列表
    Node(String v) {
      val = v;
      children = new ArrayList<>();
    }
  }
  public boolean leafSimilar(Node r1, Node r2) {
    Stack<Node> s1 = new Stack<>();
    Stack<Node> s2 = new Stack<>();
    s1.push(r1);
    s2.push(r2);
    while (true) {
      String a = next(s1);
      String b = next(s2);
      if(a == null && b == null) return true;
      if(a == null || b == null || !a.equals(b)) return false;
    }
  }

  // 辅助函数：利用栈 s 进行 DFS，每次返回遇到的下一个叶节点的值
  private String next(Stack<Node> s) {
    while(!s.isEmpty()){
      Node t = s.pop();
      // 如果没有子节点，则 t 为叶节点
      if(t.children == null || t.children.isEmpty()){
        return t.val;
      }
      // 将子节点按从右到左的顺序压栈，保证 DFS 时左边先被遍历
      List<Node> cs = t.children;
      for(int i = cs.size() - 1; i >= 0; i--){
        s.push(cs.get(i));
      }
    }
    return null;
  }

  public static void main(String[] args) {
    // First tree
    Node root1 = new Node("a");
    Node child1 = new Node("b");
    Node child5 = new Node("m");
    Node child2 = new Node("c");

    root1.children.add(child1);
    root1.children.add(child5);
    root1.children.add(child2);

    child5.children.add(new Node("e"));
    child1.children.add(new Node("d"));
    child2.children.add(new Node("f"));

    // Second tree
    Node root2 = new Node("x");
    Node child3 = new Node("y");
    Node child4 = new Node("z");

    root2.children.add(child3);
    root2.children.add(child4);
    child3.children.add(new Node("d"));
    child4.children.add(new Node("e"));
    child4.children.add(new Node("f"));

    NarySimilar sol = new NarySimilar();
    boolean res = sol.leafSimilar(root1, root2);
    System.out.println("Leaf similar: " + res);
  }
}
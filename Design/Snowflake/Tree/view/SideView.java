package Snowflake.Tree.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SideView {
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

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ret = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    if(root == null) return ret;

    queue.offer(root);
    while(!queue.isEmpty()){
      int size = queue.size();
      for(int i = 0; i < size; i++){
        TreeNode curr = queue.poll();
        if(i == 0) ret.add(curr.val);
        if(curr.right != null) queue.offer(curr.right);
        if(curr.left != null) queue.offer(curr.left);
      }
    }
    return ret;
  }

  public List<Integer> rightSideViewdfs(TreeNode root) {
    List<Integer> ret = new ArrayList<>();
    dfs(root, ret, 0);
    return ret;
  }

  private void dfs(TreeNode node, List<Integer> ret, int level) {
    if (node == null) {
      return;
    }

    // If this level is not yet visited, it means this is the first node encountered at this level (rightmost node)
    if (level == ret.size()) {
      ret.add(node.val);
    }

    // Traverse right subtree first, then left subtree
    dfs(node.right, ret, level + 1);
    dfs(node.left, ret, level + 1);
  }

  public static void main(String[] args) {
    SideView vvdfs = new SideView();
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(6);
    System.out.println(vvdfs.rightSideView(root));
    System.out.println(vvdfs.rightSideViewdfs(root));
  }
}

import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserialize {
  public String serialize(TreeNode root) {
    if(root == null) return "";
    StringBuilder ret = new StringBuilder();

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while(!queue.isEmpty()){
      TreeNode curr = queue.poll();
      if(curr == null) ret.append("null,");
      else  {
        ret.append(String.valueOf(curr.val)).append(",");
        queue.add(curr.left);
        queue.add(curr.right);
      }
    }
    ret.deleteCharAt(ret.length()-1);
    return ret.toString();
  }


  public TreeNode deserialize(String data) {
    if(data == null || data.length() == 0) return null;

    String[] tokens = data.split(",");
    TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    int i = 1;
    while(i < tokens.length){
      TreeNode curr = queue.poll();

      if(!tokens[i].equals("null")){
        curr.left = new TreeNode(Integer.parseInt(tokens[i]));
        queue.offer(curr.left);
      }
      i++;
      if(i < tokens.length && !tokens[i].equals("null")){
        curr.right = new TreeNode(Integer.parseInt(tokens[i]));
        queue.offer(curr.right);
      }
      i++;
    }
    return root;
  }

  public static void main(String[] args) {
    SerializeandDeserialize s = new SerializeandDeserialize();
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(5);

    // Serialize the tree
    String serialized = s.serialize(root);
    System.out.println("Serialized tree: " + serialized);


    // Deserialize the serialized string and obtain the reconstructed tree
    TreeNode deserialized = s.deserialize(serialized);

    // Serialize the reconstructed tree again (for verification)
    String reSerialized = s.serialize(deserialized);
    System.out.println("Re-serialized tree: " + reSerialized);
  }

}

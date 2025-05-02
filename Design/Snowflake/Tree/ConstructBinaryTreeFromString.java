import java.util.Stack;

public class ConstructBinaryTreeFromString {
    public TreeNode str2tree(String s) {
        if (s == null || s.isEmpty()) return null;

        Stack<TreeNode> stack = new Stack<>();
        int i = 0;
        TreeNode root = null;

        while (i < s.length()) {
            char c = s.charAt(i);

            // If it's a number (handles negative numbers too)
            if (Character.isDigit(c) || c == '-') {
                int start = i;
                i++; // Move past first character (digit or '-')

                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    i++; // Extract the full number
                }

                int num = Integer.parseInt(s.substring(start, i));
                TreeNode node = new TreeNode(num);

                if (stack.isEmpty()) {
                    root = node; // First node is the root
                } else {
                    TreeNode parent = stack.peek();
                    if (parent.left == null) {
                        parent.left = node;
                    } else {
                        parent.right = node;
                    }
                }

                stack.push(node);
            } else if (c == '(') {
                i++; // Move past '('
            } else if (c == ')') {
                stack.pop(); // Completed a subtree
                i++;
            }
        }
        return root;
    }

    // Helper function to print inorder traversal
    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public TreeNode str2tree_recursion(String s) {
        if (s == null || s.isEmpty()) return null;
        int idx = s.indexOf('(');
        if (idx == -1) return new TreeNode(Integer.parseInt(s));
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, idx)));

        int cnt = 0;
        int start = idx;
        for(int i = idx; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') cnt++;
            else if (c == ')') cnt--;

            if(cnt == 0 && start == idx) {
                root.left = str2tree_recursion(s.substring(start + 1, i));
                start = i + 1;
            } else if (cnt == 0) {
                root.right = str2tree_recursion(s.substring(start + 1, i));
            }
        }
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromString tree = new ConstructBinaryTreeFromString();
        TreeNode root = tree.str2tree("4(2(3)(1))(6(5)(7))");
        tree.inOrder(root); // Expected Output: 3 2 1 4 5 6 7
        System.out.println(" ");
        TreeNode node = tree.str2tree("-4(2(3)(1))(6(5)(7))");
        tree.inOrder(node);

    }
}

package Basic;

public class UniqueBST {
  public int numTrees(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;

    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] += dp[j] * dp[i - j - 1];
      }
    }
    return dp[n];
  }

  public static void main(String[] args) {
    UniqueBST u = new UniqueBST();
    int n = 3;
    System.out.println(u.numTrees(n));
  }
}

// follow up: catalan
// 1. n nodes, how many structures of binary trees -> h(n)
// 2. n pairs of parenthesis, how many valid sequences -> h(n) (......)......
// 3. full binary tree: for all nodes, they either have no children or two children, 2n + 1 nodes,
// how many structures of full bs can it construct?  -> h(n)


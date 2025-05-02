public class ValidPanlindromeIII {
    public boolean isValidPalindrome(String s, int k) {
        String temp = "";
        for(int i = s.length() - 1; i >= 0; i--) {
            temp += s.charAt(i);
        }

        int n = s.length();
        s = '#' + s;
        temp = '#' + temp;
        int[][] dp = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return n - dp[n][n] <= k;
    }

    public boolean isValidPalindrome_optimized(String s, int k) {
        // track len of longest palindrome from a center
        int n = s.length();
        int[] dp = new int[n];
        char[] chars = s.toCharArray();
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            int colMax = 0; // longest subsequence length found in the previous iteration
            for(int j = i - 1; j >= 0; j--) {
                int prev = dp[j];
                if(chars[i] == chars[j]) {
                    dp[j] = colMax + 2;
                }
                colMax = Math.max(colMax, prev);
            }
        }
        int ret = 0;
        for(int len : dp) {
            ret = Math.max(ret, len);
        }
        return n - ret <= k;
    }

    public static void main(String[] args) {
        ValidPanlindromeIII v = new ValidPanlindromeIII();
        System.out.println(v.isValidPalindrome("abcdeca", 2));
        System.out.println(v.isValidPalindrome_optimized("abcdeca", 2));
    }
}

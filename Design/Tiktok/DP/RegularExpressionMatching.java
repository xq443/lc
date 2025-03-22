package Tiktok.DP;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        s = '#' + s;
        p = '#' + p;

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                dp[i][j] = false;
            }
        }
        dp[0][0] = true;
        for(int j = 2; j <= m; j += 2) { // m * m *
            dp[0][j] = dp[0][j - 2] && p.charAt(j) == '*';
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                char currs = s.charAt(i);
                char currp = p.charAt(j);
                if(Character.isAlphabetic(currp)) {
                    dp[i][j] = (currs == currp) && dp[i - 1][j - 1];
                } else if (currp == '.') { // single
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (currp == '*') {
                    boolean result1 = dp[i][j - 2];
                    // s: X X X X Z Z Z
                    // p: X X X X Z *
                    boolean result2 = dp[i - 1][j] && (currs == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                    dp[i][j] = result1 || result2;
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        RegularExpressionMatching r = new RegularExpressionMatching();
        System.out.println(r.isMatch("ab", ".*"));
    }
}

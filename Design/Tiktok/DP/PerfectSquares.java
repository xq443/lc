package Tiktok.DP;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PerfectSquares {
    public int numSquares(int n) {
        // TC: O(n * n^1/2)
        // SC: O(n)
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        int level = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for(int i = 0; i < size; i++) {
                int curr = queue.poll();
                for(int j = 1; j * j <= curr; j++) {
                    int next = curr - j * j;
                    if(next == 0) return level;
                    queue.offer(next);
                }
            }
        }
        return level;
    }

    public int numSquares_dp(int n) {
        // TC: O(n * n^1/2)
        // SC: O(n)
        int[] dp = new int[n + 1]; // num n can be filled up with # numbers
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // base case

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        System.out.println(ps.numSquares(12));
        System.out.println(ps.numSquares_dp(12));
    }
}

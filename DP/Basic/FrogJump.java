import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FrogJump {
    public boolean canCross(int[] stones) {
        // Edge case: frog must jump 1 unit from stone 0 to stone 1
        if (stones[1] != 1) return false;

        // Map stone position → set of jump sizes that can reach this stone
        Map<Integer, Set<Integer>> dp = new HashMap<>();

        for (int stone : stones) {
            dp.put(stone, new HashSet<>());
        }
        dp.get(0).add(0); // Initial state: position 0 with a jump of 0

        for (int stone : stones) {
            for (int k : dp.get(stone)) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && dp.containsKey(stone + step)) {
                        dp.get(stone + step).add(step);
                    }
                }
            }
        }

        // If there's any jump that reaches the last stone, return true
        return !dp.get(stones[stones.length - 1]).isEmpty();
    }

    // time: O(N^2)
    // space: O(N^2)

    public static void main(String[] args) {
        FrogJump jump = new FrogJump();
        System.out.println(jump.canCross(new int[] {0,1,3,5,6,8,12,17}));
        System.out.println(jump.canCross(new int[] {0,1,2,3,4,8,9,11}));
    }
}

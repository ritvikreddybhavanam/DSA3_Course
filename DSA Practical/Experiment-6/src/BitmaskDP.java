import java.util.*;

public class BitmaskDP {

    static int tsp(int[][] cost) {

        int n = cost.length;

        int totalMasks = 1 << n;

        int[][] dp = new int[totalMasks][n];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dp[1][0] = 0;

        for (int mask = 1; mask < totalMasks; mask++) {

            for (int current = 0; current < n; current++) {

                if ((mask & (1 << current)) == 0) {
                    continue;
                }

                if (dp[mask][current] == Integer.MAX_VALUE) {
                    continue;
                }

                for (int next = 0; next < n; next++) {

                    if ((mask & (1 << next)) != 0) {
                        continue;
                    }

                    int newMask = mask | (1 << next);

                    int newCost = dp[mask][current] + cost[current][next];

                    dp[newMask][next] = Math.min(dp[newMask][next], newCost);
                }
            }
        }

        int fullMask = totalMasks - 1;

        int answer = Integer.MAX_VALUE;

        for (int last = 0; last < n; last++) {

            if (dp[fullMask][last] != Integer.MAX_VALUE) {

                answer = Math.min(answer, dp[fullMask][last] + cost[last][0]);
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int[][] cost = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        int answer = tsp(cost);

        System.out.println("Minimum TSP Cost = " + answer);
    }
}
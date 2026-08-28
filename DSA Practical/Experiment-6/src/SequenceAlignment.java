import java.io.*;
import java.util.*;

public class SequenceAlignment {

    static int match = 1;
    static int mismatch = -1;
    static int gap = -2;

    public static String[] readSequences(String filePath) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String a = br.readLine();
        String b = br.readLine();

        br.close();

        return new String[]{a, b};
    }

    public static int[][] alignment(String a, String b) {

        int m = a.length();
        int n = b.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + gap;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + gap;
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                int diagonal;

                if (a.charAt(i - 1) == b.charAt(j - 1)) {

                    diagonal =
                            dp[i - 1][j - 1] + match;

                } else {

                    diagonal =
                            dp[i - 1][j - 1] + mismatch;
                }

                int up =
                        dp[i - 1][j] + gap;

                int left =
                        dp[i][j - 1] + gap;

                dp[i][j] = Math.max(diagonal, Math.max(up, left));
            }
        }

        return dp;
    }

    public static void main(String[] args) throws IOException {

        String[] sequences = readSequences("DSA Practical/Experiment-6/corpus/a1.txt");

        String a = sequences[0];
        String b = sequences[1];

        System.out.println("First Sequence  : " + a);
        System.out.println("Second Sequence : " + b);

        int[][] dp = alignment(a, b);

        System.out.println("\nAlignment Score = " + dp[a.length()][b.length()]);

        System.out.println("\nDP Table:");

        for (int i = 0; i <= a.length(); i++) {

            for (int j = 0; j <= b.length(); j++) {

                System.out.print(dp[i][j] + "\t");
            }

            System.out.println();
        }
    }
}
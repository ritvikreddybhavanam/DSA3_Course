import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class EditDistance {

    public static int calculate(String a, String b) {

        int m = a.length();
        int n = b.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];
                } else {

                    int deletion = dp[i - 1][j];
                    int insertion = dp[i][j - 1];
                    int substitution = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(substitution, Math.min(insertion, substitution));
                }
            }
        }
        return dp[m][n];
    }

    public static List<String> readSequences(String filePath) throws IOException {

        return Files.readAllLines(Paths.get(filePath));
    }

    public static void main(String[] args) throws IOException {

        List<String> sequences = readSequences("DSA Practical/Experiment-5/corpus/a1.txt");

        String a = sequences.get(0);

        String b = sequences.get(1);

        int distance = calculate(a, b);

        System.out.println("Edit distance = " + distance);
    }

}

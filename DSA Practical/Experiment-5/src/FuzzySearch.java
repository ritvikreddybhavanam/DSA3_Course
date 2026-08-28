import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuzzySearch {

    public static int editDistance(String a, String b) {

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

                    dp[i][j] = 1 + Math.min(deletion, Math.min(insertion, substitution));
                }
            }
        }

        return dp[m][n];
    }

    public static List<String> getWords(String filePath) throws FileNotFoundException {

        if (filePath == null) {
            System.out.println("File path not found");
            return new ArrayList<>();
        }

        File file = new File(filePath);

        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (!line.isEmpty()) {
                    words.add(line);
                }
            }

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return words;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter search word: ");
        String query = sc.nextLine();

        String bestMatch = "";
        int minDistance = Integer.MAX_VALUE;

        List<String> words = getWords("DSA Practical/Experiment-5/corpus/a2.txt");

        for (String word : words) {

            int distance =
                    editDistance(query, word);

            System.out.println(
                    word + " -> distance = " + distance
            );

            if (distance < minDistance) {

                minDistance = distance;
                bestMatch = word;
            }
        }

        System.out.println();
        System.out.println("Best Match : " + bestMatch);
        System.out.println("Edit Distance : " + minDistance);

        sc.close();
    }
}
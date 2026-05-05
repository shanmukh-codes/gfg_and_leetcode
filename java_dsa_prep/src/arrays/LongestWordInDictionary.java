
package arrays;

public class LongestWordInDictionary {
    // Optimal Solution
    // Time Complexity: O(nlogn)
    // Space Complexity: O(1)
    public static String longestWord(String s, String[] d) {
        String longestWord = "";

        for (String word : d) {
            if (isSubsequence(word, s)) {
                if (word.length() > longestWord.length()
                        || word.length() == longestWord.length() && word.compareTo(longestWord) < 0) {
                    longestWord = word;
                }
            }
        }

        return longestWord;
    }

    public static boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    public static void main(String[] args) {
        String s = "abpcplea";
        String[] d = { "ale", "apple", "monkey", "plea" };
        System.out.println("Longest word: " + longestWord(s, d)); // Expected: apple
    }
}

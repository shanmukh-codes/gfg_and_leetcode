package strings;

/*
 * Example 1:
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic substrings: "a", "b", "c".
 * 
 * Example 2:
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic substrings: "a", "a", "a", "aa", "aa", "aaa".
 * 
 * Constraints:
 * 1 <= s.length <= 1000
 * s consist of lowercase English letters.
 * 
 * @param s first string
 * @return number of palindromic substrings
 */

public class PalindromicSubstrings {

    // Approach 1: Expand Around Center
    // Time complexity: O(n^2)
    // Space complexity: O(1)
    public static int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes (center is a single character)
            count += expandAroundCenter(s, i, i);
            // Even length palindromes (center is between two characters)
            count += expandAroundCenter(s, i, i + 1);
        }

        return count;
    }

    // Helper function to expand around the center
    private static int expandAroundCenter(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
        return count;
    }

    // Approach 2: Dynamic Programming
    // Time complexity: O(n^2)
    // Space complexity: O(n^2)
    public static int countSubstringsDP(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int count = 0;
        // dp[i][j] will be true if the substring s[i..j] is a palindrome
        boolean[][] dp = new boolean[n][n];

        // All substrings of length 1 are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }

        // Check for substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                count++;
            }
        }

        // Check for substrings of length 3 or more
        // k is the length of the substring
        for (int k = 3; k <= n; k++) {
            // i is the starting index
            for (int i = 0; i <= n - k; i++) {
                // j is the ending index
                int j = i + k - 1;

                // Check if the inner substring is a palindrome and the outer characters match
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String s1 = "abc";
        System.out.println("Expand Around Center: " + countSubstrings(s1));
        System.out.println("Dynamic Programming: " + countSubstringsDP(s1));

        String s2 = "aaa";
        System.out.println("Expand Around Center: " + countSubstrings(s2));
        System.out.println("Dynamic Programming: " + countSubstringsDP(s2));
    }
}

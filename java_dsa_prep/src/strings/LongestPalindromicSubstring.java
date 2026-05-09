package strings;

/*
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * 
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 * 
 * Constraints:
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 * 
 * @param s first string
 * @return longest palindromic substring
 */

public class LongestPalindromicSubstring {

    // Approach 1: Expand Around Center
    // Time complexity: O(n^2)
    // Space complexity: O(1)
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes (center is a single character)
            int len1 = expandAroundCenter(s, i, i);
            // Even length palindromes (center is between two characters)
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            //
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    // Helper function to expand around the center
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // Approach 2: Dynamic Programming
    // Time complexity: O(n^2)
    // Space complexity: O(n^2)
    public static String longestPalindromeDP(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int n = s.length();
        // dp[i][j] will be true if the substring s[i..j] is a palindrome
        boolean[][] dp = new boolean[n][n];

        int start = 0;
        int maxLength = 1;

        // All substrings of length 1 are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Check for substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
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
                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        String s1 = "babad";
        System.out.println("Expand Around Center: " + longestPalindrome(s1));
        System.out.println("Dynamic Programming: " + longestPalindromeDP(s1));

        String s2 = "cbbd";
        System.out.println("Expand Around Center: " + longestPalindrome(s2));
        System.out.println("Dynamic Programming: " + longestPalindromeDP(s2));
    }
}

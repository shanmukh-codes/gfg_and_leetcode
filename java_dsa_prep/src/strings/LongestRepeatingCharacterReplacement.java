package strings;

/*
 * Example 1:
 * 
 * Input: s = "ABAB", k = 2
 * 
 * Output: 4
 * 
 * Explanation:
 * Replace the two 'C's with 'A's to make s = "AAAA".
 * Example 2:
 * 
 * Input: s = "AABABBA", k = 1
 * 
 * Output: 4
 * 
 * Explanation:
 * Replace the one 'B' in the middle with 'A' and form "AAAAAA" or replace the
 * one 'A' with 'B' in "AABABBA" to get "AAABABA".
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 105
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 */
public class LongestRepeatingCharacterReplacement {

    // Optimal approach using sliding window
    // Time complexity: O(n)
    // Space complexity: O(1) or O(26) which is constant
    public static int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[] counts = new int[26];
        int left = 0;
        int maxLen = 0;
        int maxRepeatCount = 0;

        for (int right = 0; right < n; right++) {
            // Increment the count of the current character
            counts[s.charAt(right) - 'A']++;

            // Update the maximum frequency of any character in the current window
            maxRepeatCount = Math.max(maxRepeatCount, counts[s.charAt(right) - 'A']);

            // Check if the current window is valid
            // A window is valid if: window length - max repeat count <= k
            // This means we need at most k replacements to make the window have all
            // same characters
            int windowLength = right - left + 1;
            if (windowLength - maxRepeatCount > k) {
                // Shrink the window from the left
                counts[s.charAt(left) - 'A']--;
                left++;
            }

            // Update the maximum length found so far
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println("Longest Repeating Character Replacement: " + characterReplacement(s1, k1));

        String s2 = "AABABBA";
        int k2 = 1;
        System.out.println("Longest Repeating Character Replacement: " + characterReplacement(s2, k2));

        String s3 = "AAAA";
        int k3 = 2;
        System.out.println("Longest Repeating Character Replacement: " + characterReplacement(s3, k3));

        String s4 = "ABCDE";
        int k4 = 2;
        System.out.println("Longest Repeating Character Replacement: " + characterReplacement(s4, k4));
    }
}

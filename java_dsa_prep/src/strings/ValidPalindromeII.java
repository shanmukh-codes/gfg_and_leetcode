package strings;

/*
 * Example 1:
 * 
 * Input: s = "aba"
 * Output: true
 * Example 2:
 * 
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character at index 1 ('b') or index 2 ('c').
 * Example 3:
 * 
 * Input: s = "abc"
 * Output: false
 * Explanation: You could delete only one character.
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 */
public class ValidPalindromeII {

    // Optimal approach
    // Time complexity: O(n)
    // Space complexity: O(1)
    public static boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Try deleting the left character OR the right character
                return isPalindromeRange(s, left + 1, right) || isPalindromeRange(s, left, right - 1);
            }
            left++;
            right--;
        }

        return true;
    }

    // Helper function to check if a range is a palindrome
    private static boolean isPalindromeRange(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "aba";
        System.out.println("Valid Palindrome II: " + validPalindrome(s1));

        String s2 = "abca";
        System.out.println("Valid Palindrome II: " + validPalindrome(s2));

        String s3 = "abc";
        System.out.println("Valid Palindrome II: " + validPalindrome(s3));
    }
}

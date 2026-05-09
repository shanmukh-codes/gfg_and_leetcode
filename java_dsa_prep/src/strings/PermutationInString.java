package strings;

import java.util.Arrays;

/*
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1,
 * or false otherwise.
 * In other words, return true if one of s1’s permutations is the substring of
 * s2.
 * 394. Decode String
 * 
 * Example: Input: s1 = "ab", s2 = "eidbaooo" Output: true Explanation:
 * "ba" is an anagram of "ab" and is a substring of "eidbaooo".
 * 
 * Constraints: 1 <= s1.length, s2.length <= 104 s1 and s2 consist of
 * lowercase English letters.
 * 
 * @param s1 first string
 * @param s2 second string
 * @return true if s2 contains a permutation of s1, false otherwise
 */

public class PermutationInString {

    // Sliding Window Approach
    // Time complexity: O(n)
    // Space complexity: O(1)
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1Count = new int[26];
        int[] windowCount = new int[26];

        // Initialize the first window
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            windowCount[s2.charAt(i) - 'a']++;
        }

        // Check the first window
        if (Arrays.equals(s1Count, windowCount)) {
            return true;
        }

        // Slide the window across s2
        for (int i = s1.length(); i < s2.length(); i++) {
            // Add the new character entering the window
            windowCount[s2.charAt(i) - 'a']++;
            // Remove the character leaving the window
            windowCount[s2.charAt(i - s1.length()) - 'a']--;

            // Check if the current window is a permutation
            if (Arrays.equals(s1Count, windowCount)) {
                return true;
            }
        }

        return false;
    }

    // Helper function to check if two count arrays match
    private static boolean matches(int[] arr1, int[] arr2) {
        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2)); // Output: true

        String s3 = "ab";
        String s4 = "eidboaoo";
        System.out.println(checkInclusion(s3, s4)); // Output: false
    }
}

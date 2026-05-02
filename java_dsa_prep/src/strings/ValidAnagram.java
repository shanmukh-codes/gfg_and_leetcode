package strings;

import java.util.Arrays;

public class ValidAnagram {
    /**
     * Given two strings s and t, return true if t is an anagram of s, and false
     * otherwise.
     * An Anagram is a word or phrase formed by rearranging the letters of a
     * different word or phrase, typically using all the original letters exactly
     * once.
     * 
     * Example 1:
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     * 
     * Example 2:
     * Input: s = "rat", t = "car"
     * Output: false
     * 
     * Constraints:
     * 1 <= s.length, t.length <= 5 * 104
     * s and t consist of lowercase English letters.
     * 
     * Follow up: What if the inputs contain Unicode characters? How would you adapt
     * your solution to handle such cases?
     * 
     * @param s first string
     * @param t second string
     * @return true if t is an anagram of s, false otherwise
     */

    // Brute Force approach
    // Time complexity: O(n log n)
    // Space complexity: O(n)
    public static boolean isAnagramBruteForce(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    // Optimized approach
    // Time complexity: O(n)
    // Space complexity: O(1)
    public static boolean isAnagramOptimized(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println("Brute Force: " + isAnagramBruteForce(s, t));
        System.out.println("Optimized: " + isAnagramOptimized(s, t));
    }
}

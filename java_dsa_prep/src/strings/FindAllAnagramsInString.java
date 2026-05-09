/*
 * Given a string and a pattern, find all the starting indices of p’s
 * anagrams in the given string.
 * 438. Find All Anagrams in a String (LeetCode)
 * 
 * Example: Input: s = "cbaebabacd", p = "abc" Output: [0, 6]
 */
package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInString {

    // Brute Force Approach
    // Time complexity: O(n*m)
    // Space complexity: O(m)
    public static List<Integer> findAnagramsBruteForce(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return result;
        }
        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (isAnagram(s.substring(i, i + p.length()), p)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean isAnagram(String s, String p) {
        if (s.length() != p.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[p.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // Sliding Window Approach - Optimal
    // Time complexity: O(n)
    // Space complexity: O(1)
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return result;
        }
        int[] pCount = new int[26];
        int[] windowCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
            windowCount[s.charAt(i) - 'a']++;
        }

        if (Arrays.equals(pCount, windowCount)) {
            result.add(0);
        }

        for (int i = p.length(); i < s.length(); i++) {
            windowCount[s.charAt(i) - 'a']++;
            windowCount[s.charAt(i - p.length()) - 'a']--;
            if (Arrays.equals(pCount, windowCount)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s, p));
    }
}

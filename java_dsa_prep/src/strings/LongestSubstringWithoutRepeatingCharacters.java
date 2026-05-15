/*
 * 
 */
package strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    // approach 1: using sliding window
    public static int longestSubstringWithoutRepeatingCharacters(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // approach 2: using sliding window with map
    public static int longestSubstringWithoutRepeatingCharactersOptimized(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }
            map.put(currentChar, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(longestSubstringWithoutRepeatingCharacters(s));
        System.out.println(longestSubstringWithoutRepeatingCharactersOptimized(s));
    }
}

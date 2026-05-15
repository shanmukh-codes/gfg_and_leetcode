/*
 * Example 1:
 * 
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * 
 * Output: "BANC"
 * 
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from
 * string 't'.
 * Example 2:
 * 
 * Input: s = "a", t = "a"
 * 
 * Output: "a"
 * 
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * 
 * Input: s = "a", t = "aa"
 * 
 * Output: ""
 * 
 * Explanation: Both 'a's in t must be included in the window.
 * Since s only has one 'a', it is impossible to find such a window.
 * 
 * Constraints:
 * 
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 */
package strings;

public class MinimumWindowSubstring {
    // Optimal approach using sliding window
    // Time complexity: O(n)
    // Space complexity: O(1) or O(26) which is constant

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // 1. Target frequencies
        int[] targetCounts = new int[128];
        for (char c : t.toCharArray()) {
            targetCounts[c]++;
        }

        // 2. Sliding window setup
        int left = 0;
        int minLeft = 0;
        int minLen = Integer.MAX_VALUE;
        int required = t.length(); // characters from 't' we still need to match

        // 3. Expand the window
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            // If targetCounts > 0, it means 't' needs this character!
            if (targetCounts[rightChar] > 0) {
                required--;
            }
            // Always decrement, so unneeded chars become negative
            targetCounts[rightChar]--;

            // 4. Shrink the window when we have a valid match (required == 0)
            while (required == 0) {
                // Record the shortest valid window
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                // Try to throw out the left character to make the window smaller
                char leftChar = s.charAt(left);
                targetCounts[leftChar]++; // Put it back

                // If the count becomes > 0, we just threw away a character we actually needed!
                if (targetCounts[leftChar] > 0) {
                    required++; // Window is no longer valid, need to expand again
                }
                left++; // Actually shrink
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC")); // Output: "BANC"
        System.out.println(solution.minWindow("a", "a")); // Output: "a"
        System.out.println(solution.minWindow("a", "aa")); // Output: ""
    }

}

/*
 * Example 1:
 * 
 * Input: s = "abcdebdde", t = "bde"
 * 
 * Output: "bcde"
 * 
 * Explanation:
 * "bcde" is a subsequence of s and is the minimum length window that satisfies
 * this.
 * Example 2:
 * 
 * Input: s = "jmeqksfrs", t = "mjqck"
 * 
 * Output: ""
 * 
 * Explanation:
 * No subsequence of s can form t.
 * 
 * Constraints:
 * 
 * 1 <= s.length, t.length <= 2 * 104
 * s and t consist of lowercase English letters.
 */
package strings;

public class MinimumWindowSubsequence {
    public String minWindow(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return "";
        }

        int n = s1.length();
        int m = s2.length();

        int minLen = Integer.MAX_VALUE;
        int minStart = -1;

        int i = 0;
        int j = 0;

        while (i < n) {
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
                if (j == m) {
                    int end = i;
                    j--;
                    while (j >= 0) {
                        if (s1.charAt(i) == s2.charAt(j)) {
                            j--;
                        }
                        i--;
                    }
                    i++;

                    if (end - i + 1 < minLen) {
                        minLen = end - i + 1;
                        minStart = i;
                    }
                    j = 0;
                }
            }
            i++;
        }
        return minStart == -1 ? "" : s1.substring(minStart, minStart + minLen);
    }
}

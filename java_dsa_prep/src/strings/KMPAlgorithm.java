/*
 * Implementation of KMP algorithm for string searching.
 * 
 * KMP algorithm is an efficient string matching algorithm that finds occurrences of a 
 * pattern within a main text.
 * 
 * Complexity:
 * - Preprocessing: O(m), where m is the length of the pattern
 * - Searching: O(n), where n is the length of the text
 * - Total: O(n + m)
 * 
 * Space: O(m) for the LPS array
 */
package strings;

public class KMPAlgorithm {
    public int strStr(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        if (m == 0) {
            return 0;
        }
        int[] lps = computeLPSArray(pattern);
        int i = 0, j = 0;
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == m) {
                return i - j;
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    public int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int length = 0;
        int i = 1;
        lps[0] = 0;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {

    }
}

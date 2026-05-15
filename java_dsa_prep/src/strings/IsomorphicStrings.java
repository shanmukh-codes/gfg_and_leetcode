/*
 * Implementation of Isomorphic Strings algorithm.
 * 
 * Two strings are isomorphic if the characters in s can be replaced to get t. 
 * All occurrences of a character must be replaced with another character while 
 * preserving the order of characters. No two characters may map to the same character, 
 * but a character may map to itself.
 * 
 * Complexity:
 * - Time: O(n), where n is the length of the strings
 * - Space: O(1), as the size of the mapping arrays is constant (256 for ASCII)
 */
package strings;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

    // Array approach
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] lastSeenInS = new int[256];
        int[] lastSeenInT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            if (lastSeenInS[charS] != lastSeenInT[charT]) {
                return false;
            }
            lastSeenInS[charS] = i + 1;
            lastSeenInT[charT] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}

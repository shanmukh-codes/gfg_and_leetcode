
/*
You are given a string s consisting only of characters a, b and c. Return the number of substrings containing at least one occurrence of all three characters of s.

Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of all three characters are:
"abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc", and "abc".

Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of all three characters are:
"aaacb", "aacb", and "acb".
*/
package arrays;

public class SubstringsContainingAllThreeCharacters {

    // Brute Force Solution
    // Time Complexity: O(n^3)
    // Space Complexity: O(1)
    public static int numberOfSubstringsBruteForce(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int[] freq = new int[3];
                for (int k = i; k <= j; k++) {
                    freq[s.charAt(k) - 'a']++;
                }
                if (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // Optimal Solution
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int numberOfSubstringsOptimal(String s) {
        int n = s.length();
        int count = 0;
        int left = 0;
        int[] freq = new int[3];
        for (int right = 0; right < n; right++) {
            freq[s.charAt(right) - 'a']++;
            while (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                count += (n - right);
                freq[s.charAt(left) - 'a']--;
                left++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s1 = "abcabc";
        System.out.println("Optimal Number of substrings for " + s1 + ": " + numberOfSubstringsOptimal(s1)); // Expected:
                                                                                                             // 10
        System.out.println("Brute Force Number of substrings for " + s1 + ": " + numberOfSubstringsBruteForce(s1)); // Expected:
                                                                                                                    // 10

        String s2 = "aaacb";
        System.out.println("Optimal Number of substrings for " + s2 + ": " + numberOfSubstringsOptimal(s2)); // Expected:
                                                                                                             // 3
        System.out.println("Brute Force Number of substrings for " + s2 + ": " + numberOfSubstringsBruteForce(s2)); // Expected:
                                                                                                                    // 3
    }
}

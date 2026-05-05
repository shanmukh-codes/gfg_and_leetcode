/*
 * You are given a string s of length n containing only the characters 'Q', 'W', 'E', and 'R'.
 * A string is balanced if each of the characters occurs n / 4 times.
 * Return the minimum length of the substring that can be replaced with any other string of the same length to make s balanced.
 * 
 * Example 1:
 * Input: s = "QWER"
 * Output: 0
 * Explanation: s is already balanced.
 * 
 * Example 2:
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: Replace 'Q' with 'R'.
 * 
 * Example 3:
 * Input: s = "QQQW"
 * Output: 3
 * Explanation: Replace 'QQQ' with 'RWE'.
 */
package arrays;

public class ReplaceSubstringForBalancedString {

    // Approach 1: Brute Force
    // Time complexity: O(n^3)
    // Space complexity: O(1)
    public static int balancedStringBruteForce(String s) {
        int n = s.length();
        int[] count = new int[4];
        for (int i = 0; i < n; i++) {
            count[getCharIndex(s.charAt(i))]++;
        }
        int target = n / 4;
        int minLength = n;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int[] tempCount = count.clone();
                for (int k = i; k <= j; k++) {
                    tempCount[getCharIndex(s.charAt(k))]--;
                }
                if (tempCount[0] <= target && tempCount[1] <= target && tempCount[2] <= target
                        && tempCount[3] <= target) {
                    minLength = Math.min(minLength, j - i + 1);
                }
            }
        }
        return minLength;
    }

    // Optimal approach to find the minimum length of the substring to be replaced
    // to make the string balanced.
    // Time complexity: O(n)
    // Space complexity: O(1)
    public static int balancedStringOptimal(String s) {
        int n = s.length();
        int target = n / 4;
        int[] count = new int[4];
        for (char ch : s.toCharArray()) {
            count[getCharIndex(ch)]++;
        }

        if (count[0] <= target && count[1] <= target && count[2] <= target && count[3] <= target) {
            return 0;
        }

        int left = 0, right = 0;
        int minLength = n;
        while (right < n) {
            count[getCharIndex(s.charAt(right))]--;
            while (left <= right && count[0] <= target && count[1] <= target && count[2] <= target
                    && count[3] <= target) {
                minLength = Math.min(minLength, right - left + 1);
                count[getCharIndex(s.charAt(left))]++;
                left++;
            }
            right++;
        }
        return minLength;
    }

    private static int getCharIndex(char ch) {
        switch (ch) {
            case 'Q':
                return 0;
            case 'W':
                return 1;
            case 'E':
                return 2;
            case 'R':
                return 3;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        String s1 = "QWER";
        System.out.println("Brute force Minimum replacements for " + s1 + ": " + balancedStringBruteForce(s1)); // Expected:
                                                                                                                // 0
        System.out.println("Optimal Minimum replacements for " + s1 + ": " + balancedStringOptimal(s1)); // Expected: 0

        String s2 = "QQWE";
        System.out.println("Brute force Minimum replacements for " + s2 + ": " + balancedStringBruteForce(s2)); // Expected:
                                                                                                                // 1
        System.out.println("Optimal Minimum replacements for " + s2 + ": " + balancedStringOptimal(s2)); // Expected: 1

        String s3 = "QQQW";
        System.out.println("Brute force Minimum replacements for " + s3 + ": " + balancedStringBruteForce(s3)); // Expected:
                                                                                                                // 3
        System.out.println("Optimal Minimum replacements for " + s3 + ": " + balancedStringOptimal(s3)); // Expected: 3

        String s4 = "QQWERERR";
        System.out.println("Brute force Minimum replacements for " + s4 + ": " + balancedStringBruteForce(s4)); // Expected:
                                                                                                                // 0
        System.out.println("Optimal Minimum replacements for " + s4 + ": " + balancedStringOptimal(s4)); // Expected: 0
    }
}

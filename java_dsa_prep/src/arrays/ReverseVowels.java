/*
 * Remove Vowels in place
 * 
 * Example 1:
 * Input: s = "hello"
 * Output: "holle"
 * 
 * Example 2:
 * Input: s = "leetcode"
 * Output: "leotcede"
 * 
 * Constraints:
 * 1 <= s.length <= 3 * 10^5
 * s consists of printable ASCII characters.
 * Note that lowercase and uppercase English letters are said to be "different"
 * characters.
 */
package arrays;

public class ReverseVowels {
    // Brute force approach - reverse vowels

    // Optimized approach
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        String vowels = "aeiouAEIOU"; // Quick lookup string

        while (left < right) {
            // Advance left until it hits a vowel
            while (left < right && vowels.indexOf(chars[left]) == -1) {
                left++;
            }

            // Advance right until it hits a vowel
            while (left < right && vowels.indexOf(chars[right]) == -1) {
                right--;
            }

            // Both pointers are sitting on vowels! Swap them.
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        ReverseVowels rv = new ReverseVowels();

        String s1 = "hello";
        System.out.println("Input: " + s1);
        System.out.println("Output: " + rv.reverseVowels(s1)); // Expected: holle

        String s2 = "leetcode";
        System.out.println("Input: " + s2);
        System.out.println("Output: " + rv.reverseVowels(s2)); // Expected: leotcede
    }
}

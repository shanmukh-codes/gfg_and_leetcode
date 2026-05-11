package strings;

// Given a string s (contains upper and lower case letters), return the length of the longest palindrome that can be built with those letters.
/*
 */
public class LongestPalindrome {

    public static int longestPalindrome(String s) {
        int[] counts = new int[128];
        for (char c : s.toCharArray()) {
            counts[c]++;
        }
        int length = 0;
        boolean hasOdd = false;
        for (int c : counts) {
            length += (c / 2) * 2;
            if (c % 2 == 1) {
                hasOdd = true;
            }
        }

        return hasOdd ? length + 1 : length;
    }

}

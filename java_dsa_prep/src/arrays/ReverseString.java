/*
 * Write a function that reverses a string. The input string is given as an array
 * of characters s.
 * 
 * Example 1:
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * 
 * Example 2:
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
package arrays;

import java.util.Arrays;

public class ReverseString {
    // Brute force approach
    public void reverseStringBruteForce(char[] s) {
        int n = s.length;
        char[] temp = new char[n];
        for (int i = 0; i < n; i++) {
            temp[i] = s[n - 1 - i];
        }
        for (int i = 0; i < n; i++) {
            s[i] = temp[i];
        }
    }

    // Optimized approach
    public void reverseString(char[] s) {
        int n = s.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void main(String[] args) {
        char[] s1 = { 'h', 'e', 'l', 'l', 'o' };
        char[] s2 = { 'H', 'a', 'n', 'n', 'a', 'h' };

        ReverseString reverseString = new ReverseString();

        System.out.println("String 1: " + Arrays.toString(s1));
        reverseString.reverseString(s1);
        System.out.println("Reversed: " + Arrays.toString(s1));
        System.out.println();

        System.out.println("String 2: " + Arrays.toString(s2));
        reverseString.reverseString(s2);
        System.out.println("Reversed: " + Arrays.toString(s2));
        System.out.println();
    }

}

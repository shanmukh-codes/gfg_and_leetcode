package strings;

import java.util.Stack;

/*
 * Example 1:
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * 
 * Example 2:
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * 
 * Example 3:
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * 
 * Constraints:
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 * 
 * @param s the encoded string
 * @return the decoded string
 */

public class DecodeString {
    public static String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        int repeat = 0;
        int i = 0;

        while (i < s.length()) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                // Read the full number (could be multi-digit)
                repeat = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    repeat = repeat * 10 + (s.charAt(i) - '0');
                    i++;
                }
                // i is now at the '['
            } else if (ch == '[') {
                // Find the matching ']' using a counter
                int start = i + 1;
                int end = findMatchingBracket(s, start);

                // Recursively decode the inner string
                String decodedInner = decodeString(s.substring(start, end));

                // Append the decoded string 'repeat' times
                for (int k = 0; k < repeat; k++) {
                    result.append(decodedInner);
                }

                // Move i to the character after ']'
                i = end + 1;
            } else {
                // Regular character
                result.append(ch);
                i++;
            }
        }

        return result.toString();
    }

    // Helper function to find the matching closing bracket
    private static int findMatchingBracket(String s, int start) {
        int balance = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                balance++;
            } else if (s.charAt(i) == ']') {
                balance--;
                if (balance == 0) {
                    return i;
                }
            }
        }
        return -1; // Should not happen for valid input
    }

    // --- Iterative Solution using Stack (More efficient, no recursion overhead)
    // ---

    public static String decodeStringIterative(String s) {
        // Stack to store previous repeat counts and partial results
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> resultStack = new Stack<>();

        StringBuilder currentResult = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // Push current state to stack
                countStack.push(k);
                resultStack.push(currentResult);

                // Reset for the new inner scope
                k = 0;
                currentResult = new StringBuilder();
            } else if (ch == ']') {
                // Pop the repeat count and previous result
                int repeatCount = countStack.pop();
                StringBuilder prevResult = resultStack.pop();

                // Repeat the current result 'repeatCount' times
                for (int i = 0; i < repeatCount; i++) {
                    prevResult.append(currentResult);
                }

                // Update current result
                currentResult = prevResult;
            } else {
                // Regular character
                currentResult.append(ch);
            }
        }

        return currentResult.toString();
    }

    public static void main(String[] args) {

    }
}

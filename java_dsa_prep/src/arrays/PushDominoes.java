/*
 * Problem Statement: 
 * There are n dominoes in a line, each positioned as either 'L' (left), 
 * 'R' (right), or '.' (empty).

 * The dominoes are pushed simultaneously from the left and right. 
 * When a domino is pushed, it topples the adjacent domino in the same direction.
 * This continues until no more dominoes can be toppled.

 * The task is to return the final configuration of the dominoes after all pushes.
 * 
 * Example 1: 
 * Input: "RR.L" Output: "RR.L"
 * 
 * Explanation: The first R topples the second R. 
 * The L topples the dot.
 * 
 * Example 2: 
 * Input: ".L.R...L..L.." 
 * Output: "LL.RR.LL..L.."
 * 
 * Explanation: The L topples the dot to its left. 
 * The R topples the dot to its right. 
 * The V shape in the middle cancels out.
 */
package arrays;

public class PushDominoes {

    // Brute Force Approach (Simulation)
    // Time Complexity: O(n^2)
    // Space Complexity: O(n) for the char array
    public static String pushDominoesBruteForce(String dominoes) {
        char[] A = dominoes.toCharArray();
        int N = A.length;

        while (true) {
            char[] nextA = A.clone();
            boolean changed = false;

            for (int i = 0; i < N; i++) {
                if (A[i] == '.') {
                    boolean leftPush = (i > 0 && A[i - 1] == 'R');
                    boolean rightPush = (i < N - 1 && A[i + 1] == 'L');

                    if (leftPush && rightPush) {
                        // Case 1: .R.L. -> Stays .R.L.
                        continue;
                    } else if (leftPush) {
                        // Case 2: .R... -> .RR..
                        nextA[i] = 'R';
                        changed = true;
                    } else if (rightPush) {
                        // Case 3: ...L. -> ..L..
                        nextA[i] = 'L';
                        changed = true;
                    }
                    // Case 4: ... -> ... (no change)
                }
            }

            A = nextA;
            if (!changed) {
                break;
            }
        }

        return new String(A);
    }

    // Optimal Approach (Two Pointers / Segment Processing)
    // Time Complexity: O(n)
    // Space Complexity: O(1) (excluding the space for the result string)
    public static String pushDominoesOptimal(String dominoes) {
        String s = "L" + dominoes + "R";
        StringBuilder result = new StringBuilder();
        int i = 0;
        int j = 1;
        while (j < s.length()) {
            if (s.charAt(j) == '.') {
                j++;
                continue;
            }

            int dots = j - i - 1;
            char leftBoundary = s.charAt(i);
            char rightBoundary = s.charAt(j);

            if (leftBoundary == 'L' && rightBoundary == 'L') {
                result.append(String.valueOf('L').repeat(dots + 1));
            } else if (leftBoundary == 'R' && rightBoundary == 'R') {
                result.append(String.valueOf('R').repeat(dots + 1));
            } else if (leftBoundary == 'L' && rightBoundary == 'R') {
                result.append("L");
                result.append(String.valueOf('.').repeat(dots));
            } else if (leftBoundary == 'R' && rightBoundary == 'L') {
                result.append("R");
                int half = dots / 2;
                result.append(String.valueOf('R').repeat(half));

                if (dots % 2 != 0) {
                    result.append(".");
                }
                result.append(String.valueOf('L').repeat(half));
            }

            i = j;
            j++;
        }

        // Remove the imaginary 'L' we added at the start!
        return result.substring(1).toString();
    }

    public static void main(String[] args) {
        System.out.println(pushDominoesOptimal("RR.L"));
    }
}

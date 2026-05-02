/*
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle that can be formed within the histogram.
 * 
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above histogram (panels) is being depicted in a way that each 'x' represents a pixel.
 * To make sure the area of the rectangle is given.
 * In this case, the maximum area of the rectangle is 10.
 * 
 * Example 2:
 * Input: heights = [2,4]
 * Output: 4
 * 
 * Constraints:
 * 1 <= heights.length <= 105
 * 0 <= heights[i] <= 104
 */

package arrays;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {

    // Brute Force Approach
    public static int largestRectangleAreaBruteForce(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int minHeight = heights[i];
            for (int j = i; j < n; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                int width = j - i + 1;
                maxArea = Math.max(maxArea, minHeight * width);
            }
        }
        return maxArea;
    }

    // Optimized Approach using Stack
    public static int largestRectangleAreaOptimized(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights1 = { 2, 1, 5, 6, 2, 3 };
        int[] heights2 = { 2, 4 };
        int[] heights3 = { 1, 1 };
        int[] heights4 = { 0, 9 };

        System.out.println("Heights 1: [2, 1, 5, 6, 2, 3]");
        System.out.println("Brute Force: " + largestRectangleAreaBruteForce(heights1));
        System.out.println("Optimized: " + largestRectangleAreaOptimized(heights1));
        System.out.println();

        System.out.println("Heights 2: [2, 4]");
        System.out.println("Brute Force: " + largestRectangleAreaBruteForce(heights2));
        System.out.println("Optimized: " + largestRectangleAreaOptimized(heights2));
        System.out.println();

        System.out.println("Heights 3: [1, 1]");
        System.out.println("Brute Force: " + largestRectangleAreaBruteForce(heights3));
        System.out.println("Optimized: " + largestRectangleAreaOptimized(heights3));
        System.out.println();

        System.out.println("Heights 4: [0, 9]");
        System.out.println("Brute Force: " + largestRectangleAreaBruteForce(heights4));
        System.out.println("Optimized: " + largestRectangleAreaOptimized(heights4));
        System.out.println();
    }
}

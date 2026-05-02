package arrays;

/*
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 * 
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * 
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * 
 * Constraints:
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */

public class TrappingRainWater {

    // Brute Force Approach
    public static int trapBruteForce(int[] height) {
        int n = height.length;
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            int leftMax = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            int rightMax = 0;
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            int waterLevel = Math.min(leftMax, rightMax);
            if (waterLevel > height[i]) {
                totalWater += waterLevel - height[i];
            }
        }
        return totalWater;
    }

    // Optimized Approach
    public static int trapOptimized(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int totalWater = 0;

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                totalWater += leftMax - height[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                totalWater += rightMax - height[right];
            }
        }

        return totalWater;
    }

    public static void main(String[] args) {
        int[] height1 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int[] height2 = { 4, 2, 0, 3, 2, 5 };
        System.out.println("Brute Force: " + trapBruteForce(height1));
        System.out.println("Optimized: " + trapOptimized(height1));
        System.out.println("Brute Force: " + trapBruteForce(height2));
        System.out.println("Optimized: " + trapOptimized(height2));
    }
}

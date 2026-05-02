package arrays;

/*
 * Problem: Given an array of integers nums and an integer k, return the maximum
 * average value of a subarray of size k.
 * Example 1:
 * Input: nums = [1, 12, -5, -6, 50, 3], k = 4
 * Output: 12.75
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75.
 * Example 2:
 * Input: nums = [5], k = 1
 * Output: 5.0
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class MaximumAverageSubarrayI {

    // Brute force approach
    public static double maximumAverageSubarrayIBruteForce(int[] nums, int k) {
        double maxAverage = Double.NEGATIVE_INFINITY;
        for (int i = 0; i <= nums.length - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            double average = (double) sum / k;
            maxAverage = Math.max(maxAverage, average);
        }
        return maxAverage;
    }

    // Sliding window approach
    public static double maximumAverageSubarrayI(int[] nums, int k) {
        // Calculate the sum of the first k elements
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        // Initialize maxAverage with the average of the first k elements
        double maxAverage = (double) sum / k;

        // Slide the window through the rest of the array
        for (int i = k; i < nums.length; i++) {
            // Add the new element entering the window
            sum += nums[i];

            // Subtract the element leaving the window
            // The element leaving the window is at index i - k
            sum -= nums[i - k];

            // Calculate the average of the current window and update maxAverage if needed
            double currentAverage = (double) sum / k;
            if (currentAverage > maxAverage) {
                maxAverage = currentAverage;
            }
        }

        return maxAverage;
    }

    // Driver code
    public static void main(String[] args) {
        int[] nums = { 1, 12, -5, -6, 50, 3 };
        int k = 4;
        System.out.println(maximumAverageSubarrayI(nums, k)); // Output: 12.75
    }
}

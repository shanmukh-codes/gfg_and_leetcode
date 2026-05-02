/*  
 * Problem: Given a binary array nums and an integer k, return the maximum number of
 * consecutive 1's in the array if you can flip at most k 0's.
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation:
 * Flip the three 0's in the middle to 1: [1,1,1,0,0,0,1,1,1,1,0]
 * 
 * Time Complexity: O(n) when using sliding window, O(n^2) when using brute force
 * Space Complexity: O(1)
 */
package arrays;

public class MaxConsecutiveOnesIII {

    // Brute Force Approach
    public static int maxConsecutiveOnesByFlippingKZeroesBruteForce(int[] nums, int k) {
        int maxCount = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            if (nums[i] == 0) {
                nums[i] = 1;
                int currentCount = 0;
                for (int num : nums) {
                    currentCount = (num == 1) ? currentCount + 1 : 0;
                    maxCount = Math.max(maxCount, currentCount);
                }
                nums[i] = 0;
            }
        }
        if (maxCount == 0) {
            return n;
        }
        return maxCount;
    }

    // Optimal Approach
    public static int maxConsecutiveOnesByFlippingKZeroesOptimal(int[] nums, int k) {
        int maxCount = 0;
        int n = nums.length;
        int left = 0;
        int right = 0;
        int zeroCount = 0;
        while (right < n) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxCount = Math.max(maxCount, right - left + 1);
            right++;
        }
        return maxCount;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums = { 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1 };
        System.out.println("Max Consecutive Ones by Flipping K Zeroes (Brute Force): "
                + maxConsecutiveOnesByFlippingKZeroesBruteForce(nums, 1));
        System.out.println("Max Consecutive Ones by Flipping K Zeroes (Optimal): "
                + maxConsecutiveOnesByFlippingKZeroesOptimal(nums, 1));

        int[] nums2 = { 1, 0, 1, 1, 0, 1 };
        System.out.println("Max Consecutive Ones by Flipping K Zeroes (Brute Force): "
                + maxConsecutiveOnesByFlippingKZeroesBruteForce(nums2, 1));
        System.out.println("Max Consecutive Ones by Flipping K Zeroes (Optimal): "
                + maxConsecutiveOnesByFlippingKZeroesOptimal(nums2, 1));

        int[] nums3 = { 0, 0, 0, 0, 0 };
        System.out.println("Max Consecutive Ones by Flipping K Zeroes (Brute Force): "
                + maxConsecutiveOnesByFlippingKZeroesBruteForce(nums3, 1));
        System.out.println("Max Consecutive Ones by Flipping K Zeroes (Optimal): "
                + maxConsecutiveOnesByFlippingKZeroesOptimal(nums3, 1));
    }
}

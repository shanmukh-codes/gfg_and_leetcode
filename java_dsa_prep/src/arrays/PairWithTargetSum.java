/*
 * Given an array of integers nums (sorted in ascending order) and a target sum,
 * find if there exists a pair of numbers in the array that adds up to the target.
 * Return the indices of the pair, or -1 if no such pair is found.
 * 
 * Ex: nums = [1, 2, 3, 4, 6], target = 6 -> Output : [1, 2] (2 + 4 = 6)
 * 
 */
package arrays;

public class PairWithTargetSum {

    // Brute Force Approach
    public static int[] findPairBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i + 1, j + 1 };
                }
            }
        }
        return new int[] { -1, -1 };
    }

    // Optimized Approach
    public static int[] findPairOptimized(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return new int[] { left + 1, right + 1 };
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 6 };
        int target = 6;
        int[] result = findPairBruteForce(nums, target);
        System.out.println(java.util.Arrays.toString(result)); // Output: [1, 2]
        result = findPairOptimized(nums, target);
        System.out.println(java.util.Arrays.toString(result)); // Output: [1, 2]
    }
}

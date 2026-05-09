/*
 * Given an integer array nums, return the number of triplets [i, j, k] such
 * that i < j < k and nums[i] + nums[j] > nums[k].
 * 
 * Ex: nums = [2, 2, 3, 4] -> Output : 3
 * Explanation: [2, 2, 3], [2, 3, 4], [2, 2, 4]
 * 
 */
package arrays;

import java.util.Arrays;

public class ValidTriangleNumber {

    // Brute Force Approach
    public static int triangleNumberBruteForce(int[] nums) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // Optimized Approach
    public static int triangleNumber(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return 0;

        Arrays.sort(nums);
        int count = 0;

        // Fix the largest side c
        for (int i = n - 1; i >= 2; i--) {
            int c = nums[i];
            int left = 0;
            int right = i - 1;

            while (left < right) {
                // If a + b > c, then all elements from left to right-1 will also
                // satisfy the condition
                if (nums[left] + nums[right] > c) {
                    count += (right - left);
                    right--;
                } else {
                    // If a + b <= c, we need a larger 'a'
                    left++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 2, 3, 4 };
        System.out.println(triangleNumberBruteForce(nums)); // Output: 3
        System.out.println(triangleNumber(nums)); // Output: 3
    }

}

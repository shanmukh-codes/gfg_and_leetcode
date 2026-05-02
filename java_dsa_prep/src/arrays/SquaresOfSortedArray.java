/*
 * Problem: Given an array of integers nums sorted in non-decreasing order,
 * return an array of the squares of each number sorted in non-decreasing order.
 * Example: Input: nums = [-4,-1,0,3,10] Output: [0,1,9,16,100] 
 * Explanation: After squaring, the array becomes [-4^2,-1^2,0^2,3^2,10^2] = [16,1,0,9,100].
 * After sorting, the array becomes [0,1,9,16,100].
 * 
 * Time Complexity:
 * Brute Force: O(n log n)
 * Optimal: O(n)
 * 
 * Space Complexity:
 * Brute Force: O(1)
 * Optimal: O(n)
 */
package arrays;

import java.util.Arrays;

public class SquaresOfSortedArray {

    // Brute force approach
    public static int[] squaresOfSortedArrayBruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    // Optimal approach
    public static int[] squaresOfSortedArrayOptimal(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int index = n - 1;

        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;
            } else {
                result[index] = rightSquare;
                right--;
            }
            index--;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = { -4, -1, 0, 3, 10 };
        int[] result = squaresOfSortedArrayBruteForce(nums);
        System.out.println("Brute Force: " + Arrays.toString(result));

        int[] nums2 = { -4, -1, 0, 3, 10 };
        int[] result2 = squaresOfSortedArrayOptimal(nums2);
        System.out.println("Optimal: " + Arrays.toString(result2));
    }
}

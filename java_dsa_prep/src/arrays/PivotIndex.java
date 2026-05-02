package arrays;

/*
 * Problem: Given an array of integers nums, calculate the pivot index of this array.
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the right of the index.
 * If the array does not have a pivot index, then return -1. If there are multiple pivot indexes, return the left-most pivot index.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class PivotIndex {
    // Brute force approach
    public static int pivotIndexBruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int leftSum = 0;
            int rightSum = 0;

            for (int j = 0; j < i; j++) {
                leftSum += nums[j];
            }

            for (int j = i + 1; j < nums.length; j++) {
                rightSum += nums[j];
            }

            if (leftSum == rightSum) {
                return i;
            }
        }

        return -1;
    }

    // Optimal approach - Calculate total sum first. Then iterate through the array,
    // maintaining a left sum. For each element, check if leftSum equals rightSum.
    // If yes, return the index. else, update leftSum.
    public static int pivotIndexOptimal(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 7, 3, 6, 5, 6 };
        System.out.println("Pivot index: " + pivotIndexOptimal(nums1));

        int[] nums2 = { 1, 2, 3 };
        System.out.println("Pivot index: " + pivotIndexOptimal(nums2));

        int[] nums3 = { 2, 1, -1 };
        System.out.println("Pivot index: " + pivotIndexOptimal(nums3));
    }
}

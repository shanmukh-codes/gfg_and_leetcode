/*
 * Given an array nums of n integers such that 0 <= nums[i] <= 2.
 * Sort the array in place so that the first k elements are 0, the next k are 1,
 * and the last k are 2.
 * You must use constant extra space and O(n) time.
 * 
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * 
 * Example 2:
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 */
package arrays;

import java.util.Arrays;

public class SortColors {

    // Brute force approach
    public void sortColorsBruteForce(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                count0++;
            } else if (nums[i] == 1) {
                count1++;
            } else {
                count2++;
            }
        }

        for (int i = 0; i < count0; i++) {
            temp[i] = 0;
        }

        for (int i = count0; i < count0 + count1; i++) {
            temp[i] = 1;
        }

        for (int i = count0 + count1; i < n; i++) {
            temp[i] = 2;
        }

        for (int i = 0; i < n; i++) {
            nums[i] = temp[i];
        }
    }

    // Dutch National Flag algorithm
    public void sortColors(int[] nums) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        int mid = 0;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums1 = { 2, 0, 2, 1, 1, 0 };
        int[] nums2 = { 2, 0, 1 };

        SortColors sortColors = new SortColors();

        System.out.println("Array 1: " + Arrays.toString(nums1));
        sortColors.sortColors(nums1);
        System.out.println("Sorted: " + Arrays.toString(nums1));
        System.out.println();

        System.out.println("Array 2: " + Arrays.toString(nums2));
        sortColors.sortColors(nums2);
        System.out.println("Sorted: " + Arrays.toString(nums2));
        System.out.println();
    }
}

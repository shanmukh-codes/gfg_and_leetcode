package arrays;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSumII {
    /**
     * Given a 1-indexed array of integers numbers that is already sorted in
     * non-decreasing order, find two numbers such that they add up to a specific
     * target number. Let these two numbers be numbers[index1] and numbers[index2]
     * where 1 <= index1 < index2 <= numbers.length.
     * 
     * Return the two indices index1 and index2 in non-decreasing order as an
     * integer array [index1, index2].
     * 
     * The tests are generated such that there is exactly one solution.
     * 
     * You must not use the same element twice. Your solution must use only
     * constant extra space.
     * 
     * Example 1:
     * Input: numbers = [2,7,11,15], target = 9
     * Output: [1,2]
     * Explanation: The two numbers are 2 and 7. 2 + 7 = 9. The indices are 1 and 2.
     * 
     * Example 2:
     * Input: numbers = [2,3,4], target = 6
     * Output: [1,3]
     * Explanation: The two numbers are 2 and 4. 2 + 4 = 6. The indices are 1 and 3.
     * 
     * Example 3:
     * Input: numbers = [-1,0], target = -1
     * Output: [1,2]
     * Explanation: The two numbers are -1 and 0. -1 + 0 = -1. The indices are 1 and
     * 2.
     * 
     * Constraints:
     * 2 <= numbers.length <= 3 * 104
     * -1000 <= numbers[i] <= 1000
     * numbers is sorted in non-decreasing order.
     * -1000 <= target <= 1000
     * There is exactly one solution.
     * 
     * @param numbers the sorted array of integers
     * @param target  the target sum
     * @return the indices of the two numbers that add up to the target
     */

    // Brute Force approach
    // Time complexity: O(n^2)
    // Space complexity: O(1)
    public static int[] twoSumBruteForce(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                    return result;
                }
            }
        }
        return result;
    }

    // Optimized approach
    // Time complexity: O(n)
    // Space complexity: O(1)
    public static int[] twoSumOptimized(int[] numbers, int target) {
        int[] result = new int[2];
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int currentSum = numbers[left] + numbers[right];
            if (currentSum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                return result;
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    // Optimized approach - Using HashMap
    // Time complexity: O(n)
    // Space complexity: O(n)
    public static int[] twoSumOptimizedHashMap(int[] numbers, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            if (map.containsKey(complement)) {
                result[0] = map.get(complement) + 1;
                result[1] = i + 1;
                return result;
            }
            map.put(numbers[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = { 2, 7, 11, 15 };
        int target = 9;
        // Brute Force approach
        int[] result1 = twoSumBruteForce(numbers, target);
        System.out.println("Brute Force: " + Arrays.toString(result1));
        // Optimized approach
        int[] result2 = twoSumOptimized(numbers, target);
        System.out.println("Optimized: " + Arrays.toString(result2));
        // Optimized approach - Using HashMap
        int[] result3 = twoSumOptimizedHashMap(numbers, target);
        System.out.println("Optimized HashMap: " + Arrays.toString(result3));
    }
}

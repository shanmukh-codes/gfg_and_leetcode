/*
 Problem: Max Consecutive Ones I (LeetCode 485)

 You are given a binary array (an array of 0s and 1s). Your task is to find the maximum number of consecutive 1s in the array.

 Example 1:
 Input: nums = [1,1,0,1,1,1]
 Output: 3
 Explanation: There are two groups of consecutive ones. The first group has length 2, and the second group has length 3. The maximum length is 3.

 Example 2:
 Input: nums = [1,0,1,1,0,1]
 Output: 2

 Example 3:
 Input: nums = [0,0,0,0]
 Output: 0

 Constraints:
 - The length of the input array can be between 1 and 10^5.
 - Each element in the array is either 0 or 1.
*/

package arrays;

public class MaxConsecutiveOnesI {
    // Brute force approach
    public static int maxConsecutiveOnesBruteForce(int[] nums) {
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentCount = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 1) {
                    currentCount++;
                } else {
                    break;
                }
            }
            if (currentCount > maxCount) {
                maxCount = currentCount;
            }
        }
        return maxCount;
    }

    // Optimal approach
    public static int maxConsecutiveOnesOptimal(int[] nums) {
        int maxCount = 0;
        int currentCount = 0;
        for (int num : nums) {
            currentCount = num == 1 ? currentCount + 1 : 0;
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }

    // Example Usage
    public static void main(String[] args) {
        int[] nums1 = { 1, 1, 0, 1, 1, 1 };
        System.out.println(
                "Max consecutive ones in " + java.util.Arrays.toString(nums1) + " is: "
                        + maxConsecutiveOnesBruteForce(nums1));
        System.out.println("Max consecutive ones in " + java.util.Arrays.toString(nums1) + " is: "
                + maxConsecutiveOnesOptimal(nums1));
    }
}

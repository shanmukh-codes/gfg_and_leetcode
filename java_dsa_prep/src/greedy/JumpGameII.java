/* 
 * Jump Game II (LeetCode 45)
 * 
 * Problem Description:
 * You are given a 0-indexed array of integers nums of length n. You are initially
 * positioned at index 0.
 * 
 * Each element nums[i] represents the maximum length of a forward jump from
 * index i. In other words, if you are at index i, you can jump to any index
 * (i + j) where: 0 <= j <= nums[i] and i + j < n
 * 
 * Return the minimum number of jumps to reach index n - 1. The test cases are
 * generated such that you can reach index n - 1.
 * 
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Constraints:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 * It's guaranteed that you can reach nums[n - 1].
 * 
 * Approach:
 * We use a greedy approach similar to BFS level order traversal.
 * We maintain 'currentReach' (the farthest we can reach with current jumps)
 * and 'maxReach' (the farthest we can reach with one more jump).
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
package greedy;

public class JumpGameII {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int jumps = 0;
        int currentReach = 0; // Farthest index reachable with current number of jumps
        int maxReach = 0; // Farthest index reachable with one more jump

        // Iterate up to the second to last element
        for (int i = 0; i < n - 1; i++) {
            // Update the farthest we can reach from the current position
            maxReach = Math.max(maxReach, i + nums[i]);

            // If we have reached the end of the current jump's reach
            if (i == currentReach) {
                jumps++; // Increment the jump count
                currentReach = maxReach; // Update the reach for the next jump

                // If currentReach has reached or surpassed the end, we are done
                if (currentReach >= n - 1) {
                    break;
                }
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        JumpGameII solution = new JumpGameII();

        // Test cases
        int[] nums1 = { 2, 3, 1, 1, 4 };
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = [2, 3, 1, 1, 4]");
        System.out.println("Minimum jumps: " + solution.jump(nums1)); // Expected: 2
        System.out.println("--------------------");

        int[] nums2 = { 2, 3, 0, 1, 4 };
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = [2, 3, 0, 1, 4]");
        System.out.println("Minimum jumps: " + solution.jump(nums2)); // Expected: 2
        System.out.println("--------------------");

        int[] nums3 = { 1, 1, 1, 1, 1 };
        System.out.println("Test Case 3:");
        System.out.println("Input: nums = [1, 1, 1, 1, 1]");
        System.out.println("Minimum jumps: " + solution.jump(nums3)); // Expected: 4
        System.out.println("--------------------");

        int[] nums4 = { 1 };
        System.out.println("Test Case 4:");
        System.out.println("Input: nums = [1]");
        System.out.println("Minimum jumps: " + solution.jump(nums4)); // Expected: 0
        System.out.println("--------------------");

        int[] nums5 = { 5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0 };
        System.out.println("Test Case 5:");
        System.out.println("Input: nums = [5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0]");
        System.out.println("Minimum jumps: " + solution.jump(nums5)); // Expected: 3
        System.out.println("--------------------");
    }

}

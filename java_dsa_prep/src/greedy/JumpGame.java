// Java program for Jump Game (LeetCode 55)
package greedy;

public class JumpGame {

    // Main function to check if the end can be reached
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int n = nums.length;
        int maxReach = 0; // Keeps track of the farthest index we can reach so far

        for (int i = 0; i < n; i++) {
            // If the current index 'i' is beyond the farthest we could reach,
            // it means we got stuck somewhere before this index.
            if (i > maxReach) {
                return false;
            }

            // Update the farthest reachable index from the current position
            maxReach = Math.max(maxReach, i + nums[i]);

            // Optimization: If maxReach is already at or beyond the last index, we are done
            if (maxReach >= n - 1) {
                return true;
            }
        }

        // This line is technically redundant if the optimization above is used,
        // but good for clarity if the loop finishes without returning.
        return true;
    }

    // Backward greedy approach
    public static boolean canJumpBackward(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int n = nums.length;
        int goal = n - 1; // Start from the last index

        // Iterate backwards from the second to last index
        for (int i = n - 2; i >= 0; i--) {
            // If we can reach the current goal from index 'i'
            if (i + nums[i] >= goal) {
                goal = i; // Update the goal to 'i'
            }
        }

        // If the goal is the first index, we can reach the end
        return goal == 0;
    }

    public static void main(String[] args) {
        // Test cases
        int[] nums1 = { 2, 3, 1, 1, 4 };
        System.out.println("Test Case 1: Can jump? " + canJump(nums1)); // Expected: true

        int[] nums2 = { 3, 2, 1, 0, 4 };
        System.out.println("Test Case 2: Can jump? " + canJump(nums2)); // Expected: false

        int[] nums3 = { 0 };
        System.out.println("Test Case 3: Can jump? " + canJump(nums3)); // Expected: true

        int[] nums4 = { 1, 1, 0, 1 };
        System.out.println("Test Case 4: Can jump? " + canJump(nums4)); // Expected: false

        int[] nums5 = { 2, 0, 0 };
        System.out.println("Test Case 5: Can jump? " + canJump(nums5)); // Expected: true
    }
}

package arrays;

/*
 * Problem Statement: 
 * Let's define a "mountain" array as a subarray of length >= 3 that first strictly increases
 * and then strictly decreases. 
 * 
 * Given an array of integers, your task is to find the length of the longest "mountain"
 * subarray. If no mountain exists, return 0.
 * 
 * Examples:
 * Input: [2, 1, 4, 7, 3, 2, 5]
 * Output: 5 (The longest mountain is [1, 4, 7, 3, 2])
 * 
 * Input: [2, 2, 2]
 * Output: 0 (Not strictly increasing/decreasing)
 * 
 * Input: [1, 2, 3]
 * Output: 0 (Only increasing, no decrease)
 */
public class LongestMountainInArray {

    // Optimal Approach (One Pass)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int longestMountain(int[] arr) {
        int N = arr.length;
        int maxLen = 0;

        // We need at least 3 elements to form a mountain
        if (N < 3) {
            return 0;
        }
        int i = 1;
        while (i < N) {
            int up = 0;
            int down = 0;

            while (i < N && arr[i] > arr[i - 1]) {
                up++;
                i++;
            }

            while (i < N && arr[i] < arr[i - 1]) {
                down++;
                i++;
            }

            if (up > 0 && down > 0) {
                maxLen = Math.max(maxLen, up + down + 1);
            }

            while (i < N && arr[i] == arr[i - 1]) {
                i++;
            }

        }

        return maxLen;
    }

    // Alternative Brute Force Approach (for understanding)
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static int longestMountainBruteForce(int[] arr) {
        int N = arr.length;
        int maxLen = 0;

        for (int i = 1; i < N - 1; i++) {
            // Check if 'i' is a peak
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                // Expand left
                int left = i - 1;
                while (left > 0 && arr[left] > arr[left - 1]) {
                    left--;
                }

                // Expand right
                int right = i + 1;
                while (right < N - 1 && arr[right] > arr[right + 1]) {
                    right++;
                }

                int currentLen = right - left + 1;
                maxLen = Math.max(maxLen, currentLen);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr1 = { 2, 1, 4, 7, 3, 2, 5 };
        System.out.println("Longest Mountain (Optimal): " + longestMountain(arr1)); // Output: 5
        System.out.println("Longest Mountain (Brute Force): " + longestMountainBruteForce(arr1)); // Output: 5

        int[] arr2 = { 2, 2, 2 };
        System.out.println("Longest Mountain (Optimal): " + longestMountain(arr2)); // Output: 0

        int[] arr3 = { 0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };
        System.out.println("Longest Mountain (Optimal): " + longestMountain(arr3)); // Output: 11
    }
}

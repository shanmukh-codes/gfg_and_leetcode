package heaps;

/*
 * LeetCode 215: Kth Largest Element in an Array
 * 
 * Problem Description:
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 
 * Example 1:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * 
 * Example 2:
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * 
 * Constraints:
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 
 * Approach: Min-Heap (Priority Queue)
 * We can maintain a min-heap of size k. Iterate through the array:
 * 1. Add the current element to the heap
 * 2. If the heap size exceeds k, remove the smallest element (heap's root)
 * After iterating through all elements, the root of the heap will be the kth largest element.
 * 
 * Time Complexity: O(n log k)
 * - We process n elements
 * - Each heap operation (add/poll) takes O(log k) time since the heap size is at most k
 * 
 * Space Complexity: O(k)
 * - To store the k largest elements in the heap
 */

import java.util.PriorityQueue;

public class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid input");
        }

        // Create a min-heap. In Java, PriorityQueue is a min-heap by default.
        // We want to keep the k largest elements, so we use a min-heap of size k.
        // The smallest element in this heap (the root) will be the kth largest overall.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        // Iterate through all the numbers in the array
        for (int num : nums) {
            // Add the current number to the heap
            minHeap.add(num);

            // If the heap size becomes greater than k, remove the smallest element
            // This ensures we only keep the k largest elements seen so far
            if (minHeap.size() > k) {
                minHeap.poll(); // Removes the root (smallest element)
            }
        }

        // After processing all numbers, the root of the min-heap is the kth largest
        // element
        return minHeap.peek();
    }
}

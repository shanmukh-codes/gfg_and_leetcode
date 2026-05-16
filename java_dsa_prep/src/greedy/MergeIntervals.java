/* 
 * Merge Intervals (LeetCode 56)
 * 
 * Problem Description:
 * Given an array of intervals where intervals[i] = [start, end], merge all 
 * overlapping intervals, and return an array of the non-overlapping intervals 
 * that cover all the intervals in the input.
 * 
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * 
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * 
 * Constraints:
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= start <= end <= 10^4
 * 
 * Approach:
 * 1. Sort the intervals based on their start times.
 * 2. Initialize a result list with the first interval.
 * 3. Iterate through the sorted intervals and merge if overlapping:
 *    - If the current interval's start is less than or equal to the last 
 *      merged interval's end, they overlap. Merge them by updating the end 
 *      of the last merged interval.
 *    - Otherwise, add the current interval to the result list.
 * 
 * Time Complexity: O(n log n) due to sorting
 * Space Complexity: O(n) for the result list (or O(1) if modifying in-place, 
 *                     but usually a new list is returned)
 */
package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 1. Sort intervals based on start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        // Initialize with the first interval
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        // 2. Iterate and merge
        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            // Check for overlap: current start <= last end
            if (nextStart <= currentEnd) {
                // Overlap exists, merge them
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // No overlap, add current interval to result
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();

        // Test Case 1
        int[][] intervals1 = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        int[][] result1 = solution.merge(intervals1);
        System.out.println("Test Case 1:");
        printIntervals(result1);
        // Expected: [[1,6],[8,10],[15,18]]
        System.out.println("---------------------");

        // Test Case 2
        int[][] intervals2 = { { 1, 4 }, { 4, 5 } };
        int[][] result2 = solution.merge(intervals2);
        System.out.println("Test Case 2:");
        printIntervals(result2);
        // Expected: [[1,5]]
        System.out.println("---------------------");

        // Test Case 3
        int[][] intervals3 = { { 1, 4 }, { 0, 4 } };
        int[][] result3 = solution.merge(intervals3);
        System.out.println("Test Case 3:");
        printIntervals(result3);
        // Expected: [[0,4]]
        System.out.println("---------------------");

        // Test Case 4
        int[][] intervals4 = { { 1, 4 }, { 2, 3 } };
        int[][] result4 = solution.merge(intervals4);
        System.out.println("Test Case 4:");
        printIntervals(result4);
        // Expected: [[1,4]]
        System.out.println("---------------------");
    }

    // Helper function to print intervals
    private static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
            if (i < intervals.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}

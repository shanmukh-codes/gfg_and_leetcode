/* 
 * Insert Interval (LeetCode 57)
 * 
 * Problem Description:
 * You are given a set of non-overlapping intervals with values [a, b] sorted by their start values. 
 * You are also given a new interval [c, d]. Insert the new interval into the set such that the 
 * resulting set is also sorted and non-overlapping. If the new interval overlaps with one 
 * or more existing intervals, merge them into a single interval.
 * 
 * Example 1:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Merge [3,5],[6,7],[8,10] into a single interval [3,10]
 * 
 * Constraints:
 * 0 <= intervals.length <= 3 * 10^4
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 10^9
 * intervals is sorted by intervals[i][0] in ascending order.
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 10^9
 * 
 * Approach:
 * Since the intervals are sorted, we can use a greedy approach with three phases:
 * 1. Add all intervals that end before the new interval starts (no overlap).
 * 2. Merge overlapping intervals by updating the new interval's bounds.
 * 3. Add all intervals that start after the new interval ends (no overlap).
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) (for the result list, or O(1) if modifying in-place, 
 *                     but usually a new list is returned)
 */
package greedy;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][] { newInterval };
        }

        List<int[]> result = new ArrayList<>();
        int n = intervals.length;
        int i = 0;
        int newStart = newInterval[0];
        int newEnd = newInterval[1];

        // Phase 1: Add all intervals that end before the new interval starts
        while (i < n && intervals[i][1] < newStart) {
            result.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge overlapping intervals
        // Overlap condition: interval starts before new interval ends
        while (i < n && intervals[i][0] <= newEnd) {
            newStart = Math.min(newStart, intervals[i][0]);
            newEnd = Math.max(newEnd, intervals[i][1]);
            i++;
        }
        result.add(new int[] { newStart, newEnd });

        // Phase 3: Add all intervals that start after the new interval ends
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        InsertInterval solution = new InsertInterval();

        // Test Case 1
        int[][] intervals1 = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        int[] newInterval1 = { 4, 8 };
        int[][] result1 = solution.insert(intervals1, newInterval1);
        System.out.println("Test Case 1:");
        printIntervals(result1);
        // Expected: [[1,2],[3,10],[12,16]]
        System.out.println("---------------------");

        // Test Case 2
        int[][] intervals2 = { { 1, 3 }, { 6, 9 } };
        int[] newInterval2 = { 2, 5 };
        int[][] result2 = solution.insert(intervals2, newInterval2);
        System.out.println("Test Case 2:");
        printIntervals(result2);
        // Expected: [[1,5],[6,9]]
        System.out.println("---------------------");

        // Test Case 3
        int[][] intervals3 = { { 1, 5 } };
        int[] newInterval3 = { 2, 3 };
        int[][] result3 = solution.insert(intervals3, newInterval3);
        System.out.println("Test Case 3:");
        printIntervals(result3);
        // Expected: [[1,5]]
        System.out.println("---------------------");

        // Test Case 4: No overlap, insert at beginning
        int[][] intervals4 = { { 3, 5 }, { 6, 9 } };
        int[] newInterval4 = { 1, 2 };
        int[][] result4 = solution.insert(intervals4, newInterval4);
        System.out.println("Test Case 4:");
        printIntervals(result4);
        // Expected: [[1,2],[3,5],[6,9]]
        System.out.println("---------------------");

        // Test Case 5: No overlap, insert at end
        int[][] intervals5 = { { 1, 2 }, { 3, 5 } };
        int[] newInterval5 = { 6, 8 };
        int[][] result5 = solution.insert(intervals5, newInterval5);
        System.out.println("Test Case 5:");
        printIntervals(result5);
        // Expected: [[1,2],[3,5],[6,8]]
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

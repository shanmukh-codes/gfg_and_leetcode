/*
 * Leetcode 252. Meeting Rooms
 * 
 * 
 */

package greedy;

import java.util.Arrays;

public class MeetingRoomsI {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        // Sort intervals based on start times
        // This is the key greedy choice: process meetings in chronological order
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Check for overlaps
        for (int i = 0; i < intervals.length - 1; i++) {
            int currentEnd = intervals[i][1];
            int nextStart = intervals[i + 1][0];

            // If the current meeting ends after the next one starts, there's an overlap
            if (currentEnd > nextStart) {
                return false;
            }
        }

        return true;
    }
}

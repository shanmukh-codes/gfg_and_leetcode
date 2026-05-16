
package greedy;

import java.util.ArrayList;
import java.util.Arrays;

public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        // Sort by height in descending order, then by k in ascending order
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else {
                return a[1] - b[1];
            }
        });
        ArrayList<int[]> res = new ArrayList<>();

        // Insert each person into the result list at their specified position
        for (int[] person : people) {
            res.add(person[1], person);
        }

        return res.toArray(new int[people.length][]);
    }
}

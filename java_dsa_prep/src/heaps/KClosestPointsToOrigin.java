package heaps;

import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        // Use a max-heap to keep track of the k closest points found so far.
        // The heap stores pairs of [distance, index].
        // We use a max-heap so that the point with the largest distance is at the top
        // and can be easily removed if we find a closer point.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            int distA = a[0] * a[0] + a[1] * a[1];
            int distB = b[0] * b[0] + b[1] * b[1];
            return distB - distA; // Max-heap
        });

        // Iterate through all points
        for (int i = 0; i < points.length; i++) {
            maxHeap.offer(points[i]);

            // If the heap size exceeds k, remove the point with the largest distance
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Extract the k closest points from the heap
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin solution = new KClosestPointsToOrigin();

        // Test Case 1
        int[][] points1 = { { 1, 3 }, { -2, 2 } };
        int k1 = 1;
        int[][] result1 = solution.kClosest(points1, k1);
        System.out.println("Test Case 1:");
        System.out.println("Input: points = " + java.util.Arrays.deepToString(points1) + ", k = " + k1);
        System.out.println("Output: " + java.util.Arrays.deepToString(result1));
        // Expected: [[-2,2]]
        System.out.println("---------------------");

        // Test Case 2
        int[][] points2 = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
        int k2 = 2;
        int[][] result2 = solution.kClosest(points2, k2);
        System.out.println("Test Case 2:");
        System.out.println("Input: points = " + java.util.Arrays.deepToString(points2) + ", k = " + k2);
        System.out.println("Output: " + java.util.Arrays.deepToString(result2));
        // Expected: [[3,3],[ -2,4]] in some order
        System.out.println("---------------------");

        // Test Case 3: All points at same distance
        int[][] points3 = { { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
        int k3 = 2;
        int[][] result3 = solution.kClosest(points3, k3);
        System.out.println("Test Case 3:");
        System.out.println("Input: points = " + java.util.Arrays.deepToString(points3) + ", k = " + k3);
        System.out.println("Output: " + java.util.Arrays.deepToString(result3));
        // Expected: Any 2 points, as all have distance sqrt(2)
        System.out.println("---------------------");

        // Test Case 4: Large coordinates
        int[][] points4 = { { 100, 100 }, { 1, 1 }, { 50, 50 } };
        int k4 = 1;
        int[][] result4 = solution.kClosest(points4, k4);
        System.out.println("Test Case 4:");
        System.out.println("Input: points = " + java.util.Arrays.deepToString(points4) + ", k = " + k4);
        System.out.println("Output: " + java.util.Arrays.deepToString(result4));
        // Expected: [[1,1]]
        System.out.println("---------------------");

        // Test Case 5: k equals number of points
        int[][] points5 = { { 1, 0 }, { 0, 1 }, { 2, 2 } };
        int k5 = 3;
        int[][] result5 = solution.kClosest(points5, k5);
        System.out.println("Test Case 5:");
        System.out.println("Input: points = " + java.util.Arrays.deepToString(points5) + ", k = " + k5);
        System.out.println("Output: " + java.util.Arrays.deepToString(result5));
        // Expected: All points in some order
        System.out.println("---------------------");
    }
}

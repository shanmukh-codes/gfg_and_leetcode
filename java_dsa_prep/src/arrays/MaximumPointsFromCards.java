package arrays;

/*
 * Problem: There are several cards arranged in a row, and each card has an
 * associated number of points. You are given an integer array cardPoints and an
 * integer k. In one step, you can take one card from the beginning or from the
 * end of the row. You have to take exactly k cards. Return the maximum score
 * you can obtain.
 * Example 1:
 * Input: cardPoints = [1, 2, 3, 4, 5, 6, 1], k = 3
 * Output: 12
 * Explanation: Take the last three cards and add them to your score.
 * 6 + 5 + 1 = 12.
 * Example 2:
 * Input: cardPoints = [2, 2, 2], k = 2
 * Output: 4
 * Explanation: Take the first two cards and add them to your score. 2 + 2 = 4.
 *
 * Time Complexity: O(k)
 * Space Complexity: O(1)
 */

public class MaximumPointsFromCards {

    // Brute force approach
    public static int maxScoreBruteForce(int[] cardPoints, int k) {
        int maxScore = Integer.MIN_VALUE;
        for (int i = 0; i <= k; i++) {
            int currentScore = 0;
            // Take i cards from the left
            for (int j = 0; j < i; j++) {
                currentScore += cardPoints[j];
            }
            // Take k - i cards from the right
            for (int j = 0; j < k - i; j++) {
                currentScore += cardPoints[cardPoints.length - 1 - j];
            }
            if (currentScore > maxScore) {
                maxScore = currentScore;
            }
        }
        return maxScore;
    }

    // Sliding window approach
    public static int maxScoreOptimal(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int currentScore = 0;

        // Calculate the sum of the first k elements (taking from the left)
        for (int i = 0; i < k; i++) {
            currentScore += cardPoints[i];
        }

        int maxScore = currentScore;
        int leftIndex = k - 1;
        int rightIndex = n - 1;

        // Slide the window from left to right
        // We remove the rightmost element from the left sum and add the corresponding
        // element from the right
        for (int i = 0; i < k; i++) {
            // Remove the element at index i from the left sum
            currentScore -= cardPoints[leftIndex];
            currentScore += cardPoints[rightIndex];
            leftIndex--;
            rightIndex--;

            // Update the maximum score found so far
            if (currentScore > maxScore) {
                maxScore = currentScore;
            }
        }

        return maxScore;
    }

    // Driver code
    public static void main(String[] args) {
        int[] cardPoints1 = { 1, 2, 3, 4, 5, 6, 1 };
        int k1 = 3;
        System.out.println(maxScoreOptimal(cardPoints1, k1)); // Output: 12

        int[] cardPoints2 = { 2, 2, 2 };
        int k2 = 2;
        System.out.println(maxScoreOptimal(cardPoints2, k2)); // Output: 4

        int[] cardPoints3 = { 9, 7, 7, 9, 7, 7, 9 };
        int k3 = 7;
        System.out.println(maxScoreOptimal(cardPoints3, k3)); // Output: 65
    }
}

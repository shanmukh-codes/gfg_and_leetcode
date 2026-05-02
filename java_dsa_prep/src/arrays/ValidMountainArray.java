package arrays;

/*
 * Problem: Given an array of integers arr, return true if and only if it is a
 * valid mountain array.
 * 
 * Recall that arr is a mountain array if and only if:
 * 
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ValidMountainArray {

    // Brute force approach

    // Optimal approach
    public static boolean isValidMountainArrayOptimal(int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return false;
        }

        int left = 0;
        int right = n - 1;

        while (left < right && arr[left] < arr[left + 1]) {
            left++;
        }
        while (left < right && arr[right] < arr[right - 1]) {
            right--;
        }

        boolean metAtPeak = (left == right);
        boolean notAtStart = (left > 0);
        boolean notAtEnd = (right < n - 1);

        return metAtPeak && notAtStart && notAtEnd;
    }

    public static void main(String[] args) {
        int[] arr1 = { 2, 1 };
        // System.out.println("Array: " + java.util.Arrays.toString(arr1) + " is valid
        // mountain array: "
        // + isValidMountainArrayBruteForce(arr1));

        int[] arr2 = { 3, 5, 5 };
        // System.out.println("Array: " + java.util.Arrays.toString(arr2) + " is valid
        // mountain array: "
        // + isValidMountainArrayBruteForce(arr2));

        int[] arr3 = { 0, 3, 2, 1 };
        // System.out.println("Array: " + java.util.Arrays.toString(arr3) + " is valid
        // mountain array: "
        // + isValidMountainArrayBruteForce(arr3));

        System.out.println("Optimal: " + isValidMountainArrayOptimal(arr1));
        System.out.println("Optimal: " + isValidMountainArrayOptimal(arr2));
        System.out.println("Optimal: " + isValidMountainArrayOptimal(arr3));
    }
}

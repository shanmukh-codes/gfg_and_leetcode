/*
 * Given a non-negative integer c, find if there are two integers a and b
 * such that
 * a^2 + b^2 = c.
 * 
 * Ex: c = 5 -> Output : true (1^2 + 2^2 = 5)
 * Ex: c = 3 -> Output : false
 * 
 */
package arrays;

public class SumOfSquareNumbers {

    // Brute Force Approach
    public static boolean isSquareSumBruteForce(int c) {
        for (int a = 0; a * a <= c; a++) {
            for (int b = 0; b * b <= c; b++) {
                if (a * a + b * b == c)
                    return true;
            }
        }
        return false;
    }

    // Optimized Approach
    public static boolean isSquareSum(int c) {
        if (c < 0)
            return false;

        long left = 0;
        long right = (long) Math.sqrt(c);

        while (left <= right) {
            long sum = left * left + right * right;
            if (sum == c)
                return true;
            else if (sum < c)
                left++;
            else
                right--;
        }
        return false;
    }

    public static void main(String[] args) {
        int c = 5;
        System.out.println(isSquareSumBruteForce(c)); // Output: true
        System.out.println(isSquareSum(c)); // Output: true
    }

}

package strings;

public class StringCompression {
    public int compress(char[] chars) {
        int writeIndex = 0;
        int readIndex = 0;
        int n = chars.length;

        while (readIndex < n) {
            char currentChar = chars[readIndex];
            int count = 0;

            // Count consecutive characters
            while (readIndex < n && chars[readIndex] == currentChar) {
                count++;
                readIndex++;
            }

            // Write the character
            chars[writeIndex] = currentChar;
            writeIndex++;

            // Write the count if greater than 1
            if (count > 1) {
                String countStr = String.valueOf(count);
                for (int i = 0; i < countStr.length(); i++) {
                    chars[writeIndex] = countStr.charAt(i);
                    writeIndex++;
                }
            }
        }

        return writeIndex;
    }

    public static void main(String[] args) {
        StringCompression solution = new StringCompression();

        char[] chars1 = { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };
        int len1 = solution.compress(chars1);
        System.out.print("Compressed length: " + len1 + ", Result: ");
        for (int i = 0; i < len1; i++) {
            System.out.print(chars1[i]);
        }
        System.out.println(); // Output: 6, Result: a2b2c3

        char[] chars2 = { 'a' };
        int len2 = solution.compress(chars2);
        System.out.print("Compressed length: " + len2 + ", Result: ");
        for (int i = 0; i < len2; i++) {
            System.out.print(chars2[i]);
        }
        System.out.println(); // Output: 1, Result: a

        char[] chars3 = { 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' };
        int len3 = solution.compress(chars3);
        System.out.print("Compressed length: " + len3 + ", Result: ");
        for (int i = 0; i < len3; i++) {
            System.out.print(chars3[i]);
        }
        System.out.println(); // Output: 4, Result: a1b12
    }
}

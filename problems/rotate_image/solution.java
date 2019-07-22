class Solution {
public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) 
            rotateSquare(i, matrix);
    }

    private static void rotateSquare(int i, int[][] matrix) {
        for (int col = i, offset = 0; col < matrix.length - i - 1; col++, offset++) {
            int pix1 = matrix[i][i + offset];
            int pix2 = matrix[i + offset][matrix.length - i - 1];
            int pix3 = matrix[matrix.length - i - 1][matrix.length - i - 1 - offset];
            int pix4 = matrix[matrix.length - i - 1 - offset][i];
            matrix[i][i + offset] = pix4;
            matrix[i + offset][matrix.length - i - 1] = pix1;
            matrix[matrix.length - i - 1][matrix.length - i - 1 - offset] = pix2;
            matrix[matrix.length - i - 1 - offset][i] = pix3;
            
        }
    }
}
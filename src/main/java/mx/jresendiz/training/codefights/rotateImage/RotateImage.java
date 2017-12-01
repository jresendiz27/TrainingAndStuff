package mx.jresendiz.training.codefights.rotateImage;

/**
 * Created by jresendiz on 22/11/17.
 */
public class RotateImage {
    int[][] rotateImage(int[][] originalImage) {
        int length = originalImage.length - 1;
        for (int i = 0; i <= (length) / 2; i++) {
            for (int j = i; j < length - i; j++) {
                int p1 = originalImage[i][j];
                int p2 = originalImage[j][length - i];
                int p3 = originalImage[length - i][length - j];
                int p4 = originalImage[length - j][i];
                originalImage[j][length - i] = p1;
                originalImage[length - i][length - j] = p2;
                originalImage[length - j][i] = p3;
                originalImage[i][j] = p4;
            }
        }
        return originalImage;
    }
}

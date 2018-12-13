package mx.jresendiz.training.exercises.leftRotation;

public class LeftRotation {
    public static String rotate(int numberOfRotations, int[] elements) {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < numberOfRotations; j++) {
            for (int i = 0; i < elements.length - 1; i++) {
                int aux = elements[i];
                elements[i] = elements[i + 1];
                elements[i + 1] = aux;
            }
        }

        for (int i = 0; i < elements.length; i++) {
            result.append(elements[i]);
            if (i != (elements.length) - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }
}

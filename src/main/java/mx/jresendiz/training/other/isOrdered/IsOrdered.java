package mx.jresendiz.training.other.isOrdered;

public class IsOrdered {
    public static boolean solve(int[] array) {
        boolean isOrdered = true;
        if (array != null) {
            if (array.length <= 2) {
                return true;
            } else {
                int firstElement = array[0];
                int secondElement = array[1];
                boolean isUpward = firstElement < secondElement;
                for (int index = 1; index < array.length - 1 && isOrdered; index++) {
                    firstElement = array[index];
                    secondElement = array[index + 1];
                    if (isUpward) {
                        isOrdered = firstElement < secondElement;
                    } else {
                        isOrdered = !(firstElement < secondElement);
                    }
                }
                return isOrdered;
            }
        } else {
            return false;
        }
    }
}

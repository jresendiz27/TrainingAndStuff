package mx.jresendiz.training.exercises.primality;

public class Primality {
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        } else if (number <= 3) {
            return true;
        } else if ((number % 2 == 0) || (number % 3 == 0)) {
            return false;
        } else {
            for (int i = 3; i <= Math.ceil(Math.sqrt(number)); i += 2) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
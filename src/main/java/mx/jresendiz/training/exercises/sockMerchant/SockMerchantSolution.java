package mx.jresendiz.training.exercises.sockMerchant;

import java.util.HashMap;

public class SockMerchantSolution {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        HashMap<Integer, Integer> socks = new HashMap<>();
        int pairOfSocks = 0;
        for (int idx = 0; idx < n; idx++) {
            Integer numberOfSocks = socks.getOrDefault(ar[idx], 0);
            socks.put(ar[idx], numberOfSocks + 1);
        }
        for (Integer idx : socks.keySet()) {
            Integer value = socks.get(idx);
            if (value <= 1) continue;
            if (value % 2 == 0) {
                pairOfSocks += value / 2;
            } else {
                pairOfSocks += (value - 1) / 2;
            }
        }
        return pairOfSocks;
    }
}


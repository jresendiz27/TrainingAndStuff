package mx.jresendiz.training.exercises.firstDuplicate;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jresendiz on 22/11/17.
 */
public class FirstDuplicate {

    public int firstDuplicate(List<Integer> list) {
        HashMap<Integer, Integer> integerMap = new HashMap<>();
        for (Integer number : list) {
            if (integerMap.get(number) != null) {
                return number;
            } else {
                integerMap.put(number, 1);
            }
        }
        return -1;
    }
}

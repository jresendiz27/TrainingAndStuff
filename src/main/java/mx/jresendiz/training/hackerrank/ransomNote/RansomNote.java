package mx.jresendiz.training.hackerrank.ransomNote;

import java.util.HashMap;

public class RansomNote {
    private HashMap<String, Integer> magazineMap;
    private boolean isValid = true;

    public RansomNote(String magazine, String note) {
        magazineMap = new HashMap<>();
        for (String word : magazine.split(" ")) {
            if (magazineMap.containsKey(word)) {
                Integer count = magazineMap.get(word);
                count += 1;
                magazineMap.put(word, count);
            } else {
                magazineMap.put(word, 1);
            }
        }

        for (String word : note.split(" ")) {
            Integer count = magazineMap.get(word);
            if (count == null || count == 0) {
                isValid = false;
                break;
            } else {
                magazineMap.put(word, count - 1);
            }
        }
    }

    public boolean solve() {
        return isValid;
    }
}

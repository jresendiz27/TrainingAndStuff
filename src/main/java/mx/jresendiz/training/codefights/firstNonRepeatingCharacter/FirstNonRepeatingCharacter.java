package mx.jresendiz.training.codefights.firstNonRepeatingCharacter;

import java.util.LinkedHashMap;

/**
 * Created by jresendiz on 22/11/17.
 */
public class FirstNonRepeatingCharacter {
    char firstNonRepeatingCharacter(String string) {
        LinkedHashMap<Character, Integer> characterMap = new LinkedHashMap<>();
        for (Character character : string.toCharArray()) {
            if (characterMap.get(character) == null) {
                characterMap.put(character, 1);
            } else {
                Integer value = characterMap.get(character) + 1;
                characterMap.put(character, value);
            }
        }
        for (Object key : characterMap.keySet()) {
            if (characterMap.get((Character) key) == 1) {
                return (Character) key;
            }
        }
        return '_';
    }
}
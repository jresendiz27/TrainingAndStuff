package mx.jresendiz.training.codefights.sudoku2;

import java.util.HashSet;
import java.util.Set;

public class Sudoku2 {
    static boolean isValid(char[][] sudoku) {
        assert sudoku.length == sudoku[0].length;

        boolean isValid = true;
        Set<Character> elements = new HashSet<>();
        int sudokuSize = sudoku.length;
        // Checking rows
        for (int i = 0; i < sudokuSize && isValid; i++) {
            for (int j = 0; j < sudokuSize && isValid; j++) {
                isValid = hasValidElements(sudoku[i][j], elements);
            }
            elements.clear();
        }
        // Checkking columns
        for (int i = 0; i < sudokuSize && isValid; i++) {
            for (int j = 0; j < sudokuSize && isValid; j++) {
                isValid = hasValidElements(sudoku[j][i], elements);
            }
            elements.clear();
        }
        // Checking squares
        for (int i = 0; i < sudokuSize && isValid; i += 3) {
            for (int j = i; j < 3 && isValid; j++) {
                for (int k = i; k < 3 && isValid; k++) {
                    isValid = hasValidElements(sudoku[j % 3][k % 3], elements);
                }
            }
            elements.clear();
        }

        return isValid;
    }

    private static boolean hasValidElements(char c, Set<Character> elements) {
        if (c == '.') {
            return true;
        }
        if (!elements.contains(c)) {
            elements.add(c);
            return true;
        }
        return false;
    }
}

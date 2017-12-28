package mx.jresendiz.training.codefights.sudoku2;

import java.util.HashSet;
import java.util.Set;

public class Sudoku2 {
    static boolean sudoku2(char[][] sudoku) {
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
            // Checking columns
            for (int j = 0; j < sudokuSize && isValid; j++) {
                isValid = hasValidElements(sudoku[j][i], elements);
            }
            elements.clear();
        }
        // Checking squares
        for (int i = 0; i < sudokuSize && isValid; i += 3) {
            for (int j = 0; j < sudokuSize && isValid; j += 3) {
                // validate square
                for (int k = i; k < i + 3 && isValid; k++) {
                    for (int l = j; l < j + 3 && isValid; l++) {
                        isValid = hasValidElements(sudoku[k][l], elements);
                    }
                }
                elements.clear();
            }

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

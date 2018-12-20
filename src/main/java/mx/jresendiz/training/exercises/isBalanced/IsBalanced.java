package mx.jresendiz.training.exercises.isBalanced;

import java.util.Scanner;
import java.util.Stack;

public class IsBalanced {

    public static boolean isBalanced(String expression) {
        Stack<Character> characterStack = new Stack<>();
        for (char currentState : expression.toCharArray()) {
            if (currentState == '(') characterStack.push(')');
            else if (currentState == '[') characterStack.push(']');
            else if (currentState == '{') characterStack.push('}');
            else {
                if (characterStack.isEmpty() || currentState != characterStack.pop()) {
                    return false;
                }
            }
        }
        return characterStack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println((isBalanced(expression)) ? "YES" : "NO");
        }
    }
}


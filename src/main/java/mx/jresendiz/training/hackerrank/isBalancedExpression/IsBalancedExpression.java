package mx.jresendiz.training.hackerrank.isBalancedExpression;

import java.util.Stack;

public class IsBalancedExpression {
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
}

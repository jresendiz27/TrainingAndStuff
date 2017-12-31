package mx.jresendiz.training.hackerrank.isBalancedExpression

import org.spockframework.compiler.model.Spec
import spock.lang.Specification

class IsBalancedExpressionSpec extends Specification {
    void "Should validate if a given expression is balanced"() {
        expect:
        IsBalancedExpression.isBalanced(expression) == result
        where:
        expression     || result
        "{[()]}"       || true
        "{[(])}"       || false
        "{{[[(())]]}}" || true
    }
}

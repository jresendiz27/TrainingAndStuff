package mx.jresendiz.training.exercises.isBalanced

import spock.lang.Specification

class IsBalancedSpec extends Specification {
    void "Should verify is ordered problem"() {
        expect:
        IsBalanced.isBalanced(elements as String) == result
        where:
        elements       || result
        '{[()]}'       || true
        '{[(])}'       || false
        '{{[[(())]]}}' || true

    }
}

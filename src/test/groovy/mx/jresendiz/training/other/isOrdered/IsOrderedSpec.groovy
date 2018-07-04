package mx.jresendiz.training.other.isOrdered

import spock.lang.Specification

class IsOrderedSpec extends Specification {
    void "Should verify is ordered problem"() {
        expect:
        IsOrdered.solve(elements as int[]) == result
        where:
        elements                 || result
        [1, 2, 3, 4, 5]          || true
        [5, 1, 2, 3, 4]          || false
        [1, 1, 1, 1, 1]          || true
        [3, 2, 2, 4]             || false
        [1, 2, 4, 3, 5]          || false
        [1, 1, 1, 1, 1, 1, 0, 1] || false

    }
}
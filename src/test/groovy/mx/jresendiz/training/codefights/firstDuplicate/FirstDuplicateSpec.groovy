package mx.jresendiz.training.codefights.firstDuplicate

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by jresendiz on 22/11/17.
 */
class FirstDuplicateSpec extends Specification {
    @Shared
    FirstDuplicate firstDuplicate

    def setup() {
        firstDuplicate = new FirstDuplicate()
    }

    @Unroll
    void "Should verify the first duplicate"() {
        expect:
        result == firstDuplicate.firstDuplicate(numerList as List<Integer>)
        where:
        numerList                      | result
        [2, 3, 3, 1, 5, 2]             | 3
        [2, 4, 3, 5, 1]                | -1
        [2, 3, 3]                      | 3
        [8, 4, 6, 2, 6, 4, 7, 9, 5, 8] | 6

    }

    def cleanup() {

    }
}

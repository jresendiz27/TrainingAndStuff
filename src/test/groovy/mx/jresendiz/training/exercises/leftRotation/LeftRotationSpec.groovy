package mx.jresendiz.training.exercises.leftRotation

import spock.lang.Specification

class LeftRotationSpec extends Specification {
    void "Verify the left rotation problem"() {
        // No more examples were added because they just provide you with one.
        // Should work with all cases ... Screenshot is provided in the readme file
        expect:
        result == LeftRotation.rotate(numberOfRotation as int, elements as int[])
        where:
        numberOfRotation || elements        | result
        4                || [1, 2, 3, 4, 5] | "5 1 2 3 4"
    }
}

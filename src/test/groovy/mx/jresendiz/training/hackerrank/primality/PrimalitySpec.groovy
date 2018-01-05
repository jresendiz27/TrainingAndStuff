package mx.jresendiz.training.hackerrank.primality

import spock.lang.Specification

class PrimalitySpec extends Specification {
    void "Should verify if a number is prime, considering time complexity"() {
        expect:
        Primality.isPrime(number) == result
        where:
        number || result
        1      || false
        4      || false
        9      || false
        16     || false
        25     || false
        36     || false
        49     || false
        64     || false
        81     || false
        100    || false
        121    || false
        144    || false
        169    || false
        196    || false
        225    || false
        256    || false
        289    || false
        324    || false
        361    || false
        400    || false
        441    || false
        484    || false
        529    || false
        576    || false
        625    || false
        676    || false
        729    || false
        784    || false
        841    || false
        907    || true
    }
}

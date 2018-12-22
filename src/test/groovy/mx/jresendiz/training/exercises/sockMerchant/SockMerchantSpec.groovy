package mx.jresendiz.training.exercises.sockMerchant

import spock.lang.Specification

class SockMerchantSpec extends Specification {
    void "Should verify ransom note problem"() {
        expect:
        SockMerchantSolution.sockMerchant(numberOfSocks, socks as int[]) == pairs
        where:
        numberOfSocks || socks                                || pairs
        9             || [10, 20, 20, 10, 10, 30, 50, 10, 20] || 3
        10            || [1, 1, 3, 1, 2, 1, 3, 3, 3, 3]       || 4


    }
}

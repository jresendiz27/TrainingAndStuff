package mx.jresendiz.training.codefights.firstNonRepeatingCharacter

import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by jresendiz on 22/11/17.
 */
class FirstNonRepeatingCharacterTest extends Specification {


    @Shared
    FirstNonRepeatingCharacter firstNonRepeatingCharacter

    void setup() {
        firstNonRepeatingCharacter = new FirstNonRepeatingCharacter()
    }

    def "FirstNonRepeatingCharacter"() {
        expect:
        (result as char) == firstNonRepeatingCharacter.firstNonRepeatingCharacter(string as String)
        where:
        string                                                | result
        "abacabad"                                            | "c"
        "abacabaabacaba"                                      | "_"
        "bcb"                                                 | "c"
        "abcdefghijklmnopqrstuvwxyziflskecznslkjfabe"         | "d"
        "xdnxxlvupzuwgigeqjggosgljuhliybkjpibyatofcjbfxwtalc" | "d"
    }
}

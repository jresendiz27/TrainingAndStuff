package mx.jresendiz.training.hackerrank.ransomNote

import spock.lang.Specification

class RansomNoteSpec extends Specification {
    void "Should verify ransom note problem"() {
        given:
        RansomNote ransomNote = new RansomNote(magazine, note);
        expect:
        ransomNote.solve() == result
        where:
        magazine                        | note                    || result
        "give me one grand today night" | "give one grand today"  || true
        "two times three is not four"   | "two times two is four" || false
    }
}

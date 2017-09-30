package tddbc

import spock.lang.Specification
import spock.lang.Unroll

class SemanticVersionSpec extends Specification{
//    def "セマンティックバージョンクラスを作ることができる"() {
//        expect:
//        new SemanticVersion().class.toString() == "class tddbc.SemanticVersion"
//    }
//
//    def "major, minor, patchをコンストラクタにとる"() {
//        expect:
//        new SemanticVersion(1, 4, 2).class.toString() == "class tddbc.SemanticVersion"
//    }

    @Unroll
    def "major #major, minor #minor, patch #patchを . で連結して#expectedと返す" () {
        when:
        def sut = new SemanticVersion(major, minor, patch)

        then:
        sut.getVersion() == expected

        where:
        major | minor | patch || expected
        1 | 4 | 2 || "1.4.2"
        2 | 1 | 0 || "2.1.0"
    }

    @Unroll
    def "1.4.2と#major。#minor。#patchのバージョンオブジェクトと比較すると#expectedが返る" () {
        when:
        def sut = new SemanticVersion(1, 4, 2)
        def other = new SemanticVersion(major, minor, patch)

        then:
        sut.isSameVersion(other) == expected

        where:
        major|minor|patch||expected
        1 | 4 | 2 || true
        2 | 1 | 0 || false
    }

    @Unroll
    def "不正な値があると例外を送出する" () {
        when:
        def sut = new SemanticVersion(-1, -1, -1)

        then:
        Exception e = thrown()
        expected == e.class

        where:
        major|minor|patch||expected
        -1 | 0 | 0 || IllegalArgumentException
        0 | -1 | 0 || IllegalArgumentException
        0 | 0 | -1 || IllegalArgumentException
        -2 | 0 | -1 || IllegalArgumentException
        -2 | -1 | 0 || IllegalArgumentException
        0 | -2 | -1 || IllegalArgumentException
        -2 | -3 | 1 || IllegalArgumentException

    }

    def "パッチバージョンアップができる" () {
        when:
        def sut = new SemanticVersion(2, 3, 10)
        sut.releasePatch()

        then:
        sut.getVersion() == "2.3.11"
    }

    def "マイナーバージョンアップができる" () {
        when:
        def sut = new SemanticVersion(2, 3, 10)
        sut.releaseMinorUpdate()

        then:
        sut.getVersion() == "2.4.0"
    }

    def "メジャーバージョンアップができる" () {
        when:
        def sut = new SemanticVersion(2, 3, 10)
        sut.releaseMajorUpdate()

        then:
        sut.getVersion() == "3.0.0"
    }
}

package com.doublea.stack

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec


internal class MatchParensTest : StringSpec({
    val validStrings = listOf("{[(Hello)]}", "(Hell(o))", "()()()()()", "((((((([])))))))", "((((((())))))[])")
    val invalidStrings = listOf("(Hello", "(Hell(o)", "()()(()()()", "((((((())))))[)")

    "it returns true when parens match" {
        validStrings.forEach {
            hasMatchingParens(it) shouldBe true
        }
    }

    "it returns false when parens don't match" {
        invalidStrings.forEach {
            hasMatchingParens(it) shouldBe false
        }
    }
})
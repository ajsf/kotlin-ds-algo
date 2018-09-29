package com.doublea.stack

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class MinimumElementStackTest : StringSpec({
    "it returns minimum element" {
        val stack = MinimumElementStack<Int>()
        stack.push(10)
        stack.push(5)
        stack.getMin() shouldBe 5
        stack.pop()
        stack.getMin() shouldBe 10
    }

    "it works with multiple minimum elements are popped" {
        val stack = MinimumElementStack<Int>()
        stack.push(10)
        stack.push(5)
        stack.push(2)
        stack.push(20)
        stack.push(1)
        stack.push(30)
        stack.getMin() shouldBe 1
        stack.pop()
        stack.pop()
        stack.getMin() shouldBe 2
        stack.pop()
        stack.pop()
        stack.getMin() shouldBe 5
    }

    "it works when there are duplicate min values" {
        val stack = MinimumElementStack<Int>()
        stack.push(5)
        stack.push(10)
        stack.push(5)
        stack.push(5)
        stack.getMin() shouldBe 5
        stack.pop()
        stack.getMin() shouldBe 5
        stack.pop()
        stack.pop()
        stack.getMin() shouldBe 5
    }
})
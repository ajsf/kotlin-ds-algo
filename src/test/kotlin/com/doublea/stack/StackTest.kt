package com.doublea.stack

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class StackTest : StringSpec({

    "pop should throw a StackUnderflowException when the stack is empty" {
        val stack = Stack<Int>()
        shouldThrow<StackUnderflowException> {
            stack.pop()
        }
    }
    "peek should throw a StackUnderflowException when the stack is empty" {
        val stack = Stack<Int>()
        shouldThrow<StackUnderflowException> {
            stack.peek()
        }
    }

    "push should throw a StackOverflowException when the stack is full" {
        val stack = Stack<Int>()
        for (i in 0 until MAX_SIZE) {
            stack.push(i)
        }
        shouldThrow<StackOverflowException> {
            stack.push(1)
        }
    }

    "it should allow you to pop and peek one item" {
        val stack = Stack<Int>()
        stack.push(10)
        stack.peek() shouldBe 10
    }

    "pop should remove them item" {
        val stack = Stack<Int>()
        stack.push(10)
        val item = stack.pop()
        item shouldBe 10
        shouldThrow<StackUnderflowException> {
            stack.peek()
        }
    }

    "when multiple elements are added, the most recent element should be the top" {
        val stack = Stack<Int>()
        stack.push(1)
        stack.push(2)
        stack.push(3)
        stack.peek() shouldBe 3
    }

    "it should return the size of the stack" {
        val stack = Stack<Int>()
        stack.size() shouldBe 0
        stack.push(1)
        stack.size() shouldBe 1
        stack.push(2)
        stack.size() shouldBe 2
        stack.pop()
        stack.size() shouldBe 1
        stack.pop()
        stack.size() shouldBe 0
    }

    "isEmpty should return true when list is first created" {
        val stack = Stack<Int>()
        stack.isEmpty() shouldBe true
    }

    "isEmpty should return false when an item has been added" {
        val stack = Stack<Int>()
        stack.push(1)
        stack.isEmpty() shouldBe false
    }

    "isFull should return true when the stack size is the same as the max size" {
        val stack = Stack<Int>()
        for (i in 0 until MAX_SIZE) {
            stack.push(i)
        }
        stack.isFull() shouldBe true
    }

    "isFull should return false when the stack size is less than the max size" {
        val stack = Stack<Int>()
        for (i in 0 until MAX_SIZE - 1) {
            stack.push(i)
        }
        stack.isFull() shouldBe false
    }
})
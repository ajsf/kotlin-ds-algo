package com.doublea.stack

class MinimumElementStack<T : Comparable<T>> {
    private val stack = Stack<T>()
    private val minStack = Stack<T>()

    fun push(data: T) {
        stack.push(data)
        if (minStack.isEmpty().not()) {
            val min = minStack.peek()
            if (data <= min) minStack.push(data)
        } else {
            minStack.push(data)
        }
    }

    fun pop() {
        val p = stack.pop()
        if (minStack.isEmpty().not() && minStack.peek() == p) {
            minStack.pop()
        }
    }

    fun peek() = stack.peek()

    fun getMin(): T = minStack.peek()
}
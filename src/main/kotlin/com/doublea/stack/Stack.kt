package com.doublea.stack

const val MAX_SIZE = 40

class Stack<T> {

    private var top: Element<T>? = null
    private var size = 0

    fun push(data: T) {
        if (size == MAX_SIZE) throw StackOverflowException()
        val element = Element(data, top)
        top = element
        size++
    }

    fun pop(): T {
        top?.let {
            val data = it.data
            top = it.next
            size--
            return data
        }
        throw StackUnderflowException()
    }

    fun peek(): T {
        top?.let {
            return it.data
        }
        throw StackUnderflowException()
    }

    fun size() = size
    fun isEmpty(): Boolean = size == 0
    fun isFull(): Boolean = size == MAX_SIZE
}

class StackOverflowException : Exception()
class StackUnderflowException : Exception()
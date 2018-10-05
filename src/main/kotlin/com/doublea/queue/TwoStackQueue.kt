package com.doublea.queue

import com.doublea.stack.Stack

class TwoStackQueue<T> : Queue<T> {

    private val forwardStack = Stack<T>()
    private val reverseStack = Stack<T>()

    override fun enqueue(data: T) {
        if (isFull()) {
            throw QueueOverflowException()
        }
        if (forwardStack.isEmpty()) {

            while (!reverseStack.isEmpty()) {
                forwardStack.push(reverseStack.pop())

            }
        }
        forwardStack.push(data)
    }

    override fun dequeue(): T {
        if (isEmpty()) {
            throw EmptyQueueException()
        }
        if (reverseStack.isEmpty()) {

            while (!forwardStack.isEmpty()) {
                reverseStack.push(forwardStack.pop())
            }
        }
        return reverseStack.pop()
    }

    override fun peek(): T {
        while (!forwardStack.isEmpty()) {
            reverseStack.push(forwardStack.pop())
        }
        return reverseStack.peek()
    }

    override fun offer(data: T): Boolean {
        return try {
            enqueue(data)
            true
        } catch (e: QueueOverflowException) {
            false
        }
    }

    override fun isEmpty(): Boolean {
        return reverseStack.isEmpty() && forwardStack.isEmpty()
    }

    override fun isFull(): Boolean {
        return reverseStack.isFull() || forwardStack.isFull()
    }
}
package com.doublea.queue

const val MAX_SIZE = 40

interface Queue<T> {
    fun enqueue(data: T)
    fun dequeue(): T
    fun peek() : T
    fun offer(data: T): Boolean
    fun isEmpty() : Boolean
    fun isFull() : Boolean
}

class EmptyQueueException : Exception()
class QueueOverflowException : Exception()
package com.doublea.queue


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
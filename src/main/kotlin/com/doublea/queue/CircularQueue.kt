package com.doublea.queue

private const val EMPTY_VALUE = -1

class CircularQueue<T> : Queue<T> {

    private var head = EMPTY_VALUE
    private var tail = EMPTY_VALUE

    private val elements = arrayOfNulls<Any?>(MAX_SIZE)

    override fun enqueue(data: T) {
        if (isFull()) throw QueueOverflowException()

        tail = (tail + 1) % elements.size
        elements[tail] = data

        if (head == EMPTY_VALUE) {
            head = tail
        }
    }

    override fun dequeue(): T {
        if (isEmpty()) {
            throw EmptyQueueException()
        }
        val data = elements[head] as T
        head = if (head == tail) {
            EMPTY_VALUE
        } else {
            (head + 1) % elements.size
        }
        return data
    }

    override fun peek(): T {
        if (head == EMPTY_VALUE) throw EmptyQueueException()
        return elements[head] as T
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
        return head == -1
    }

    override fun isFull(): Boolean {
        // return (head - tail == 1 || head == 0 && tail == MAX_SIZE - 1)
        val nextIndex = (tail + 1) % elements.size
        return nextIndex == head
    }
}
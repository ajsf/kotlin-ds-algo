package com.doublea.queue

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

internal class TwoStackQueueTest : StringSpec({
    "peek should always return the oldest enqueued value" {
        val q = TwoStackQueue<Int>()
        q.enqueue(1)
        q.peek() shouldBe 1
        q.enqueue(2)
        q.peek() shouldBe 1
        q.enqueue(3)
        q.peek() shouldBe 1
        q.dequeue()
        q.peek() shouldBe 2
    }

    "dequeue should return values from oldest to newest" {
        val q = TwoStackQueue<Int>()
        for (i in 0..10) {
            q.enqueue(i)
        }
        for (i in 0..10) {
            q.dequeue() shouldBe i
        }
        q.isEmpty() shouldBe true
    }

    "dequeue on an empty queue throws an EmptyQueueException" {
        val q = TwoStackQueue<Int>()
        shouldThrow<EmptyQueueException> {
            q.dequeue()
        }
    }
    "enqueue on a full queue throws an QueueOverflowException" {
        val q = TwoStackQueue<Int>()
        for (i in 0 until MAX_SIZE) {
            q.enqueue(i)
        }
        shouldThrow<QueueOverflowException> {
            q.enqueue(1)
        }
    }

    "offer on a queue that is not full should return true and add the value to the queue" {
        val q = TwoStackQueue<Int>()
        q.offer(1) shouldBe true
        q.peek() shouldBe 1
    }

    "offer on a full queue should return false" {
        val q = TwoStackQueue<Int>()
        for (i in 0 until MAX_SIZE) {
            q.enqueue(i)
        }
        q.offer(1) shouldBe false
    }

    "it wraps properly" {
        val q = TwoStackQueue<Int>()
        for (i in 0 until MAX_SIZE) {
            q.enqueue(i)
        }
        q.isFull() shouldBe true
        for (i in 0 until MAX_SIZE - 1) {
            q.dequeue()
        }
        q.peek() shouldBe MAX_SIZE - 1
        for (i in 0 until MAX_SIZE - 1) {
            q.enqueue(i)
        }
        q.isFull() shouldBe true
        q.dequeue() shouldBe MAX_SIZE - 1
        for (i in 0 until MAX_SIZE - 1) {
            q.dequeue() shouldBe i
        }
        q.isEmpty() shouldBe true

    }
})
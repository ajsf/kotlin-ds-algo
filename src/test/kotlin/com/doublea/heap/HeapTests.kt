package com.doublea.heap

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.util.stream.Stream

val unsortedList = listOf(3, 87, 345, 2, -1, 64, 32, 78, 3)
val ascSortedList = unsortedList.sorted()
val dscSortedList = unsortedList.sortedDescending()

internal class MaxHeapTest : StringSpec({
    "remove returns items in order from highest to lowest" {
        val heap = MaxHeap<Int>()
        unsortedList.forEach {
            heap.insert(it)
        }
        dscSortedList.forEach {
            heap.remove() shouldBe it
        }
    }

    "getHighestPriority returns the largest value" {
        val heap = MaxHeap<Int>()
        unsortedList.forEach {
            heap.insert(it)
        }
        heap.getHighestPriority() shouldBe 345
    }

    "getHighestPriority returns null for an empty heap" {
        val heap = MaxHeap<Int>()
        heap.getHighestPriority() shouldBe null
    }

    "findMinElement returns correct min value" {
        val heap = MaxHeap<Int>()
        unsortedList.forEach { heap.insert(it) }
        heap.findMinElement() shouldBe -1
    }

    "findMinElement returns null for an empty heap" {
        val heap = MaxHeap<Int>()
        heap.findMinElement() shouldBe null
    }

    "findMinElement returns correct value for heap with one item" {
        val heap = MaxHeap<Int>()
        heap.insert(1)
        heap.findMinElement() shouldBe 1
    }
})

internal class MinHeapTest : StringSpec({
    "remove returns items in order from lowest to highest" {
        val heap = MinHeap<Int>()
        unsortedList.forEach {
            heap.insert(it)
        }
        ascSortedList.forEach {
            heap.remove() shouldBe it
        }
    }

    "getHighestPriority returns the smallest value" {
        val heap = MinHeap<Int>()
        unsortedList.forEach {
            heap.insert(it)
        }
        heap.getHighestPriority() shouldBe -1
    }

    "getHighestPriority returns null for an empty heap" {
        val heap = MinHeap<Int>()
        heap.getHighestPriority() shouldBe null
    }

    "findMaxElement returns correct max value" {
        val heap = MinHeap<Int>()
        unsortedList.forEach { heap.insert(it) }
        heap.findMaxElement() shouldBe 345
    }

    "findMaxElement returns null for an empty heap" {
        val heap = MinHeap<Int>()
        heap.findMaxElement() shouldBe null
    }

    "findMaxElement returns correct value for heap with one item" {
        val heap = MinHeap<Int>()
        heap.insert(1)
        heap.findMaxElement() shouldBe 1
    }

    "getKLargestElements returns a string with largest elements from a stream" {
        val stream = unsortedList.stream()
        getKLargestElements(5, stream) shouldBe "32 64 78 87 345"
    }

    "getKLargestElements works correctly when k is larger than the stream" {
        val stream = unsortedList.stream()
        getKLargestElements(134, stream) shouldBe ascSortedList.joinToString(" ")
    }

    "getKLargestElements returns an empty string for an empty stream" {
        val stream = Stream.empty<Int>()
        getKLargestElements(5, stream) shouldBe ""
    }
})
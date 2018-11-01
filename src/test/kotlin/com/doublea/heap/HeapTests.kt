package com.doublea.heap

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

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
})
package com.doublea.sorting

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

private val sortedArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
private val unsortedArray = intArrayOf(1, 5, 6, 4, 2, 7, 10, 3, 8, 9)

internal class SelectionSortTest : StringSpec({
    "it sorts in place" {
        val list = unsortedArray.copyOf().toMutableList()
        list.selectionSort()
        list.forEachIndexed { index, int ->
            int shouldBe sortedArray[index]
        }
    }
})
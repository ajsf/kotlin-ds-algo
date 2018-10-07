package com.doublea.sorting

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

private val sortedArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
private val unsortedArray = intArrayOf(1, 5, 6, 4, 2, 7, 10, 3, 8, 9)

internal class MergeSortTest : StringSpec({
    "it sorts out of place" {
        val list = unsortedArray.copyOf().toMutableList()
        val newList = list.mergeSort()
        list.print()
        newList.print()
        newList.forEachIndexed { index, int ->
            int shouldBe sortedArray[index]
        }
        list.forEachIndexed { index, int ->
            int shouldBe unsortedArray[index]
        }

    }
    "it sorts more complicated lists" {
        val list = (intArrayOf(2, 6, 8, 124, 6, 0, 4, 89) + unsortedArray.copyOf()).toMutableList()
        val expected = mutableListOf(0, 1, 2, 2, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8, 9, 10, 89, 124)
        val newList = list.mergeSort()
        list.print()
        newList.print()
        newList.forEachIndexed { index, int ->
            int shouldBe expected[index]
        }
    }
})
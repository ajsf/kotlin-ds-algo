package com.doublea.search

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

private val sortedArray = intArrayOf(1, 2, 3, 4, 6, 7, 8, 9, 10)

internal class BinarySearchRecursiveTest : StringSpec({
    val list = sortedArray.toMutableList()

    "it returns the index for every item in the list" {
        sortedArray.forEachIndexed { index, i ->
            list.myBinarySearchRecursive(i) shouldBe index
        }
    }

    "it returns -1 for items not in the list" {
        list.myBinarySearchRecursive(5) shouldBe -1
        list.myBinarySearchRecursive(11) shouldBe -1
    }
})

internal class BinarySearchImperativeTest : StringSpec({
    val list = sortedArray.toMutableList()

    "it returns the index for every item in the list" {
        sortedArray.forEachIndexed { index, i ->
            list.myBinarySearchImperative(i) shouldBe index
        }
    }

    "it returns -1 for items not in the list" {
        list.myBinarySearchImperative(5) shouldBe -1
        list.myBinarySearchImperative(11) shouldBe -1
    }
})

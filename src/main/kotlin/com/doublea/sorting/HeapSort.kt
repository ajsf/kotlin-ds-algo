package com.doublea.sorting

fun <T : Comparable<T>> MutableList<T>.heapsort() {
    heapify(lastIndex)
    var endIndex = lastIndex
    while (endIndex > 0) {
        swap(0, endIndex)
        endIndex--
        percolateDown(0, endIndex)
    }
}

private fun leftChildIndex(i: Int, endIndex: Int): Int {
    val childIndex = 2 * i + 1
    return if (childIndex <= endIndex) childIndex else -1
}

private fun rightChildIndex(i: Int, endIndex: Int): Int {
    val childIndex = 2 * i + 2
    return if (childIndex <= endIndex) childIndex else -1
}

private fun parentIndex(i: Int, endIndex: Int) = if (i < 0 || i > endIndex) -1 else (i - 1) / 2


private fun <T : Comparable<T>> MutableList<T>.percolateDown(index: Int, endIndex: Int) {
    val leftChildIndex = leftChildIndex(index, endIndex)
    val rightChildIndex = rightChildIndex(index, endIndex)

    if (leftChildIndex != -1 && get(leftChildIndex) > get(index)) {
        swap(leftChildIndex, index)
        percolateDown(leftChildIndex, endIndex)
    }
    if (rightChildIndex != -1 && get(rightChildIndex) > get(index)) {
        swap(rightChildIndex, index)
        percolateDown(rightChildIndex, endIndex)
    }
}

private fun <T : Comparable<T>> MutableList<T>.heapify(endIndex: Int) {
    var index = parentIndex(endIndex, endIndex)
    while (index >= 0) {
        percolateDown(index, endIndex)
        index--
    }
}
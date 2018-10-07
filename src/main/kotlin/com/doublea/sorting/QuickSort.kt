package com.doublea.sorting

fun <T : Comparable<T>> MutableList<T>.quickSort() {
    if (size < 2) return
    val pivot = partition(0)
    subList(0, pivot).quickSort()
    subList(pivot + 1, size).quickSort()
}

private fun <T : Comparable<T>> MutableList<T>.partition(pivotIndex: Int): Int {
    val pivot = this[pivotIndex]
    var low = pivotIndex
    var high = lastIndex
    while (low < high) {
        while (this[low] <= pivot && low < high) {
            low++
        }
        while (this[high] > pivot) {
            high--
        }
        if (low < high) {
            swap(low, high)
        }
    }
    swap(pivotIndex, high)
    return high
}
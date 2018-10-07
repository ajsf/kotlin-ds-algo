package com.doublea.sorting

fun <T : Comparable<T>> MutableList<T>.insertionSort() {
    for (i in 1 until this.size) {
        for (j in (1..i).reversed()) {
            if (this[j - 1] < this[j]) break
            swap(j - 1, j)
        }
    }
}

fun <T : Comparable<T>> MutableList<T>.insertionSort(startIndex: Int, increment: Int) {
    for (i in startIndex until this.size step increment) {
        val next = minOf(i + increment, this.lastIndex)
        for (j in (increment..next).step(increment).reversed()) {
            if (this[j - increment] < this[j]) break
            swap(j - increment, j)
        }
    }
}


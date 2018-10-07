package com.doublea.sorting

fun <T : Comparable<T>> MutableList<T>.bubbleSort() {
    for (i in 0 until this.size) {
        var swapped = false
        for (j in (1 + i until this.size).reversed()) {
            if (this[j - 1] > this[j]) {
                this.swap(j - 1, j)
                swapped = true
            }
        }
        if (swapped.not()) return
    }
    this.print()
}
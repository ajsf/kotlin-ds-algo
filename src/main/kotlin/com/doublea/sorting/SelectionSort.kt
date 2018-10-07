package com.doublea.sorting

fun <T : Comparable<T>> MutableList<T>.selectionSort() {
    for (i in 0 until this.size) {
        for (j in i + 1 until this.size) {
            if (this[j] < this[i]) {
                this.swap(i, j)
                this.print()
            }
        }
    }
}

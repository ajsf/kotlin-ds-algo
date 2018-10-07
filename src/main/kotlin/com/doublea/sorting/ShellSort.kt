package com.doublea.sorting

fun <T : Comparable<T>> MutableList<T>.shellSort() {
    var increment = this.size / 2
    while (increment >= 1) {
        for (startIndex in 0 until increment) {
            insertionSort(startIndex, increment)
        }
        increment /= 2
    }
}

package com.doublea.sorting

internal fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

internal fun <T> List<T>.print() {
    this.forEach {
        print("$it-")
    }
    println()
}
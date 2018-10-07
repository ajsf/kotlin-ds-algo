package com.doublea.search

fun <T : Comparable<T>> MutableList<T>.myBinarySearchRecursive(target: T): Int {
    if (size == 0) return -1
    if (size == 1) {
        return if (first() == target) 0 else -1
    }
    val mid = size / 2 + size % 2
    val item = this[mid]
    if (item == target) return mid
    if (item > target) return subList(0, mid).myBinarySearchRecursive(target)
    val search = subList(mid + 1, size).myBinarySearchRecursive(target)
    return if (search >= 0) mid + search + 1 else -1
}

fun <T : Comparable<T>> MutableList<T>.myBinarySearchImperative(target: T): Int {
    var min = 0
    var max = lastIndex
    while (min <= max) {
        val mid = min + (max - min) / 2
        val item = this[mid]
        if (item == target) return mid
        if (item > target) {
            max = mid - 1
        } else {
            min = mid + 1
        }
    }
    return -1
}
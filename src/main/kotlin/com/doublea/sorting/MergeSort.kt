package com.doublea.sorting

fun <T : Comparable<T>> MutableList<T>.mergeSort(): MutableList<T> {
    if (size < 2) return this
    val (firstHalf, secondHalf) = split()
    val firstSorted = firstHalf.mergeSort()
    val secondSorted = secondHalf.mergeSort()
    return firstSorted.merge(secondSorted)
}

private fun <T : Comparable<T>> MutableList<T>.split(): Pair<MutableList<T>, MutableList<T>> {
    val half = size / 2 + size % 2
    val firstHalf = subList(0, half)
    val secondHalf = subList(half, size)
    return firstHalf to secondHalf
}

private fun <T : Comparable<T>> MutableList<T>.merge(listToMerge: MutableList<T>): MutableList<T> {
    var firstIndex = 0
    var secondIndex = 0
    val result = mutableListOf<T>()
    while (firstIndex < size && secondIndex < listToMerge.size) {
        if (this[firstIndex] < listToMerge[secondIndex]) {
            result.add(this[firstIndex])
            firstIndex++
        } else {
            result.add(listToMerge[secondIndex])
            secondIndex++
        }
    }
    if (firstIndex < size) result += subList(firstIndex, size)
    if (secondIndex < listToMerge.size) result += listToMerge.subList(secondIndex, listToMerge.size)
    return result
}
package com.doublea.heap

class MaxHeap<T : Comparable<T>> : BinaryHeap<T>() {
    override fun compareFunction(first: T, second: T): Boolean {
        return first > second
    }

    fun findMinElement(): T? {
        if (list.isEmpty()) return null
        if (list.size == 1) return list.first()
        val firstLeaf = parentIndex(list.lastIndex) + 1
        var min = list[firstLeaf]
        for (i in (firstLeaf + 1)..list.lastIndex) {
            if (list[i] < min) min = list[i]
        }
        return min
    }
}